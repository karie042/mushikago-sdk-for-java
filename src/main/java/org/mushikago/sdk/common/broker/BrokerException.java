package org.mushikago.sdk.common.broker;

/**
 * リクエストに失敗した場合に送出される例外。<br>
 * @author miningbrownie
 *
 */
public class BrokerException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 指定された詳細メッセージを使用して、BrokerExceptionを構築します。<br>
	 * @param message 詳細メッセージ
	 */
	public BrokerException(String message) {
		super(message);
	}
}
