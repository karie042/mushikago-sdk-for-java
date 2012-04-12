package org.mushikago.sdk.services.hotaru.model.domain.create;

import net.sf.json.JSONObject;

import org.mushikago.sdk.services.hotaru.model.HotaruResponse;

/**
 * ドメイン作成リクエストの結果
 * @author miningbrownie
 */
public class DomainCreateResponse extends HotaruResponse {
  
  /**
   * DomainCreateResponseを構築します。<br>
   * @param json APIサーバからのレスポンス。<br>
   */
  public DomainCreateResponse(JSONObject json) {
    super(json);
  }
}
