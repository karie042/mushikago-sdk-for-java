package org.mushikago.tombo.model.info;

import org.mushikago.tombo.model.TomboResponse;

public class InfoResponse extends TomboResponse {
	
	private long diskUsage;
	private int apiCount;
	private int imageNum;
	
	public void setDiskUsage(long usage) { this.diskUsage = usage; }
	public long getDiskUsage() { return this.diskUsage; }
	
	public void setApiCount(int count) { this.apiCount = count; }
	public int getApiCount() { return this.apiCount; }
	
	public void setImageNum(int num) { this.imageNum = num; }
	public int getImageNum() { return this.imageNum; }
}
