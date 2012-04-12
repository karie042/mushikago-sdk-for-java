package org.mushikago.sdk.services.mitsubachi.model.http.push;

import net.sf.json.JSONObject;

import org.mushikago.sdk.services.mitsubachi.model.MitsubachiResponse;

/**
 * HttpPushのレスポンス。<br>
 * @author miningbrownie
 */
public class HttpPushResponse extends MitsubachiResponse {
	
	/**
	 * HttpPushResponseを構築します。<br>
	 * @param json APIサーバからのレスポンス。<br>
	 */
	public HttpPushResponse(JSONObject json) {
		super(json);
	}
}
