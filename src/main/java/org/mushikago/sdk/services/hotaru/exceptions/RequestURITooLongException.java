package org.mushikago.sdk.services.hotaru.exceptions;

import net.sf.json.JSONObject;

import org.mushikago.sdk.common.APICallException;

/**
 * URIが長すぎるエラー。<br>
 * サーバが処理できないほどURIが長すぎる場合に発生するエラー
 * @author miningbrownie
 *
 */
public class RequestURITooLongException extends APICallException {
  
  private static final long serialVersionUID = 1L;
  
  /**
   * 指定したJSONの内容を使用して、RequestURITooLongExceptionを構築します。
   * @param json
   */
  public RequestURITooLongException(JSONObject json) {
    super(json);
  }
}
