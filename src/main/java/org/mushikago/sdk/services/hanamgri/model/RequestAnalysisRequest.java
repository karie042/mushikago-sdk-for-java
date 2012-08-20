package org.mushikago.sdk.services.hanamgri.model;

import org.apache.http.client.methods.HttpPut;
import org.mushikago.sdk.common.annotation.RequestMetaData;
import org.mushikago.sdk.common.annotation.RequestParameter;

@RequestMetaData(path = "/1/hanamgri/domains/:domain_name/analysis", httpMethodClass = HttpPut.class)
public class RequestAnalysisRequest extends HanamgriRequest {
  /**
   * ドメイン名
   */
  @RequestParameter(name = "domain_name", isRequired = true, isPartOfURI = true)
  private String domainName = null;

  /**
   * 解析対象URL
   */
  @RequestParameter(name = "url", isRequired = true)
  private String url = null;

  /**
   * 解析対象ページの文字コード
   */
  @RequestParameter(name = "charset")
  private String charset = null;

  /**
   * 検索用タグ
   */
  @RequestParameter(name = "tag")
  private String tag = null;
  
  /**
   * 自動学習するかどうか
   */
  @RequestParameter(name = "auto_feedback")
  private Boolean autoFeedback = true;
  

  public RequestAnalysisRequest() {
    this(null, null);
  }

  public RequestAnalysisRequest(String domainName, String url) {
    super();
    this.domainName = domainName;
    this.url = url;
  }

  public String getDomainName() {
    return domainName;
  }

  public void setDomainName(String domainName) {
    this.domainName = domainName;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getCharset() {
    return charset;
  }

  public void setCharset(String charset) {
    this.charset = charset;
  }

  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }
  
  public Boolean getAutoFeedback() {
      return autoFeedback;
    }

    public void setAutoFeedback(Boolean autoFeedback) {
      this.autoFeedback = autoFeedback;
    }
}
