package org.mushikago.sdk.common.exception;

import net.sf.json.JSONObject;

/**
 * リクエストとリソースの矛盾によるエラー。<br>
 * リクエストが要求したリソースに対する操作が、リソースの現在の状態と矛盾している
 * @author miningbrownie
 *
 */
public class ConflictException extends APICallException {
  
  private static final long serialVersionUID = 1L;
  
  /**
   * 指定したJSONの内容を使用して、ConflictExceptionを構築します。
   * @param json
   */
  public ConflictException(JSONObject json) {
    super(json);
  }
}
