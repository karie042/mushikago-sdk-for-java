package org.mushikago.tombo.model.capture;

import net.sf.json.JSONObject;

import org.mushikago.tombo.model.TomboResponse;

public class CaptureResponse extends TomboResponse {
	
	public CaptureResponse(JSONObject json) {
		
		super(json);
		
		if("null".equals(json.getJSONObject("response").toString())) { return; }
		
		this.imageUrl = json.getJSONObject("response").getString("image_url");
		this.id = json.getJSONObject("response").getString("id");
	}
	protected String imageUrl;
	protected String id;
	
	public String getImageUrl() { return this.imageUrl; }
	
	public String getId() { return this.id; }
}
