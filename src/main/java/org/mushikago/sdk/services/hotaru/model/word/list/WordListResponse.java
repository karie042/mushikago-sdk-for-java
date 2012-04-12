package org.mushikago.sdk.services.hotaru.model.word.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.mushikago.sdk.services.hotaru.model.HotaruResponse;
import org.mushikago.sdk.services.hotaru.model.word.Word;

/**
 * 単語一覧取得リクエストの結果
 * @author miningbrownie
 */
public class WordListResponse extends HotaruResponse {
  
  /**
   * パラメータ名（総数）
   */
  public static final String PARAM_KEY_TOTAL = "total";
  
  /**
   * パラメータ名（単語一覧）
   */
  public static final String PARAM_KEY_WORDS = "words";
  
  /**
   * パラメータ名（単語の名称）
   */
  public static final String PARAM_KEY_WORD = "word";
  
  /**
   * パラメータ名（出現数）
   */
  public static final String PARAM_KEY_COUNT = "count";
  
  /**
   * パラメータ（総数）
   */
  private final int total;
  
  /**
   * パラメータ（単語一覧）
   */
  private final List<Word> words = new ArrayList<Word>();
  
  /**
   * WordListResponseを構築します。<br>
   * @param json APIサーバからのレスポンス。<br>
   */
  public WordListResponse(JSONObject json) {
    
    super(json);
    
    final JSONObject response = json.getJSONObject("response");
    
    this.total = response.getInt(PARAM_KEY_TOTAL);
    
    final JSONArray wordsJson = response.getJSONArray(PARAM_KEY_WORDS);
    for(int i = 0; i < wordsJson.size(); i++) {
      final JSONObject wordJson = wordsJson.getJSONObject(i);
      this.words.add(new Word(wordJson.getString(PARAM_KEY_WORD), wordJson.getInt(PARAM_KEY_COUNT)));
    }
  }
  
  /**
   * 総数を取得します。<br>
   * @return 総数
   */
  public int getTotal() { return this.total; }
  
  /**
   * 単語一覧を取得します。<br>
   * @return 単語一覧
   */
  public List<Word> getWords() { return Collections.unmodifiableList(this.words); }
}
