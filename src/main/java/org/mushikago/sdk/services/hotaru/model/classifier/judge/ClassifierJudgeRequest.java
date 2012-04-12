package org.mushikago.sdk.services.hotaru.model.classifier.judge;

import java.io.UnsupportedEncodingException;
import java.util.TreeMap;

import org.apache.http.client.methods.HttpRequestBase;
import org.mushikago.sdk.common.RequestException;
import org.mushikago.sdk.common.auth.MushikagoAuth;
import org.mushikago.sdk.common.util.ConnectInfo;
import org.mushikago.sdk.common.util.ParamUtils;
import org.mushikago.sdk.services.hotaru.model.classifier.ClassifierRequest;

/**
 * タグ判定リクエスト。<br>
 * @author miningbrownie
 */
public class ClassifierJudgeRequest extends ClassifierRequest {
  
  /**
   * 操作名
   */
  public static final String OPERATION_NAME = "judge";
  
  /**
   * パラメータ名（ドメイン名）
   */
  public static final String PARAM_KEY_DOMAIN_NAME = "domain_name";
  
  /**
   * パラメータ名（テキスト）
   */
  public static final String PARAM_KEY_TEXT = "text";
  
  /**
   * パラメータ（ドメイン名）
   */
  private String domainName = null;
  
  /**
   * パラメータ（テキスト）
   */
  private String text = null;
  
  /**
   * パラメータを持たない、空のClassifierJudgeRequestを構築します。<br>
   */
  public ClassifierJudgeRequest() {}
  
  /**
   * 指定されたドメイン名を使用して、ClassifierJudgeRequestを構築します。<br>
   * @param domainName ドメイン名
   */
  public ClassifierJudgeRequest(String domainName) {
    this.domainName = domainName;
  }
  
  /**
   * 指定されたドメイン名、テキストを使用して、ClassifierJudgeRequestを構築します。<br>
   * @param domainName ドメイン名
   * @param text テキスト
   */
  public ClassifierJudgeRequest(String domainName, String text) {
    this.domainName = domainName;
    this.text = text;
  }
  
  /**
   * ドメイン名を取得します。<br>
   * @return ドメイン名
   */
  public String getDomainName() { return this.domainName; }
  
  /**
   * テキストを取得します。<br>
   * @return テキスト
   */
  public String getText() { return this.text; }
  
  /**
   * ドメイン名を設定します。<br>
   * @param name ドメイン名
   */
  public void setDomainName(String name) { this.domainName = name; }
  
  /**
   * テキストを設定します。<br>
   * @param text テキスト
   */
  public void setText(String text) { this.text = text; }
  
  @Override
  public HttpRequestBase toHttpMethod(MushikagoAuth auth, ConnectInfo ci) throws RequestException {
    
    try {
      TreeMap<String, String> requestParams = new TreeMap<String, String>();
      if(null == this.domainName) throw new RequestException("ドメイン名を指定してください");
      requestParams.put(PARAM_KEY_DOMAIN_NAME, ParamUtils.paramEncode(String.valueOf(this.domainName)));
      if(null == this.text) throw new RequestException("テキストを指定してください");
      requestParams.put(PARAM_KEY_TEXT, ParamUtils.paramEncode(String.valueOf(this.text)));
      return this.toHttpPostMethod(auth, ci, this.path(this.serviceName, this.categoryName, OPERATION_NAME), requestParams);
    }
    catch(UnsupportedEncodingException e) {
      throw new RequestException(e.getMessage());
    }
  }
}
