package org.mushikago.sdk.services.mitsubachi.model.project.update;

import net.sf.json.JSONObject;

import org.mushikago.sdk.services.mitsubachi.model.MitsubachiResponse;

/**
 * プロジェクト更新に対するレスポンス。<br>
 * @author miningbrownie
 *
 */
public class ProjectUpdateResponse extends MitsubachiResponse {
	
	/**
	 * ProjectUpdateResponseを構築します。<br>
	 * @param json APIサーバからのレスポンス
	 */
	public ProjectUpdateResponse(JSONObject json) {
		
		super(json);
	}
}
