package org.mushikago.sdk.services.mitsubachi.exceptions;

import org.mushikago.sdk.common.APICallException;

import net.sf.json.JSONObject;

/**
 * 規定外の値をパラメーターに指定したことによるエラー。<br>
 * 必須項目がないことによるエラー。
 * @author miningbrownie
 *
 */
public class InvalidParameterValueException extends APICallException {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 指定したJSONの内容を使用して、InvalidParameterValueExceptionを構築します。
	 * @param json
	 */
	public InvalidParameterValueException(JSONObject json) {
		super(json);
	}
}
