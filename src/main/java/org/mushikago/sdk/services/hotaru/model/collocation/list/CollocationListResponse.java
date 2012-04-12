package org.mushikago.sdk.services.hotaru.model.collocation.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.mushikago.sdk.services.hotaru.model.HotaruResponse;

/**
 * 共起グラフ一覧リクエストの結果
 * @author miningbrownie
 */
public class CollocationListResponse extends HotaruResponse {
  
  /**
   * パラメータ名（共起グラフ一覧）
   */
  public static final String PARAM_KEY_COLLOCATIONS = "collocations";
  
  /**
   * パラメータ名（共起グラフID）
   */
  public static final String PARAM_KEY_COLLOCATION_ID = "collocation_id";
  
  /**
   * パラメータ名（共起グラフ名）
   */
  public static final String PARAM_KEY_COLLOCATION_NAME = "collocation_name";
  
  /**
   * パラメータ名（タグ一覧）
   */
  public static final String PARAM_KEY_TAGS = "tags";
  
  /**
   * パラメータ名（品詞一覧）
   */
  public static final String PARAM_KEY_PARTS_OF_SPEECH = "parts_of_speech";
  
  /**
   * パラメータ名（単語出現数下限）
   */
  public static final String PARAM_KEY_LOWER_THRESHOLD = "lower_threshold";
  
  /**
   * パラメータ名（単語出現数上限）
   */
  public static final String PARAM_KEY_UPPER_THRESHOLD = "upper_threshold";
  
  /**
   * パラメータ名（説明文）
   */
  public static final String PARAM_KEY_DESCRIPTION = "description";
  
  /**
   * パラメータ名（説明文）
   */
  public static final String PARAM_KEY_CREATE_DATE = "create_date";
  
  /**
   * パラメータ名（ステータス）
   */
  public static final String PARAM_KEY_STATUS = "status";
  
  /**
   * パラメータ名（共起グラフの総数）
   */
  public static final String PARAM_KEY_TOTAL = "total";
  
  /**
   * パラメータ（共起グラフの総数）
   */
  private final int total;
  
  /**
   * パラメータ（共起グラフの一覧）
   */
  private final List<Collocation> collocations = new ArrayList<Collocation>();
  
  /**
   * CollocationListResponseを構築します。<br>
   * @param json APIサーバからのレスポンス。<br>
   */
  public CollocationListResponse(JSONObject json) {
    
    super(json);
    
    final JSONObject response = json.getJSONObject("response");
    
    this.total = response.getInt(PARAM_KEY_TOTAL);
    
    final JSONArray collocationsJson = response.getJSONArray(PARAM_KEY_COLLOCATIONS);
    for(int i = 0; i < collocationsJson.size(); i++) {
      
      final JSONObject collocationJson = collocationsJson.getJSONObject(i);
      
      final JSONArray tagsJson = collocationJson.getJSONArray(PARAM_KEY_TAGS);
      final List<String> tags = new ArrayList<String>();
      for(int j = 0; j < tagsJson.size(); j++) tags.add(tagsJson.getString(j));
      
      final JSONArray partsOfSpeechJson = collocationJson.getJSONArray(PARAM_KEY_PARTS_OF_SPEECH);
      final List<String> partsOfSpeech = new ArrayList<String>();
      for(int j = 0; j < partsOfSpeechJson.size(); j++) partsOfSpeech.add(partsOfSpeechJson.getString(j));
      
      final Collocation collocation = new Collocation(
        collocationJson.getString(PARAM_KEY_COLLOCATION_ID),
        collocationJson.getString(PARAM_KEY_COLLOCATION_NAME),
        tags.toArray(new String[tags.size()]),
        partsOfSpeech.toArray(new String[partsOfSpeech.size()]),
        collocationJson.getInt(PARAM_KEY_LOWER_THRESHOLD),
        collocationJson.getInt(PARAM_KEY_UPPER_THRESHOLD),
        collocationJson.getString(PARAM_KEY_DESCRIPTION),
        collocationJson.getString(PARAM_KEY_CREATE_DATE),
        Status.valueOf(collocationJson.getString(PARAM_KEY_STATUS).toUpperCase()
      ));
      
      this.collocations.add(collocation);
    }
  }
  
  /**
   * 共起グラフの総数を取得します。<br>
   * @return 共起グラフの総数
   */
  public int getTotal() { return this.total; }
  
  /**
   * 共起グラフの一覧を取得します。<br>
   * @return 共起グラフ一覧
   */
  public List<Collocation> getCollocations() { return Collections.unmodifiableList(this.collocations); }
}
