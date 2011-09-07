package org.mushikago.tombo.model.capture;

import org.mushikago.tombo.model.TomboResponse;

public class CaptureResponse extends TomboResponse {
	
	protected String imageUrl;
	protected String id;
	
	public void setImageUrl(String url) { this.imageUrl = url; }
	public String getImageUrl() { return this.imageUrl; }
	
	public void setId(String id) { this.id = id; }
	public String getId() { return this.id; }
}
