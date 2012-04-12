package org.mushikago.sdk.services.hotaru.model.dictionary.put;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.TreeMap;

import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.mushikago.sdk.common.RequestException;
import org.mushikago.sdk.common.auth.MushikagoAuth;
import org.mushikago.sdk.common.util.ConnectInfo;
import org.mushikago.sdk.common.util.ParamUtils;
import org.mushikago.sdk.services.hotaru.model.dictionary.DictionaryRequest;

/**
 * 辞書登録リクエスト。<br>
 * @author miningbrownie
 */
public class DictionaryPutRequest extends DictionaryRequest {
  
  /**
   * 操作名
   */
  public static final String OPERATION_NAME = "put";
  
  /**
   * パラメータ名（辞書ファイル）
   */
  public static final String PARAM_KEY_DICTIONARY_FILE = "dictionary_file";
  
  /**
   * パラメータ名（辞書名）
   */
  public static final String PARAM_KEY_DICTIONARY_NAME = "dictionary_name";
  
  /**
   * パラメータ名（説明文）
   */
  public static final String PARAM_KEY_DESCRIPTION = "description";
  
  /**
   * パラメータ（辞書ファイル）
   */
  private File dictionaryFile = null;
  
  /**
   * パラメータ（辞書名）
   */
  private String dictionaryName = null;
  
  /**
   * パラメータ（説明文）
   */
  private String description = null;
  
  /**
   * パラメータを持たない、空のDictionaryPutRequestを構築します。<br>
   */
  public DictionaryPutRequest() {}
  
  /**
   * 指定された辞書ファイル、辞書名、説明文を使用して、DictionaryPutRequestを構築します。<br>
   * @param file 辞書ファイル
   * @param name 辞書名
   * @param description 説明文
   */
  public DictionaryPutRequest(File file, String name, String description) {
    this.dictionaryFile = file;
    this.dictionaryName = name;
    this.description = description;
  }
  
  /**
   * 辞書ファイルを取得します。<br>
   * @return 辞書ファイル
   */
  public File getDictionaryFile() { return this.dictionaryFile; }
  
  /**
   * 辞書名を取得します。<br>
   * @return 辞書名
   */
  public String getDictionaryName() { return this.dictionaryName; }
  
  /**
   * 説明文を取得します。<br>
   * @return 説明文
   */
  public String getDescription() { return this.description; }
  
  /**
   * 辞書ファイルを設定します。<br>
   * @param file 辞書ファイル
   */
  public void setDictionaryFile(File file) { this.dictionaryFile = file; }
  
  /**
   * 辞書名を設定します。<br>
   * @param name 辞書名
   */
  public void setDictionaryFileName(String name) { this.dictionaryName = name; }
  
  /**
   * 説明文を設定します。<br>
   * @param description 説明文
   */
  public void setDescription(String description) { this.description = description; }
  
  @Override
  public HttpRequestBase toHttpMethod(MushikagoAuth auth, ConnectInfo ci) throws RequestException {
    
    try {
      TreeMap<String, String> requestParams = new TreeMap<String, String>();
      if(null == this.dictionaryFile) throw new RequestException("辞書ファイルを指定してください");
      if(null != this.dictionaryName) requestParams.put(PARAM_KEY_DICTIONARY_NAME, ParamUtils.paramEncode(String.valueOf(this.dictionaryName)));
      if(null != this.description) requestParams.put(PARAM_KEY_DESCRIPTION, this.description);
      HttpPut put = this.toHttpPutMethod(auth, ci, this.path(this.serviceName, this.categoryName, OPERATION_NAME), requestParams);
      
      MultipartEntity entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
      entity.addPart(PARAM_KEY_DICTIONARY_FILE, new FileBody(this.dictionaryFile));
      put.setEntity(entity);
      
      return put;
    }
    catch(UnsupportedEncodingException e) {
      throw new RequestException(e.getMessage());
    }
  }
}
