package org.mushikago.sdk.services.mitsubachi.model.script.deploy;

import net.sf.json.JSONObject;

import org.mushikago.sdk.services.mitsubachi.model.MitsubachiResponse;

/**
 * スクリプトデプロイに対するレスポンス。<br>
 * @author miningbrownie
 *
 */
public class ScriptDeployResponse extends MitsubachiResponse {
	
	/**
	 * ScriptDeployResponseを構築します。<br>
	 * @param json APIサーバからのレスポンス。<br>
	 */
	public ScriptDeployResponse(JSONObject json) {
		
		super(json);
	}
}
