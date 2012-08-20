package org.mushikago.sdk.common;

/**
 * リクエスト関係の例外です。<br>
 * 
 * @author miningbrownie
 */
public class RequestException extends Exception {

  private static final long serialVersionUID = 1L;

  /**
   * 指定された詳細メッセージを使用して、RequestExceptionを構築します。
   * 
   * @param msg
   *          詳細メッセージ
   */
  public RequestException(String msg) {
    super(msg);
  }

  public RequestException(Throwable t) {
    super(t);
  }
}
