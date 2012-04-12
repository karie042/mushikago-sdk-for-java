package org.mushikago.sdk.services.mitsubachi.exceptions;

import net.sf.json.JSONObject;

import org.mushikago.sdk.common.APICallException;

public class InternalServerErrorException extends APICallException {
	
	private static final long serialVersionUID = 1L;
	
	public InternalServerErrorException(JSONObject json) {
		super(json);
	}
}
