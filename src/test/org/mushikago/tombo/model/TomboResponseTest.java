package org.mushikago.tombo.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class TomboResponseTest {

	@Test
	public void testGetSetHttpStatus() {
		TomboResponse response = new TomboResponse();
		assertEquals(-1, response.getHttpStatus());
		int status = 10;
		response.setHttpStatus(status);
		assertEquals(status, response.getHttpStatus());
		status = 30;
		response.setHttpStatus(status);
		assertEquals(status, response.getHttpStatus());
	}
	
	@Test
	public void testGetSetMessage() {
		TomboResponse response = new TomboResponse();
		assertEquals(null, response.getMessage());
		String msg = "abcde";
		response.setMessage(msg);
		assertEquals(msg, response.getMessage());
		msg = "12345";
		response.setMessage(msg);
		assertEquals(msg, response.getMessage());
	}
}
