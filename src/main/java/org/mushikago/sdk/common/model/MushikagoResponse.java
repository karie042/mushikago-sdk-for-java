package org.mushikago.sdk.common.model;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * mushikagoサービスのレスポンスに対する基底クラス。<br>
 * 
 * @author miningbrownie
 */
public class MushikagoResponse {

  /**
   * ステータスコード。<br>
   */
  protected int status = -1;

  /**
   * 詳細メッセージ。<br>
   */
  protected String message = null;

  /**
   * レスポンスデータ
   */
  private final JSONObject responseRawData;

  /**
   * 指定されたJSONデータを使用して、MushikagoResponseを構築します。<br>
   * 
   * @param json
   *          APIサーバからのレスポンス。<br>
   */
  public MushikagoResponse(JSONObject json) {
    this.status = Integer.valueOf(json.getJSONObject("meta").getString("status"));
    this.message = json.getJSONObject("meta").getString("message");
    if (json.containsKey("response")) {
      this.responseRawData = json.getJSONObject("response");
    }
    else {
      this.responseRawData = new JSONObject();
    }
  }

  /**
   * ステータスコードを設定。<br>
   * 
   * @param status
   *          ステータスコード
   */
  public void setStatus(int status) {
    this.status = status;
  }

  /**
   * ステータスコードを取得。<br>
   * 
   * @return ステータスコード
   */
  public int getStatus() {
    return this.status;
  }

  /**
   * 詳細メッセージを設定。<br>
   * 
   * @param msg
   *          詳細メッセージ
   */
  public void setMessage(String msg) {
    this.message = msg;
  }

  /**
   * 詳細メッセージを取得。<br>
   * 
   * @return 詳細メッセージ
   */
  public String getMessage() {
    return this.message;
  }

  protected JSONObject getResponseRawData() {
    return responseRawData;
  }

  protected String getString(String key, String defaultValue) {
    if (responseRawData.containsKey(key)) {
      return responseRawData.getString(key);
    }
    return defaultValue;
  }

  protected String getString(String key) {
    return getString(key, null);
  }

  protected Boolean getBoolean(String key, Boolean defaultValue) {
    if (responseRawData.containsKey(key)) {
      return responseRawData.getBoolean(key);
    }
    return defaultValue;
  }

  protected Boolean getBoolean(String key) {
    return getBoolean(key, null);
  }

  protected Integer getInteger(String key, Integer defaultValue) {
    if (responseRawData.containsKey(key)) {
      return responseRawData.getInt(key);
    }
    return defaultValue;
  }

  protected Integer getInteger(String key) {
    return getInteger(key, null);
  }

  protected Double getDouble(String key, Double defaultValue) {
    if (responseRawData.containsKey(key)) {
      return responseRawData.getDouble(key);
    }
    return defaultValue;
  }

  protected Double getDouble(String key) {
    return getDouble(key, null);
  }

  protected JSONObject getJSONObject(String key, JSONObject defaultValue) {
    if (responseRawData.containsKey(key)) {
      return responseRawData.getJSONObject(key);
    }
    return defaultValue;
  }

  protected JSONObject getJSONObject(String key) {
    return getJSONObject(key, null);
  }

  protected JSONArray getJSONArray(String key, JSONArray defaultValue) {
    if (responseRawData.containsKey(key)) {
      return responseRawData.getJSONArray(key);
    }
    return defaultValue;
  }

  protected JSONArray getJSONArray(String key) {
    return getJSONArray(key, null);
  }

}
