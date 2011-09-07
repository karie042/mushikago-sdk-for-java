package org.mushikago.tombo.model.delete;

import org.mushikago.tombo.model.TomboRequest;

public class DeleteRequest extends TomboRequest {
	
	private String id;
	
	public DeleteRequest(String id) {
		this.id = id;
	}
	
	public String getId() { return this.id; }
	public void setId(String id) { this.id = id; }
	
	@Override
	public String toString() {
		return String.format("id=%s", this.id);
	}
}
