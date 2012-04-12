package org.mushikago.sdk.services.hotaru.model.classifier.judge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.mushikago.sdk.services.hotaru.model.HotaruResponse;

/**
 * タグ判定リクエストの結果
 * @author miningbrownie
 */
public class ClassifierJudgeResponse extends HotaruResponse {
  
  /**
   * パラメータ名（タグ一覧）
   */
  public static final String PARAM_KEY_TAGS = "tags";
  
  /**
   * パラメータ名（タグ名）
   */
  public static final String PARAM_KEY_TAG = "tag";
  
  /**
   * パラメータ名（スコア）
   */
  public static final String PARAM_KEY_SCORE = "score";
  
  /**
   * パラメータ（タグ一覧）
   */
  private final List<Tag> tags = new ArrayList<Tag>();
  
  /**
   * ClassifierJudgeResponseを構築します。<br>
   * @param json APIサーバからのレスポンス。<br>
   */
  public ClassifierJudgeResponse(JSONObject json) {
    
    super(json);
    
    final JSONObject response = json.getJSONObject("response");
    
    final JSONArray tagsJson = response.getJSONArray(PARAM_KEY_TAGS);
    for(int i = 0; i < tagsJson.size(); i++) {
      final JSONObject tagJson = tagsJson.getJSONObject(i);
      this.tags.add(new Tag(tagJson.getString(PARAM_KEY_TAG), tagJson.getInt(PARAM_KEY_SCORE)));
    }
  }
  
  /**
   * タグ一覧を取得します。<br>
   * @return タグ一覧
   */
  public List<Tag> getTags() { return Collections.unmodifiableList(this.tags); }
}
