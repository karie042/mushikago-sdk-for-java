package org.mushikago.tombo;

import net.sf.json.JSONObject;

import org.mushikago.tombo.auth.Credentials;
import org.mushikago.tombo.model.TomboException;
import org.mushikago.tombo.model.capture.CaptureException;
import org.mushikago.tombo.model.capture.CaptureRequest;
import org.mushikago.tombo.model.capture.CaptureResponse;
import org.mushikago.tombo.model.captures.CapturesException;
import org.mushikago.tombo.model.captures.CapturesRequest;
import org.mushikago.tombo.model.captures.CapturesResponse;
import org.mushikago.tombo.model.delete.DeleteException;
import org.mushikago.tombo.model.delete.DeleteRequest;
import org.mushikago.tombo.model.delete.DeleteResponse;
import org.mushikago.tombo.model.info.InfoException;
import org.mushikago.tombo.model.info.InfoRequest;
import org.mushikago.tombo.model.info.InfoResponse;

public class TomboClient {
	
	private final HttpRequestBroker broker;
	
	public TomboClient(Credentials credentials) {
		this.broker = new HttpRequestBroker(credentials);
	}
	
	public InfoResponse info(InfoRequest request) throws InfoException {
		
		
		try {
			JSONObject json = this.broker.request(request);
			return new InfoResponse(json);
		}
		catch(TomboException e)  { throw new InfoException(e.getMessage()); }
		catch(BrokerException e) { throw new InfoException(e.getMessage()); }
	}
	
	public DeleteResponse delete(DeleteRequest request) throws DeleteException {
		
		try {
			JSONObject json = this.broker.request(request);
			return new DeleteResponse(json);
		}
		catch(TomboException e)  { throw new DeleteException(e.getMessage()); }
		catch(BrokerException e) { throw new DeleteException(e.getMessage()); }
	}
	
	public CapturesResponse captures(CapturesRequest request) throws CapturesException {
		
		
		try {
			JSONObject json = this.broker.request(request);
			return new CapturesResponse(json);
		}
		catch(TomboException e)  { throw new CapturesException(e.getMessage()); }
		catch(BrokerException e) { throw new CapturesException(e.getMessage()); }
	}
	
	public CaptureResponse capture(CaptureRequest request) throws CaptureException {
		
		try {
			JSONObject json = this.broker.request(request);
			return new CaptureResponse(json);
		}
		catch(TomboException e)  { throw new CaptureException(e.getMessage()); }
		catch(BrokerException e) { throw new CaptureException(e.getMessage()); }
	}
}
