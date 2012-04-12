package org.mushikago.sdk.services.hotaru.model.collocation.create;

import net.sf.json.JSONObject;

import org.mushikago.sdk.services.hotaru.model.HotaruResponse;

/**
 * 共起グラフ作成リクエストの結果
 * @author miningbrownie
 */
public class CollocationCreateResponse extends HotaruResponse {
  
  /**
   * パラメータ名（共起ID）
   */
  public static final String PARAM_KEY_COLLOCATION_ID = "collocation_id";
  
  /**
   * パラメータ（共起ID）
   */
  private final String collocationId;
  
  /**
   * CollocationCreateResponseを構築します。<br>
   * @param json APIサーバからのレスポンス。<br>
   */
  public CollocationCreateResponse(JSONObject json) {
    
    super(json);
    
    final JSONObject response = json.getJSONObject("response");
    
    this.collocationId = response.getString(PARAM_KEY_COLLOCATION_ID);
  }
  
  /**
   * 共起IDを取得します。<br>
   * @return 共起ID
   */
  public String getCollocationId() { return this.collocationId; }
}
