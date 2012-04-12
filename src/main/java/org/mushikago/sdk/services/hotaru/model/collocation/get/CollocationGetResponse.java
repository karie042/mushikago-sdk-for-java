package org.mushikago.sdk.services.hotaru.model.collocation.get;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.mushikago.sdk.services.hotaru.model.HotaruResponse;

/**
 * 共起グラフから、相関の強い単語を取得するリクエストの結果
 * @author miningbrownie
 */
public class CollocationGetResponse extends HotaruResponse {
  
  /**
   * パラメータ名（相関の強い単語一覧）
   */
  public static final String PARAM_KEY_WORDS = "words";
  
  /**
   * パラメータ名（単語）
   */
  public static final String PARAM_KEY_WORD = "word";
  
  /**
   * パラメータ名（スコア）
   */
  public static final String PARAM_KEY_SCORE = "score";
  
  /**
   * パラメータ（単語）
   */
  private final String targetWord;
  
  /**
   * パラメータ（相関の強い単語一覧）
   */
  private final List<Word> words = new ArrayList<Word>();
  
  /**
   * CollocationGetResponseを構築します。<br>
   * @param json APIサーバからのレスポンス。<br>
   */
  public CollocationGetResponse(JSONObject json) {
    
    super(json);
    
    final JSONObject response = json.getJSONObject("response");
    
    this.targetWord = response.getString(PARAM_KEY_WORD);
    
    final JSONArray wordsJson = response.getJSONArray(PARAM_KEY_WORDS);
    for(int i = 0; i < wordsJson.size(); i++) {
      final JSONObject wordJson = wordsJson.getJSONObject(i);
      this.words.add(new Word(wordJson.getString(PARAM_KEY_WORD), wordJson.getDouble(PARAM_KEY_SCORE)));
    }
  }
  
  /**
   * 単語を取得します。<br>
   * @return 単語
   */
  public String getTargetWord() { return this.targetWord; }
  
  /**
   * 相関の強い単語の一覧を取得します。<br>
   * @return 相関の強い単語一覧
   */
  public List<Word> getWords() { return Collections.unmodifiableList(this.words); }
}
