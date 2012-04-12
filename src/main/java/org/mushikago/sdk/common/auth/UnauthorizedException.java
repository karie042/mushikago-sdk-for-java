package org.mushikago.sdk.common.auth;

import org.mushikago.sdk.common.APICallException;

import net.sf.json.JSONObject;

public class UnauthorizedException extends APICallException {
	
	private static final long serialVersionUID = 1L;
	
	public UnauthorizedException(JSONObject json) {
		super(json);
	}
}
