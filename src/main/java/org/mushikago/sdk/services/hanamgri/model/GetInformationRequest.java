package org.mushikago.sdk.services.hanamgri.model;

import org.apache.http.client.methods.HttpGet;
import org.mushikago.sdk.common.annotation.RequestMetaData;
import org.mushikago.sdk.common.annotation.RequestParameter;

/**
 * GetInformationリクエストを表すクラスです
 * 
 * @author ope
 */
@RequestMetaData(path = "/1/hanamgri/domains/:domain_name", httpMethodClass = HttpGet.class)
public class GetInformationRequest extends HanamgriRequest {
  /**
   * ドメイン名
   */
  @RequestParameter(name = "domain_name", isRequired = true, isPartOfURI = true)
  private String domainName = null;

  /**
   * 空のGetInformationリクエストを作成します
   */
  public GetInformationRequest() {
    this(null);
  }

  /**
   * ドメイン名を指定してGetInformationリクエストを作成します
   * 
   * @param domainName
   */
  public GetInformationRequest(String domainName) {
    super();
    this.domainName = domainName;
  }

  /**
   * ドメイン名を取得します
   */
  public String getDomainName() {
    return domainName;
  }

  /**
   * ドメイン名を設定します
   */
  public void setDomainName(String domainName) {
    this.domainName = domainName;
  }
}
