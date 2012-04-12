package org.mushikago.sdk.services.tombo;

import net.sf.json.JSONObject;

import org.mushikago.sdk.common.APICallException;
import org.mushikago.sdk.common.MushikagoClient;
import org.mushikago.sdk.common.RequestException;
import org.mushikago.sdk.common.auth.Credentials;
import org.mushikago.sdk.common.auth.UnauthorizedException;
import org.mushikago.sdk.common.broker.BrokerException;
import org.mushikago.sdk.services.tombo.exceptions.InternalErrorException;
import org.mushikago.sdk.services.tombo.exceptions.InvalidParameterException;
import org.mushikago.sdk.services.tombo.model.capture.CaptureRequest;
import org.mushikago.sdk.services.tombo.model.capture.CaptureResponse;
import org.mushikago.sdk.services.tombo.model.captures.CapturesRequest;
import org.mushikago.sdk.services.tombo.model.captures.CapturesResponse;
import org.mushikago.sdk.services.tombo.model.delete.DeleteRequest;
import org.mushikago.sdk.services.tombo.model.delete.DeleteResponse;
import org.mushikago.sdk.services.tombo.model.info.InfoRequest;
import org.mushikago.sdk.services.tombo.model.info.InfoResponse;

public class TomboClient extends MushikagoClient {
	
	public TomboClient(Credentials credentials) {
		
		super(credentials, "api.mushikago.org");
	}
	
	@Override
	protected void checkResponse(JSONObject json) throws APICallException {
		
		final int status = json.getJSONObject("meta").getInt("status");
		switch(status) {
		case 401: throw new UnauthorizedException(json);
		case 403: throw new InvalidParameterException(json);
		case 500: throw new InternalErrorException(json);
		}
	}
	
	public InfoResponse info(InfoRequest request) throws RequestException, BrokerException, APICallException {
		
		JSONObject json = this.broker.request(request, this.ci);
		this.checkResponse(json);
		return new InfoResponse(json);
	}
	
	public DeleteResponse delete(DeleteRequest request) throws RequestException, BrokerException, APICallException {
		
		JSONObject json = this.broker.request(request, this.ci);
		this.checkResponse(json);
		return new DeleteResponse(json);
	}
	
	public CapturesResponse captures(CapturesRequest request) throws RequestException, BrokerException, APICallException {
		
		JSONObject json = this.broker.request(request, this.ci);
		this.checkResponse(json);
		return new CapturesResponse(json);
	}
	
	public CaptureResponse capture(CaptureRequest request) throws RequestException, BrokerException, APICallException {
		
		JSONObject json = this.broker.request(request, this.ci);
		this.checkResponse(json);
		return new CaptureResponse(json);
	}
}
