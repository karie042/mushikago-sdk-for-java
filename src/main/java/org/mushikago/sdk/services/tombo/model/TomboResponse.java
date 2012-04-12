package org.mushikago.sdk.services.tombo.model;

import org.mushikago.sdk.common.model.MushikagoResponse;

import net.sf.json.JSONObject;

public abstract class TomboResponse extends MushikagoResponse {
	
	public TomboResponse(JSONObject json) {
		super(json);
	}
}
