package org.mushikago.sdk.services.hotaru.model.domain.delete;

import net.sf.json.JSONObject;

import org.mushikago.sdk.services.hotaru.model.HotaruResponse;

/**
 * ドメイン削除リクエストの結果
 * @author miningbrownie
 */
public class DomainDeleteResponse extends HotaruResponse {
  
  /**
   * DomainDeleteResponseを構築します。<br>
   * @param json APIサーバからのレスポンス。<br>
   */
  public DomainDeleteResponse(JSONObject json) {
    super(json);
  }
}
