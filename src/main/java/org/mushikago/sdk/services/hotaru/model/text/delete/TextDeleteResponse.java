package org.mushikago.sdk.services.hotaru.model.text.delete;

import net.sf.json.JSONObject;

import org.mushikago.sdk.services.hotaru.model.HotaruResponse;

/**
 * テキスト削除リクエストの結果
 * @author miningbrownie
 */
public class TextDeleteResponse extends HotaruResponse {
  
  /**
   * TextDeleteResponseを構築します。<br>
   * @param json APIサーバからのレスポンス。<br>
   */
  public TextDeleteResponse(JSONObject json) {
    
    super(json);
  }
}
