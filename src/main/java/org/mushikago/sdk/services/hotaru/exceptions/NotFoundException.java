package org.mushikago.sdk.services.hotaru.exceptions;

import net.sf.json.JSONObject;

import org.mushikago.sdk.common.APICallException;

/**
 * リソースが見つからないエラー。<br>
 * 指定したリソースが存在しない場合に発生するエラー
 * @author miningbrownie
 *
 */
public class NotFoundException extends APICallException {
  
  private static final long serialVersionUID = 1L;
  
  /**
   * 指定したJSONの内容を使用して、NotFoundExceptionを構築します。
   * @param json
   */
  public NotFoundException(JSONObject json) {
    super(json);
  }
}
