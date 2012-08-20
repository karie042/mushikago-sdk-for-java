package org.mushikago.sdk.common.auth;

import net.sf.json.JSONObject;

import org.mushikago.sdk.common.exception.APICallException;

public class UnauthorizedException extends APICallException {

  private static final long serialVersionUID = 1L;

  public UnauthorizedException(JSONObject json) {
    super(json);
  }
}
