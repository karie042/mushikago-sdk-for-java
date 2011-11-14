package org.mushikago.tombo.model.captures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class CapturesResponseTest {
	
	public JSONObject makeTestData() {
		
		JSONObject json = new JSONObject();
		
		JSONObject meta = new JSONObject();
		meta.put("status", 206);
		meta.put("message", "122abcdez");
		json.put("meta", meta);
		
		JSONObject response = new JSONObject();
		response.put("total", 55);
		
		JSONArray images = new JSONArray();
		JSONObject image = new JSONObject();
		
		image.put("image_id", "image_id-test");
		image.put("image_url", "image_url-test");
		image.put("image_size", "11");
		image.put("thumbnail_url", "thumbnail_url-test");
		image.put("thumbnail_size", "22");
		image.put("source_url", "source_url-test");
		image.put("image_format", "JPG");
		image.put("image_quality", "33");
		image.put("state", "state-test");
		JSONArray tags = new JSONArray();
		tags.add("narita");
		tags.add("tarina");
		image.put("tags", tags);
		images.add(image);
		response.put("images", images);
		json.put("response", response);
		
		return json;
	}
	
	@Test
	public void testCapturesResponse() {
		
		CapturesResponse res = new CapturesResponse(this.makeTestData());
		assertEquals(206, res.getHttpStatus());
		assertEquals("122abcdez", res.getMessage());
		assertEquals(55, res.getTotal());
		for(CapturesResponseDetail detail : res.getDetails()) {
			
			assertEquals("image_id-test", detail.getId());
			assertEquals("image_url-test", detail.getImageUrl());
			assertEquals(11, detail.getImageSize());
			assertEquals("thumbnail_url-test", detail.getThumbnailUrl());
			assertEquals(22, detail.getThumbnailSize());
			assertEquals("JPG", detail.getFormat().name());
			assertEquals("source_url-test", detail.getSourceUrl());
			assertEquals(33, detail.getQuality());
			assertEquals("state-test", detail.getState());
			
			List<String> tags = new ArrayList<String>();
			tags.add("narita");
			tags.add("tarina");
			
			for(String tag : detail.getTags()) {
				assertTrue(tags.contains(tag));
			}
		}
	}
}
