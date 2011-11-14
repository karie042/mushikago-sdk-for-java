package org.mushikago.tombo;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.ClientProtocolException;
import org.junit.Test;
import org.mushikago.tombo.auth.Credentials;
import org.mushikago.tombo.model.capture.CaptureException;
import org.mushikago.tombo.model.capture.CaptureRequest;
import org.mushikago.tombo.model.capture.CaptureResponse;
import org.mushikago.tombo.model.capture.CaptureRequest.ImageFormat;
import org.mushikago.tombo.model.captures.CapturesException;
import org.mushikago.tombo.model.captures.CapturesRequest;
import org.mushikago.tombo.model.captures.CapturesResponse;
import org.mushikago.tombo.model.captures.CapturesResponseDetail;
import org.mushikago.tombo.model.delete.DeleteException;
import org.mushikago.tombo.model.delete.DeleteRequest;
import org.mushikago.tombo.model.delete.DeleteResponse;
import org.mushikago.tombo.model.info.InfoException;
import org.mushikago.tombo.model.info.InfoRequest;
import org.mushikago.tombo.model.info.InfoResponse;
import org.mushikago.tombo.model.TomboRequest;

public class TomboClientTest {
	
	@Test
	public void testInfo() throws InfoException {
		
		Credentials credentials = new Credentials("AKIAI22ECC2PRTBZ3XSA", "jugemujugemugogounosurikire");
		TomboClient tombo = new TomboClient(credentials);
		
		InfoRequest info = new InfoRequest();
		InfoResponse infoRes = tombo.info(info);
		
		assertEquals(207, infoRes.getHttpStatus());
		assertEquals("abcdez", infoRes.getMessage());
		assertEquals(15, infoRes.getImageNum());
		assertEquals(9, infoRes.getApiCount());
		assertEquals(125, infoRes.getDiskUsage());
	}
	
	@Test
	public void testDelete() throws DeleteException {
		
		Credentials credentials = new Credentials("AKIAI22ECC2PRTBZ3XSA", "jugemujugemugogounosurikire");
		TomboClient tombo = new TomboClient(credentials);
		
		DeleteRequest delete = new DeleteRequest("4a1eb2d4-13df-43c7-88ea-9cbfe9ac6547");
		DeleteResponse deleteRes = tombo.delete(delete);
		
		assertEquals(206, deleteRes.getHttpStatus());
		assertEquals("122abcdez", deleteRes.getMessage());
		assertEquals("1532", deleteRes.getId());
	}
	
	@Test
	public void testCaptures() throws CapturesException {
		
		Credentials credentials = new Credentials("AKIAI22ECC2PRTBZ3XSA", "jugemujugemugogounosurikire");
		TomboClient tombo = new TomboClient(credentials);
		
		CapturesRequest captures = new CapturesRequest();
		CapturesResponse capturesRes = tombo.captures(captures);
		assertEquals(206, capturesRes.getHttpStatus());
		assertEquals("122abcdez", capturesRes.getMessage());
		assertEquals(55, capturesRes.getTotal());
		for(CapturesResponseDetail detail : capturesRes.getDetails()) {
			
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
	
	@Test
	public void testCapture() throws CaptureException {
		
		Credentials credentials = new Credentials("AKIAI22ECC2PRTBZ3XSA", "jugemujugemugogounosurikire");
		TomboClient tombo = new TomboClient(credentials);
		
		CaptureRequest capture = new CaptureRequest("http://yahoo.co.jp/").withReqireThumbnail(true).withQuality(10).withTags(new String[] {"narita", "aaa"});
		CaptureResponse response = tombo.capture(capture);
		System.out.println(response.getHttpStatus());
		System.out.println(response.getImageUrl());
		System.out.println(response.getId());
		System.out.println(response.getMessage());
	}
	
	private static aspect impl {
		JSONObject around() :
			cflow(execution(void TomboClientTest.testInfo())) &&
			call(JSONObject HttpRequestBroker.request(TomboRequest)) {
			
			JSONObject json = new JSONObject();
			
			JSONObject meta = new JSONObject();
			meta.put("status", 207);
			meta.put("message", "abcdez");
			json.put("meta", meta);
			
			JSONObject response = new JSONObject();
			response.put("image_num", "15");
			response.put("disk_usage", "125");
			response.put("api_count", "9");
			json.put("response", response);
			System.out.println(json.toString());
			return json;
		}
		
		JSONObject around() :
			cflow(execution(void TomboClientTest.testDelete())) &&
			call(JSONObject HttpRequestBroker.request(TomboRequest)) {
			
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
		
		JSONObject around() :
			cflow(execution(void TomboClientTest.testCapture())) &&
			call(JSONObject HttpRequestBroker.request(TomboRequest)) {
			
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
		
		JSONObject around() :
			cflow(execution(void TomboClientTest.testCaptures())) &&
			call(JSONObject HttpRequestBroker.request(TomboRequest)) {
			
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
	}
}
