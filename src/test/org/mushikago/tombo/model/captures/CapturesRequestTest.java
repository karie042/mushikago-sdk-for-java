package org.mushikago.tombo.model.captures;

import static org.junit.Assert.*;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.Test;
import org.mushikago.tombo.model.capture.CaptureRequest;
import org.mushikago.tombo.model.capture.CaptureRequest.ImageFormat;

public class CapturesRequestTest {
	
	@Test
	public void testCapturesRequest() {
		// ===============================================================================================
		// コンストラクタ系
		// ===============================================================================================
		{
			assertEquals("id=null, limit=10, offset=0, domain=null, tag=null", new CapturesRequest().toString());
			assertEquals("id=abcde, limit=90, offset=50, domain=test, tag=tagtag", new CapturesRequest("abcde", 90, 50, "test", "tagtag").toString());
		}
		
		// ===============================================================================================
		// with系
		// ===============================================================================================
		{
			CapturesRequest req = new CapturesRequest();
			assertNotSame(req, req.withId("1"));
			assertNotSame(req, req.withLimit(1));
			assertNotSame(req, req.withOffset(1));
			assertNotSame(req, req.withDomain("dom"));
			assertNotSame(req, req.withTag("tag"));
			
			assertEquals("id=abcde, limit=10, offset=0, domain=null, tag=null", new CapturesRequest().withId("abcde").toString());
			
			assertEquals("id=abcde, limit=90, offset=0, domain=null, tag=null", new CapturesRequest().withId("abcde").withLimit(90).toString());
			assertEquals("id=abcde, limit=90, offset=0, domain=null, tag=null", new CapturesRequest().withLimit(90).withId("abcde").toString());
			
			assertEquals("id=abcde, limit=90, offset=50, domain=null, tag=null", new CapturesRequest().withId("abcde").withLimit(90).withOffset(50).toString());
			assertEquals("id=abcde, limit=90, offset=50, domain=null, tag=null", new CapturesRequest().withOffset(50).withId("abcde").withLimit(90).toString());
			
			assertEquals("id=abcde, limit=90, offset=50, domain=test, tag=null", new CapturesRequest().withId("abcde").withLimit(90).withOffset(50).withDomain("test").toString());
			assertEquals("id=abcde, limit=90, offset=50, domain=test, tag=null", new CapturesRequest().withDomain("test").withId("abcde").withLimit(90).withOffset(50).toString());
			
			assertEquals("id=abcde, limit=90, offset=50, domain=test, tag=tagtag", new CapturesRequest().withId("abcde").withLimit(90).withOffset(50).withDomain("test").withTag("tagtag").toString());
			assertEquals("id=abcde, limit=90, offset=50, domain=test, tag=tagtag", new CapturesRequest().withTag("tagtag").withId("abcde").withLimit(90).withOffset(50).withDomain("test").toString());
		}
		
		// ===============================================================================================
		// set・get・add系
		// ===============================================================================================
		{
			CapturesRequest req = new CapturesRequest();
			req.setId("a");
			req.setLimit(1);
			req.setOffset(2);
			req.setDomain("b");
			req.setTag("c");
			
			assertEquals("id=a, limit=1, offset=2, domain=b, tag=c", req.toString());
		}
	}
}
