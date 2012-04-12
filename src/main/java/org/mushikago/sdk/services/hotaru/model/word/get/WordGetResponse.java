package org.mushikago.sdk.services.hotaru.model.word.get;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.mushikago.sdk.services.hotaru.model.HotaruResponse;
import org.mushikago.sdk.services.hotaru.model.word.Tag;

/**
 * 単語の詳細情報取得リクエストの結果
 * @author miningbrownie
 */
public class WordGetResponse extends HotaruResponse {
  
  /**
   * パラメータ名（単語）
   */
  public static final String PARAM_KEY_WORD = "word";
  
  /**
   * パラメータ名（出現数）
   */
  public static final String PARAM_KEY_COUNT = "count";
  
  /**
   * パラメータ（タグ一覧）
   */
  public static final String PARAM_KEY_TAGS = "tags";
  
  /**
   * パラメータ名（タグの名称）
   */
  public static final String PARAM_KEY_TAG = "tag";
  
  /**
   * パラメータ（単語）
   */
  private final String word;
  
  /**
   * パラメータ（出現数）
   */
  private final int count;
  
  /**
   * パラメータ（タグ一覧）
   */
  private List<Tag> tags = new ArrayList<Tag>();
  
  /**
   * WordGetResponseを構築します。<br>
   * @param json APIサーバからのレスポンス。<br>
   */
  public WordGetResponse(JSONObject json) {
    
    super(json);
    
    final JSONObject response = json.getJSONObject("response");
    
    this.word = response.getString(PARAM_KEY_WORD);
    this.count = response.getInt(PARAM_KEY_COUNT);
    
    final JSONArray tagsJson = response.getJSONArray(PARAM_KEY_TAGS);
    for(int i = 0; i < tagsJson.size(); i++) {
      final JSONObject tagJson = tagsJson.getJSONObject(i);
      this.tags.add(new Tag(tagJson.getString(PARAM_KEY_TAG), tagJson.getInt(PARAM_KEY_COUNT)));
    }
  }
  
  /**
   * 単語を取得します。<br>
   * @return 単語
   */
  public String getWord() { return this.word; }
  
  /**
   * 出現数を取得します。<br>
   * @return 単語数
   */
  public int getCount() { return this.count; }
  
  /**
   * タグ一覧を取得します。<br>
   * @return タグ一覧
   */
  public List<Tag> getTags() { return Collections.unmodifiableList(this.tags); }
}
