package org.mushikago.sdk.services.mitsubachi.model.resource.store;

import net.sf.json.JSONObject;

import org.mushikago.sdk.services.mitsubachi.model.MitsubachiResponse;

/**
 * リソースストアに対するレスポンス。<br>
 * @author miningbrownie
 *
 */
public class ResourceStoreResponse extends MitsubachiResponse {
	
	/**
	 * ResourceStoreResponseを構築します。<br>
	 * @param json APIサーバからのレスポンス。<br>
	 */
	public ResourceStoreResponse(JSONObject json) {
		
		super(json);
	}
}
