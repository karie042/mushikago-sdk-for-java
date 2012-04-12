package org.mushikago.sdk.services.hotaru.exceptions;

import net.sf.json.JSONObject;

import org.mushikago.sdk.common.APICallException;

/**
 * リクエスト構文エラー。<br>
 * リクエストの構文が間違っている場合に発生します。
 * @author miningbrownie
 *
 */
public class BadRequestException extends APICallException {
  
  private static final long serialVersionUID = 1L;
  
  /**
   * 指定したJSONの内容を使用して、BadRequestExceptionを構築します。
   * @param json
   */
  public BadRequestException(JSONObject json) {
    super(json);
  }
}
