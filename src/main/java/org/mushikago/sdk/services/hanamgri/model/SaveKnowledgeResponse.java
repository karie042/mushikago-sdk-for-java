package org.mushikago.sdk.services.hanamgri.model;

import net.sf.json.JSONObject;

/**
 * SaveKnowledgeのレスポンスを表すクラスです
 * @author ope
 *
 */
public class SaveKnowledgeResponse extends HanamgriResponse {
  public SaveKnowledgeResponse(JSONObject json) {
    super(json);
  }
}
