package org.mushikago.sdk.services.hotaru.model.collocation.download;

import java.io.UnsupportedEncodingException;
import java.util.TreeMap;

import org.apache.http.client.methods.HttpRequestBase;
import org.mushikago.sdk.common.RequestException;
import org.mushikago.sdk.common.auth.MushikagoAuth;
import org.mushikago.sdk.common.util.ConnectInfo;
import org.mushikago.sdk.common.util.ParamUtils;
import org.mushikago.sdk.services.hotaru.model.collocation.CollocationRequest;

/**
 * 共起グラフダウンロードのリクエスト。<br>
 * @author miningbrownie
 */
public class CollocationDownloadRequest extends CollocationRequest {
  
  /**
   * 操作名
   */
  public static final String OPERATION_NAME = "download";
  
  /**
   * パラメータ名（ドメイン名）
   */
  public static final String PARAM_KEY_DOMAIN_NAME = "domain_name";
  
  /**
   * パラメータ名（共起グラフID）
   */
  public static final String PARAM_KEY_COLLOCATION_ID = "collocation_id";
  
  /**
   * パラメータ（ドメイン名）
   */
  private String domainName = null;
  
  /**
   * パラメータ（共起グラフID）
   */
  private String collocationId = null;
  
  /**
   * パラメータを持たない、空のCollocationDownloadRequestを構築します。<br>
   */
  public CollocationDownloadRequest() {}
  
  /**
   * 指定されたドメイン名を使用して、CollocationDownloadRequestを構築します。<br>
   * @param domainName ドメイン名
   */
  public CollocationDownloadRequest(String domainName) {
    this.domainName = domainName;
  }
  
  /**
   * 指定されたドメイン名、共起グラフIDを使用して、CollocationDownloadRequestを構築します。<br>
   * @param domainName ドメイン名
   * @param id 共起グラフID
   */
  public CollocationDownloadRequest(String domainName, String id) {
    this.domainName = domainName;
    this.collocationId = id;
  }
  
  /**
   * ドメイン名を取得します。<br>
   * @return ドメイン名
   */
  public String getDomainName() { return this.domainName; }
  
  /**
   * 共起グラフIDを取得します。<br>
   * @return 共起グラフID
   */
  public String getCollocationId() { return this.collocationId; }
  
  /**
   * ドメイン名を設定します。<br>
   * @param name ドメイン名
   */
  public void setDomainName(String name) { this.domainName = name; }
  
  /**
   * 共起グラフIDを設定します。<br>
   * @param id 共起グラフID
   */
  public void setCollocationId(String id) { this.collocationId = id; }
  
  @Override
  public HttpRequestBase toHttpMethod(MushikagoAuth auth, ConnectInfo ci) throws RequestException {
    
    try {
      TreeMap<String, String> requestParams = new TreeMap<String, String>();
      if(null == this.domainName) throw new RequestException("ドメイン名を指定してください");
      requestParams.put(PARAM_KEY_DOMAIN_NAME, ParamUtils.paramEncode(this.domainName));
      if(null == this.collocationId) throw new RequestException("共起グラフIDを指定してください");
      requestParams.put(PARAM_KEY_COLLOCATION_ID, ParamUtils.paramEncode(this.collocationId));
      return this.toHttpGetMethod(auth, ci, this.path(this.serviceName, this.categoryName, OPERATION_NAME), requestParams);
    }
    catch(UnsupportedEncodingException e) {
      throw new RequestException(e.getMessage());
    }
  }
}
