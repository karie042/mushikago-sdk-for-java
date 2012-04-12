package org.mushikago.sdk.services.hotaru.model.dictionary.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

import org.mushikago.sdk.services.hotaru.model.HotaruResponse;

/**
 * 辞書一覧取得リクエストの結果
 * @author miningbrownie
 */
public class DictionaryListResponse extends HotaruResponse {
  
  public static final String PARAM_KEY_DICTIONARYS = "dictionaries";
  public static final String PARAM_KEY_DICTIONARY_ID = "dictionary_id";
  public static final String PARAM_KEY_DICTIONARY_NAME = "dictionary_name";
  public static final String PARAM_KEY_DESCRIPTION = "description";
  public static final String PARAM_KEY_CREATE_DATE = "create_date";
  public static final String PARAM_KEY_SIZE = "size";
  public static final String PARAM_KEY_STATUS = "status";
  public static final String PARAM_KEY_TOTAL = "total";
  
  private final int total;
  private List<Dictionary> dictionaries = new ArrayList<Dictionary>();
  
  /**
   * DictionaryListResponseを構築します。<br>
   * @param json APIサーバからのレスポンス。<br>
   */
  public DictionaryListResponse(JSONObject json) {
    
    super(json);
    
    final JSONObject response = json.getJSONObject("response");
    
    this.total = response.getInt(PARAM_KEY_TOTAL);
    
    JSONArray dictionariesJson = response.getJSONArray(PARAM_KEY_DICTIONARYS);
    for(int i = 0; i < dictionariesJson.size(); i++) {
      
      JSONObject dictionaryJson = dictionariesJson.getJSONObject(i);
      this.dictionaries.add(new Dictionary(
        dictionaryJson.getString(PARAM_KEY_DICTIONARY_ID),
        dictionaryJson.getString(PARAM_KEY_DICTIONARY_NAME),
        JSONNull.getInstance().equals(dictionaryJson.getString(PARAM_KEY_DESCRIPTION)) ? "" : dictionaryJson.getString(PARAM_KEY_DESCRIPTION),
        dictionaryJson.getString(PARAM_KEY_CREATE_DATE),
        dictionaryJson.getInt(PARAM_KEY_SIZE)
      ));
    }
  }
  
  /**
   * 辞書の総数を取得します。<br>
   * @return 辞書の総数
   */
  public int getTotal() { return this.total; }
  
  /**
   * 辞書一覧を取得します。<br>
   * @return 辞書一覧
   */
  public List<Dictionary> getDictionaries() { return Collections.unmodifiableList(this.dictionaries); }
}
