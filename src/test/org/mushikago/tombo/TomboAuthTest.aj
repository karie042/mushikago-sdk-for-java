package org.mushikago.tombo;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.TreeMap;

import org.junit.Test;
import org.mushikago.tombo.ParamUtils;
import org.mushikago.tombo.TomboAuth;
import org.mushikago.tombo.auth.Credentials;

public class TomboAuthTest {

	@Test
	public void testMakeRequestUrl() throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {
		
		Credentials credentials = new Credentials("hogehoge", "samplefugafuga");
		TomboAuth auth = new TomboAuth(credentials);
		TreeMap<String, String> params = new TreeMap<String, String>();
		params.put("url", ParamUtils.paramEncode("http://miningbrownie.co.jp/"));
		params.put("tag", ParamUtils.paramEncode("tokyo,japan"));
		String url = auth.makeRequestUrl("POST", "/1/capture.json", params);
		System.out.println(url);
		assertEquals("http://tombo.mushikago.org/1/capture.json?api_key=hogehoge&timestamp=2011-08-11T17%3A09%3A14Z&signature=HJUyXAldzzOTCqWgwJQlxc75NEsF17cUtFrqQ46wBRE%3D", url);
	}
	
	private static aspect impl {
		String around() :
			cflow(execution(void TomboAuthTest.testMakeRequestUrl())) &&
			call( String TomboAuth.makeTimeStamp() ) {
			return "2011-08-11T17:09:14Z";
		}
	}
}
