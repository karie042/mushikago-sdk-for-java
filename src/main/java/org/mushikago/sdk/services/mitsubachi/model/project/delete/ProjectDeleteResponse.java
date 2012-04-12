package org.mushikago.sdk.services.mitsubachi.model.project.delete;

import net.sf.json.JSONObject;

import org.mushikago.sdk.services.mitsubachi.model.MitsubachiResponse;

/**
 * プロジェクト削除に対するレスポンス。<br>
 * @author miningbrownie
 */
public class ProjectDeleteResponse extends MitsubachiResponse {
	
	/**
	 * ProjectDeleteResponseを構築します。<br>
	 * @param json APIサーバからのレスポンス。<br>
	 */
	public ProjectDeleteResponse(JSONObject json) {
		
		super(json);
	}
}
