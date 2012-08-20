package org.mushikago.sdk.services.hanamgri.model;

import net.sf.json.JSONObject;

/**
 * UpdateDomainのレスポンスを表すクラスです
 * @author ope
 *
 */
public class UpdateDomainResponse extends HanamgriResponse {
  public UpdateDomainResponse(JSONObject json) {
    super(json);
  }
}
