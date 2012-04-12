package org.mushikago.sdk.services.mitsubachi.model.resource.get;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import net.sf.json.JSONObject;

import org.mushikago.sdk.services.mitsubachi.model.MitsubachiResponse;

/**
 * リソース取得に対するレスポンス。<br>
 * @author miningbrownie
 *
 */
public class ResourceGetResponse extends MitsubachiResponse {
	
	/**
	 * レスポンスからurlパラメータを取得するキー。<br>
	 */
	public static final String PARAM_KEY_URL = "url";
	
	/**
	 * レスポンス(url)。<br>
	 */
	private final String url;
	
	/**
	 * ResourceGetResponseを構築します。<br>
	 * @param json APIサーバからのレスポンス
	 * @throws ResourceGetException 詳細が無かった場合
	 */
	public ResourceGetResponse(JSONObject json) {
		
		super(json);
		
		final JSONObject response = json.getJSONObject("response");
		
		this.url = response.getString(PARAM_KEY_URL);
	}
	
	/**
	 * urlを取得。<br>
	 * @return url
	 */
	public String getUrl() { return this.url; }
	
	/**
	 * InputStreamの取得。<br>
	 * @return InputStream
	 * @throws IOException ファイルにアクセスできない、ファイルをオープンできなかった場合
	 */
	public InputStream getStream() throws IOException {
		
		URL url = new URL(this.getUrl());
		return url.openStream();
	}
}
