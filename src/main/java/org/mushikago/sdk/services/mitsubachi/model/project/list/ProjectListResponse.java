package org.mushikago.sdk.services.mitsubachi.model.project.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.mushikago.sdk.services.mitsubachi.model.MitsubachiResponse;

/**
 * プロジェクト一覧に対するレスポンス。<br>
 * @author miningbrownie
 *
 */
public class ProjectListResponse extends MitsubachiResponse {
	
	/**
	 * レスポンスからtotalパラメータを取得するキー。<br>
	 */
	public static final String PARAM_KEY_TOTAL = "total";
	
	/**
	 * レスポンスからprojectsパラメータを取得するキー。<br>
	 */
	public static final String PARAM_KEY_PROJECTS = "projects";
	
	/**
	 * レスポンスからnameパラメータを取得するキー。<br>
	 */
	public static final String PARAM_KEY_PROJECT_NAME = "name";
	
	/**
	 * レスポンス(total)。<br>
	 */
	private final int total;
	
	/**
	 * レスポンス(projects)。<br>
	 */
	private final List<Project> projects = new ArrayList<Project>();
	
	/**
	 * ProjectListResponseを構築します。<br>
	 * @param json APIサーバからのレスポンス。<br>
	 * @throws ProjectListException 詳細情報が無かった場合
	 */
	public ProjectListResponse(JSONObject json) {
		
		super(json);
		
		final JSONObject response = json.getJSONObject("response");
		
		this.total = response.getInt(PARAM_KEY_TOTAL);
		
		JSONArray projects = response.getJSONArray(PARAM_KEY_PROJECTS);
		for(int i = 0; i < projects.size(); i++) {
			
			final JSONObject projectJson = projects.getJSONObject(i);
			final Project project = new Project(projectJson.getString(PARAM_KEY_PROJECT_NAME));
			this.projects.add(project);
		}
	}
	
	/**
	 * プロジェクト数の取得。<br>
	 * @return プロジェクト数
	 */
	public int getTotal() { return this.total; }
	
	/**
	 * プロジェクトの一覧取得。<br>
	 * @return プロジェクト一覧
	 */
	public List<Project> getProjects() { return Collections.unmodifiableList(this.projects); }
}
