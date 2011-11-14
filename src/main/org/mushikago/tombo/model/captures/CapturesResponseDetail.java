package org.mushikago.tombo.model.captures;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.mushikago.tombo.model.capture.CaptureRequest.ImageFormat;

public class CapturesResponseDetail {
	
	public CapturesResponseDetail(JSONObject image) {
		
		this.id = image.getString("image_id");
		this.imageUrl = image.getString("image_url");
		this.imageSize = image.getLong("image_size");
		this.thumbnailUrl = image.getString("thumbnail_url");
		this.thumbnailSize = image.getLong("thumbnail_size");
		this.sourceUrl = image.getString("source_url");
		this.format = ImageFormat.valueOf(image.getString("image_format").toUpperCase());
		this.quality = image.getInt("image_quality");
		this.state = image.getString("state");
		
		List<String> tags = new ArrayList<String>();
		JSONArray _tags = image.getJSONArray("tags");
		for(int j = 0; j < _tags.size(); j++) {
			tags.add(_tags.getString(j));
		}
		
		this.tags = tags.toArray(new String[tags.size()]);
	}
	
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
	public String getImageUrl() { return this.imageUrl; }
	public long getImageSize() { return this.imageSize; }
	public String getThumbnailUrl() { return this.thumbnailUrl; }
	public long getThumbnailSize() { return this.thumbnailSize; }
	public String getSourceUrl() { return this.sourceUrl; }
	public ImageFormat getFormat() { return this.format; }
	public int getQuality() { return this.quality; }
	public String getState() { return this.state; }
	public String[] getTags() { return this.tags; }
}
