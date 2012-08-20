package org.mushikago.sdk.services.hanamgri.model;

import net.sf.json.JSONObject;

/**
 * SaveDictionaryのレスポンスを表すクラスです
 * @author ope
 *
 */
public class SaveDictionaryResponse extends HanamgriResponse {
  public SaveDictionaryResponse(JSONObject json) {
    super(json);
  }
}
