package org.mushikago.sdk.services.hotaru.model.domain.info;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.mushikago.sdk.services.hotaru.model.HotaruResponse;
import org.mushikago.sdk.services.hotaru.model.domain.create.DomainCreateRequest.Splitter;
import org.mushikago.sdk.services.hotaru.model.domain.list.DomainListRequest.Status;

/**
 * ドメイン情報取得リクエストの結果
 * @author miningbrownie
 */
public class DomainInfoResponse extends HotaruResponse {
  
  /**
   * パラメータ名（説明文）
   */
  public static final String PARAM_KEY_DESCRIPTION = "description";
  
  /**
   * パラメータ名（解析種類）
   */
  public static final String PARAM_KEY_SPRITTER = "splitter";
  
  /**
   * パラメータ名（辞書ID一覧）
   */
  public static final String PARAM_KEY_DICTIONARY_IDS = "dictionary_ids";
  
  /**
   * パラメータ名（単語数）
   */
  public static final String PARAM_KEY_WORD_COUNT = "word_count";
  
  /**
   * パラメータ名（作成日）
   */
  public static final String PARAM_KEY_CREATE_DATE = "create_date";
  
  /**
   * パラメータ名（ステータス）
   */
  public static final String PARAM_KEY_STATUS = "status";
  
  /**
   * パラメータ名（使用量を記録した時間）
   */
  public static final String PARAM_KEY_USAGE_UPDATE = "usage_update";
  
  /**
   * パラメータ名（ストレージ使用量）
   */
  public static final String PARAM_KEY_USAGE = "usage";
  
  /**
   * パラメータ（説明文）
   */
  private final String description;
  
  /**
   * パラメータ（解析種類）
   */
  private final Splitter spritter;
  
  /**
   * パラメータ（辞書ID一覧）
   */
  private final List<String> dictionaryIds = new ArrayList<String>();
  
  /**
   * パラメータ（単語数）
   */
  private final int wordCount;
  
  /**
   * パラメータ（作成日）
   */
  private final String createDate;
  
  /**
   * パラメータ（ステータス）
   */
  private final Status status;
  
  /**
   * パラメータ（使用量を記録した時間）
   */
  private final String usageUpdate;
  
  /**
   * パラメータ（ストレージ使用量）
   */
  private final String usage;
  
  /**
   * 説明文を取得します。<br>
   * @return 説明文
   */
  public String getDescription() { return this.description; }
  
  /**
   * 解析種類を取得します。<br>
   * @return 解析種類
   */
  public Splitter getSplitter() { return this.spritter; }
  
  /**
   * 辞書ID一覧を取得します。<br>
   * @return 辞書ID一覧
   */
  public List<String> getDictionaryIds() { return Collections.unmodifiableList(this.dictionaryIds); }
  
  /**
   * 単語数を取得します。<br>
   * @return 単語数
   */
  public int getWordCount() { return this.wordCount; }
  
  /**
   * 作成日を取得します。<br>
   * @return 作成日
   */
  public String getCreateDate() { return this.createDate; }
  
  /**
   * ドメインステータスを取得します。<br>
   * @return ドメインステータス
   */
  public Status getDomainStatus() { return this.status; }
  
  /**
   * 使用量を記録した時間を取得します。<br>
   * @return 使用量を記録した時間
   */
  public String getUsageUpdate() { return this.usageUpdate; }
  
  /**
   * ストレージ使用量を取得します。<br>
   * @return ストレージ使用量
   */
  public String getUsage() { return this.usage; }
  
  /**
   * DomainInfoResponseを構築します。<br>
   * @param json APIサーバからのレスポンス。<br>
   */
  public DomainInfoResponse(JSONObject json) {
    
    super(json);
    
    final JSONObject response = json.getJSONObject("response");
    this.description = response.getString(PARAM_KEY_DESCRIPTION);
    
    String splitter = response.getString(PARAM_KEY_SPRITTER);
    Pattern p = Pattern.compile("^([0-9]+)[g|G].*$");
    Matcher m = p.matcher(splitter);
    if(m.find()) {
      splitter = splitter.substring(1) + m.group(1);
    }
    
    this.spritter = Splitter.valueOf(splitter.toUpperCase());
    JSONArray ids = response.getJSONArray(PARAM_KEY_DICTIONARY_IDS);
    for(int i = 0; i < ids.size(); i++) this.dictionaryIds.add(ids.getString(i));
    this.wordCount = response.getInt(PARAM_KEY_WORD_COUNT);
    this.createDate = response.getString(PARAM_KEY_CREATE_DATE);
    this.status = Status.valueOf(response.getString(PARAM_KEY_STATUS).toUpperCase());
    this.usageUpdate = response.getString(PARAM_KEY_USAGE_UPDATE);
    this.usage = response.getString(PARAM_KEY_USAGE);
  }
}
