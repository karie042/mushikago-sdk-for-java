package org.mushikago.sdk.services.hanamgri.model;

import org.apache.http.client.methods.HttpGet;
import org.mushikago.sdk.common.annotation.RequestMetaData;
import org.mushikago.sdk.common.annotation.RequestParameter;

/**
 * ListAnalysesのリクエストを表すクラスです
 */

@RequestMetaData(path = "/1/hanamgri/domains/:domain_name/analyses", httpMethodClass = HttpGet.class)
public class ListAnalysesRequest extends HanamgriRequest {
  /**
   * ドメイン名
   */
  @RequestParameter(name = "domain_name", isRequired = true, isPartOfURI = true)
  private String domainName = null;

  /**
   * 最大取得件数
   */
  @RequestParameter(name = "limit")
  private Integer limit = null;

  /**
   * 開始位置
   */
  @RequestParameter(name = "offset")
  private Integer offset = null;

  /**
   * 検索文字の状態
   */
  @RequestParameter(name = "filter")
  private String filter = null;

  /**
   * 解析の状態
   */
  @RequestParameter(name = "status")
  private String status = null;

  /**
   * 空のListAnalysesリクエストを作成します。
   */
  public ListAnalysesRequest() {
    this(null);
  }
  
  /***
   * 指定されたdomain_nameを使用して ListAnalysesRequestオブジェクトを構築します。<br>
   * @param domain_name ドメイン名
   */
  public ListAnalysesRequest(String domainName){
    this.domainName = domainName;
  }

  /**
   * @return domainName ドメイン名を取得します
   */
  public String getDomainName() {
    return domainName;
  }

  /**
   * @param domainName をセットします domainName
   */
  public void setDomainName(String domainName) {
    this.domainName = domainName;
  }

  /**
   * 最大取得件数を取得します
   */
  public Integer getLimit() {
    return limit;
  }

  /**
   * 最大取得件数を設定します
   */
  public void setLimit(Integer limit) {
    this.limit = limit;
  }

  /**
   * 開始位置を取得します
   */
  public Integer getOffset() {
    return offset;
  }

  /**
   * 開始位置を設定します
   */
  public void setOffset(Integer offset) {
    this.offset = offset;
  }

  /**
   * 検索文字を取得します
   */
  public String getFilter() {
    return filter;
  }

  /**
   * 検索文字を設定します
   */
  public void setFilter(String filter) {
    this.filter = filter;
  }

  /**
   * 解析の状態を取得します
   */
  public String getStatus() {
    return status;
  }

  /**
   * 解析の状態を設定します
   */
  public void setStatus(String status) {
    this.status = status;
  }

}
