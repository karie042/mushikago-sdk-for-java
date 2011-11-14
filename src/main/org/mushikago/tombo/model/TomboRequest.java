package org.mushikago.tombo.model;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.TreeMap;

import org.apache.http.client.methods.HttpRequestBase;
import org.mushikago.tombo.ParamUtils;
import org.mushikago.tombo.TomboAuth;

public abstract class TomboRequest {
	
	protected String endpoint = "tombo.mushikago.org";
	
	protected String makeRequestUrl(TomboAuth auth, String httpMethod, String requestPath, TreeMap<String, String> requestParams) throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException {
		
		TreeMap<String, String> _requestParams = new TreeMap<String, String>();
		_requestParams.putAll(requestParams);
		
		String apiKey = ParamUtils.paramEncode(auth.getApiKey());
		String timestamp = ParamUtils.paramEncode(auth.makeTimeStamp());
		
		_requestParams.put("api_key",   apiKey);
		_requestParams.put("timestamp", timestamp);
		
		String signature = auth.toSignature(httpMethod, this.endpoint, requestPath, _requestParams);
		
		return String.format("http://%s%s?api_key=%s&timestamp=%s&signature=%s", this.endpoint, requestPath, apiKey, timestamp, signature);
	}
	
	public abstract HttpRequestBase toHttpMethod(TomboAuth auth) throws TomboException;
}
