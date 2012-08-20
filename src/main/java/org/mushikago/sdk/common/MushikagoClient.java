package org.mushikago.sdk.common;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.mushikago.sdk.common.auth.Credentials;
import org.mushikago.sdk.common.auth.UnauthorizedException;
import org.mushikago.sdk.common.broker.BrokerException;
import org.mushikago.sdk.common.broker.HttpRequestBroker;
import org.mushikago.sdk.common.exception.APICallException;
import org.mushikago.sdk.common.exception.BadRequestException;
import org.mushikago.sdk.common.exception.ConflictException;
import org.mushikago.sdk.common.exception.ForbiddenException;
import org.mushikago.sdk.common.exception.InternalErrorException;
import org.mushikago.sdk.common.exception.InternalServerErrorException;
import org.mushikago.sdk.common.exception.NotFoundException;
import org.mushikago.sdk.common.exception.RequestEntityTooLargeException;
import org.mushikago.sdk.common.exception.RequestURITooLongException;
import org.mushikago.sdk.common.exception.ServiceUnavailableException;
import org.mushikago.sdk.common.util.ConnectInfo;
import org.mushikago.sdk.services.hanamgri.model.HanamgriRequest;

/**
 * 各mushikagoサービスと通信を行うクライアントの基底クラス。<br>
 * <br>
 * 通信先(URL、ポート)を保持します。
 * 通信の際に必要となる認証オブジェクトを保持します。
 * 
 * @author miningbrownie
 */
public abstract class MushikagoClient {
  private static final String DEFAULT_ENDPOINT = "api.mushikago.org";

  /**
   * リクエスト代行オブジェクト
   */
  protected final HttpRequestBroker broker;

  /**
   * 通信先情報
   */
  protected final ConnectInfo ci;

  /**
   * 指定した認証用オブジェクトを使用して、MushikagoClientを構築します。<br>
   * 
   * @param credentials
   *          認証用オブジェクト
   */
  protected MushikagoClient(Credentials credentials) {
    this(credentials, DEFAULT_ENDPOINT);
  }

  /**
   * 指定した認証用オブジェクトと通信先URLを使用して、MushikagoClientを構築します。<br>
   * 
   * @param credentials
   *          認証用オブジェクト
   * @param endpoint
   *          通信先のURL
   */
  protected MushikagoClient(Credentials credentials, final String endpoint) {
    this(credentials, new ConnectInfo(endpoint, null, "https"));
  }

  /**
   * 指定した認証用オブジェクトと通信先URLを使用して、MushikagoClientを構築します。<br>
   * 
   * @param credentials
   *          認証用オブジェクト
   * @param endpoint
   *          通信先のURL
   */
  protected MushikagoClient(Credentials credentials, ConnectInfo ci) {
    this.broker = new HttpRequestBroker(credentials);
    this.ci = ci;
  }


  /**
   * 
   * @param endpoint
   * @param useSsl
   * @return
   */
  protected static final ConnectInfo createConnectInfo(String endpoint, boolean useSsl) {
    String[] hostAndPort = endpoint.split(":", 2);
    String host = hostAndPort[0];
    Integer port = null;
    if (hostAndPort.length == 2 && hostAndPort[1] != null) {
      port = Integer.valueOf(hostAndPort[1]);
    }
    return new ConnectInfo(host, port, useSsl ? "https" : "http");
  }
  
  /**
   * 通信先のURLを設定。<br>
   * 
   * @param endpoint
   *          URL
   */
  public void setEndpoint(String endpoint) {
    this.ci.setEndpoint(endpoint);
  }

  /**
   * 通信先のURLを取得。<br>
   * 
   * @return URL
   */
  public String getEndPoint() {
    return this.ci.getEndpoint();
  }

  /**
   * 通信先のポートを設定。<br>
   * 
   * @param port
   *          port番号
   */
  public void setPort(int port) {
    this.ci.setPort(port);
  }

  /**
   * 通信先のポートを取得。<br>
   * 
   * @return ポート番号
   */
  public int getPort() {
    return this.ci.getPort();
  }

  /**
   * 通信先のスキーマを設定。<br>
   * 
   * @param schema
   *          URLのスキーマ
   */
  public void setSchema(String schema) {
    this.ci.setSchema(schema);
  }

  /**
   * 通審査器のスキーマを取得。<br>
   * 
   * @return URLのスキーマ
   */
  public String getSchema() {
    return this.ci.getSchema();
  }

  /**
   * @param json
   * @throws APICallException
   */
  protected void handleExceptions(JSONObject json) throws APICallException {
    try {
      final int status = json.getJSONObject("meta").getInt("status");
      switch (status) {
      case 400:
        throw new BadRequestException(json);
      case 401:
        throw new UnauthorizedException(json);
      case 403:
        throw new ForbiddenException(json);
      case 404:
        throw new NotFoundException(json);
      case 409:
        throw new ConflictException(json);
      case 413:
        throw new RequestEntityTooLargeException(json);
      case 414:
        throw new RequestURITooLongException(json);
      case 500:
        throw new InternalErrorException(json);
      case 503:
        throw new ServiceUnavailableException(json);
      }
    }
    catch (JSONException e) {
      JSONObject detail = new JSONObject();
      detail.put("error", json.toString());
      throw new InternalServerErrorException(detail);
    }
  }

  /**
   * @param request
   * @return
   * @throws RequestException
   * @throws BrokerException
   * @throws APICallException
   */
  protected JSONObject sendRequest(HanamgriRequest request) throws RequestException, BrokerException, APICallException {
    JSONObject response = this.broker.request(request, this.ci);
    handleExceptions(response);
    return response;
  }
}
