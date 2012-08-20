package org.mushikago.sdk.services.hanamgri.model;

/**
 * Dictionaryリソースの表現
 * 
 * @author ope
 */
public class Dictionary {
  private String dictionaryName;
  private String description;
  private String createdAt;

  /**
   * 辞書名を取得します
   * 
   * @return
   */
  public String getDictionaryName() {
    return dictionaryName;
  }

  /**
   * 辞書名を設定します
   * 
   * @param dictionaryName
   */
  public void setDictionaryName(String dictionaryName) {
    this.dictionaryName = dictionaryName;
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
