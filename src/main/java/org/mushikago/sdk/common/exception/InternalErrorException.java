package org.mushikago.sdk.common.exception;

import net.sf.json.JSONObject;

/**
 * サーバーエラー。<br>
 * 何らかの理由で、ファイル操作が失敗したことによるエラー
 * 
 * @author miningbrownie
 */
public class InternalErrorException extends APICallException {

  private static final long serialVersionUID = 1L;

  /**
   * 指定したJSONの内容を使用して、InternalErrorExceptionを構築します。
   * 
   * @param json
   */
  public InternalErrorException(JSONObject json) {
    super(json);
  }
}
