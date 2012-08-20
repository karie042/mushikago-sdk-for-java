package org.mushikago.sdk.services.hanamgri.model;

import org.apache.http.client.methods.HttpGet;
import org.mushikago.sdk.common.annotation.RequestMetaData;
import org.mushikago.sdk.common.annotation.RequestParameter;

/**
 * ListKnowledgesのリクエストを表すクラスです
 */

@RequestMetaData(path = "/1/hanamgri/knowledges", httpMethodClass = HttpGet.class)
public class ListKnowledgesRequest extends HanamgriRequest {
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
   * 学習データ保存の進捗状況
   */
  @RequestParameter(name = "status")
  private String status = null;

  /**
   * 空のListKnowledgesリクエストを作成します。
   */
  public ListKnowledgesRequest() {
    super();
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
   * 学習データ保存の進捗状況を取得します
   */
  public String getStatus() {
    return status;
  }

  /**
   * 学習データ保存の進捗状況を設定します
   */
  public void setStatus(String status) {
    this.status = status;
  }
}
