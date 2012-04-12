package org.mushikago.sdk.services.hotaru.model.dictionary.delete;

import java.io.UnsupportedEncodingException;
import java.util.TreeMap;

import org.apache.http.client.methods.HttpRequestBase;
import org.mushikago.sdk.common.RequestException;
import org.mushikago.sdk.common.auth.MushikagoAuth;
import org.mushikago.sdk.common.util.ConnectInfo;
import org.mushikago.sdk.common.util.ParamUtils;
import org.mushikago.sdk.services.hotaru.model.dictionary.DictionaryRequest;

/**
 * 辞書削除リクエスト。<br>
 * @author miningbrownie
 */
public class DictionaryDeleteRequest extends DictionaryRequest {
  
  /**
   * 操作名
   */
  public static final String OPERATION_NAME = "delete";
  
  /**
   * パラメータ名（辞書ID）
   */
  public static final String PARAM_KEY_DICTIONARY_ID = "dictionary_id";
  
  /**
   * パラメータ（辞書ID）
   */
  private String id = null;
  
  /**
   * パラメータを持たない、空のDictionaryDeleteRequestを構築します。<br>
   */
  public DictionaryDeleteRequest() {}
  
  /**
   * 指定された辞書IDを使用して、DictionaryDeleteRequestを構築します。<br>
   * @param id 辞書ID
   */
  public DictionaryDeleteRequest(String id) {
    this.id = id;
  }
  
  /**
   * 辞書IDを取得します。<br>
   * @return 辞書ID
   */
  public String getId() { return this.id; }
  
  /**
   * 辞書IDを設定します。<br>
   * @param id 辞書ID
   */
  public void setId(String id) { this.id = id; }
  
  @Override
  public HttpRequestBase toHttpMethod(MushikagoAuth auth, ConnectInfo ci) throws RequestException {
    
    try {
      TreeMap<String, String> requestParams = new TreeMap<String, String>();
      if(null == this.id) throw new RequestException("辞書IDを指定してください");
      requestParams.put(PARAM_KEY_DICTIONARY_ID, ParamUtils.paramEncode(String.valueOf(this.id)));
      return this.toHttpDeleteMethod(auth, ci, this.path(this.serviceName, this.categoryName, OPERATION_NAME), requestParams);
    }
    catch(UnsupportedEncodingException e) {
      throw new RequestException(e.getMessage());
    }
  }
}
