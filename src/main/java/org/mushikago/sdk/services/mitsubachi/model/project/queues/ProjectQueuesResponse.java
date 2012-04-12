package org.mushikago.sdk.services.mitsubachi.model.project.queues;

import net.sf.json.JSONObject;

import org.mushikago.sdk.services.mitsubachi.model.MitsubachiResponse;

/**
 * プロジェクトQueuesに対するレスポンス。<br>
 * @author miningbrownie
 *
 */
public class ProjectQueuesResponse extends MitsubachiResponse {
	
	/**
	 * レスポンスからcountパラメータを取得するキー。<br>
	 */
	public static final String PARAM_KEY_COUNT = "count";
	
	/**
	 * レスポンス(count)。<br>
	 */
	private final long count;
	
	/**
	 * ProjectQueuesExceptionを構築します。<br>
	 * @param json APIサーバからのレスポンス
	 * @throws ProjectQueuesException 詳細が無かった場合
	 */
	public ProjectQueuesResponse(JSONObject json) {
		
		super(json);
		
		final JSONObject response = json.getJSONObject("response");
		
		this.count = response.getLong(PARAM_KEY_COUNT);
	}
	
	/**
	 * Queueの未処理数を取得。<br>
	 * @return Queueの未処理数
	 */
	public long getCount() { return this.count; }
}
