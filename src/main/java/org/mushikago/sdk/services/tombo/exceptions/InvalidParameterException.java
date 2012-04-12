package org.mushikago.sdk.services.tombo.exceptions;

import net.sf.json.JSONObject;

import org.mushikago.sdk.common.APICallException;

public class InvalidParameterException extends APICallException {
	
	private static final long serialVersionUID = 1L;
	
	public InvalidParameterException(JSONObject json) {
		super(json);
	}
}
