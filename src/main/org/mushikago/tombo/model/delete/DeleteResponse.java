package org.mushikago.tombo.model.delete;

import net.sf.json.JSONObject;

import org.mushikago.tombo.model.TomboResponse;

public class DeleteResponse extends TomboResponse {
	
	public DeleteResponse(JSONObject json) {
		
		super(json);
		
		if("null".equals(json.getJSONObject("response").toString())) { return; }
		
		this.id = json.getJSONObject("response").getString("id");
	}

	private String id;
	
	public String getId() { return this.id; }
	
	@Override
	public String toString() {
		return String.format("id=%s", this.id);
	}
}
