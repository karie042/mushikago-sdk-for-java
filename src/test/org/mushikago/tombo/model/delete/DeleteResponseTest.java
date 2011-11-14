package org.mushikago.tombo.model.delete;

import static org.junit.Assert.*;

import net.sf.json.JSONObject;

import org.junit.Test;

public class DeleteResponseTest {
	
	public JSONObject makeTestData() {
		
		JSONObject json = new JSONObject();
		
		JSONObject meta = new JSONObject();
		meta.put("status", 206);
		meta.put("message", "122abcdez");
		json.put("meta", meta);
		
		JSONObject response = new JSONObject();
		response.put("id", "1532");
		json.put("response", response);
		System.out.println(json.toString());
		return json;
	}
	
	@Test
	public void testDeleteResponse() {
		
		DeleteResponse res = new DeleteResponse(this.makeTestData());
		assertEquals(206, res.getHttpStatus());
		assertEquals("122abcdez", res.getMessage());
		assertEquals("1532", res.getId());
	}
}
