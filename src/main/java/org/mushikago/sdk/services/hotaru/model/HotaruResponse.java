package org.mushikago.sdk.services.hotaru.model;

import net.sf.json.JSONObject;

import org.mushikago.sdk.common.model.MushikagoResponse;

/**
 * hotaruAPIのレスポンスに対する基底クラス。<br>
 * @author miningbrownie
 */
public class HotaruResponse extends MushikagoResponse {
  
  /**
   * HotaruResponseを構築します。<br>
   * @param json APIサーバからのレスポンス。<br>
   */
  public HotaruResponse(JSONObject json) {
    super(json);
  }
}
