package org.mushikago.sdk.services.hotaru.model.text.put;

import net.sf.json.JSONObject;

import org.mushikago.sdk.services.hotaru.model.HotaruResponse;

/**
 * テキスト登録リクエストの結果
 * @author miningbrownie
 */
public class TextPutResponse extends HotaruResponse {
  
  /**
   * パラメータ名（テキストID）
   */
  public static final String PARAM_KEY_TEXT_ID = "text_id";
  
  /**
   * パラメータ（テキストID）
   */
  private final String textId;
  
  /**
   * TextPutResponseを構築します。<br>
   * @param json APIサーバからのレスポンス。<br>
   */
  public TextPutResponse(JSONObject json) {
    
    super(json);
    
    final JSONObject response = json.getJSONObject("response");
    
    this.textId = response.getString(PARAM_KEY_TEXT_ID);
  }
  
  public String getTextId() { return this.textId; }
}
