package org.mushikago.sdk.services.hotaru.model.text.get;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

import org.mushikago.sdk.services.hotaru.model.HotaruResponse;

/**
 * テキスト取得リクエストの結果
 * @author miningbrownie
 */
public class TextGetResponse extends HotaruResponse {
  
  /**
   * パラメータ名（テキスト説明文）
   */
  public static final String PARAM_KEY_DESCRIPTION = "description";
  
  /**
   * パラメータ名（URL）
   */
  public static final String PARAM_KEY_URL = "url";
  
  /**
   * パラメータ名（タグ一覧）
   */
  public static final String PARAM_KEY_TAGS = "tags";
  
  /**
   * パラメータ（テキスト説明文）
   */
  private final String description;
  
  /**
   * パラメータ（URL）
   */
  private final String url;
  
  /**
   * パラメータ（タグ一覧）
   */
  private final List<String> tags = new ArrayList<String>();
  
  /**
   * TextGetResponseを構築します。<br>
   * @param json APIサーバからのレスポンス。<br>
   */
  public TextGetResponse(JSONObject json) {
    
    super(json);
    
    final JSONObject response = json.getJSONObject("response");
    
    this.description = JSONNull.getInstance().equals(response.getString(PARAM_KEY_DESCRIPTION)) ? "" : response.getString(PARAM_KEY_DESCRIPTION);
    this.url = response.getString(PARAM_KEY_URL);
    
    final JSONArray tagsJson = response.getJSONArray(PARAM_KEY_TAGS);
    for(int i = 0; i < tagsJson.size(); i++) {
      
      this.tags.add(tagsJson.getString(i));
    }
  }
  
  /**
   * テキスト説明文を取得します。<br>
   * @return テキスト説明文
   */
  public String getDescription() { return this.description; }
  
  /**
   * URLを取得します。<br>
   * 当APIで取得できるURLは、一時的なものになっていますので、恒久的に使用できるものではありません。
   * @return URL
   */
  public String getUrl() { return this.url; }
  
  /**
   * タグの一覧を取得します。<br>
   * @return タグの一覧
   */
  public List<String> getTags() { return Collections.unmodifiableList(this.tags); }
  
  /**
   * InputStreamの取得。<br>
   * @return InputStream
   * @throws IOException ファイルにアクセスできない、ファイルをオープンできなかった場合
   */
  public InputStream getStream() throws IOException {
    
    URL url = new URL(this.getUrl());
    return url.openStream();
  }
}
