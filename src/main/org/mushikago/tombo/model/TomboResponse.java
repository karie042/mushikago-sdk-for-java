package org.mushikago.tombo.model;

public class TomboResponse {
	
	protected int httpStatus = -1;
	protected String message = null;
	
	public TomboResponse() {
		
	}
	
	public TomboResponse(TomboResponse response) {
		
		this.httpStatus = response.httpStatus;
		this.message = response.message;
	}
	
	public void setHttpStatus(int status) { this.httpStatus = status; }
	public int getHttpStatus() { return this.httpStatus; }
	
	public void setMessage(String msg) { this.message = msg; }
	public String getMessage() { return this.message; }
}
