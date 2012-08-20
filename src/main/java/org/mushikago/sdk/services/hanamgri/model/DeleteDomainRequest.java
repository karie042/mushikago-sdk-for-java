package org.mushikago.sdk.services.hanamgri.model;

import org.apache.http.client.methods.HttpDelete;
import org.mushikago.sdk.common.annotation.RequestMetaData;
import org.mushikago.sdk.common.annotation.RequestParameter;

/**
 * DeleteDomainリクエストを表すクラスです
 * 
 * @author ope
 */
@RequestMetaData(path = "/1/hanamgri/domains/:domain_name", httpMethodClass = HttpDelete.class)
public class DeleteDomainRequest extends HanamgriRequest {

  /**
   * ドメイン名
   */
  @RequestParameter(name = "domain_name", isRequired = true, isPartOfURI = true)
  private String domainName = null;

  /**
   * 空のDeleteDomainリクエストを作成します
   */
  public DeleteDomainRequest() {
    this(null);
  }

  /**
   * ドメイン名を指定してDeleteDomainリクエストを作成します
   * 
   * @param domainName
   */
  public DeleteDomainRequest(String domainName) {
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
}
