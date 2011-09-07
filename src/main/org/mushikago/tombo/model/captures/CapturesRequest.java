package org.mushikago.tombo.model.captures;

import org.mushikago.tombo.model.TomboRequest;

public class CapturesRequest extends TomboRequest {
	
	private String id = null;
	private int limit = 10;
	private int offset = 0;
	private String domain = null;
	private String tag = null;
	
	public CapturesRequest() {}
	
	public CapturesRequest(String id) {
		this.id = id;
	}
	
	public CapturesRequest(String id, int limit) {
		this.id = id;
		this.limit = limit;
	}
	
	public CapturesRequest(String id, int limit, int offset) {
		this.id = id;
		this.limit = limit;
		this.offset = offset;
	}
	
	public CapturesRequest(String id, int limit, int offset, String domain) {
		this.id = id;
		this.limit = limit;
		this.offset = offset;
		this.domain = domain;
	}
	
	public CapturesRequest(String id, int limit, int offset, String domain, String tag) {
		this.id = id;
		this.limit = limit;
		this.offset = offset;
		this.domain = domain;
		this.tag = tag;
	}
	
	public String getId() { return this.id; }
	public void setId(String id) { this.id = id; }
	
	public int getLimit() { return this.limit; }
	public void setLimit(int limit) { this.limit = limit; }
	
	public int getOffset() { return this.offset; }
	public void setOffset(int offset) { this.offset = offset; }
	
	public String getDomain() { return this.domain; }
	public void setDomain(String domain) { this.domain = domain; }
	
	public String getTag() { return this.tag; }
	public void setTag(String tag) { this.tag = tag; }
	
	private CapturesRequest copy() {
		CapturesRequest result = new CapturesRequest(this.id, this.limit, this.offset, this.domain, this.tag);
		return result;
	}
	
	public CapturesRequest withId(String id) {
		CapturesRequest result = this.copy();
		result.id = id;
		return result;
	}
	
	public CapturesRequest withLimit(int limit) {
		CapturesRequest result = this.copy();
		result.limit = limit;
		return result;
	}
	
	public CapturesRequest withOffset(int offset) {
		CapturesRequest result = this.copy();
		result.offset = offset;
		return result;
	}
	
	public CapturesRequest withDomain(String domain) {
		CapturesRequest result = this.copy();
		result.domain = domain;
		return result;
	}
	
	public CapturesRequest withTag(String tag) {
		CapturesRequest result = this.copy();
		result.tag = tag;
		return result;
	}
	
	@Override
	public String toString() {
		return String.format("id=%s, limit=%s, offset=%s, domain=%s, tag=%s", this.id, this.limit, this.offset, this.domain, this.tag);
	}
}
