package org.mushikago.sdk.services.hanamgri.model;

import org.apache.http.client.methods.HttpDelete;
import org.mushikago.sdk.common.annotation.RequestMetaData;
import org.mushikago.sdk.common.annotation.RequestParameter;

/**
 * DeleteKnowledgeリクエストを表すクラスです
 * 
 * @autor ope
 */

@RequestMetaData(path = "/1/hanamgri/knowledge", httpMethodClass = HttpDelete.class)
public class DeleteKnowledgeRequest extends HanamgriRequest {
  /**
   * 学習データ名
   */
  @RequestParameter(name = "knowledge_name", isRequired = true)
  private String knowledgeName = null;

  /**
   * 空のDeleteKnowledgeリクエストを作成します
   */
  public DeleteKnowledgeRequest() {
    this(null);
  }

  /**
   * 学習データ名を指定してDeleteKnowledgeリクエストを作成します
   * 
   * @param knowledgeName
   */
  public DeleteKnowledgeRequest(String knowledgeName) {
    super();
    this.knowledgeName = knowledgeName;
  }

  /**
   * knowledgeNameを取得します
   * 
   * @return knowledge_name
   */
  public String getKnowledgeName() {
    return knowledgeName;
  }

  /**
   * knowledgeNameを設定します
   * 
   * @param knowledgeName
   */
  public void setKnowledgeName(String knowledgeName) {
    this.knowledgeName = knowledgeName;
  }

}
