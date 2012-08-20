package org.mushikago.sdk.services.hanamgri.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 学習データ一覧取得リクエストの結果
 * 
 * @author miningbrownie
 */

public class ListKnowledgesResponse extends HanamgriResponse {
  /**
   * レスポンスからknowledgesパラメータを取得するキー
   */
  public static final String PARAMETER_KEY_DICTIONARIES = "knowledges";

  /**
   * レスポンスからknowledge_nameパラメータを取得するキー
   */
  public static final String PARAMETER_KEY_DICTIONARY_NAME = "knowledge_name";

  /**
   * レスポンスからdescriptionパラメータを取得するキー
   */
  public static final String PARAMETER_KEY_DESCRIPTION = "description";

  /**
   * レスポンスからstatusパラメータを取得するキー
   */
  public static final String PARAMETER_KEY_STATUS = "status";

  /**
   * レスポンスからcreated_atパラメータを取得するキー
   */
  public static final String PARAMETER_KEY_CREATED_AT = "created_at";

  /**
   * レスポンスからtotalパラメータを取得するキー
   */
  public static final String PARAMETER_KEY_TOTAL = "total";

  /**
   * パラメータ(学習データ一覧)
   */
  private final List<Knowledge> knowledges = new ArrayList<Knowledge>();

  /**
   * パラメータ(学習データ一覧)
   */
  private final int total;

  /**
   * ListKnowledgesResponseを構築します。<br>
   * 
   * @param json
   *          APIサーバからのレスポンス。<br>
   */
  public ListKnowledgesResponse(JSONObject json) {
    super(json);

    this.total = getInteger(PARAMETER_KEY_TOTAL, 0);

    JSONArray knowledgeJsons = getJSONArray(PARAMETER_KEY_DICTIONARIES);
    for (int i = 0; i < knowledgeJsons.size(); i++) {
      JSONObject knowledgeJson = knowledgeJsons.getJSONObject(i);
      Knowledge knowledge = new Knowledge();
      knowledge.setKnowledgeName(knowledgeJson.getString(PARAMETER_KEY_DICTIONARY_NAME));
      knowledge.setDescription(knowledgeJson.getString(PARAMETER_KEY_DESCRIPTION));
      knowledge.setStatus(knowledgeJson.getString(PARAMETER_KEY_STATUS));
      knowledge.setCreatedAt(knowledgeJson.getString(PARAMETER_KEY_CREATED_AT));
      this.knowledges.add(knowledge);
    }
  }

  /**
   * 学習データの合計数を取得します。<br>
   * 
   * @return 学習データ数
   */
  public int getTotal() {
    return this.total;
  }

  /**
   * 学習データの一覧を取得します
   * 
   * @return
   */
  public List<Knowledge> getKnowledges() {
    return Collections.unmodifiableList(this.knowledges);
  }
}
