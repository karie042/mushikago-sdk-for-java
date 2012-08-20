package org.mushikago.sdk.services.tombo.exceptions;

import net.sf.json.JSONObject;

import org.mushikago.sdk.common.exception.APICallException;

public class InternalErrorException extends APICallException {

  private static final long serialVersionUID = 1L;

  public InternalErrorException(JSONObject json) {
    super(json);
  }
}
