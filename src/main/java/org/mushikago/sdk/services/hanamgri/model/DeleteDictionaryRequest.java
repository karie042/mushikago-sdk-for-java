package org.mushikago.sdk.services.hanamgri.model;

import org.apache.http.client.methods.HttpDelete;
import org.mushikago.sdk.common.annotation.RequestMetaData;
import org.mushikago.sdk.common.annotation.RequestParameter;

/**
 * DeleteDictionaryリクエストを表すクラスです
 * 
 * @autor ope
 */

@RequestMetaData(path = "/1/hanamgri/dictionary", httpMethodClass = HttpDelete.class)
public class DeleteDictionaryRequest extends HanamgriRequest {
  /**
   * 辞書名
   */
  @RequestParameter(name = "dictionary_name", isRequired = true)
  private String dictionaryName = null;

  /**
   * 空のDeleteDictionaryリクエストを作成します
   */
  public DeleteDictionaryRequest() {
    this(null);
  }

  /**
   * 辞書名を指定してDeleteDictionaryリクエストを作成します
   * 
   * @param dictionaryName
   */
  public DeleteDictionaryRequest(String dictionaryName) {
    super();
    this.dictionaryName = dictionaryName;
  }

  /**
   * dictionaryNameを取得します
   * 
   * @return dictionary_name
   */
  public String getDictionaryName() {
    return dictionaryName;
  }

  /**
   * dictionaryNameを設定します
   * 
   * @param dictionaryName
   */
  public void setDictionaryName(String dictionaryName) {
    this.dictionaryName = dictionaryName;
  }

}
