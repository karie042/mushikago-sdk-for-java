package org.mushikago.sdk.services.mitsubachi.model.resource.delete;

import net.sf.json.JSONObject;

import org.mushikago.sdk.services.mitsubachi.model.MitsubachiResponse;

/**
 * リソース削除に対するレスポンス。<br>
 * @author miningbrownie
 *
 */
public class ResourceDeleteResponse extends MitsubachiResponse {
	
	/**
	 * ResourceDeleteResponseを構築します。<br>
	 * @param json APIサーバからのレスポンス
	 */
	public ResourceDeleteResponse(JSONObject json) {
		
		super(json);
	}
}
