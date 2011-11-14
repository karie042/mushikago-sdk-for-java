package org.mushikago.tombo;

import net.sf.json.JSONObject;

import org.mushikago.tombo.auth.Credentials;
import org.mushikago.tombo.model.TomboException;
import org.mushikago.tombo.model.TomboRequest;

public abstract class RequestBroker {
	
	protected final TomboAuth tomboAuth;
	
	public RequestBroker(Credentials credentials) {
		this.tomboAuth = new TomboAuth(credentials);
	}
	
	public abstract JSONObject request(TomboRequest request) throws TomboException, BrokerException;
}
