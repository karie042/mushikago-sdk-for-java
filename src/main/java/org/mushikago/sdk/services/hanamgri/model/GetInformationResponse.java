package org.mushikago.sdk.services.hanamgri.model;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GetInformationResponse extends HanamgriResponse {
/**
 * レスポンスからdomain_nameパラメータを取得するキー
 */
  private static final String PARAMETER_KEY_DOMAIN_NAME = "domain_name";

/**
 * レスポンスからdescriptionパラメータを取得するキー
 */
  private static final String PARAMETER_KEY_DESCRIPTION = "description";

/**
 * レスポンスからseedsパラメータを取得するキー
 */
  private static final String PARAMETER_KEY_SEEDS = "seeds";

/**
 * レスポンスからcreated_atパラメータを取得するキー
 */
  private static final String PARAMETER_KEY_CREATED_AT = "created_at";

/**
 * レスポンスからupdated_atパラメータを取得するキー
 */
  private static final String PARAMETER_KEY_UPDATED_AT = "updated_at";

/**
 * レスポンスからschemaパラメータを取得するキー
 */
  private static final String PARAMETER_KEY_SCHEMA = "schema";

  private String domainName;
  private String description;
  private List<String> seeds = new ArrayList<String>();
  private String createdAt;
  private String updatedAt;
  private Schema schema;

  public GetInformationResponse(JSONObject json) {
    super(json);
    domainName = getString(PARAMETER_KEY_DOMAIN_NAME);
    description = getString(PARAMETER_KEY_DESCRIPTION);
    createdAt = getString(PARAMETER_KEY_CREATED_AT);
    updatedAt = getString(PARAMETER_KEY_UPDATED_AT);
    schema = new Schema();

    JSONArray seedsJson = getJSONArray(PARAMETER_KEY_SEEDS);
    
    for(int i = 0; i < seedsJson.size(); i++) {
      if (seedsJson.getString(i) != null ){
        seeds.add(seedsJson.getString(i));
      }
    }
    
    final JSONObject jsonSchema = getJSONObject(PARAMETER_KEY_SCHEMA);
    final JSONArray jsonFields = jsonSchema.getJSONArray("fields");
    for(int i = 0; i < jsonFields.size(); i++) {
      final JSONObject jsonField = jsonFields.getJSONObject(i);
      String name = jsonField.getString("name");
      boolean required = jsonField.getBoolean("required");
      String type = jsonField.getString("type");
      Field field = new Field(name, required, type);
      schema.addField(field);
    }
  }

/**
 * ドメイン名を取得します
 */
  public String getDomainName() {
    return domainName;
  }

/**
 * ドメイン名を設定します
 */
  public void setDomainName(String domainName) {
    this.domainName = domainName;
  }

/**
 * 説明文を取得します
 */
  public String getDescription() {
    return description;
  }

/**
 * 説明文を設定します
 */
  public void setDescription(String description) {
    this.description = description;
  }

/**
 * シードを取得します
 */
  public List<String> getSeeds() {
    return seeds;
  }

/**
 * シードを設定します
 */
  public void setSeeds(List<String> seeds) {
    this.seeds = seeds;
  }

/**
 * 作成日を取得します
 */
  public String getCreatedAt() {
    return createdAt;
  }

/**
 * 作成日を設定します
 */
  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

/**
 * 更新日を取得します
 */
  public String getUpdatedAt() {
    return updatedAt;
  }

/**
 * 更新日を設定します
 */
  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }

/**
 * スキーマを取得します
 */
  public Schema getSchema() {
    return this.schema;
  }

/**
 * スキーマを設定します
 */
  public void setSchema(Schema schema) {
    this.schema = schema;
  }

}
