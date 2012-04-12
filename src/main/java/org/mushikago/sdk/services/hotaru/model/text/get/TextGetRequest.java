package org.mushikago.sdk.services.hotaru.model.text.get;

import java.io.UnsupportedEncodingException;
import java.util.TreeMap;

import org.apache.http.client.methods.HttpRequestBase;
import org.mushikago.sdk.common.RequestException;
import org.mushikago.sdk.common.auth.MushikagoAuth;
import org.mushikago.sdk.common.util.ConnectInfo;
import org.mushikago.sdk.common.util.ParamUtils;
import org.mushikago.sdk.services.hotaru.model.text.TextRequest;

/**
 * テキスト取得リクエスト。<br>
 * @author miningbrownie
 */
public class TextGetRequest extends TextRequest {
  
  /**
   * 操作名
   */
  public static final String OPERATION_NAME = "get";
  
  /**
   * パラメータ名（ドメイン名）
   */
  public static final String PARAM_KEY_DOMAIN_NAME = "domain_name";
  
  /**
   * パラメータ名（テキストID）
   */
  public static final String PARAM_KEY_TEXT_ID = "text_id";
  
  /**
   * パラメータ（ドメイン名）
   */
  private String domainName = null;
  
  /**
   * パラメータ（テキストID）
   */
  private String textId = null;
  
  /**
   * パラメータを持たない、空のTextGetRequestを構築します。<br>
   */
  public TextGetRequest() {}
  
  /**
   * 指定されたドメイン名を使用して、TextGetRequestを構築します。<br>
   * @param domainName ドメイン名
   */
  public TextGetRequest(String domainName) {
    this.domainName = domainName;
  }
  
  /**
   * 指定されたドメイン名、テキストIDを使用して、TextGetRequestを構築します。<br>
   * @param domainName ドメイン名
   * @param textId テキストID
   */
  public TextGetRequest(String domainName, String textId) {
    this.domainName = domainName;
    this.textId = textId;
  }
  
  /**
   * ドメイン名を取得します。<br>
   * @return ドメイン名
   */
  public String getDomainName() { return this.domainName; }
  
  /**
   * テキストIDを取得します。<br>
   * @return テキストID
   */
  public String getTextId() { return this.textId; }
  
  /**
   * ドメイン名を設定します。<br>
   * @param name ドメイン名
   */
  public void setDomainName(String name) { this.domainName = name; }
  
  /**
   * テキストIDを設定します。<br>
   * @param textId テキストID
   */
  public void setTextId(String textId) { this.textId = textId; }
  
  @Override
  public HttpRequestBase toHttpMethod(MushikagoAuth auth, ConnectInfo ci) throws RequestException {
    
    try {
      TreeMap<String, String> requestParams = new TreeMap<String, String>();
      if(null == this.domainName) throw new RequestException("ドメイン名を指定してください");
      requestParams.put(PARAM_KEY_DOMAIN_NAME, ParamUtils.paramEncode(this.domainName));
      if(null == this.textId) throw new RequestException("テキストIDを指定してください");
      requestParams.put(PARAM_KEY_TEXT_ID, ParamUtils.paramEncode(this.textId));
      return this.toHttpGetMethod(auth, ci, this.path(this.serviceName, this.categoryName, OPERATION_NAME), requestParams);
    }
    catch(UnsupportedEncodingException e) {
      throw new RequestException(e.getMessage());
    }
  }
}
