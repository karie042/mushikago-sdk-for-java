package org.mushikago.sdk.common.exception;

import net.sf.json.JSONObject;

/**
 * サーバエラー。<br>
 * サーバ内部でエラーが発生し、レスポンスを返す事ができなかった場合に発生するエラー
 * 
 * @author miningbrownie
 */
public class InternalServerErrorException extends APICallException {

  private static final long serialVersionUID = 1L;

  /**
   * 指定したJSONの内容を使用して、InternalServerErrorExceptionを構築します。
   * 
   * @param json
   */
  public InternalServerErrorException(JSONObject json) {
    super(json);
  }
}
