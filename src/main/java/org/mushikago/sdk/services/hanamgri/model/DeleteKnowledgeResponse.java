package org.mushikago.sdk.services.hanamgri.model;

import net.sf.json.JSONObject;

/**
 * DeleteKnowledgeのレスポンスを表すクラスです
 * 
 * @author ope
 */
public class DeleteKnowledgeResponse extends HanamgriResponse {
  public DeleteKnowledgeResponse(JSONObject json) {
    super(json);
  }
}
