package org.mushikago.tombo.model.captures;

import org.mushikago.tombo.model.capture.CaptureRequest.ImageFormat;

public class CapturesResponseDetail {
	
	private String id = null;
	
	private String imageUrl = null;
	private long imageSize = -1;
	
	private String thumbnailUrl = null;
	private long thumbnailSize = -1;
	
	private String sourceUrl = null;
	private ImageFormat format = null;
	private int quality = -1;
	private String state = null;
	private String[] tags = null;
	
	public String getId() { return this.id; }
	public void setId(String id) { this.id = id; }
	
	public String getImageUrl() { return this.imageUrl; }
	public void setImageUrl(String url) { this.imageUrl = url; }
	
	public long getImageSize() { return this.imageSize; }
	public void setImageSize(long size) { this.imageSize = size; }
	
	public String getThumbnailUrl() { return this.thumbnailUrl; }
	public void setThumbnailUrl(String url) { this.thumbnailUrl = url; }
	
	public long getThumbnailSize() { return this.thumbnailSize; }
	public void setThumbnailSize(long size) { this.thumbnailSize = size; }
	
	public String getSourceUrl() { return this.sourceUrl; }
	public void setSourceUrl(String url) { this.sourceUrl = url; }
	
	public ImageFormat getFormat() { return this.format; }
	public void setFormat(ImageFormat fmt) { this.format = fmt; }
	
	public int getQuality() { return this.quality; }
	public void setQuality(int quality) { this.quality = quality; }
	
	public String getState() { return this.state; }
	public void setState(String state) { this.state = state; }
	
	public String[] getTags() { return this.tags; }
	public void setTags(String[] tags) { this.tags = tags; }
}
