package org.mushikago.sdk.services.mitsubachi.model.script.delete;

import net.sf.json.JSONObject;

import org.mushikago.sdk.services.mitsubachi.model.MitsubachiResponse;

/**
 * スクリプト削除に対するレスポンス。<br>
 * @author miningbrownie
 *
 */
public class ScriptDeleteResponse extends MitsubachiResponse {
	
	/**
	 * ScriptDeleteResponseを構築します。<br>
	 * @param json APIサーバからのレスポンス。<br>
	 */
	public ScriptDeleteResponse(JSONObject json) {
		
		super(json);
	}
}
