package org.mushikago.sdk.services.hanamgri.model;

import org.apache.http.client.methods.HttpPut;
import org.mushikago.sdk.common.annotation.RequestMetaData;
import org.mushikago.sdk.common.annotation.RequestParameter;

/**
 * CreateDomainリクエストを表すクラスです
 * 
 * @author ope
 */
@RequestMetaData(path = "/1/hanamgri/domains/:domain_name", httpMethodClass = HttpPut.class)
public class CreateDomainRequest extends HanamgriRequest {

  /**
   * ドメイン名
   */
  @RequestParameter(name = "domain_name", isRequired = true, isPartOfURI = true)
  private String domainName = null;

  /**
   * シード
   */
  @RequestParameter(name = "seeds", isRequired = true)
  private String seeds = null;

  /**
   * スキーマ
   */
  @RequestParameter(name = "schema", isRequired = true)
  private Schema schema = null;

  /**
   * ドメインの説明
   */
  @RequestParameter(name = "description")
  private String description = null;

  /**
   * プリセットに使用する辞書
   */
  @RequestParameter(name = "dictionary_name")
  private String dictionaryName = null;

  /**
   * 空のCreateDomainリクエストを作成します
   */
  public CreateDomainRequest() {
    this(null, null, null);
  }

  /**
   * ドメイン名とシードとスキーマを指定してCreateDomainリクエストを作成します
   * 
   * @param domainName
   * @param seeds
   * @param schema
   */
  public CreateDomainRequest(String domainName, String seeds, Schema schema) {
    super();
    this.domainName = domainName;
    this.seeds = seeds;
    this.setSchema(schema);
  }

  /**
   * domain_nameを取得します
   * 
   * @return
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
   * seedsを取得します
   * 
   * @return
   */
  public String getSeeds() {
    return seeds;
  }

  /**
   * seedsを設定します
   * 
   * @param seeds
   */
  public void setSeeds(String seeds) {
    this.seeds = seeds;
  }

  /**
   * schemaを取得します
   * 
   * @return
   */
  public Schema getSchema() {
    return schema;
  }

  /**
   * schemaを設定します
   * 
   * @param schema
   */
  public void setSchema(Schema schema) {
    this.schema = schema;
  }

  /**
   * descriptionを取得します
   * 
   * @return
   */
  public String getDescription() {
    return description;
  }

  /**
   * descriptionを設定します
   * 
   * @return
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * dictionary_nameを取得します
   * 
   * @return
   */
  public String getDictionaryName() {
    return dictionaryName;
  }

  /**
   * dictionary_nameを設定します
   * 
   * @return
   */

  public void setDictionaryName(String dictionaryName) {
    this.dictionaryName = dictionaryName;
  }
}
