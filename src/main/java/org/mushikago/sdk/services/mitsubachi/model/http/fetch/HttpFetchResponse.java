package org.mushikago.sdk.services.mitsubachi.model.http.fetch;

import net.sf.json.JSONObject;

import org.mushikago.sdk.services.mitsubachi.model.MitsubachiResponse;

/**
 * HttpFetchのレスポンス。<br>
 * @author miningbrownie
 */
public class HttpFetchResponse extends MitsubachiResponse {
	
	/**
	 * HttpFetchResponseを構築します。<br>
	 * @param json APIサーバからのレスポンス。<br>
	 */
	public HttpFetchResponse(JSONObject json) {
		super(json);
	}
}
