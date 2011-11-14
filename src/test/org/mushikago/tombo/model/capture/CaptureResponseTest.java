package org.mushikago.tombo.model.capture;

import static org.junit.Assert.*;

import net.sf.json.JSONObject;

import org.junit.Test;

public class CaptureResponseTest {
	
	public JSONObject makeTestData() {
		
		JSONObject json = new JSONObject();
		
		JSONObject meta = new JSONObject();
		meta.put("status", 206);
		meta.put("message", "122abcdez");
		json.put("meta", meta);
		
		JSONObject response = new JSONObject();
		response.put("id", "2221");
		response.put("image_url", "fffgd");
		json.put("response", response);
		
		return json;
	}
	
	@Test
	public void testCaptureResponse() {
		
		CaptureResponse delete = new CaptureResponse(this.makeTestData());
		assertEquals(206, delete.getHttpStatus());
		assertEquals("122abcdez", delete.getMessage());
		assertEquals("2221", delete.getId());
		assertEquals("fffgd", delete.getImageUrl());
	}
}
