package org.mushikago.sdk.services.hotaru.model.dictionary.put;

import net.sf.json.JSONObject;

import org.mushikago.sdk.services.hotaru.model.HotaruResponse;

/**
 * 辞書登録リクエストの結果
 * @author miningbrownie
 */
public class DictionaryPutResponse extends HotaruResponse {
  
  /**
   * パラメータ名（辞書ID）
   */
  public static final String PARAM_KEY_DICTIONARY_ID = "dictionary_id";
  
  /**
   * パラメータ（辞書ID）
   */
  private final String id;
  
  /**
   * DictionaryPutResponseを構築します。<br>
   * @param json APIサーバからのレスポンス。<br>
   */
  public DictionaryPutResponse(JSONObject json) {
    
    super(json);
    
    final JSONObject response = json.getJSONObject("response");
    
    this.id = response.getString(PARAM_KEY_DICTIONARY_ID);
  }
  
  /**
   * 辞書IDを取得します。<br>
   * @return 辞書ID
   */
  public String getId() { return this.id; }
}
