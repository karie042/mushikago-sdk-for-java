package org.mushikago.sdk.services.hanamgri.model;

import org.apache.http.client.methods.HttpGet;
import org.mushikago.sdk.common.annotation.RequestMetaData;
import org.mushikago.sdk.common.annotation.RequestParameter;

/**
 * SearchSchemaリクエストを表すクラスです
 * 
 * @author miningbrownie
 */
@RequestMetaData(path = "/1/hanamgri/domains/:domain_name/schema/search", httpMethodClass = HttpGet.class)
public class SearchSchemaRequest extends HanamgriRequest {
  /**
   * ドメイン名
   */
  @RequestParameter(name = "domain_name", isRequired = true, isPartOfURI = true)
  private String domainName = null;

  /**
   * 検索対象のフィールド名
   */
  @RequestParameter(name = "query_key", isRequired = true)
  private String queryKey = null;

  /**
   * 検索対象のフィールド値
   */
  @RequestParameter(name = "query_value", isRequired = true)
  private String queryValue = null;

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
   * 空のSearchSchemaリクエストを作成します
   */
  public SearchSchemaRequest() {
    this(null, null,null);
  }

  /**
   * ドメイン名と検索対象のフィールド名と検索対象のフィールド値を指定してSearchSchemaリクエストを作成します
   * 
   * @param domainName
   * @param query_key
   * @param query_value
   * @param limit 最大取得件数
   * @param offset 開始位置
   */
  public SearchSchemaRequest(String domainName, String queryKey, String queryValue) {
    super();
    this.domainName = domainName;
    this.queryKey = queryKey;
    this.queryValue = queryValue;
  }

  /**
   * ドメイン名を取得します
   * 
   * @return domainName
   */
  public String getDomainName() {
    return domainName;
  }

  /**
   * ドメイン名を設定します
   * 
   * @param domainName
   */
  public void setDomainName(String domainName) {
    this.domainName = domainName;
  }

  /**
   * 検索対象のフィールド名を取得します
   * 
   * @return quryKey
   */
  public String getQueryKey() {
    return queryKey;
  }

  /**
   * 検索対象のフィールド名を設定します
   * 
   * @param queryKey
   */
  public void setQueryKey(String queryKey) {
    this.queryKey = queryKey;
  }

  /**
   * 検索対象のフィールド値を取得します
   * 
   * @return queryValue
   */
  public String getQueryValue() {
    return queryValue;
  }

  /**
   * 検索対象のフィールド値を設定します
   * 
   * @param queryValue
   */
  public void setQueryValue(String queryValue) {
    this.queryValue = queryValue;
  }

  /**
   * 最大取得件数を取得します
   * 
   * @return limit
   */
  public Integer getLimit() {
    return limit;
  }

  /**
   * 最大取得件数を設定します
   * 
   * @param limit
   */
  public void setLimit(Integer limit) {
    this.limit = limit;
  }

  /**
   * 開始位置を取得します
   * 
   * @return offset
   */
  public Integer getOffset() {
    return offset;
  }

  /**
   * 開始位置を設定します
   * 
   * @param offset
   */
  public void setOffset(Integer offset) {
    this.offset = offset;
  }

}
