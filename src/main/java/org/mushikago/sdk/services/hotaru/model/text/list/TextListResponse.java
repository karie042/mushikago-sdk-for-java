package org.mushikago.sdk.services.hotaru.model.text.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

import org.mushikago.sdk.services.hotaru.model.HotaruResponse;
import org.mushikago.sdk.services.hotaru.model.text.list.Text.Status;

/**
 * テキスト一覧取得リクエストの結果
 * @author miningbrownie
 */
public class TextListResponse extends HotaruResponse {
  
  /**
   * パラメータ名（テキスト一覧）
   */
  public static final String PARAM_KEY_TEXTS = "texts";
  
  /**
   * パラメータID（テキストID）
   */
  public static final String PARAM_KEY_TEXT_ID = "text_id";
  
  /**
   * パラメータ名（テキスト説明文）
   */
  public static final String PARAM_KEY_DESCRIPTION = "description";
  
  /**
   * パラメータ名（タグ一覧）
   */
  public static final String PARAM_KEY_TAGS = "tags";
  
  /**
   * パラメータ名（作成日）
   */
  public static final String PARAM_KEY_CREATE_DATE = "create_date";
  
  /**
   * パラメータ名（ステータス）
   */
  public static final String PARAM_KEY_STATUS = "status";
  
  /**
   * パラメータ名（テキストの総数）
   */
  public static final String PARAM_KEY_TOTAL = "total";
  
  /**
   * テキストの総数
   */
  private final int total;
  
  /**
   * パラメータ（テキスト一覧）
   */
  private final List<Text> texts = new ArrayList<Text>();
  
  /**
   * TextListResponseを構築します。<br>
   * @param json APIサーバからのレスポンス。<br>
   */
  public TextListResponse(JSONObject json) {
    
    super(json);
    
    final JSONObject response = json.getJSONObject("response");
    
    this.total = response.getInt(PARAM_KEY_TOTAL);
    
    final JSONArray textsJson = response.getJSONArray(PARAM_KEY_TEXTS);
    for(int i = 0; i < textsJson.size(); i++) {
      
      final JSONObject textJson = textsJson.getJSONObject(i);
      
      final List<String> tags = new ArrayList<String>();
      final JSONArray _tags = textJson.getJSONArray(PARAM_KEY_TAGS);
      for(int j = 0; j < _tags.size(); j++) tags.add(_tags.getString(j));
      
      this.texts.add(new Text(
        textJson.getString(PARAM_KEY_TEXT_ID),
        JSONNull.getInstance().equals(textJson.getString(PARAM_KEY_DESCRIPTION)) ? "" : textJson.getString(PARAM_KEY_DESCRIPTION),
        tags.toArray(new String[tags.size()]),
        textJson.getString(PARAM_KEY_CREATE_DATE),
        Status.valueOf(textJson.getString(PARAM_KEY_STATUS).toUpperCase()))
      );
    }
  }
  
  /**
   * テキストの総数を取得します。<br>
   * @return テキストの総数
   */
  public int getTotal() { return this.total; }
  
  /**
   * テキスト一覧を取得します。<br>
   * @return テキスト一覧
   */
  public List<Text> getTexts() { return Collections.unmodifiableList(this.texts); }
}
