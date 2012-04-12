package org.mushikago.sdk.services.hotaru.model.tag.put;

import net.sf.json.JSONObject;

import org.mushikago.sdk.services.hotaru.model.HotaruResponse;

/**
 * タグ登録リクエストの結果
 * @author miningbrownie
 */
public class TagPutResponse extends HotaruResponse {
  
  /**
   * TagPutResponseを構築します。<br>
   * @param json APIサーバからのレスポンス。<br>
   */
  public TagPutResponse(JSONObject json) {
    
    super(json);
  }
}
