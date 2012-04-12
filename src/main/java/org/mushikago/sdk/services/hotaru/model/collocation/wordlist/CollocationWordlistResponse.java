package org.mushikago.sdk.services.hotaru.model.collocation.wordlist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.mushikago.sdk.services.hotaru.model.HotaruResponse;

/**
 * 単語一覧取得リクエストの結果
 * @author miningbrownie
 */
public class CollocationWordlistResponse extends HotaruResponse {
  
  /**
   * パラメータ名（単語一覧）
   */
  public static final String PARAM_KEY_WORDS = "words";
  
  /**
   * パラメータ名（単語の総数）
   */
  public static final String PARAM_KEY_TOTAL = "total";
  
  /**
   * パラメータ（単語の総数）
   */
  private final int total;
  
  /**
   * パラメータ（単語一覧）
   */
  private final List<String> words = new ArrayList<String>();
  
  /**
   * CollocationWordlistResponseを構築します。<br>
   * @param json APIサーバからのレスポンス。<br>
   */
  public CollocationWordlistResponse(JSONObject json) {
    
    super(json);
    
    final JSONObject response = json.getJSONObject("response");
    
    this.total = response.getInt(PARAM_KEY_TOTAL);
    
    final JSONArray wordsJson = response.getJSONArray(PARAM_KEY_WORDS);
    for(int i = 0; i < wordsJson.size(); i++) this.words.add(wordsJson.getString(i));
  }
  
  /**
   * 単語の総数を取得します。<br>
   * @return 単語の総数
   */
  public int getTotal() { return this.total; }
  
  /**
   * 単語一覧を取得します。<br>
   * @return 単語一覧
   */
  public List<String> getWords() { return Collections.unmodifiableList(this.words); }
}
