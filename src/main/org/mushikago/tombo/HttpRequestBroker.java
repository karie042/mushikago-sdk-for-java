package org.mushikago.tombo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.TreeMap;

import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.mushikago.tombo.auth.Credentials;
import org.mushikago.tombo.model.TomboException;
import org.mushikago.tombo.model.TomboRequest;
import org.mushikago.tombo.model.captures.CapturesException;

public class HttpRequestBroker extends RequestBroker {
	
	public HttpRequestBroker(Credentials credentials) {
		
		super(credentials);
	}
	
	public JSONObject request(TomboRequest request) throws TomboException, BrokerException {
		
		HttpRequestBase method = request.toHttpMethod(this.tomboAuth);
		
		try {
			HttpClient http = new DefaultHttpClient();
			HttpResponse res = http.execute(method);
			String str = EntityUtils.toString(res.getEntity());
			JSONObject json = JSONObject.fromObject(str);
			
			return json;
		}
		catch(ClientProtocolException e){ throw new BrokerException(e.getMessage()); }
		catch(IOException e) { throw new BrokerException(e.getMessage()); }
	}
}
