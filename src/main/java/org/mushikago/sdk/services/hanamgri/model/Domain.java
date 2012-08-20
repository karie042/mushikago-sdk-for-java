package org.mushikago.sdk.services.hanamgri.model;

/**
 * ドメイン一覧で取得できるドメインリソースの表現。<br>
 * @author miningbrownie
 *
 */
public class Domain {
  /**
   * ドメイン名
   */
  private String domainName;
  
  /**
   * ドメインの説明文
   */
  private String description;
  
  /**
   * ドメイン更新日
   */
  private String updatedAt;
  
  public Domain(String domainName, String description, String updatedAt) {
    this.domainName = domainName;
    this.description = description;
    this.updatedAt = updatedAt;
  }
  
  /**
   * ドメイン名を取得します。<br>
   * @return ドメイン名
   */
  public String getDomainName() {
    return domainName;
  }

  /**
   * ドメインの説明文を取得します。<br>
   * @return ドメインの説明文
   */
  public String getDescription() {
    return description;
  }

  /**
   * ドメインの更新日を取得します。<br>
   * @return ドメインの更新日
   */
  public String getUpdatedAt() {
    return updatedAt;
  }


}
