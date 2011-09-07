package org.mushikago.tombo.model.captures;

import java.util.ArrayList;
import java.util.List;

import org.mushikago.tombo.model.TomboResponse;

public class CapturesResponse extends TomboResponse {
	
	private int total;
	private List<CapturesResponseDetail> details = new ArrayList<CapturesResponseDetail>();
	
	public int getTotal() { return this.total; }
	public void setTotal(int total) { this.total = total; }
	
	public CapturesResponseDetail[] getDetails() { return this.details.toArray(new CapturesResponseDetail[this.details.size()]); }
	public void setDetails(List<CapturesResponseDetail> details) { this.details = details; }
}
