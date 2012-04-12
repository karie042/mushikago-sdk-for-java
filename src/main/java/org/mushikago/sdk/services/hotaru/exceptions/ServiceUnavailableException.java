package org.mushikago.sdk.services.hotaru.exceptions;

import net.sf.json.JSONObject;

import org.mushikago.sdk.common.APICallException;

/**
 * リクエストを受付できないエラー。<br>
 * サーバのメンテナンス等でサービスが停止している場合に発生するエラー
 * @author miningbrownie
 *
 */
public class ServiceUnavailableException extends APICallException {
  
  private static final long serialVersionUID = 1L;
  
  /**
   * 指定したJSONの内容を使用して、ServiceUnavailableExceptionを構築します。
   * @param json
   */
  public ServiceUnavailableException(JSONObject json) {
    super(json);
  }
}
