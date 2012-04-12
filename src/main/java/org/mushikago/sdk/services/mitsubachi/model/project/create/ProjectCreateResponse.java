package org.mushikago.sdk.services.mitsubachi.model.project.create;

import net.sf.json.JSONObject;

import org.mushikago.sdk.services.mitsubachi.model.MitsubachiResponse;

/**
 * プロジェクト作成リクエストの結果
 * @author miningbrownie
 */
public class ProjectCreateResponse extends MitsubachiResponse {
	
	/**
	 * ProjectCreateResponseを構築します。<br>
	 * @param json APIサーバからのレスポンス。<br>
	 */
	public ProjectCreateResponse(JSONObject json) {
		
		super(json);
	}
}
