package org.mushikago.tombo.model;

import net.sf.json.JSONObject;

public abstract class TomboResponse {
	
	protected int httpStatus = -1;
	protected String message = null;
	
	public TomboResponse(JSONObject json) {
		
		this.httpStatus = Integer.valueOf(json.getJSONObject("meta").getString("status"));
		this.message = json.getJSONObject("meta").getString("message");
	}
	
	public void setHttpStatus(int status) { this.httpStatus = status; }
	public int getHttpStatus() { return this.httpStatus; }
	
	public void setMessage(String msg) { this.message = msg; }
	public String getMessage() { return this.message; }
}
