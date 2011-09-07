package org.mushikago.tombo;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

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

public class RealTest {
	
	@Test
	public void testReal() throws CaptureException, CapturesException, InfoException, DeleteException, InterruptedException {
		
		Credentials credentials = new Credentials("AKIAI22ECC2PRTBZ3XSA", "jugemujugemugogounosurikire");
		TomboClient tombo = new TomboClient(credentials);
		
		CaptureRequest captureReq = null;
		CaptureResponse captureRes = null;
		
		CapturesRequest capturesReq = null;
		CapturesResponse capturesRes = null;
		
		InfoRequest infoReq = null;
		InfoResponse infoRes = null;
		
		DeleteRequest deleteReq = null;
		DeleteResponse deleteRes = null;
		
//		captureReq = new CaptureRequest("http://yahoo.co.jp", ImageFormat.PNG, 65, true, new String[] {"test-1", "test-2"});
		captureReq = new CaptureRequest("http://yahoo.co.jp", ImageFormat.PNG, 65, true, new String[] {"test-1", "test-2"});
		captureRes = tombo.capture(captureReq);
		String id = captureRes.getId();
		System.out.println(id);
		capturesReq = new CapturesRequest(id);
		capturesRes = tombo.captures(capturesReq);
		CapturesResponseDetail[] details = capturesRes.getDetails();
		assertEquals(1, details.length);
		assertEquals(captureReq.getUrl(), details[0].getSourceUrl());
		assertEquals(captureReq.getImageFormat(), details[0].getFormat());
		assertEquals(captureReq.getQuality(), details[0].getQuality());
		
		if(null != details[0].getThumbnailUrl()) { assertTrue(captureReq.getRequireThumbnail()); }
		if(0 < details[0].getThumbnailSize()) { assertTrue(captureReq.getRequireThumbnail()); }
		
		List<String> tags = Arrays.asList(details[0].getTags());
		for(String tag : captureReq.getTags()) { assertTrue(tags.contains(tag)); }
		
		infoReq = new InfoRequest();
		infoRes = tombo.info(infoReq);
		
		deleteReq = new DeleteRequest(id);
		deleteRes = tombo.delete(deleteReq);
		
	}
}
