package org.mushikago.sdk.services.hotaru.model.text.put;

import java.io.UnsupportedEncodingException;
import java.util.TreeMap;

import org.apache.http.client.methods.HttpRequestBase;
import org.mushikago.sdk.common.RequestException;
import org.mushikago.sdk.common.auth.MushikagoAuth;
import org.mushikago.sdk.common.util.ConnectInfo;
import org.mushikago.sdk.common.util.ParamUtils;
import org.mushikago.sdk.services.hotaru.model.text.TextRequest;

/**
 * テキスト登録リクエスト。<br>
 * @author miningbrownie
 */
public class TextPutRequest extends TextRequest {
  
  /**
   * 操作名
   */
  public static final String OPERATION_NAME = "put";
  
  /**
   * パラメータ名（ドメイン名）
   */
  public static final String PARAM_KEY_DOMAIN_NAME = "domain_name";
  
  /**
   * パラメータ名（テキスト説明文）
   */
  public static final String PARAM_KEY_DESCRIPTION = "description";
  
  /**
   * パラメータ名（テキスト）
   */
  public static final String PARAM_KEY_TEXT = "text";
  
  /**
   * パラメータ名（タグ）
   */
  public static final String PARAM_KEY_TAGS = "tags";
  
  /**
   * パラメータ（ドメイン名）
   */
  private String domainName = null;
  
  /**
   * パラメータ（テキスト説明文）
   */
  private String description = null;
  
  /**
   * パラメータ（テキスト）
   */
  private String text = null;
  
  /**
   * パラメータ（タグ）
   */
  private String tags = null;
  
  /**
   * ドメイン名を取得します。<br>
   * @return ドメイン名
   */
  public String getDomainName() { return this.domainName; }
  
  /**
   * 説明文を取得します。<br>
   * @return テキスト名
   */
  public String getDescription() { return this.description; }
  
  /**
   * テキストを取得します。<br>
   * @return テキスト
   */
  public String getText() { return this.text; }
  
  /**
   * タグを取得します。<br>
   * @return タグ
   */
  public String getTags() { return this.tags; }
  
  /**
   * ドメイン名を取得します。<br>
   * @param name ドメイン名
   */
  public void setDomainName(String name) { this.domainName = name; }
  
  /**
   * 説明文を取得します、<br>
   * @param description テキスト名
   */
  public void setDescription(String description) { this.description = description; }
  
  /**
   * テキストを取得します。<br>
   * @param text テキスト
   */
  public void setText(String text) { this.text = text; }
  
  /**
   * タグを取得します。<br>
   * @param tags タグ
   */
  public void setTags(String tags) { this.tags = tags; }
  
  /**
   * パラメータを持たない、空のTextPutRequestを構築します、<br>
   */
  public TextPutRequest() {}
  
  /**
   * 指定されたドメイン名、テキスト名、テキスト、タグを使用して、TextPutReqesutを構築します。<br>
   * @param domainName ドメイン名
   * @param description 説明文
   * @param text テキスト
   * @param tags タグ
   */
  public TextPutRequest(String domainName, String description, String text, String tags) {
    this.domainName = domainName;
    this.description = description;
    this.text = text;
    this.tags = tags;
  }
  
  @Override
  public HttpRequestBase toHttpMethod(MushikagoAuth auth, ConnectInfo ci) throws RequestException {
    
    try {
      TreeMap<String, String> requestParams = new TreeMap<String, String>();
      if(null == this.domainName) throw new RequestException("ドメイン名を指定してください");
      requestParams.put(PARAM_KEY_DOMAIN_NAME, ParamUtils.paramEncode(this.domainName));
      if(null != this.description) requestParams.put(PARAM_KEY_DESCRIPTION, ParamUtils.paramEncode(this.description));
      if(null == this.text) throw new RequestException("テキストを指定してください");
      requestParams.put(PARAM_KEY_TEXT, ParamUtils.paramEncode(this.text));
      if(null == this.tags) throw new RequestException("タグを指定してください");
      requestParams.put(PARAM_KEY_TAGS, ParamUtils.paramEncode(this.tags));
      return this.toHttpPutMethod(auth, ci, this.path(this.serviceName, this.categoryName, OPERATION_NAME), requestParams);
    }
    catch(UnsupportedEncodingException e) {
      throw new RequestException(e.getMessage());
    }
  }
}
