package org.mushikago.sdk.services.mitsubachi.exceptions;

import net.sf.json.JSONObject;

import org.mushikago.sdk.common.exception.APICallException;

public class FileNotFoundException extends APICallException {

  private static final long serialVersionUID = 1L;

  public FileNotFoundException(JSONObject json) {
    super(json);
  }
}
