package org.mushikago.tombo.model.info;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.TreeMap;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.mushikago.tombo.ParamUtils;
import org.mushikago.tombo.TomboAuth;
import org.mushikago.tombo.model.TomboException;
import org.mushikago.tombo.model.TomboRequest;

public class InfoRequest extends TomboRequest {
	
	@Override
	public HttpRequestBase toHttpMethod(TomboAuth auth) throws TomboException {
		
		try {
			TreeMap<String, String> requestParams = new TreeMap<String, String>();
			
			String url = this.makeRequestUrl(auth, "GET", "/1/info.json", requestParams);
			String getParamString = ParamUtils.mapToString(requestParams);
			if("" != getParamString) { url = String.format("%s&%s", url, getParamString); }
			System.out.println(url);
			return new HttpGet(url);
		}
		catch(UnsupportedEncodingException e) { throw new InfoException(e.getMessage()); }
		catch(InvalidKeyException e) { throw new InfoException(e.getMessage()); }
		catch(NoSuchAlgorithmException e) { throw new InfoException(e.getMessage()); }
	}
}
