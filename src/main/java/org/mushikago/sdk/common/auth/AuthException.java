package org.mushikago.sdk.common.auth;

/**
 * 認証系の操作でエラーが発生した際に作成される例外。<br>
 * @author miningbrownie
 *
 */
public class AuthException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 指定された詳細メッセージを使用して、AuthExceptionを構築します。<br>
	 * @param message 詳細メッセージ
	 */
	public AuthException(String message) {
		
		super(message);
	}
}
