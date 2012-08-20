package org.mushikago.sdk.services.mitsubachi.exceptions;

import net.sf.json.JSONObject;

import org.mushikago.sdk.common.exception.APICallException;

public class ProjectAlreadyExistsException extends APICallException {

  private static final long serialVersionUID = 1L;

  public ProjectAlreadyExistsException(JSONObject json) {
    super(json);
  }
}
