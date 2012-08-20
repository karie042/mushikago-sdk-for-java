package org.mushikago.sdk.services.hanamgri.model;

import org.apache.http.client.methods.HttpPut;
import org.mushikago.sdk.common.annotation.RequestMetaData;
import org.mushikago.sdk.common.annotation.RequestParameter;

/**
 * SaveDictionaryリクエストを表すクラスです
 * 
 * @author ope
 */
@RequestMetaData(path = "/1/hanamgri/dictionary", httpMethodClass = HttpPut.class)
public class SaveDictionaryRequest extends HanamgriRequest {

  /**
   * ドメイン名
   */
  @RequestParameter(name = "domain_name", isRequired = true)
  private String domainName = null;

  /**
   * ドメインの説明
   */
  @RequestParameter(name = "description")
  private String description = null;

  /**
   * 空のSaveDictionaryリクエストを作成します
   */
  public SaveDictionaryRequest() {
    this(null);
  }

  /**
   * ドメイン名を指定してSaveDictionaryリクエストを作成します
   * 
   * @param domainName
   */
  public SaveDictionaryRequest(String domainName) {
    super();
    this.domainName = domainName;
  }

  /**
   * domain_nameを取得します
   * 
   * @return
   */
  public String getDomainName() {
    return domainName;
  }

  /**
   * domain_nameを設定します
   * 
   * @param domainName
   */
  public void setDomainName(String domainName) {
    this.domainName = domainName;
  }

  /**
   * descriptionを取得します
   * 
   * @return
   */
  public String getDescription() {
    return description;
  }

  /**
   * descriptionを設定します
   * 
   * @return
   */
  public void setDescription(String description) {
    this.description = description;
  }
}
