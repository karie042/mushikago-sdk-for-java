package org.mushikago.tombo.model.captures;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.mushikago.tombo.model.TomboResponse;
import org.mushikago.tombo.model.capture.CaptureRequest.ImageFormat;

public class CapturesResponse extends TomboResponse {
	
	public CapturesResponse(JSONObject json) {
		super(json);
		
		if("null".equals(json.getJSONObject("response").toString())) { return; }
		
		this.total = json.getJSONObject("response").getInt("total");
		JSONArray images = json.getJSONObject("response").getJSONArray("images");
		List<CapturesResponseDetail> details = new ArrayList<CapturesResponseDetail>();
		for(int i = 0; i < images.size(); i++) {
			
			JSONObject image = images.getJSONObject(i);
			
			CapturesResponseDetail detail = new CapturesResponseDetail(image);
			details.add(detail);
		}
		
		this.details = details;
	}
	private int total;
	private List<CapturesResponseDetail> details = new ArrayList<CapturesResponseDetail>();
	
	public int getTotal() { return this.total; }
	
	public CapturesResponseDetail[] getDetails() { return this.details.toArray(new CapturesResponseDetail[this.details.size()]); }
}
