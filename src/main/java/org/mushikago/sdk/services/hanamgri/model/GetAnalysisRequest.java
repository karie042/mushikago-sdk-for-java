package org.mushikago.sdk.services.hanamgri.model;

import org.apache.http.client.methods.HttpGet;
import org.mushikago.sdk.common.annotation.RequestMetaData;
import org.mushikago.sdk.common.annotation.RequestParameter;

@RequestMetaData(path = "/1/hanamgri/domains/:domain_name/analyses/:request_id", httpMethodClass = HttpGet.class)
public class GetAnalysisRequest extends HanamgriRequest {
  /**
   * ドメイン名
   */
  @RequestParameter(name = "domain_name", isRequired = true, isPartOfURI = true)
  private String domainName = null;

  /**
   * 解析結果取得用のID
   */
  @RequestParameter(name = "request_id", isRequired = true, isPartOfURI = true)
  private String requestId = null;

  public GetAnalysisRequest() {
    this(null, null);
  }

  public GetAnalysisRequest(String domainName, String requestId) {
    super();
    this.domainName = domainName;
    this.requestId = requestId;
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

  /**
   * 解析結果取得用のIDを取得します
   */
  public String getRequestId() {
    return requestId;
  }

  /**
   * 解析結果取得用のIDを設定します
   */
  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }

}
