package org.mushikago.sdk.services.hotaru.model.text.tagset;

import net.sf.json.JSONObject;

import org.mushikago.sdk.services.hotaru.model.HotaruResponse;

/**
 * タグの追加・変更リクエストの結果
 * @author miningbrownie
 */
public class TextTagsetResponse extends HotaruResponse {
  
  /**
   * TextTagsetResponseを構築します。<br>
   * @param json APIサーバからのレスポンス。<br>
   */
  public TextTagsetResponse(JSONObject json) {
    
    super(json);
  }
}
