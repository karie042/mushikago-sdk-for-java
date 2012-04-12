package org.mushikago.sdk.services.mitsubachi.model.project.discontinue;

import net.sf.json.JSONObject;

import org.mushikago.sdk.services.mitsubachi.model.MitsubachiResponse;

/**
 * プロジェクトDiscontinueに対するレスポンス。<br>
 * @author miningbrownie
 *
 */
public class ProjectDiscontinueResponse extends MitsubachiResponse {
	
	/**
	 * ProjectDiscontinueResponseを構築します。<br>
	 * @param json APIサーバからのレスポンス。<br>
	 */
	public ProjectDiscontinueResponse(JSONObject json) {
		
		super(json);
	}
}
