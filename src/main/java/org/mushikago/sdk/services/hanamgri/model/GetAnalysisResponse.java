package org.mushikago.sdk.services.hanamgri.model;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GetAnalysisResponse extends HanamgriResponse {
  /**
   * レスポンスからanalysis_dataパラメータを取得するキー
   */
  private static final String PARAMETER_KEY_ANALYSIS_DATA = "analysis_data";

  /**
   * レスポンスからblocksパラメータを取得するキー
   */
  private static final String PARAMETER_KEY_BLOCKS = "blocks";

  /**
   * レスポンスからresultsパラメータを取得するキー
   */
  private static final String PARAMETER_KEY_RESULTS = "results";

  /**
   * レスポンスからurlパラメータを取得するキー
   */
  private static final String PARAMETER_KEY_URL = "url";

  /**
   * レスポンスからcreated_atパラメータを取得するキー
   */
  private static final String PARAMETER_KEY_CREATED_AT = "created_at";

  /**
   * レスポンス(analysis_data)
   */
  private AnalysisData analysisData;

  /**
   * レスポンス(url)
   */
  private String url;

  /**
   * レスポンス(created_at)
   */
  private String createdAt;

  /**
   * GetAnalysisResponseを構築します。<br>
   * 
   * @param json
   *          APIサーバからのレスポンス。<br>
   */
  public GetAnalysisResponse(JSONObject json) {
    super(json);
    setUrl(getString(PARAMETER_KEY_URL));
    setCreatedAt(getString(PARAMETER_KEY_CREATED_AT));

    analysisData = new AnalysisData();
    JSONObject analysisDataJson = getJSONObject(PARAMETER_KEY_ANALYSIS_DATA);

    JSONArray blocksJson = analysisDataJson.getJSONArray(PARAMETER_KEY_BLOCKS);
    List<String> blocks = new ArrayList<String>();
    for (int i = 0; i < blocksJson.size(); i++) {
      blocks.add(blocksJson.getString(i));
    }
    analysisData.setBlocks(blocks);

    if (analysisDataJson.containsKey(PARAMETER_KEY_RESULTS)) {
      JSONArray resultsJson = analysisDataJson.getJSONArray(PARAMETER_KEY_RESULTS);
      List<FieldSet> results = new ArrayList<FieldSet>();
      for (int i = 0; i < resultsJson.size(); i++) {
        results.add(convertToFieldSet(resultsJson.getJSONObject(i)));
      }
      analysisData.setResults(results);
    }
  }

  protected FieldSet convertToFieldSet(JSONObject fieldSetJson) {
    FieldSet fieldSet = new FieldSet();
    for (Object key : fieldSetJson.keySet()) {
      fieldSet.put(key.toString(), fieldSetJson.getString(key.toString()));
    }
    return fieldSet;
  }

  public AnalysisData getAnalysisData() {
    return analysisData;
  }

  public void setAnalysisData(AnalysisData analysisData) {
    this.analysisData = analysisData;
  }

  /**
   * @return the url
   */
  public String getUrl() {
    return url;
  }

  /**
   * @param url
   *          the url to set
   */
  public void setUrl(String url) {
    this.url = url;
  }

  /**
   * @return the createdAt
   */
  public String getCreatedAt() {
    return createdAt;
  }

  /**
   * @param createdAt
   *          the createdAt to set
   */
  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

}
