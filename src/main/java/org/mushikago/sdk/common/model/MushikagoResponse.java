package org.mushikago.sdk.common.model;

import net.sf.json.JSONObject;

/**
 * mushikagoサービスのレスポンスに対する基底クラス。<br>
 * @author miningbrownie
 *
 */
public class MushikagoResponse {
	
	/**
	 * ステータスコード。<br>
	 */
	protected int status = -1;
	
	/**
	 * 詳細メッセージ。<br>
	 */
	protected String message = null;
	
	/**
	 * 指定されたJSONデータを使用して、MushikagoResponseを構築します。<br>
	 * @param json APIサーバからのレスポンス。<br>
	 */
	public MushikagoResponse(JSONObject json) {
		
		this.status = Integer.valueOf(json.getJSONObject("meta").getString("status"));
		this.message = json.getJSONObject("meta").getString("message");
	}
	
	/**
	 * ステータスコードを設定。<br>
	 * @param status ステータスコード
	 */
	public void setStatus(int status) { this.status = status; }
	
	/**
	 * ステータスコードを取得。<br>
	 * @return ステータスコード
	 */
	public int getStatus() { return this.status; }
	
	/**
	 * 詳細メッセージを設定。<br>
	 * @param msg 詳細メッセージ
	 */
	public void setMessage(String msg) { this.message = msg; }
	
	/**
	 * 詳細メッセージを取得。<br>
	 * @return 詳細メッセージ
	 */
	public String getMessage() { return this.message; }
}
