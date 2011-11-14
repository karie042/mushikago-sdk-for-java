package org.mushikago.tombo.model.capture;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mushikago.tombo.model.capture.CaptureRequest;
import org.mushikago.tombo.model.capture.CaptureRequest.ImageFormat;

public class CaptureRequestTest {
	
	@SuppressWarnings("deprecation")
	@Test
	public void testCaptureRequest() {
		
		String url = "http://yahoo.co.jp/?a=1,2&b=3";
		// ===============================================================================================
		// コンストラクタ系
		// ===============================================================================================
		{
			assertEquals("url=http://yahoo.co.jp/?a=1,2&b=3, fmt=JPG, quality=80, requireThumbnail=false, tags=", new CaptureRequest(url).toString());
			assertEquals("url=http://yahoo.co.jp/?a=1,2&b=3, fmt=PNG, quality=100, requireThumbnail=true, tags=narita", new CaptureRequest(url, ImageFormat.PNG, 100, true, "narita").toString());
			assertEquals("url=http://yahoo.co.jp/?a=1,2&b=3, fmt=PNG, quality=100, requireThumbnail=true, tags=narita,test,abc", new CaptureRequest(url, ImageFormat.PNG, 100, true, new String[] {"narita", "test", "abc"}).toString());
		}
		// ===============================================================================================
		// with系
		// ===============================================================================================
		{
			CaptureRequest req = new CaptureRequest("a");
			assertNotSame(req, req.withImageFormat(ImageFormat.JPG));
			assertNotSame(req, req.withReqireThumbnail(true));
			assertNotSame(req, req.withQuality(2));
			assertNotSame(req, req.withTag("1"));
			assertNotSame(req, req.withTags(new String[] {"1", "2"}));
			
			assertEquals("url=http://yahoo.co.jp/?a=1,2&b=3, fmt=JPG, quality=80, requireThumbnail=false, tags=", new CaptureRequest(url).withImageFormat(ImageFormat.JPG).toString());
			assertEquals("url=http://yahoo.co.jp/?a=1,2&b=3, fmt=JPG, quality=80, requireThumbnail=false, tags=", new CaptureRequest(url).withImageFormat(ImageFormat.JPG).toString());
			assertEquals("url=http://yahoo.co.jp/?a=1,2&b=3, fmt=PNG, quality=80, requireThumbnail=false, tags=", new CaptureRequest(url).withImageFormat(ImageFormat.PNG).toString());
			assertEquals("url=http://yahoo.co.jp/?a=1,2&b=3, fmt=PNG, quality=0, requireThumbnail=false, tags=", new CaptureRequest(url).withImageFormat(ImageFormat.PNG).withQuality(0).toString());
			assertEquals("url=http://yahoo.co.jp/?a=1,2&b=3, fmt=PNG, quality=100, requireThumbnail=false, tags=", new CaptureRequest(url).withQuality(100).withImageFormat(ImageFormat.PNG).toString());
			assertEquals("url=http://yahoo.co.jp/?a=1,2&b=3, fmt=PNG, quality=100, requireThumbnail=false, tags=", new CaptureRequest(url).withImageFormat(ImageFormat.PNG).withQuality(100).withReqireThumbnail(false).toString());
			assertEquals("url=http://yahoo.co.jp/?a=1,2&b=3, fmt=PNG, quality=100, requireThumbnail=true, tags=", new CaptureRequest(url).withReqireThumbnail(true).withImageFormat(ImageFormat.PNG).withQuality(100).toString());
			assertEquals("url=http://yahoo.co.jp/?a=1,2&b=3, fmt=PNG, quality=100, requireThumbnail=true, tags=narita", new CaptureRequest(url).withImageFormat(ImageFormat.PNG).withQuality(100).withReqireThumbnail(true).withTag("narita").toString());
			assertEquals("url=http://yahoo.co.jp/?a=1,2&b=3, fmt=PNG, quality=100, requireThumbnail=true, tags=narita", new CaptureRequest(url).withTag("narita").withImageFormat(ImageFormat.PNG).withQuality(100).withReqireThumbnail(true).toString());
			assertEquals("url=http://yahoo.co.jp/?a=1,2&b=3, fmt=PNG, quality=100, requireThumbnail=true, tags=narita,test,abc", new CaptureRequest(url).withImageFormat(ImageFormat.PNG).withQuality(100).withReqireThumbnail(true).withTags(new String[] {"narita", "test", "abc"}).toString());
			assertEquals("url=http://yahoo.co.jp/?a=1,2&b=3, fmt=PNG, quality=100, requireThumbnail=true, tags=narita,test,abc", new CaptureRequest(url).withTags(new String[] {"narita", "test", "abc"}).withImageFormat(ImageFormat.PNG).withQuality(100).withReqireThumbnail(true).toString());
		}
		// ===============================================================================================
		// set・get・add系
		// ===============================================================================================
		{
			CaptureRequest req = new CaptureRequest(url, ImageFormat.PNG, 100, true, new String[] {"narita", "test", "abc"});
			req.setUrl("testUrl");
			req.setImageFormat(ImageFormat.JPG);
			req.setQuality(55);
			req.setRequireThumbnail(false);
			req.setTags(new String[] {"RRR", "YYY"});
			
			assertEquals("testUrl", req.getUrl());
			assertEquals(ImageFormat.JPG, req.getImageFormat());
			assertEquals(55, req.getQuality());
			assertEquals(false, req.getRequireThumbnail());
			assertEquals(new String[] {"RRR", "YYY"}, req.getTags());
			
			assertEquals("url=testUrl, fmt=JPG, quality=55, requireThumbnail=false, tags=RRR,YYY", req.toString());
			
			req.setTags(new String[] {});
			assertEquals("url=testUrl, fmt=JPG, quality=55, requireThumbnail=false, tags=", req.toString());
			
			req.addTag("123");
			assertEquals("url=testUrl, fmt=JPG, quality=55, requireThumbnail=false, tags=123", req.toString());
			
			req.addTags(new String[] {});
			req.addTags(new String[] {"aaaaa", "bbbbb"});
			assertEquals("url=testUrl, fmt=JPG, quality=55, requireThumbnail=false, tags=123,aaaaa,bbbbb", req.toString());
		}
	}
}
