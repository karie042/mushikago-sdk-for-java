package org.mushikago.tombo.model.delete;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.TreeMap;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpRequestBase;
import org.mushikago.tombo.ParamUtils;
import org.mushikago.tombo.TomboAuth;
import org.mushikago.tombo.model.TomboException;
import org.mushikago.tombo.model.TomboRequest;

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
	public HttpRequestBase toHttpMethod(TomboAuth auth) throws TomboException {
		
		try {
			TreeMap<String, String> requestParams = new TreeMap<String, String>();
			requestParams.put("id", ParamUtils.paramEncode(this.id));
			
			String url = this.makeRequestUrl(auth, "DELETE", "/1/delete.json", requestParams);
			String getParamString = ParamUtils.mapToString(requestParams);
			if("" != getParamString) { url = String.format("%s&%s", url, getParamString); }
			System.out.println(url);
			return new HttpDelete(url);
		}
		catch(UnsupportedEncodingException e) { throw new DeleteException(e.getMessage()); }
		catch(InvalidKeyException e) { throw new DeleteException(e.getMessage()); }
		catch(NoSuchAlgorithmException e) { throw new DeleteException(e.getMessage()); }
	}
}
