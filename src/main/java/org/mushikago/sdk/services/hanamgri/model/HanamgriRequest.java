package org.mushikago.sdk.services.hanamgri.model;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.mushikago.sdk.common.RequestException;
import org.mushikago.sdk.common.annotation.RequestMetaData;
import org.mushikago.sdk.common.annotation.RequestParameter;
import org.mushikago.sdk.common.auth.AuthException;
import org.mushikago.sdk.common.auth.MushikagoAuth;
import org.mushikago.sdk.common.model.MushikagoRequest;
import org.mushikago.sdk.common.util.ConnectInfo;
import org.mushikago.sdk.common.util.ParamUtils;

/**
 * Hanamgriのリクエストを表す抽象クラスです
 * 
 * @author ope
 */
public abstract class HanamgriRequest extends MushikagoRequest {
  private static Log logger = LogFactory.getLog(HanamgriRequest.class);

  @Override
  public HttpRequestBase toHttpMethod(MushikagoAuth auth, ConnectInfo ci) throws RequestException {
    // リクエストオブジェクトの実装クラス情報を取得する
    Class<? extends HanamgriRequest> concreteRequestClass = getClass();
    logger.debug(concreteRequestClass);

    // リクエストオブジェクトのメタデータを取得する
    RequestMetaData requestMetaData = concreteRequestClass.getAnnotation(RequestMetaData.class);
    String path = requestMetaData.path();

    TreeMap<String, String> urlEncodedParams = new TreeMap<String, String>();
    List<NameValuePair> rawParams = new ArrayList<NameValuePair>();
    MultipartEntity entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);

    try {
      // リクエストオブジェクトのメンバ変数を取得し、リクエストパラメータに変換する
      Field fields[] = concreteRequestClass.getDeclaredFields();
      for (Field field : fields) {
        RequestParameter requestParameter = field.getAnnotation(RequestParameter.class);
        if (requestParameter == null) {
          continue;
        }

        field.setAccessible(true);
        Object value = field.get(this);
        logger.debug(field.getName() + " : " + field.get(this));

        if (value == null) {
          if (requestParameter.isRequired()) {
            throw new RequestException(field.getName() + " can not be null");
          }
          continue;
        }

        if (requestParameter.isPartOfURI()) {
          path = path.replace(":" + requestParameter.name(), ParamUtils.paramEncode(String.valueOf(value)));
        }
        else if (requestParameter.isMultipart()) {
          if (value instanceof File) {
            entity.addPart(requestParameter.name(), new FileBody((File) value));
          }
        }
        else {
          urlEncodedParams.put(requestParameter.name(), ParamUtils.paramEncode(String.valueOf(value)));
          rawParams.add(new BasicNameValuePair(requestParameter.name(), String.valueOf(value)));
        }
      }

      // httpRequestのインスタンスを作成する
      HttpRequestBase httpRequest = requestMetaData.httpMethodClass().newInstance();

      if (httpRequest instanceof HttpEntityEnclosingRequestBase) {
        String uri;
        try {
          uri = makeRequestUrl(auth, ci, httpRequest.getMethod(), path, urlEncodedParams);
        }
        catch (AuthException e) {
          throw new RequestException(e.getMessage());
        }
        httpRequest.setURI(new URI(uri));
        logger.debug(uri.toString());

        for (NameValuePair rawParam : rawParams) {
          entity.addPart(rawParam.getName(), new StringBody(rawParam.getValue(), Charset.forName(HTTP.UTF_8)));
        }
        ((HttpEntityEnclosingRequestBase) httpRequest).setEntity(entity);
      }
      else {
        URI uri = buildRequestUri(auth, ci, httpRequest.getMethod(), path, urlEncodedParams);
        httpRequest.setURI(uri);
        logger.debug(uri.toString());

      }

      return httpRequest;
    }
    catch (InstantiationException e) {
      throw new RequestException(e);
    }
    catch (IllegalAccessException e) {
      throw new RequestException(e);
    }
    catch (URISyntaxException e) {
      throw new RequestException(e);
    }
    catch (IllegalArgumentException e) {
      throw new RequestException(e);
    }
    catch (UnsupportedEncodingException e) {
      throw new RequestException(e);
    }
  }

}
