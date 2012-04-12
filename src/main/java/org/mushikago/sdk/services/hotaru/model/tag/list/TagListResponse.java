package org.mushikago.sdk.services.hotaru.model.tag.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.mushikago.sdk.services.hotaru.model.HotaruResponse;

/**
 * タグ一覧取得リクエストの結果
 * @author miningbrownie
 */
public class TagListResponse extends HotaruResponse {
  
  /**
   * パラメータ名（タグ一覧）
   */
  public static final String PARAM_KEY_TAGS = "tags";
  
  /**
   * パラメータ名（タグ名）
   */
  public static final String PARAM_KEY_TAG = "tag";
  
  /**
   * パラメータ名（出現数）
   */
  public static final String PARAM_KEY_COUNT = "count";
  
  /**
   * パラメータ名（タグ数）
   */
  public static final String PARAM_KEY_TOTAL = "total";
  
  /**
   * パラメータ（総数）
   */
  private final int total;
  
  /**
   * パラメータ（タグ一覧）
   */
  private final List<Tag> tags = new ArrayList<Tag>();
  
  /**
   * TagListResponseを構築します。<br>
   * @param json APIサーバからのレスポンス。<br>
   */
  public TagListResponse(JSONObject json) {
    
    super(json);
    
    final JSONObject response = json.getJSONObject("response");
    
    this.total = response.getInt(PARAM_KEY_TOTAL);
    
    JSONArray tagsJson = response.getJSONArray(PARAM_KEY_TAGS);
    for(int i = 0; i < tagsJson.size(); i++) {
      JSONObject tagJson = tagsJson.getJSONObject(i);
      this.tags.add(new Tag(tagJson.getString(PARAM_KEY_TAG), tagJson.getInt(PARAM_KEY_COUNT)));
    }
  }
  
  /**
   * タグの総数を取得します。<br>
   * @return タグの総数
   */
  public int getTotal() { return this.total; }
  
  /**
   * タグの一覧を取得します。<br>
   * @return タグの一覧
   */
  public List<Tag> getTags() { return Collections.unmodifiableList(this.tags); }
}
