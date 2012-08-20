package org.mushikago.sdk.services.hanamgri.model;

import org.apache.http.client.methods.HttpGet;
import org.mushikago.sdk.common.annotation.RequestMetaData;
import org.mushikago.sdk.common.annotation.RequestParameter;

/**
 * ListDomainsのリクエストを表すクラスです
 */

@RequestMetaData(path = "/1/hanamgri/domains", httpMethodClass = HttpGet.class)
public class ListDomainsRequest extends HanamgriRequest {

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
   * 検索文字（先頭一致）
   */
  @RequestParameter(name = "filter")
  private String filter = null;

  /**
   * 空のListDomainsリクエストを作成します。
   */
  public ListDomainsRequest() {}
  
  /***
   * 指定されたlimit,offset,filter,statusを仕様して ListDomainsRequestオブジェクトを構築します。<br>
   * @param limit 最大取得件数
   * @param offset 開始位置
   * @param filter 検索文字列（先頭一致）
   */
  public ListDomainsRequest(Integer limit, Integer offset, String filter){
    this.limit = limit;
    this.offset = offset;
    this.filter = filter;
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

}
