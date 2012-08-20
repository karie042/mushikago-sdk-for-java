package org.mushikago.sdk.services.hanamgri.model;

import org.apache.http.client.methods.HttpGet;
import org.mushikago.sdk.common.annotation.RequestMetaData;
import org.mushikago.sdk.common.annotation.RequestParameter;

/**
 * GetQueueSizeリクエストを表すクラスです
 * 
 * @author ope
 */
@RequestMetaData(path = "/1/hanamgri/domains/:domain_name/queues", httpMethodClass = HttpGet.class)
public class GetQueueSizeRequest extends HanamgriRequest {
  /**
   * ドメイン名
   */
  @RequestParameter(name = "domain_name", isRequired = true, isPartOfURI = true)
  private String domainName = null;

  /**
   * 空のGetQueueSizeリクエストを作成します
   */
  public GetQueueSizeRequest() {
    this(null);
  }

  /**
   * ドメイン名を指定してGetQueueSizeリクエストを作成します
   * 
   * @param domainName
   * @param seeds
   */
  public GetQueueSizeRequest(String domainName) {
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
