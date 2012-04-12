package org.mushikago.sdk.services.mitsubachi.model;

import net.sf.json.JSONObject;

import org.mushikago.sdk.common.model.MushikagoResponse;

/**
 * mitsubachiAPIのレスポンスに対する基底クラス。<br>
 * @author miningbrownie
 */
public class MitsubachiResponse extends MushikagoResponse {
	
	/**
	 * MitsubachiResponseを構築します。<br>
	 * @param json APIサーバからのレスポンス。<br>
	 */
	public MitsubachiResponse(JSONObject json) {
		super(json);
	}
}
