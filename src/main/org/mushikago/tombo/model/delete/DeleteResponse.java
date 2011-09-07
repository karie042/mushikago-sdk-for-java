package org.mushikago.tombo.model.delete;

import org.mushikago.tombo.model.TomboResponse;

public class DeleteResponse extends TomboResponse {
	
	private String id;
	
	public String getId() { return this.id; }
	public void setId(String id) { this.id = id; }
	
	@Override
	public String toString() {
		return String.format("id=%s", this.id);
	}
}
