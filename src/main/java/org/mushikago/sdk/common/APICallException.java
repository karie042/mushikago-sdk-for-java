package org.mushikago.sdk.common;

import net.sf.json.JSONObject;

/**
 * APIサーバから返された内容が正常では無い場合に送出されるエラーの基底クラスです。<br>
 * @author miningbrownie
 *
 */
public abstract class APICallException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 指定されたJSONObjectを使用して、APICallExceptionを構築します。<br>
	 * @param json
	 */
	public APICallException(JSONObject json) {
		
		super(json.toString());
	}
}
