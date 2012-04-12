package org.mushikago.sdk.services.tombo.model.info;

import net.sf.json.JSONObject;

import org.mushikago.sdk.services.tombo.model.TomboResponse;

public class InfoResponse extends TomboResponse {
	
	public InfoResponse(JSONObject json) {
		super(json);
		
		if("null".equals(json.getJSONObject("response").toString())) { return; }
		
		this.apiCount = Integer.valueOf(json.getJSONObject("response").getString("api_count"));
		this.diskUsage = Integer.valueOf(json.getJSONObject("response").getString("disk_usage"));
		this.imageNum = Integer.valueOf(json.getJSONObject("response").getString("image_num"));
	}
	private long diskUsage;
	private int apiCount;
	private int imageNum;
	
	public long getDiskUsage() { return this.diskUsage; }
	
	public int getApiCount() { return this.apiCount; }
	
	public int getImageNum() { return this.imageNum; }
}
