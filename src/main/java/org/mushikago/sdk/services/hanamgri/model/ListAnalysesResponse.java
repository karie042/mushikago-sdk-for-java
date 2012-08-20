package org.mushikago.sdk.services.hanamgri.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 解析一覧取得リクエストの結果
 * @author miningbrownie
 *
 */

public class ListAnalysesResponse extends HanamgriResponse{  
  /**
   * レスポンスからanalysesパラメータを取得するキー
   */
  public static final String PARAMETER_KEY_ANALYSES = "analyses";
  
  /**
   * レスポンスからrequest_idパラメータを取得するキー
   */
  public static final String PARAMETER_KEY_REQUEST_ID = "request_id";
  
  /**
   * レスポンスからsave_urlパラメータを取得するキー
   */
  public static final String PARAMETER_KEY_SAVE_URL = "save_url";
  
  /**
   * レスポンスからupdated_atパラメーメタを取得するキー
   */
  public static final String PARAMETER_KEY_UPDATED_AT = "updated_at";
  
  /**
   * レスポンスからstatusパラメーメタを取得するキー
   */
  public static final String PARAMETER_KEY_STATUS = "status";
  
  /**
   * レスポンスからtagパラメーメタを取得するキー
   */
  public static final String PARAMETER_KEY_TAG = "tag";
  
  /**
   * レスポンスからtotalパラメータを取得するキー
   */
  public static final String PARAMETER_KEY_TOTAL = "total";
 
  /**
   * パラメータ(解析一覧)
   */
  private final List<Analysis> analyses = new ArrayList<Analysis>();
  
  /**
   * パラメータ(解析一覧)
   */
  private final int total;
  
  /**
   * ListAnalysesResponseを構築します。<br>
   * @param json APIサーバからのレスポンス。<br>
   */
  public ListAnalysesResponse(JSONObject json){
    super(json);
    
    this.total = getInteger(PARAMETER_KEY_TOTAL, 0);
    
    JSONArray analysisJsons = getJSONArray(PARAMETER_KEY_ANALYSES);
    for(int i = 0; i < analysisJsons.size(); i++){
      JSONObject analysisJson = analysisJsons.getJSONObject(i);
      Analysis analysis = new Analysis(
        analysisJson.getString(PARAMETER_KEY_REQUEST_ID),
        analysisJson.getString(PARAMETER_KEY_SAVE_URL),
        analysisJson.getString(PARAMETER_KEY_UPDATED_AT),
        analysisJson.getString(PARAMETER_KEY_STATUS),
        analysisJson.getString(PARAMETER_KEY_TAG)
       );
      this.analyses.add(analysis);
    }
  }
  
  /**
   * 解析数を取得します。<br>
   * @return 解析数
   */
  public int getTotal() { return this.total; }
  
  public List<Analysis> getAnalyses() { return Collections.unmodifiableList(this.analyses); }
}
