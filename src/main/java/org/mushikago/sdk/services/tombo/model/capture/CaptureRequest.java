package org.mushikago.sdk.services.tombo.model.capture;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.mushikago.sdk.common.RequestException;
import org.mushikago.sdk.common.auth.AuthException;
import org.mushikago.sdk.common.auth.MushikagoAuth;
import org.mushikago.sdk.common.util.ConnectInfo;
import org.mushikago.sdk.common.util.ParamUtils;
import org.mushikago.sdk.services.tombo.model.TomboRequest;

public class CaptureRequest extends TomboRequest {
	
	public enum ImageFormat { JPG, PNG }
	
	private String url = null;
	private ImageFormat format = ImageFormat.JPG;
	private int quality = 80;
	private boolean requireThumbnail = false;
	private List<String> tags = new ArrayList<String>();
	private String userAgent = null;
	private int delayTime = 0;
	
	public CaptureRequest(String url) {
		this.url = url;
	}
	
	public CaptureRequest(String url, ImageFormat fmt, int quality, boolean requireThumbnail, String tag) {
		this(url, fmt, quality, requireThumbnail, new String[] {tag});
	}
	
	public CaptureRequest(String url, ImageFormat fmt, int quality, boolean requireThumbnail, String[] tags) {
		this(url);
		this.format = fmt;
		this.quality = quality;
		this.requireThumbnail = requireThumbnail;
		this.tags.addAll(Arrays.asList(tags));
	}
	
	public String getUrl()               { return this.url; }
	public ImageFormat getImageFormat()  { return this.format; }
	public int getQuality()              { return this.quality; }
	public boolean getRequireThumbnail() { return this.requireThumbnail; }
	public String[] getTags()            { return this.tags.toArray(new String[this.tags.size()]); }
	public String getUserAgent()         { return this.userAgent; }
	public int getDelayTime()            { return this.delayTime; }
	
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
	public void setUserAgent(String userAgent)       { this.userAgent = userAgent; }
	public void setDelayTime(int time)               { this.delayTime = time; }
	
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
		result.delayTime = this.delayTime;
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
	
	public CaptureRequest withDelayTime(int time) {
    CaptureRequest result = this.copy();
    result.delayTime = time;
    return result;
	}
	
	public CaptureRequest withUserAgent(String userAgent) {
		CaptureRequest result = this.copy();
		result.setUserAgent(userAgent);
		return result;
	}
	
	@Override
	public String toString() {
		return String.format("url=%s, fmt=%s, quality=%d, requireThumbnail=%b, tags=%s, useragent=%s, delayTime=%d", this.url, this.format, this.quality, this.requireThumbnail, this.getOfficialTags(), this.userAgent, this.delayTime);
	}

	@Override
	public HttpRequestBase toHttpMethod(MushikagoAuth auth, ConnectInfo ci) throws RequestException {
		
		try {
			TreeMap<String, String> requestParams = new TreeMap<String, String>();
			
			requestParams.put("url", ParamUtils.paramEncode(this.url));
			requestParams.put("image_format", ParamUtils.paramEncode(this.format.name()));
			requestParams.put("image_quality", ParamUtils.paramEncode(String.valueOf(this.quality)));
			requestParams.put("thumbnail", ParamUtils.paramEncode(this.requireThumbnail ? "1" : "0"));
			requestParams.put("tags", ParamUtils.paramEncode(this.getOfficialTags()));
			
			if(null != this.userAgent) { requestParams.put("useragent", ParamUtils.paramEncode(this.userAgent).replaceAll("\\+", "%20")); }
			
			requestParams.put("delay_time", String.valueOf(this.delayTime));
			
			String url = this.makeRequestUrl(auth, ci, "POST", "/1/tombo/capture.json", requestParams);
			HttpPost post = new HttpPost(url);
			String postParamString = ParamUtils.mapToString(requestParams);
			if("" != postParamString) {
				post.setHeader("Content-Type", "application/x-www-form-urlencoded");
				post.setEntity(new StringEntity(postParamString));
			}
			
			return post;
		}
		catch(UnsupportedEncodingException e) { throw new RequestException(e.getMessage()); }
		catch(AuthException e) { throw new RequestException(e.getMessage()); }
	}
}
