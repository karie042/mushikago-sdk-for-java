package org.mushikago.sdk.services.mitsubachi.model.script.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.mushikago.sdk.services.mitsubachi.model.MitsubachiResponse;

/**
 * スクリプト一覧に対するレスポンス。<br>
 * @author miningbrownie
 *
 */
public class ScriptListResponse extends MitsubachiResponse {
	
	/**
	 * レスポンスからscriptsパラメータを取得するキー。<br>
	 */
	public static final String PARAM_KEY_SCRIPTS = "scripts";
	
	/**
	 * レスポンスからnameパラメータを取得するキー。<br>
	 */
	public static final String PARAM_KEY_SCRIPT_NAME = "name";
	
	/**
	 * レスポンスからsizeパラメータを取得するキー。<br>
	 */
	public static final String PARAM_KEY_SCRIPT_SIZE = "size";
	
	/**
	 * レスポンスからhas_more_filesパラメータを取得するキー。<br>
	 */
	public static final String PARAM_KEY_HAS_MORE_FILE = "has_more_files";
	
	/**
	 * レスポンスからtimestampパラメータを取得するキー。<br>
	 */
	public static final String PARAM_KEY_HAS_TIMESTAMP = "timestamp";
	
	/**
	 * レスポンス(scripts)。<br>
	 */
	private final List<Script> scripts = new ArrayList<Script>();
	
	/**
	 * レスポンス(has_more_files)。<br>
	 */
	private final boolean hasMoreFile;
	
	/**
	 * ScriptListResponseを構築します。<br>
	 * @param json APIサーバからのレスポンス。<br>
	 * @throws ScriptListException 詳細が無かった場合
	 */
	public ScriptListResponse(JSONObject json) {
		
		super(json);
		
		final JSONObject response = json.getJSONObject("response");
		
		JSONArray scripts = response.getJSONArray(PARAM_KEY_SCRIPTS);
		for(int i = 0; i < scripts.size(); i++) {
			final JSONObject child = scripts.getJSONObject(i);
			Script script = new Script(child.getString(PARAM_KEY_SCRIPT_NAME), child.getInt(PARAM_KEY_SCRIPT_SIZE), child.getString(PARAM_KEY_HAS_TIMESTAMP));
			this.scripts.add(script);
		}
		
		this.hasMoreFile = "1".equals(response.getString(PARAM_KEY_HAS_MORE_FILE)) ? true : false;
	}
	
	/**
	 * スクリプトの一覧を取得。<br>
	 * @return スクリプトの一覧
	 */
	public List<Script> getScripts() { return Collections.unmodifiableList(this.scripts); }
	
	/**
	 * まだスクリプトが存在するかを取得。<br>
	 * @return true => まだ存在する。false => 存在しない
	 */
	public boolean hasMoreFile() { return this.hasMoreFile; }
}
