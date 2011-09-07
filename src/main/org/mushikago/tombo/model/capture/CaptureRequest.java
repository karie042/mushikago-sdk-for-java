package org.mushikago.tombo.model.capture;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.mushikago.tombo.model.TomboRequest;

public class CaptureRequest extends TomboRequest {
	
	public enum ImageFormat { JPG, PNG }
	
	private String url = null;
	private ImageFormat format = ImageFormat.JPG;
	private int quality = 80;
	private boolean requireThumbnail = false;
	private List<String> tags = new ArrayList<String>();
	
	public CaptureRequest(String url) {
		this.url = url;
	}
	
	public CaptureRequest(String url, ImageFormat fmt) {
		this(url);
		this.format = fmt;
	}
	
	public CaptureRequest(String url, ImageFormat fmt, int quality) {
		this(url, fmt);
		this.quality = quality;
	}
	
	public CaptureRequest(String url, ImageFormat fmt, int quality, boolean requireThumbnail) {
		this(url, fmt, quality);
		this.requireThumbnail = requireThumbnail;
	}
	
	public CaptureRequest(String url, ImageFormat fmt, int quality, boolean requireThumbnail, String tag) {
		this(url, fmt, quality, requireThumbnail);
		this.tags.add(tag);
	}
	
	public CaptureRequest(String url, ImageFormat fmt, int quality, boolean requireThumbnail, String[] tags) {
		this(url, fmt, quality, requireThumbnail);
		this.tags.addAll(Arrays.asList(tags));
	}
	
	public String getUrl()               { return this.url; }
	public ImageFormat getImageFormat()  { return this.format; }
	public int getQuality()              { return this.quality; }
	public boolean getRequireThumbnail() { return this.requireThumbnail; }
	public String[] getTags()            { return this.tags.toArray(new String[this.tags.size()]); }
	
	public String getOfficialTags() {
		
		if(0 >= this.tags.size()) return "";
		
		StringBuilder buffer = new StringBuilder();
		for(String tag : this.tags) { buffer.append("," + tag); }
		
		return buffer.substring(1);
	}
	
	public void setUrl(String url)                   { this.url = url; }
	public void setImageFormat(ImageFormat fmt)      { this.format = fmt; }
	public void setQuality(int quality)              { this.quality = quality; }
	public void setRequireThumbnail(boolean require) { this.requireThumbnail = require; }
	
	public void setTags(String[] tags) {
		this.tags.clear();
		this.addTags(tags);
	}
	
	public void addTag(String tag)                   { this.tags.add(tag); }
	public void addTags(String[] tags)               { this.tags.addAll(Arrays.asList(tags)); }
	
	private CaptureRequest copy() {
		CaptureRequest result = new CaptureRequest(this.url);
		result.format = this.format;
		result.quality = this.quality;
		result.requireThumbnail = this.requireThumbnail;
		result.tags = new ArrayList<String>();
		result.tags.addAll(this.tags);
		return result;
	}
	
	public CaptureRequest withImageFormat(ImageFormat fmt) {
		CaptureRequest result = this.copy();
		result.format = fmt;
		return result;
	}
	
	public CaptureRequest withQuality(int quality) {
		CaptureRequest result = this.copy();
		result.quality = quality;
		return result;
	}
	
	public CaptureRequest withReqireThumbnail(boolean require) {
		CaptureRequest result = this.copy();
		result.requireThumbnail = require;
		return result;
	}
	
	public CaptureRequest withTag(String tag) {
		CaptureRequest result = this.copy();
		result.tags.add(tag);
		return result;
	}
	
	public CaptureRequest withTags(String[] tags) {
		CaptureRequest result = this.copy();
		result.tags.addAll(Arrays.asList(tags));
		return result;
	}
	
	@Override
	public String toString() {
		return String.format("url=%s, fmt=%s, quality=%d, requireThumbnail=%b, tags=%s", this.url, this.format, this.quality, this.requireThumbnail, this.getOfficialTags());
	}
}
