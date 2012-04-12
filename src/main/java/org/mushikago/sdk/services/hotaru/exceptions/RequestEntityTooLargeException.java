package org.mushikago.sdk.services.hotaru.exceptions;

import net.sf.json.JSONObject;

import org.mushikago.sdk.common.APICallException;

/**
 * リクエストが大きすぎるエラー。<br>
 * サーバが処理できないほどリクエストメッセージが巨大な場合発生するエラー
 * @author miningbrownie
 *
 */
public class RequestEntityTooLargeException extends APICallException {
  
  private static final long serialVersionUID = 1L;
  
  /**
   * 指定したJSONの内容を使用して、RequestEntityTooLargeExceptionを構築します。
   * @param json
   */
  public RequestEntityTooLargeException(JSONObject json) {
    super(json);
  }
}
