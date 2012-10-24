package org.mushikago.sdk.services.mitsubachi.model.http;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mushikago.sdk.common.util.KeyValuePair;
import org.mushikago.sdk.common.util.ParamUtils;
import org.mushikago.sdk.services.mitsubachi.model.MitsubachiRequest;

/**
 * Httpカテゴリに関するリクエストの抽象クラス。<br>
 * 
 * @author miningbrownie
 */
public abstract class HttpRequest extends MitsubachiRequest {

  /**
   * リクエストにurlパラメータを指定するキー。<br>
   */
  public static final String PARAM_KEY_URL = "url";

  /**
   * リクエストにproject_nameパラメータを指定するキー。<br>
   */
  public static final String PARAM_KEY_PROJECT_NAME = "project_name";

  /**
   * リクエストにscript_nameパラメータを指定するキー。<br>
   */
  public static final String PARAM_KEY_SCRIPT_NAME = "script_name";

  /**
   * リクエストにentity_parameterパラメータを指定するキー。<br>
   */
  public static final String PARAM_KEY_ENTITY_PARAMETER = "entity_parameter";

  /**
   * リクエストにparametersパラメータを指定するキー。<br>
   */
  public static final String PARAM_KEY_PARAMETERS = "parameters";

  /**
   * リクエストにheader_overwriteパラメータを指定するキー。<br>
   */
  public static final String PARAM_KEY_HEADER_OVERWRITE = "header_overwrite";

  /**
   * リクエストにcookiejarパラメータを指定するキー。<br>
   */
  public static final String PARAM_KEY_COOKIEJAR = "cookiejar";
  /**
   * リクエストにparent_request_idパラメータを指定するキー。<br>
   */
  public static final String PARAM_KEY_PARENT_REQUEST_ID = "parent_request_id";
  /**
   * リクエストにgroup_idパラメータを指定するキー。<br>
   */
  public static final String PARAM_KEY_GROUP_ID = "group_id";
  /**
   * リクエストにunique_keyパラメータを指定するキー。<br>
   */
  public static final String PARAM_KEY_UNIQUE_KEY = "unique_key";
  /**
   * リクエストにunique_key_expiresパラメータを指定するキー。<br>
   */
  public static final String PARAM_KEY_UNIQUE_KEY_EXPIRES = "unique_key_expires";

  /**
   * リクエスト(url)。<br>
   */
  protected String url = null; // アクセスするURL（クエリストリング付き可）

  /**
   * リクエスト(project_name)。<br>
   */
  protected String projectName = null;

  /**
   * リクエスト(script_name)。<br>
   */
  protected String scriptName = null;

  /**
   * リクエスト(entity_parameter)。<br>
   */
  protected final List<KeyValuePair> entityParameters; // Entity Bodyの「application/x-www-form-urlencoded」書式で送られるパラメータ

  /**
   * リクエスト(header_overwrite)。<br>
   */
  protected final List<KeyValuePair> headerOverWrites; // HTTPのヘッダ書き換え

  /**
   * リクエスト(parameters)。<br>
   */
  protected final Map<String, String> parameters; // スクリプトで使用できるパラメータ

  /**
   * リクエスト(cookiejar)。<br>
   */
  protected final List<Cookie> cookiejar;

  /**
   * リクエスト(parent_request_id)
   */
  protected String parentRequestId;

  /**
   * リクエスト(group_id)
   */
  protected String groupId;

  /**
   * リクエスト(unique_key)
   */
  protected String uniqueKey;

  /**
   * リクエスト(unique_key_expires)
   */
  protected Long uniqueKeyExpires;

  /**
   * HttpRequestを構築します。<br>
   */
  protected HttpRequest() {

    this.entityParameters = new ArrayList<KeyValuePair>();
    this.headerOverWrites = new ArrayList<KeyValuePair>();
    this.parameters = new HashMap<String, String>();
    this.cookiejar = new ArrayList<Cookie>();
    this.parentRequestId = null;
    this.groupId = null;
    this.uniqueKey = null;
    this.uniqueKeyExpires = null;
  }

  /**
   * 指定されたURL、プロジェクト名、スクリプト名を使用して、HttpRequestを構築します。<br>
   */
  protected HttpRequest(String url, String projectName, String scriptName) {

    this();

    this.url = url;
    this.projectName = projectName;
    this.scriptName = scriptName;
  }

  /**
   * URLを設定。<br>
   * 
   * @param url
   *          アクセス先のURL
   */
  public void setUrl(String url) {
    this.url = url;
  }

  /**
   * URLを取得。<br>
   * 
   * @return アクセス先のURL
   */
  public String getUrl() {
    return this.url;
  }

  /**
   * プロジェクト名を設定。<br>
   * 
   * @param name
   *          プロジェクト名
   */
  public void setProjectName(String name) {
    this.projectName = name;
  }

  /**
   * プロジェクト名を取得。<br>
   * 
   * @return プロジェクト名
   */
  public String getProjectName() {
    return this.projectName;
  }

  /**
   * スクリプト名を設定。<br>
   * 
   * @param name
   *          スクリプト名
   */
  public void setScriptName(String name) {
    this.scriptName = name;
  }

  /**
   * スクリプト名を取得。<br>
   * 
   * @return スクリプト名
   */
  public String getScriptName() {
    return this.scriptName;
  }

  /**
   * entity parameterの追加。<br>
   * 
   * @param key
   *          キー
   * @param value
   *          値
   */
  public void addEntityParameter(String key, String value) {
    this.entityParameters.add(new KeyValuePair(key, value));
  }

  /**
   * entity parameterを取得。<br>
   * 
   * @param index
   *          インデックス
   * @return entity parameter
   */
  public KeyValuePair getEntityParameter(int index) {
    return this.entityParameters.get(index);
  }

  /**
   * entity parameterの削除。<br>
   * 
   * @param index
   *          インデックス
   */
  public void removeEntityParameter(int index) {
    this.entityParameters.remove(index);
  }

  /**
   * 設定されているentity parameter数の取得。<br>
   * 
   * @return entity parameter数
   */
  public int getEntityParametersSize() {
    return this.entityParameters.size();
  }

  /**
   * header_overwriteの追加。<br>
   * 
   * @param key
   *          キー
   * @param value
   *          値
   */
  public void addHeaderOverWrite(String key, String value) {
    this.headerOverWrites.add(new KeyValuePair(key, value));
  }

  /**
   * header_overwriteの取得。<br>
   * 
   * @param index
   *          インデックス
   * @return header_overwrite
   */
  public KeyValuePair getHeaderOverWrite(int index) {
    return this.headerOverWrites.get(index);
  }

  /**
   * header_overwriteの削除。<br>
   * 
   * @param index
   *          インデックス
   */
  public void removeHeaderOverWrite(int index) {
    this.headerOverWrites.remove(index);
  }

  /**
   * 設定されているheader_overwrite数の取得。<br>
   * 
   * @return header_overwrite数
   */
  public int getHeaderOverWriteSize() {
    return this.headerOverWrites.size();
  }

  /**
   * parameterの追加。<br>
   * 
   * @param key
   *          キー
   * @param value
   *          値
   */
  public void addParameter(String key, String value) {
    this.parameters.put(key, value);
  }

  /**
   * parameterの取得。<br>
   * 
   * @param key
   * @return parameter
   */
  public String getParameter(String key) {
    return this.parameters.get(key);
  }

  /**
   * parameterの削除。<br>
   * 
   * @param key
   *          キー
   */
  public void removeParameter(String key) {
    this.parameters.remove(key);
  }

  /**
   * 設定されたparameter数の取得。<br>
   * 
   * @return parameter数
   */
  public int getParameterSize() {
    return this.parameters.size();
  }

  /**
   * クッキーを追加。<br>
   * 
   * @param cookie
   */
  public void addCookie(Cookie cookie) {
    cookiejar.add(cookie);
  }

  /**
   * クッキーを追加。<br>
   * 
   * @param name
   * @param value
   * @param domain
   * @param path
   * @param isSecure
   */
  public void addCookie(String name, String value, String domain, String path, boolean isSecure) {
    addCookie(new Cookie(name, value, domain, path, isSecure));
  }

  /**
   * 親リクエストIDの取得。
   * 
   * @return
   */
  public String getParentRequestId() {
    return parentRequestId;
  }

  /**
   * 親リクエストIDを設定。
   * 
   * @param parentRequestId
   */
  public void setParentRequestId(String parentRequestId) {
    this.parentRequestId = parentRequestId;
  }

  /**
   * グループIDの取得。
   * 
   * @return
   */
  public String getGroupId() {
    return groupId;
  }

  /**
   * グループIDを設定。
   * 
   * @param groupId
   */
  public void setGroupId(String groupId) {
    this.groupId = groupId;
  }

  /**
   * ユニークキーの取得。
   * 
   * @return
   */
  public String getUniqueKey() {
    return uniqueKey;
  }

  /**
   * ユニークキーを設定。
   * 
   * @param uniqueKey
   */
  public void setUniqueKey(String uniqueKey) {
    this.uniqueKey = uniqueKey;
  }

  /**
   * ユニークキー有効時間の取得。
   * 
   * @return
   */
  public Long getUniqueKeyExpires() {
    return uniqueKeyExpires;
  }

  /**
   * ユニークキー有効時間を設定。
   * 
   * @param uniqueKeyExpires
   */
  public void setUniqueKeyExpires(Long uniqueKeyExpires) {
    this.uniqueKeyExpires = uniqueKeyExpires;
  }

  /**
   * 「*=*&*=*&・・・」に並べ、URLエンコードをしたentity parameter。<br>
   * 
   * @return entity parameter
   * @throws UnsupportedEncodingException
   */
  protected String toEncodedEntityParameter() throws UnsupportedEncodingException {
    return ParamUtils.paramEncode(toEntityParameterString());
  }

  /**
   * @return
   */
  protected String toEntityParameterString() {
    StringBuilder buffer = new StringBuilder();
    for (KeyValuePair pair : this.entityParameters) {
      buffer.append(String.format("%s=%s&", pair.getKey(), pair.getValue()));
    }
    if (0 < buffer.length()) buffer.setLength(buffer.length() - 1);
    return buffer.toString();
  }

  /**
   * 「*=*&*=*&・・・」に並べ、URLエンコードをしたparameter。<br>
   * 
   * @return parameter
   * @throws UnsupportedEncodingException
   */
  protected String toEncodedParameter() throws UnsupportedEncodingException {
    return ParamUtils.paramEncode(toParameterString());
  }

  /**
   * @return
   */
  protected String toParameterString() {
    StringBuilder buffer = new StringBuilder();
    for (Map.Entry<String, String> param : this.parameters.entrySet()) {
      buffer.append(String.format("%s=%s&", param.getKey(), param.getValue()));
    }
    if (0 < buffer.length()) buffer.setLength(buffer.length() - 1);
    return buffer.toString();
  }

  /**
   * 「*: *\r\n*: *\r\n・・・」に並べ、URLエンコードをしたheader_overwrite。<br>
   * 
   * @return header_overwrite
   * @throws UnsupportedEncodingException
   */
  protected String toEncodedHeaderOverWrite() throws UnsupportedEncodingException {
    return ParamUtils.paramEncode(toHeaderOverWriteString());
  }

  /**
   * @return
   */
  protected String toHeaderOverWriteString() {
    StringBuilder buffer = new StringBuilder();
    for (KeyValuePair pair : this.headerOverWrites) {
      buffer.append(String.format("%s: %s\r\n", pair.getKey(), pair.getValue()));
    }
    if (1 < buffer.length()) {
      buffer.setLength(buffer.length() - 2);
    }
    return buffer.toString();
  }
}
