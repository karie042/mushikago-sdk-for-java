package org.mushikago.sdk.services.hanamgri.model;

/**
 * 解析結果一覧で取得できるAnalysisリソースの表現。<br>
 * @author miningbrownie
 *
 */
public class Analysis {  
  /**
   * 解析結果検索用のID
   */
  private String requestId;
  
  /**
   * 解析結果保存先のurl
   */
  private String saveUrl;
  
  /**
   * 解析結果更新日
   */
  private String updatedAt;
  
  /**
   * 現在の解析の状態
   */
  private String status;
  
  /**
   * 解析結果のタグ
   */
  private String tag;
  
  public Analysis(String requestId, String saveUrl, String updatedAt, String status, String tag) {
    this.requestId = requestId;
    this.saveUrl = saveUrl;
    this.updatedAt = updatedAt;
    this.status = status;
    this.tag = tag;
  }
  
  /**
   * 解析結果検索用のIDを取得します。<br>
   * @return 解析結果検索用のID
   */
  public String getRequestId() {
    return requestId;
  }

  /**
   * 解析結果保存先のurlを取得します。<br>
   * @return 解析結果保存先のurl
   */
  public String getSaveUrl() {
    return saveUrl;
  }

  /**
   * 解析情報更新日を取得します。<br>
   * @return 解析情報更新日
   */
  public String getUpdatedAt() {
    return updatedAt;
  }

  /**
   * 現在の解析の状態を取得します。<br>
   * @return 現在の解析の状態
   */
  public String getStatus() {
    return status;
  }

  /**
   * 解析結果のtagを取得します。<br>
   * @return 解析結果に設定されているtag
   */
  public String getTag() {
    return tag;
  }

}
