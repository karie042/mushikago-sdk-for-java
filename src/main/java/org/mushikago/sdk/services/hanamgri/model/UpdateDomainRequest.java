package org.mushikago.sdk.services.hanamgri.model;

import org.apache.http.client.methods.HttpPost;
import org.mushikago.sdk.common.annotation.RequestMetaData;
import org.mushikago.sdk.common.annotation.RequestParameter;

/**
 * UpdateDomainリクエストを表すクラスです
 * 
 * @author ope
 */
@RequestMetaData(path = "/1/hanamgri/domains/:domain_name", httpMethodClass = HttpPost.class)
public class UpdateDomainRequest extends HanamgriRequest {

  /**
   * ドメイン名
   */
  @RequestParameter(name = "domain_name", isRequired = true, isPartOfURI = true)
  private String domainName = null;
  
  /**
   * ドメインの説明
   */
  @RequestParameter(name = "description", isRequired = true)
  private String description = null;

  /**
   * 空のUpdateDomainリクエストを作成します
   */
  public UpdateDomainRequest() {
    this(null, null);
  }

  /**
   * ドメイン名とドメインの説明文を指定してUpdateDomainリクエストを作成します
   * 
   * @param domainName
   * @param description
   */
  public UpdateDomainRequest(String domainName, String description) {
    super();
    this.domainName = domainName;
    this.description = description;
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
   * descriptionを設定します
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
