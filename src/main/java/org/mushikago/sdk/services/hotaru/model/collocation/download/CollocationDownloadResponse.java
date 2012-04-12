package org.mushikago.sdk.services.hotaru.model.collocation.download;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import net.sf.json.JSONObject;

import org.mushikago.sdk.services.hotaru.model.HotaruResponse;

/**
 * 共起グラフダウンロードのリクエストの結果
 * @author miningbrownie
 */
public class CollocationDownloadResponse extends HotaruResponse {
  
  /**
   * パラメータ名（共起グラフ名）
   */
  public static final String PARAM_KEY_COLLOCATION_NAME = "collocation_name";
  
  /**
   * パラメータ名（URL）
   */
  public static final String PARAM_KEY_URL = "url";
  
  /**
   * パラメータ（共起グラフ名）
   */
  private final String collocationName;
  
  /**
   * パラメータ（URL）
   */
  private final String url;
  
  /**
   * CollocationDownloadResponseを構築します。<br>
   * @param json APIサーバからのレスポンス。<br>
   */
  public CollocationDownloadResponse(JSONObject json) {
    
    super(json);
    
    final JSONObject response = json.getJSONObject("response");
    
    this.collocationName = response.getString(PARAM_KEY_COLLOCATION_NAME);
    this.url = response.getString(PARAM_KEY_URL);
  }
  
  /**
   * 共起グラフ名を取得します。<br>
   * @return 共起グラフ名
   */
  public String getCollocationName() { return this.collocationName; }
  
  /**
   * URLを取得します。<br>
   * @return URL
   */
  public String getUrl() { return this.url; }
  
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
