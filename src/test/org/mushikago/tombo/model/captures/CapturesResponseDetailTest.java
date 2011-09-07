package org.mushikago.tombo.model.captures;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mushikago.tombo.model.capture.CaptureRequest.ImageFormat;

public class CapturesResponseDetailTest {

	@Test
	public void testGetSetId() {
		CapturesResponseDetail detail = new CapturesResponseDetail();
		assertEquals(null, detail.getId());
		String id = "abcde";
		detail.setId(id);
		assertEquals(id, detail.getId());
		id = "12345";
		detail.setId(id);
		assertEquals(id, detail.getId());
	}

	@Test
	public void testGetSetImageUrl() {
		CapturesResponseDetail detail = new CapturesResponseDetail();
		assertEquals(null, detail.getImageUrl());
		String url = "abcde";
		detail.setImageUrl(url);
		assertEquals(url, detail.getImageUrl());
		url = "12345";
		detail.setImageUrl(url);
		assertEquals(url, detail.getImageUrl());
	}

	@Test
	public void testGetSetImageSize() {
		CapturesResponseDetail detail = new CapturesResponseDetail();
		assertEquals(-1, detail.getImageSize());
		long size = 15;
		detail.setImageSize(size);
		assertEquals(size, detail.getImageSize());
		size = 110;
		detail.setImageSize(size);
		assertEquals(size, detail.getImageSize());
	}

	@Test
	public void testGetSetThumbnailUrl() {
		CapturesResponseDetail detail = new CapturesResponseDetail();
		assertEquals(null, detail.getThumbnailUrl());
		String url = "abcde";
		detail.setThumbnailUrl(url);
		assertEquals(url, detail.getThumbnailUrl());
		url = "12345";
		detail.setThumbnailUrl(url);
		assertEquals(url, detail.getThumbnailUrl());
	}

	@Test
	public void testGetSetThumbnailSize() {
		CapturesResponseDetail detail = new CapturesResponseDetail();
		assertEquals(-1, detail.getThumbnailSize());
		long size = 15;
		detail.setThumbnailSize(size);
		assertEquals(size, detail.getThumbnailSize());
		size = 110;
		detail.setThumbnailSize(size);
		assertEquals(size, detail.getThumbnailSize());
	}

	@Test
	public void testGetSetSourceUrl() {
		CapturesResponseDetail detail = new CapturesResponseDetail();
		assertEquals(null, detail.getSourceUrl());
		String url = "abcde";
		detail.setSourceUrl(url);
		assertEquals(url, detail.getSourceUrl());
		url = "12345";
		detail.setSourceUrl(url);
		assertEquals(url, detail.getSourceUrl());
	}

	@Test
	public void testGetSetFormat() {
		CapturesResponseDetail detail = new CapturesResponseDetail();
		assertEquals(null, detail.getFormat());
		ImageFormat fmt = ImageFormat.JPG;
		detail.setFormat(fmt);
		assertEquals(fmt, detail.getFormat());
		fmt = ImageFormat.PNG;
		detail.setFormat(fmt);
		assertEquals(fmt, detail.getFormat());
	}

	@Test
	public void testGetSetQuality() {
		CapturesResponseDetail detail = new CapturesResponseDetail();
		assertEquals(-1, detail.getQuality());
		int quality = 15;
		detail.setQuality(quality);
		assertEquals(quality, detail.getQuality());
		quality = 110;
		detail.setQuality(quality);
		assertEquals(quality, detail.getQuality());
	}

	@Test
	public void testGetState() {
		CapturesResponseDetail detail = new CapturesResponseDetail();
		assertEquals(null, detail.getSourceUrl());
		String state = "abcde";
		detail.setState(state);
		assertEquals(state, detail.getState());
		state = "12345";
		detail.setState(state);
		assertEquals(state, detail.getState());
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testGetTags() {
		CapturesResponseDetail detail = new CapturesResponseDetail();
		assertEquals(null, detail.getTags());
		String[] tags = new String[] {"abcde"};
		detail.setTags(tags);
		assertEquals(tags, detail.getTags());
		tags = new String[] {"12345", "98765"};
		detail.setTags(tags);
		assertEquals(tags, detail.getTags());
	}
}
