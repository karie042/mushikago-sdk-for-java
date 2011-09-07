package org.mushikago.tombo.model.capture;

import static org.junit.Assert.*;

import org.junit.Test;

public class CaptureResponseTest {
	
	@Test
	public void testGetSetImageUrl() {
		CaptureResponse delete = new CaptureResponse();
		assertEquals(null, delete.getImageUrl());
		String url = "abcde";
		delete.setImageUrl(url);
		assertEquals(url, delete.getImageUrl());
		url = "12345";
		delete.setImageUrl(url);
		assertEquals(url, delete.getImageUrl());
	}
	
	@Test
	public void testGetSetId() {
		CaptureResponse delete = new CaptureResponse();
		assertEquals(null, delete.getId());
		String id = "abcde";
		delete.setId(id);
		assertEquals(id, delete.getId());
		id = "12345";
		delete.setId(id);
		assertEquals(id, delete.getId());
	}
}
