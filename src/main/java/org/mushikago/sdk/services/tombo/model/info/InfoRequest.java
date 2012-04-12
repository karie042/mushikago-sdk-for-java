package org.mushikago.sdk.services.tombo.model.info;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.TreeMap;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.mushikago.sdk.common.RequestException;
import org.mushikago.sdk.common.auth.AuthException;
import org.mushikago.sdk.common.auth.MushikagoAuth;
import org.mushikago.sdk.common.util.ConnectInfo;
import org.mushikago.sdk.common.util.ParamUtils;
import org.mushikago.sdk.services.tombo.model.TomboRequest;

public class InfoRequest extends TomboRequest {
	
	@Override
	public HttpRequestBase toHttpMethod(MushikagoAuth auth, ConnectInfo ci) throws RequestException {
		
		try {
			TreeMap<String, String> requestParams = new TreeMap<String, String>();
			
			String url = this.makeRequestUrl(auth, ci, "GET", "/1/tombo/info.json", requestParams);
			String getParamString = ParamUtils.mapToString(requestParams);
			if("" != getParamString) { url = String.format("%s&%s", url, getParamString); }
			return new HttpGet(url);
		}
		catch(UnsupportedEncodingException e) { throw new RequestException(e.getMessage()); }
		catch(AuthException e) { throw new RequestException(e.getMessage()); }
	}
}
