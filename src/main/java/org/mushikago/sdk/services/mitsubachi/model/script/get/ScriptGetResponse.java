package org.mushikago.sdk.services.mitsubachi.model.script.get;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import net.sf.json.JSONObject;

import org.mushikago.sdk.services.mitsubachi.model.MitsubachiResponse;

/**
 * スクリプト取得に対するレスポンス。<br>
 * @author miningbrownie
 *
 */
public class ScriptGetResponse extends MitsubachiResponse {
	
	/**
	 * レスポンスからurlパラメータを取得するキー。<br>
	 */
	public static final String PARAM_KEY_URL = "url";
	
	/**
	 * レスポンス(url)。<br>
	 */
	private final String url;
	
	/**
	 * ScriptGetResponseを構築します。<br>
	 * @param json APIサーバからのレスポンス。<br>
	 * @throws ScriptGetException 詳細が無かった場合
	 */
	public ScriptGetResponse(JSONObject json) {
		
		super(json);
		
		final JSONObject response = json.getJSONObject("response");
		
		this.url = response.getString(PARAM_KEY_URL);
	}
	
	/**
	 * スクリプトのURLを取得。<br>
	 * @return スクリプトのURL
	 */
	public String getUrl() { return this.url; }
	
	/**
	 * スクリプトのInputStreamを取得。<br>
	 * @return スクリプトのInputStream
	 * @throws IOException スクリプトファイルを開けなかった場合
	 */
	public InputStream getStream() throws IOException {
		
		URL url = new URL(this.getUrl());
		return url.openStream();
	}
}
