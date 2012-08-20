package org.mushikago.sdk.services.hanamgri.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.mushikago.sdk.services.hanamgri.model.FieldSet;

public class SearchSchemaResponse extends HanamgriResponse {
  /**
   * レスポンスからresultパラメータを取得するキー
   */
  private static final String PARAMETER_KEY_RESULT = "results";

  /**
   * レスポンスからtotalパラメータを取得するキー
   */
  private static final String PARAMETER_KEY_TOTAL = "total";

  /**
   * レスポンス(results)
   */
  private final List<FieldSet> results = new ArrayList<FieldSet>();

  /**
   * レスポンス(total)
   */
  private final int total;

  /**
   * SearchShemaResponseを構築します。<br>
   * 
   * @param json
   *          APIサーバからのレスポンス。<br>
   */
  public SearchSchemaResponse(JSONObject json) {
    super(json);
    
    this.total = getInteger(PARAMETER_KEY_TOTAL, 0);
    
    JSONArray resultJson = getJSONArray(PARAMETER_KEY_RESULT);
    
    HashMap<String,String> fieldSetMap = new HashMap<String,String>();
    
    for(int i = 0; i < resultJson.size(); i++){
      String key = resultJson.getJSONObject(i).keySet().toArray()[0].toString();
      String value = resultJson.getJSONObject(i).getString(key);
      fieldSetMap.put(key,value);
    }

    FieldSet fieldSet = new FieldSet(fieldSetMap);
    this.results.add(fieldSet);
    
  }

  /**
   * フィールド数を取得します。<br>
   * 
   * @return フィールド数
   */
  public int getTotal() {
    return this.total;
  }

  public List<FieldSet> getResults() {
    return Collections.unmodifiableList(this.results);
  }
}
