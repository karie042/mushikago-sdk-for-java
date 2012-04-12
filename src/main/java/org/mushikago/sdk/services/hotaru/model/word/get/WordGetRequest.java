package org.mushikago.sdk.services.hotaru.model.word.get;

import java.io.UnsupportedEncodingException;
import java.util.TreeMap;

import org.apache.http.client.methods.HttpRequestBase;
import org.mushikago.sdk.common.RequestException;
import org.mushikago.sdk.common.auth.MushikagoAuth;
import org.mushikago.sdk.common.util.ConnectInfo;
import org.mushikago.sdk.common.util.ParamUtils;
import org.mushikago.sdk.services.hotaru.model.word.WordRequest;

/**
 * 単語の詳細情報取得リクエスト。<br>
 * @author miningbrownie
 */
public class WordGetRequest extends WordRequest {
  
  /**
   * 操作名
   */
  public static final String OPERATION_NAME = "get";
  
  /**
   * パラメータ名（ドメイン名）
   */
  public static final String PARAM_KEY_DOMAIN_NAME = "domain_name";
  
  /**
   * パラメータ名（単語）
   */
  public static final String PARAM_KEY_WORD = "word";
  
  /**
   * パラメータ（ドメイン名）
   */
  private String domainName = null;
  
  /**
   * パラメータ（単語）
   */
  private String word = null;
  
  /**
   * パラメータを持たない、空のWordGetRequestを構築します。<br>
   */
  public WordGetRequest() {}
  
  /**
   * 指定されたドメイン名を使用して、WordGetRequestを構築します。<br>
   * @param domainName
   */
  public WordGetRequest(String domainName) {
    this.domainName = domainName;
  }
  
  /**
   * 指定されたドメイン名、単語を使用して、WordGetRequestを構築します。<br>
   * @param domainName ドメイン名
   * @param word 単語
   */
  public WordGetRequest(String domainName, String word) {
    this.domainName = domainName;
    this.word = word;
  }
  
  /**
   * ドメイン名を取得します。<br>
   * @return ドメイン名
   */
  public String getDomainName() { return this.domainName; }
  
  /**
   * 単語を取得します。<br>
   * @return 単語
   */
  public String getWord() { return this.word; }
  
  /**
   * ドメイン名を設定します。<br>
   * @param name ドメイン名
   */
  public void setDomainName(String name) { this.domainName = name; }
  
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
      if(null == this.word) throw new RequestException("単語を指定してください");
      requestParams.put(PARAM_KEY_WORD, ParamUtils.paramEncode(this.word));
      return this.toHttpGetMethod(auth, ci, this.path(this.serviceName, this.categoryName, OPERATION_NAME), requestParams);
    }
    catch(UnsupportedEncodingException e) {
      throw new RequestException(e.getMessage());
    }
  }
}
