package org.mushikago.sdk.services.hanamgri.model;

import net.sf.json.JSONObject;

/**
 * CreateDomainのレスポンスを表すクラスです
 * @author ope
 *
 */
public class CreateDomainResponse extends HanamgriResponse {
  public CreateDomainResponse(JSONObject json) {
    super(json);
  }
}
