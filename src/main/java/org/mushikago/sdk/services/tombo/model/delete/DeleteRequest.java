package org.mushikago.sdk.services.tombo.model.delete;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.TreeMap;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpRequestBase;
import org.mushikago.sdk.common.RequestException;
import org.mushikago.sdk.common.auth.AuthException;
import org.mushikago.sdk.common.auth.MushikagoAuth;
import org.mushikago.sdk.common.util.ConnectInfo;
import org.mushikago.sdk.common.util.ParamUtils;
import org.mushikago.sdk.services.tombo.model.TomboRequest;

public class DeleteRequest extends TomboRequest {
	
	private String id;
	
	public DeleteRequest(String id) {
		this.id = id;
	}
	
	public String getId() { return this.id; }
	public void setId(String id) { this.id = id; }
	
	@Override
	public String toString() {
		return String.format("id=%s", this.id);
	}
	
	@Override
	public HttpRequestBase toHttpMethod(MushikagoAuth auth, ConnectInfo ci) throws RequestException {
		
		try {
			TreeMap<String, String> requestParams = new TreeMap<String, String>();
			requestParams.put("id", ParamUtils.paramEncode(this.id));
			
			String url = this.makeRequestUrl(auth, ci, "DELETE", "/1/tombo/delete.json", requestParams);
			String getParamString = ParamUtils.mapToString(requestParams);
			if("" != getParamString) { url = String.format("%s&%s", url, getParamString); }
			System.out.println(url);
			return new HttpDelete(url);
		}
		catch(UnsupportedEncodingException e) { throw new RequestException(e.getMessage()); }
		catch(AuthException e) { throw new RequestException(e.getMessage()); }
	}
}
