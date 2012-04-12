package org.mushikago.sdk.services.hotaru.model.text.delete;

import java.io.UnsupportedEncodingException;
import java.util.TreeMap;

import org.apache.http.client.methods.HttpRequestBase;
import org.mushikago.sdk.common.RequestException;
import org.mushikago.sdk.common.auth.MushikagoAuth;
import org.mushikago.sdk.common.util.ConnectInfo;
import org.mushikago.sdk.common.util.ParamUtils;
import org.mushikago.sdk.services.hotaru.model.text.TextRequest;

/**
 * テキスト削除リクエスト。<br>
 * @author miningbrownie
 */
public class TextDeleteRequest extends TextRequest {
  
  /**
   * 操作名
   */
  public static final String OPERATION_NAME = "delete";
  
  /**
   * パラメータ名（ドメイン名）
   */
  public static final String PARAM_KEY_DOMAIN_NAME = "domain_name";
  
  /**
   * パラメータ名（テキストID）
   */
  public static final String PARAM_KEY_TEXT_ID = "text_id";
  
  /**
   * パラメータ名（強制削除フラグ）
   */
  public static final String PARAM_KEY_FORCEDELETE = "forcedelete";
  
  /**
   * パラメータ（ドメイン名）
   */
  private String domainName = null;
  
  /**
   * パラメータ（テキストID）
   */
  private String textId = null;
  
  /**
   * パラメータ（強制削除フラグ）
   */
  private boolean isForceDelete = false;
  
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
   * 強制削除フラグを取得します。<br>
   * @return 強制削除フラグ
   */
  public boolean isForceDelete() { return this.isForceDelete; }
  
  /**
   * ドメイン名を設定します。<br>
   * @param name ドメイン名
   */
  public void setDomainName(String name) { this.domainName = name; }
  
  /**
   * テキストIDを設定します。<br>
   * @param id テキストID
   */
  public void setTextId(String id) { this.textId = id; }
  
  /**
   * 強制削除フラグを設定します。<br>
   * @param isForceDelete 強制削除フラグ（true:強制)
   */
  public void setForceDelete(boolean isForceDelete) { this.isForceDelete = isForceDelete; }
  
  @Override
  public HttpRequestBase toHttpMethod(MushikagoAuth auth, ConnectInfo ci) throws RequestException {
    
    try {
      TreeMap<String, String> requestParams = new TreeMap<String, String>();
      if(null == this.domainName) throw new RequestException("ドメイン名を指定してください");
      requestParams.put(PARAM_KEY_DOMAIN_NAME, ParamUtils.paramEncode(this.domainName));
      if(null == this.textId) throw new RequestException("テキストIDを指定してください");
      requestParams.put(PARAM_KEY_TEXT_ID, ParamUtils.paramEncode(this.textId));
      requestParams.put(PARAM_KEY_FORCEDELETE, ParamUtils.paramEncode(this.isForceDelete ? "1" : "0"));
      return this.toHttpDeleteMethod(auth, ci, this.path(this.serviceName, this.categoryName, OPERATION_NAME), requestParams);
    }
    catch(UnsupportedEncodingException e) {
      throw new RequestException(e.getMessage());
    }
  }
}
