package org.mushikago.sdk.services.hanamgri.model;

import net.sf.json.JSONObject;

/**
 * CreateDomainのレスポンスを表すクラスです
 * 
 * @author ope
 */
public class DeleteDomainResponse extends HanamgriResponse {
  public DeleteDomainResponse(JSONObject json) {
    super(json);
  }
}
