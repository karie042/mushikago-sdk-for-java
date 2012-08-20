package org.mushikago.sdk.services.hanamgri.model;

/**
 * Knowledgeリソースの表現
 * 
 * @author ope
 */
public class Knowledge {
  private String knowledgeName;
  private String description;
  private String status;
  private String createdAt;

  /**
   * 学習データ名を取得します
   * 
   * @return
   */
  public String getKnowledgeName() {
    return knowledgeName;
  }

  /**
   * 学習データ名を設定します
   * 
   * @param knowledgeName
   */
  public void setKnowledgeName(String knowledgeName) {
    this.knowledgeName = knowledgeName;
  }

  /**
   * 説明文を取得します
   * 
   * @return
   */
  public String getDescription() {
    return description;
  }

  /**
   * 説明文を設定します
   * 
   * @param description
   */
  public void setDescription(String description) {
    this.description = description;
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

  /**
   * 作成日を取得します
   * 
   * @return
   */
  public String getCreatedAt() {
    return createdAt;
  }

  /**
   * 作成日を設定します
   * 
   * @param createdAt
   */
  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }
}
