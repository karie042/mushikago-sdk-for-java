package org.mushikago.sdk.services.hotaru.model.text.tagset;

import java.io.UnsupportedEncodingException;
import java.util.TreeMap;

import org.apache.http.client.methods.HttpRequestBase;
import org.mushikago.sdk.common.RequestException;
import org.mushikago.sdk.common.auth.MushikagoAuth;
import org.mushikago.sdk.common.util.ConnectInfo;
import org.mushikago.sdk.common.util.ParamUtils;
import org.mushikago.sdk.services.hotaru.model.text.TextRequest;

/**
 * タグの追加・変更リクエスト。<br>
 * @author miningbrownie
 */
public class TextTagsetRequest extends TextRequest {
  
  /**
   * 操作名
   */
  public static final String OPERATION_NAME = "tagset";
  
  /**
   * リクエストパラメータ名（ドメイン名）
   */
  public static final String PARAM_KEY_DOMAIN_NAME = "domain_name";
  
  /**
   * リクエストパラメータ名（テキストID）
   */
  public static final String PARAM_KEY_TEXT_ID = "text_id";
  
  /**
   * リクエストパラメータ名（タグ）
   */
  public static final String PARAM_KEY_TAGS = "tags";
  
  /**
   * リクエストパラメータ名（置換フラグ）
   */
  public static final String PARAM_KEY_REPLACE = "replace";
  
  /**
   * リクエストパラメータ（ドメイン名）
   */
  private String domainName = null;
  
  /**
   * リクエストパラメータ（テキストID）
   */
  private String textId = null;
  
  /**
   * リクエストパラメータ（タグ）
   */
  private String tags = null;
  
  /**
   * リクエストパラメータ（置換フラグ）
   */
  private boolean isReplace = false;
  
  /**
   * パラメータを持たない、空のTextTagsetRequestを構築します。<br>
   */
  public TextTagsetRequest() {}
  
  /**
   * 指定されたドメイン名を使用して、TextTagsetRequestを構築します。<br>
   * @param domainName ドメイン名
   */
  public TextTagsetRequest(String domainName) {
    this.domainName = domainName;
  }
  
  /**
   * 指定されたドメイン名、テキストID、タグ、置換フラグを使用して、TextTagsetRequestを構築します。<br>
   * @param domainName ドメイン名
   * @param textId テキストID
   * @param tags タグ
   * @param replace 置換フラグ
   */
  public TextTagsetRequest(String domainName, String textId, String tags, boolean replace) {
    this.domainName = domainName;
    this.textId = textId;
    this.tags = tags;
    this.isReplace = replace;
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
   * タグを取得します。<br>
   * @return タグ
   */
  public String getTags() { return this.tags; }
  
  /**
   * 置換フラグを取得します。<br>
   * @return 置換フラグ
   */
  public boolean isReplace() { return this.isReplace; }
  
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
  
  /**
   * タグを設定します。<br>
   * @param tags タグ
   */
  public void setTags(String tags) { this.tags = tags; }
  
  /**
   * 置換フラグを設定します。<br>
   * @param replace 置換フラグ
   */
  public void setReplace(boolean replace) { this.isReplace = replace; }
  
  @Override
  public HttpRequestBase toHttpMethod(MushikagoAuth auth, ConnectInfo ci) throws RequestException {
    
    try {
      TreeMap<String, String> requestParams = new TreeMap<String, String>();
      if(null == this.domainName) throw new RequestException("ドメイン名を指定してください");
      requestParams.put(PARAM_KEY_DOMAIN_NAME, ParamUtils.paramEncode(this.domainName));
      if(null == this.textId) throw new RequestException("テキストIDを指定してください");
      requestParams.put(PARAM_KEY_TEXT_ID, ParamUtils.paramEncode(this.textId));
      if(null == this.tags) throw new RequestException("タグを指定してください");
      requestParams.put(PARAM_KEY_TAGS, ParamUtils.paramEncode(this.tags));
      requestParams.put(PARAM_KEY_REPLACE, ParamUtils.paramEncode(this.isReplace ? "1" : "0"));
      return this.toHttpPostMethod(auth, ci, this.path(this.serviceName, this.categoryName, OPERATION_NAME), requestParams);
    }
    catch(UnsupportedEncodingException e) {
      throw new RequestException(e.getMessage());
    }
  }
}
