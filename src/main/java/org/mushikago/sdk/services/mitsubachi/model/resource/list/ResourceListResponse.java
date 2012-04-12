package org.mushikago.sdk.services.mitsubachi.model.resource.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.mushikago.sdk.services.mitsubachi.model.MitsubachiResponse;

/**
 * リソース一覧のリクエスト。<br>
 * @author miningbrownie
 *
 */
public class ResourceListResponse extends MitsubachiResponse {
	
	/**
	 * レスポンスからfilesパラメータを取得するキー。<br>
	 */
	public static final String PARAM_KEY_RESOURCES = "files";
	
	/**
	 * レスポンスからnameパラメータを取得するキー。<br>
	 */
	public static final String PARAM_KEY_RESOURCE_NAME = "name";
	
	/**
	 * レスポンスからsizeパラメータを取得するキー。<br>
	 */
	public static final String PARAM_KEY_RESOURCE_SIZE = "size";
	
	/**
	 * レスポンスからhas_more_filesパラメータを取得するキー。<br>
	 */
	public static final String PARAM_KEY_HAS_MORE_FILE = "has_more_files";
	
	/**
	 * レスポンスからtimestampパラメータを取得するキー。<br>
	 */
	public static final String PARAM_KEY_HAS_TIMESTAMP = "timestamp";
	
  /**
   * レスポンスからcontent_typeパラメータを取得するキー。<br>
   */
  public static final String PARAM_KEY_CONTENT_TYPE = "content_type";
	
	/**
	 * レスポンス(resources)。<br>
	 */
	private final List<Resource> resources = new ArrayList<Resource>();
	
	/**
	 * レスポンス(has_more_files)。
	 */
	private final boolean hasMoreFile;
	
	/**
	 * ResourceListResponseを構築します。<br>
	 * @param json APIサーバからのレスポンス。<br>
	 * @throws ResourceListException 詳細が無かった場合
	 */
	public ResourceListResponse(JSONObject json) {
		
		super(json);
		
		final JSONObject response = json.getJSONObject("response");
		
		JSONArray files = response.getJSONArray(PARAM_KEY_RESOURCES);
		for(int i = 0; i < files.size(); i++) {
			
			final JSONObject child = files.getJSONObject(i);
			Resource resource = new Resource(child.getString(PARAM_KEY_RESOURCE_NAME), child.getInt(PARAM_KEY_RESOURCE_SIZE), child.getString(PARAM_KEY_HAS_TIMESTAMP), child.getString(PARAM_KEY_CONTENT_TYPE));
			this.resources.add(resource);
		}
		
		this.hasMoreFile = "1".equals(response.getString(PARAM_KEY_HAS_MORE_FILE)) ? true : false;
	}
	
	/**
	 * リソースの一覧を取得。<br>
	 * @return リソース一覧
	 */
	public List<Resource> getResources() { return Collections.unmodifiableList(this.resources); }
	
	/**
	 * リソースがまだ存在するかを取得。<br>
	 * @return true => まだ存在する。false => 存在しない
	 */
	public boolean hasMoreFile() { return this.hasMoreFile; }
}
