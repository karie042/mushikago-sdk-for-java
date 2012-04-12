package org.mushikago.sdk.services.hotaru.model.collocation.get;

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
public class CollocationGetRequest extends CollocationRequest {
  
  /**
   * 操作名
   */
  public static final String OPERATION_NAME = "get";
  
  /**
   * パラメータ名（ドメイン名）
   */
  public static final String PARAM_KEY_DOMAIN_NAME = "domain_name";
  
  /**
   * パラメータ名（共起グラフID）
   */
  public static final String PARAM_KEY_COLLOCATION_ID = "collocation_id";
  
  /**
   * パラメータ名（単語）
   */
  public static final String PARAM_KEY_WORD = "word";
  
  /**
   * パラメータ（ドメイン名）
   */
  private String domainName = null;
  
  /**
   * パラメータ（共起グラフID）
   */
  private String collocationId = null;
  
  /**
   * パラメータ（単語）
   */
  private String word = null;
  
  /**
   * パラメータを持たない、空のCollocationGetRequestを構築します。<br>
   */
  public CollocationGetRequest() {}
  
  /**
   * 指定されたドメイン名を使用して、CollocationGetRequestを構築します。<br>
   * @param domainName ドメイン名
   */
  public CollocationGetRequest(String domainName) {
    this.domainName = domainName;
  }
  
  /**
   * 指定されたドメイン名、共起グラフID、単語を使用して、CollocationGetRequestを構築します。<br>
   * @param domainName ドメイン名
   * @param collocationId 共起グラフID
   * @param word 単語
   */
  public CollocationGetRequest(String domainName, String collocationId, String word) {
    this.domainName = domainName;
    this.collocationId = collocationId;
    this.word = word;
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
   * 単語を取得します。<br>
   * @return 単語
   */
  public String getWord() { return this.word; }
  
  /**
   * ドメイン名を設定します。<br>
   * @param domainName ドメイン名
   */
  public void setDomainName(String domainName) { this.domainName = domainName; }
  
  /**
   * 共起グラフIDを設定します。<br>
   * @param collocationId 共起グラフID
   */
  public void setCollocationId(String collocationId) { this.collocationId = collocationId; }
  
  /**
   * 単語を設定します。<br>
   * @param word 単語
   */
  public void setWord(String word) { this.word = word; }
  
  @Override
  public HttpRequestBase toHttpMethod(MushikagoAuth auth, ConnectInfo ci) throws RequestException {
    
    try {
      TreeMap<String, String> requestParams = new TreeMap<String, String>();
      if(null == this.domainName) throw new RequestException("ドメイン名を指定してください");
      requestParams.put(PARAM_KEY_DOMAIN_NAME, ParamUtils.paramEncode(this.domainName));
      if(null == this.collocationId) throw new RequestException("共起グラフIDを指定してください");
      requestParams.put(PARAM_KEY_COLLOCATION_ID, ParamUtils.paramEncode(this.collocationId));
      if(null == this.word) throw new RequestException("単語を指定してください");
      requestParams.put(PARAM_KEY_WORD, ParamUtils.paramEncode(this.word));
      return this.toHttpGetMethod(auth, ci, this.path(this.serviceName, this.categoryName, OPERATION_NAME), requestParams);
    }
    catch(UnsupportedEncodingException e) {
      throw new RequestException(e.getMessage());
    }
  }
}
