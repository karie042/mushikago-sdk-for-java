package org.mushikago.sdk.services.hanamgri.model;

import net.sf.json.JSONObject;

import org.mushikago.sdk.common.model.MushikagoResponse;

public abstract class HanamgriResponse extends MushikagoResponse {

  public HanamgriResponse(JSONObject json) {
    super(json);
  }
}
