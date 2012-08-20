package org.mushikago.sdk.common.exception;

import net.sf.json.JSONObject;

/**
 * 操作が拒否されるエラー。<br>
 * サーバはリクエストを理解したが、そのリクエストの実行を拒否した際に発生するエラー
 * @author miningbrownie
 *
 */
public class ForbiddenException extends APICallException {
  
  private static final long serialVersionUID = 1L;
  
  /**
   * 指定したJSONの内容を使用して、ForbiddenExceptionを構築します。
   * @param json
   */
  public ForbiddenException(JSONObject json) {
    super(json);
  }
}
