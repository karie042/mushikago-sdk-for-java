package org.mushikago.sdk.services.mitsubachi.exceptions;

import net.sf.json.JSONObject;

import org.mushikago.sdk.common.exception.APICallException;

/**
 * 定義していないパラメーターを指定したことによるエラー。
 * 
 * @author miningbrownie
 */
public class UnknownParameterException extends APICallException {

  private static final long serialVersionUID = 1L;

  /**
   * 指定したJSONの内容を使用して、UnknownParameterExceptionを構築します。
   * 
   * @param json
   */
  public UnknownParameterException(JSONObject json) {
    super(json);
  }
}
