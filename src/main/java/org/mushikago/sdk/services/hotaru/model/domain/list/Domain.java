package org.mushikago.sdk.services.hotaru.model.domain.list;

import org.mushikago.sdk.services.hotaru.model.domain.list.DomainListRequest.Status;

/**
 * ドメイン一覧で取得できるDomain。<br>
 * @author miningbrownie
 *
 */
public class Domain {
  
  /**
   * ドメイン名。<br>
   */
  private final String name;
  
  /**
   * ドメインの説明文。<br>
   */
  private final String description;
  
  /**
   * ドメインの作成日。<br>
   */
  private final String createDate;
  
  /**
   * ドメインのステータス。<br>
   */
  private final Status status;
  
  /**
   * 指定されたドメイン名、説明文、作成日、ステータスを使用して、Domainオブジェクトを構築します。<br>
   * @param name ドメイン名
   * @param description 説明文
   * @param createDate 作成日
   * @param status ステータス
   */
  public Domain(String name, String description, String createDate, Status status) {
    this.name = name;
    this.description = description;
    this.createDate = createDate;
    this.status = status;
  }
  
  /**
   * ドメイン名を取得します。<br>
   * @return ドメイン名
   */
  public String getName() { return this.name; }
  
  /**
   * ドメインの説明文を取得します。<br>
   * @return ドメインの説明文
   */
  public String getDescription() { return this.description; }
  
  /**
   * ドメインの作成日を取得します。<br>
   * @return ドメインの作成日
   */
  public String getCreateDate() { return this.createDate; }
  
  /**
   * ドメインのステータスを取得します。<br>
   * @return ドメインのステータス
   */
  public Status getStatus() { return this.status; }
}
