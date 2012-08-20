package org.mushikago.sdk.services.hanamgri.model;

import org.apache.http.client.methods.HttpPut;
import org.mushikago.sdk.common.annotation.RequestMetaData;
import org.mushikago.sdk.common.annotation.RequestParameter;

/**
 * SaveKnowledgeリクエストを表すクラスです
 * 
 * @author ope
 */
@RequestMetaData(path = "/1/hanamgri/knowledge", httpMethodClass = HttpPut.class)
public class SaveKnowledgeRequest extends HanamgriRequest {

  /**
   * ドメイン名
   */
  @RequestParameter(name = "domain_name", isRequired = true)
  private String domainName = null;

  /**
   * フィールド名
   */
  @RequestParameter(name = "field_name", isRequired = true)
  private String fieldName = null;

  /**
   * ドメインの説明
   */
  @RequestParameter(name = "description")
  private String description = null;

  /**
   * 空のSaveKnowledgeリクエストを作成します
   */
  public SaveKnowledgeRequest() {
    this(null, null);
  }

  /**
   * ドメイン名を指定してSaveKnowledgeリクエストを作成します
   * 
   * @param domainName
   */
  public SaveKnowledgeRequest(String domainName, String fieldName) {
    super();
    this.domainName = domainName;
    this.fieldName = fieldName;
  }

  /**
   * domain_nameを取得します
   * 
   * @return domainName
   */
  public String getDomainName() {
    return domainName;
  }

  /**
   * domain_nameを設定します
   * 
   * @param domainName
   */
  public void setDomainName(String domainName) {
    this.domainName = domainName;
  }

  /**
   * descriptionを取得します
   * 
   * @return description
   */
  public String getDescription() {
    return description;
  }

  /**
   * descriptionを設定します
   * 
   * @param description
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * field_nameを取得します
   * @return
   */
  public String getFieldName() {
    return fieldName;
  }

  /**
   * field_nameを設定します
   * @param fieldName
   */
  public void setFieldName(String fieldName) {
    this.fieldName = fieldName;
  }
}
