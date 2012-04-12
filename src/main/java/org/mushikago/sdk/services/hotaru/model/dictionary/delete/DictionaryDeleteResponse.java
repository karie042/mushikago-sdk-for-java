package org.mushikago.sdk.services.hotaru.model.dictionary.delete;

import net.sf.json.JSONObject;

import org.mushikago.sdk.services.hotaru.model.HotaruResponse;

/**
 * 辞書削除リクエストの結果
 * @author miningbrownie
 */
public class DictionaryDeleteResponse extends HotaruResponse {
  
  /**
   * DictionaryDeleteResponseを構築します。<br>
   * @param json APIサーバからのレスポンス。<br>
   */
  public DictionaryDeleteResponse(JSONObject json) {
    
    super(json);
  }
}
