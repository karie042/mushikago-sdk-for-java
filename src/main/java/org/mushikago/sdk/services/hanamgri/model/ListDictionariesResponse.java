package org.mushikago.sdk.services.hanamgri.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 辞書一覧取得リクエストの結果
 * 
 * @author miningbrownie
 */

public class ListDictionariesResponse extends HanamgriResponse {
  /**
   * レスポンスからdictionariesパラメータを取得するキー
   */
  public static final String PARAMETER_KEY_DICTIONARIES = "dictionaries";

  /**
   * レスポンスからdictionary_nameパラメータを取得するキー
   */
  public static final String PARAMETER_KEY_DICTIONARY_NAME = "dictionary_name";

  /**
   * レスポンスからdescriptionパラメータを取得するキー
   */
  public static final String PARAMETER_KEY_DESCRIPTION = "description";

  /**
   * レスポンスからcreated_atパラメータを取得するキー
   */
  public static final String PARAMETER_KEY_CREATED_AT = "created_at";

  /**
   * レスポンスからtotalパラメータを取得するキー
   */
  public static final String PARAMETER_KEY_TOTAL = "total";

  /**
   * パラメータ(辞書一覧)
   */
  private final List<Dictionary> dictionaries = new ArrayList<Dictionary>();

  /**
   * パラメータ(辞書一覧)
   */
  private final int total;

  /**
   * ListDictionariesResponseを構築します。<br>
   * 
   * @param json
   *          APIサーバからのレスポンス。<br>
   */
  public ListDictionariesResponse(JSONObject json) {
    super(json);

    this.total = getInteger(PARAMETER_KEY_TOTAL, 0);

    JSONArray dictionaryJsons = getJSONArray(PARAMETER_KEY_DICTIONARIES);
    for (int i = 0; i < dictionaryJsons.size(); i++) {
      JSONObject dictionaryJson = dictionaryJsons.getJSONObject(i);
      Dictionary dictionary = new Dictionary();
      dictionary.setDictionaryName(dictionaryJson.getString(PARAMETER_KEY_DICTIONARY_NAME));
      dictionary.setDescription(dictionaryJson.getString(PARAMETER_KEY_DESCRIPTION));
      dictionary.setCreatedAt(dictionaryJson.getString(PARAMETER_KEY_CREATED_AT));
      this.dictionaries.add(dictionary);
    }
  }

  /**
   * 辞書の合計数を取得します。<br>
   * 
   * @return 辞書数
   */
  public int getTotal() {
    return this.total;
  }

  /**
   * 辞書の一覧を取得します
   * 
   * @return
   */
  public List<Dictionary> getDictionaries() {
    return Collections.unmodifiableList(this.dictionaries);
  }
}
