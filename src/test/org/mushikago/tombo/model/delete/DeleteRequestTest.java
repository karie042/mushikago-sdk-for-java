package org.mushikago.tombo.model.delete;

import static org.junit.Assert.*;

import org.junit.Test;

public class DeleteRequestTest {
	
	@Test
	public void testDeleteRequest() {
		String id = "abcde";
		DeleteRequest request = new DeleteRequest(id);
		assertEquals(String.format("id=%s", id), request.toString());
	}
	
	@Test
	public void testGetId() {
		String id = "abcde";
		DeleteRequest request = new DeleteRequest(id);
		assertEquals(id, request.getId());
	}
	
	@Test
	public void testSetId() {
		String id = "abcde";
		DeleteRequest request = new DeleteRequest("12345");
		request.setId(id);
		assertEquals(id, request.getId());
	}
}
