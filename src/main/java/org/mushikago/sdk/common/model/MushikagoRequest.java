package org.mushikago.sdk.common.model;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.mushikago.sdk.common.RequestException;
import org.mushikago.sdk.common.auth.AuthException;
import org.mushikago.sdk.common.auth.MushikagoAuth;
import org.mushikago.sdk.common.util.ConnectInfo;
import org.mushikago.sdk.common.util.ParamUtils;

/**
 * mushikagoサービスに対して送信されるリクエストの基底クラス。<br>
 * @author miningbrownie
 *
 */
public abstract class MushikagoRequest {
  
  /**
   * 各リクエストが、自身のパラメータを使ってorg.apache.http.client.methods.HttpRequestBaseを作成する実装をします。<br>
   * @param auth 認証用オブジェクト
   * @param ci 通信先情報
   * @return org.apache.http.client.methods.HttpRequestBase
   * @throws MushikagoException 作成に失敗した場合
   */
  public abstract HttpRequestBase toHttpMethod(MushikagoAuth auth, ConnectInfo ci) throws RequestException;
  
  /**
   * リクエスト先のURLを作成します。<br>
   * @param auth 認証用オブジェクト
   * @param ci 通信先情報
   * @param httpMethod HTTPメソッド
   * @param requestPath 通信先のパス
   * @return リクエスト先のURL
   * @throws AuthException 認証操作に失敗した場合
   * @throws UnsupportedEncodingException URLのエンコードに失敗した場合
   */
  protected String makeRequestUrl(MushikagoAuth auth, ConnectInfo ci, String httpMethod, String requestPath) throws AuthException, UnsupportedEncodingException {
    
    TreeMap<String, String> _requestParams = new TreeMap<String, String>();
    
    String apiKey = ParamUtils.paramEncode(auth.getApiKey());
    String timestamp = ParamUtils.paramEncode(auth.makeTimeStamp());
    
    _requestParams.put("api_key",   apiKey);
    _requestParams.put("timestamp", timestamp);
    
    String signature = auth.toSignature(httpMethod, ci.getEndpoint(), requestPath, _requestParams);
    
    String base = String.format("%s://%s", ci.getSchema(), ci.getEndpoint());
    if(null != ci.getPort()) {
      base = String.format("%s:%d", base, ci.getPort());
    }
    
    return String.format("%s%s?api_key=%s&timestamp=%s&signature=%s", base, requestPath, apiKey, timestamp, signature);
  }
  
  /**
   * リクエスト先のURLを作成します。<br>
   * @param auth 認証用オブジェクト
   * @param ci 通信先情報
   * @param httpMethod HTTPメソッド
   * @param requestPath 通信先のパス
   * @param requestParams パラメータ
   * @return リクエスト先のURL
   * @throws AuthException 認証操作に失敗した場合
   * @throws UnsupportedEncodingException URLのエンコードに失敗した場合
   */
  protected String makeRequestUrl(MushikagoAuth auth, ConnectInfo ci, String httpMethod, String requestPath, TreeMap<String, String> requestParams) throws AuthException, UnsupportedEncodingException {
    
    TreeMap<String, String> _requestParams = new TreeMap<String, String>();
    _requestParams.putAll(requestParams);
    
    String apiKey = ParamUtils.paramEncode(auth.getApiKey());
    String timestamp = ParamUtils.paramEncode(auth.makeTimeStamp());
    
    _requestParams.put("api_key",   apiKey);
    _requestParams.put("timestamp", timestamp);
    
    String signature = auth.toSignature(httpMethod, ci.getEndpoint(), requestPath, _requestParams);
    
    String base = String.format("%s://%s", ci.getSchema(), ci.getEndpoint());
    if(null != ci.getPort()) {
      base = String.format("%s:%d", base, ci.getPort());
    }
    
    return String.format("%s%s?api_key=%s&timestamp=%s&signature=%s", base, requestPath, apiKey, timestamp, signature);
  }
  
  /**
   * GETメソッドを作成します。<br>
   * @param auth 認証用オブジェクト
   * @param ci 通信先情報
   * @param targetPath 通信先のパス
   * @param requestParams パラメータ
   * @return GETメソッド
   * @throws MushikagoException 作成に失敗した場合
   */
  protected HttpGet toHttpGetMethod(MushikagoAuth auth, ConnectInfo ci, String targetPath, TreeMap<String, String> requestParams) throws RequestException {
    String url = this.toHttpUrl(auth, ci, "GET", targetPath, requestParams);
    return new HttpGet(url);
  }
  
  /**
   * DELETEメソッドを作成します。<br>
   * @param auth 認証用オブジェクト
   * @param ci 通信先情報
   * @param targetPath 通信先のパス
   * @param requestParams パラメータ
   * @return GETメソッド
   * @throws MushikagoException 作成に失敗した場合
   */
  protected HttpDelete toHttpDeleteMethod(MushikagoAuth auth, ConnectInfo ci, String targetPath, TreeMap<String, String> requestParams) throws RequestException {
    String url = this.toHttpUrl(auth, ci, "DELETE", targetPath, requestParams);
    return new HttpDelete(url);
  }
  
  /**
   * POSTメソッドを作成します。<br>
   * @param auth 認証用オブジェクト
   * @param ci 通信先情報
   * @param targetPath 通信先のパス
   * @param requestParams パラメータ
   * @return GETメソッド
   * @throws MushikagoException 作成に失敗した場合
   */
  protected HttpPost toHttpPostMethod(MushikagoAuth auth, ConnectInfo ci, String targetPath, TreeMap<String, String> requestParams) throws RequestException {
    String url = this.toHttpUrl(auth, ci, "POST", targetPath, requestParams);
    return new HttpPost(url);
  }
  
  /**
   * PUTメソッドを作成します。<br>
   * @param auth 認証用オブジェクト
   * @param ci 通信先情報
   * @param targetPath 通信先のパス
   * @param requestParams パラメータ
   * @return GETメソッド
   * @throws MushikagoException 作成に失敗した場合
   */
  protected HttpPut toHttpPutMethod(MushikagoAuth auth, ConnectInfo ci, String targetPath, TreeMap<String, String> requestParams) throws RequestException {
    
    try {
      
      final String url = this.makeRequestUrl(auth, ci, "PUT", targetPath, requestParams);
      final HttpPut put = new HttpPut(url);
      
      final ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
      for(Entry<String, String> requestParam : requestParams.entrySet()) {
        params.add(new BasicNameValuePair(requestParam.getKey(), ParamUtils.paramDecode(requestParam.getValue())));
      }
      
      put.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
      return put;
    }
    catch(UnsupportedEncodingException e) { throw new RequestException(e.getMessage()); }
    catch(AuthException e) { throw new RequestException(e.getMessage()); }
  }
  
//  /**
//   * PUTメソッドを作成します。<br>
//   * @param auth 認証用オブジェクト
//   * @param ci 通信先情報
//   * @param targetPath 通信先のパス
//   * @param requestParams パラメータ
//   * @return GETメソッド
//   * @throws MushikagoException 作成に失敗した場合
//   */
//  protected HttpPut toHttpPutMethod(MushikagoAuth auth, ConnectInfo ci, String targetPath, TreeMap<String, String> requestParams) throws RequestException {
//    
//    String url = this.toHttpUrl(auth, ci, "PUT", targetPath, requestParams);
//    return new HttpPut(url);
//  }
  
  /**
   * パラメータ付きのリクエスト先のURLを作成します。<br>
   * @param auth 認証用オブジェクト
   * @param ci 通信先情報
   * @param httpMethod HTTPメソッド
   * @param targetPath 通信先のパス
   * @param requestParams パラメータ
   * @return パラメータ付きのリクエスト先のURL
   * @throws MushikagoException 作成に失敗した場合
   */
  private String toHttpUrl(MushikagoAuth auth, ConnectInfo ci, String httpMethod, String targetPath, TreeMap<String, String> requestParams) throws RequestException {
    
    try {
      String url = this.makeRequestUrl(auth, ci, httpMethod, targetPath, requestParams);
      String getParamString = ParamUtils.mapToString(requestParams);
      if("" != getParamString) { url = String.format("%s&%s", url, getParamString); }
      return url;
    }
    catch(UnsupportedEncodingException e) { throw new RequestException(e.getMessage()); }
    catch(AuthException e) { throw new RequestException(e.getMessage()); }
  }
  
  /**
   * リクエスト先URIのパスを生成します。<br>
   * @param service サービス名
   * @param category APIカテゴリ
   * @param name オペレーション名
   * @return URIのパス
   */
  protected String path(String service, String category, String name) {
    String version = "1";
    return String.format("/%s/%s/%s/%s", version, service, category, name);
  }
}
