package org.mushikago.sdk.services.mitsubachi.model.http.fetch;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import net.sf.json.JSONArray;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.mushikago.sdk.common.RequestException;
import org.mushikago.sdk.common.auth.AuthException;
import org.mushikago.sdk.common.auth.MushikagoAuth;
import org.mushikago.sdk.common.util.ConnectInfo;
import org.mushikago.sdk.common.util.ParamUtils;
import org.mushikago.sdk.services.mitsubachi.model.http.HttpMethod;
import org.mushikago.sdk.services.mitsubachi.model.http.HttpRequest;

/**
 * HttpFetchのリクエスト。<br>
 * 
 * @author miningbrownie
 */
public class HttpFetchRequest extends HttpRequest {

  /**
   * リクエスト先のパス。<br>
   */
  public static final String URI_PATH = "/1/mitsubachi/http/fetch";

  /**
   * リクエストにmethodパラメータを指定するキー。<br>
   */
  public static final String PARAM_KEY_METHOD = "method";

  /**
   * リクエストにfollow_redirectパラメータを指定するキー。<br>
   */
  public static final String PARAM_KEY_FOLLOW_REDIRECT = "follow_redirect";

  /**
   * リクエスト(method)。<br>
   */
  private HttpMethod method = HttpMethod.GET;

  /**
   * リクエスト(follow_redirect)。<br>
   */
  private boolean followRedirect = true;

  /**
   * HttpFetchRequestを構築します。<br>
   */
  public HttpFetchRequest() {
    super();
  }

  /**
   * 指定されたURL、プロジェクト名、スクリプト名を使用して、HttpFetchRequestを構築します。<br>
   * 
   * @param url
   *          URL
   * @param projectName
   *          プロジェクト名
   * @param scriptName
   *          スクリプト名
   */
  public HttpFetchRequest(String url, String projectName, String scriptName) {

    super(url, projectName, scriptName);
  }

  /**
   * methodを設定。<br>
   * 
   * @param method
   *          HTTPメソッド
   */
  public void setMethod(HttpMethod method) {
    this.method = method;
  }

  /**
   * methodを取得。<br>
   * 
   * @return HTTPメソッド
   */
  public HttpMethod getMethod() {
    return this.method;
  }

  /**
   * follow_redirectを取得。<br>
   * 
   * @return follow_redirect
   */
  public boolean isFollowRedirect() {
    return this.followRedirect;
  }

  /**
   * follow_redirectを設定。<br>
   * trueが設定されるとリダイレクトを許可。<br>
   * 
   * @param follow
   *          follow_rediret
   */
  public void setFollowRedirect(boolean follow) {
    this.followRedirect = follow;
  }

  /**
   * org.apache.http.client.methods.HttpRequestBaseの作成。<br>
   * HttpRequestBaseの作成に失敗した場合、又は以下の必須パラメータを指定していない場合、HttpFetchExceptionが送出されます。<br>
   * ・url<br>
   * ・projectName<br>
   * ・scriptName<br>
   * 
   * @throws MushikagoException
   *           必須パラメータを指定していない場合
   */
  @Override
  public HttpRequestBase toHttpMethod(MushikagoAuth auth, ConnectInfo ci) throws RequestException {
    List<NameValuePair> rawParams = new ArrayList<NameValuePair>();

    rawParams.add(createNameValuePair(PARAM_KEY_URL, this.url, true));
    rawParams.add(createNameValuePair(PARAM_KEY_PROJECT_NAME, this.projectName, true));
    rawParams.add(createNameValuePair(PARAM_KEY_SCRIPT_NAME, this.scriptName, true));

    String followRedirectFlag = this.followRedirect ? "1" : "0";
    rawParams.add(createNameValuePair(PARAM_KEY_FOLLOW_REDIRECT, followRedirectFlag));
    rawParams.add(createNameValuePair(PARAM_KEY_METHOD, this.method.name()));
    rawParams.add(createNameValuePair(PARAM_KEY_ENTITY_PARAMETER, this.toEntityParameterString()));
    rawParams.add(createNameValuePair(PARAM_KEY_PARAMETERS, this.toParameterString()));
    rawParams.add(createNameValuePair(PARAM_KEY_HEADER_OVERWRITE, this.toHeaderOverWriteString()));

    if (this.parentRequestId != null) {
      rawParams.add(createNameValuePair(PARAM_KEY_PARENT_REQUEST_ID, this.parentRequestId));
    }
    if (this.groupId != null) {
      rawParams.add(createNameValuePair(PARAM_KEY_GROUP_ID, this.groupId));
    }
    if (this.uniqueKey != null) {
      rawParams.add(createNameValuePair(PARAM_KEY_UNIQUE_KEY, this.uniqueKey));
    }
    if (this.uniqueKeyExpires != null) {
      String uniqueKeyExpires = this.uniqueKeyExpires.toString();
      rawParams.add(createNameValuePair(PARAM_KEY_UNIQUE_KEY_EXPIRES, uniqueKeyExpires));
    }

    JSONArray cookiejarJson = JSONArray.fromObject(this.cookiejar);
    rawParams.add(createNameValuePair(PARAM_KEY_COOKIEJAR, cookiejarJson.toString()));

    try {
      TreeMap<String, String> requestParams = new TreeMap<String, String>();
      for (NameValuePair rawParam : rawParams) {
        requestParams.put(rawParam.getName(), ParamUtils.paramEncode(rawParam.getValue()));
      }
      HttpRequestBase httpRequest = new HttpPost();
      String uri = makeRequestUrl(auth, ci, httpRequest.getMethod(), URI_PATH, requestParams);
      httpRequest.setURI(new URI(uri));
      HttpEntity entity = new UrlEncodedFormEntity(rawParams, "UTF-8");
      ((HttpEntityEnclosingRequestBase) httpRequest).setEntity(entity);
      return httpRequest;
    }
    catch (AuthException e) {
      throw new RequestException(e.getMessage());
    }
    catch (URISyntaxException e) {
      throw new RequestException(e.getMessage());
    }
    catch (UnsupportedEncodingException e) {
      throw new RequestException(e.getMessage());
    }
  }
}
