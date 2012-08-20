package org.mushikago.sdk.services.hanamgri.model;

import net.sf.json.JSONObject;

/**
 * CreateDomainのレスポンスを表すクラスです
 * @author ope
 *
 */
public class TrainResponse extends HanamgriResponse {
  public TrainResponse(JSONObject json) {
    super(json);
  }
}
