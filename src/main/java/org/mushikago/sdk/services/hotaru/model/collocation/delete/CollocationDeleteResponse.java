package org.mushikago.sdk.services.hotaru.model.collocation.delete;

import net.sf.json.JSONObject;

import org.mushikago.sdk.services.hotaru.model.HotaruResponse;

/**
 * 共起グラフ削除リクエストの結果
 * @author miningbrownie
 */
public class CollocationDeleteResponse extends HotaruResponse {
  
  /**
   * CollocationDeleteResponseを構築します。<br>
   * @param json APIサーバからのレスポンス。<br>
   */
  public CollocationDeleteResponse(JSONObject json) {
    
    super(json);
  }
}
