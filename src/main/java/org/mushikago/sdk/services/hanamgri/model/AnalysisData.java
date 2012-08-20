package org.mushikago.sdk.services.hanamgri.model;

import java.util.List;

/**
 * 解析結果リソースの表現。<br>
 * 
 * @author miningbrownie
 */
public class AnalysisData {

  /**
   * 解析結果のブロック一覧。
   */
  private List<String> blocks;

  /**
   * 解析結果のフィールド一覧。
   */
  private List<FieldSet> results;

  /**
   * 解析結果のブロック一覧を取得します。
   */
  public List<String> getBlocks() {
    return blocks;
  }

  /**
   * 解析結果のブロック一覧を設定します。
   * 
   * @param blocks
   *          ブロック
   */
  public void setBlocks(List<String> blocks) {
    this.blocks = blocks;
  }

  /**
   * 解析結果のフィールド一覧を取得します。
   * 
   * @return
   */
  public List<FieldSet> getResults() {
    return results;
  }

  /**
   * 解析結果のフィールド一覧を設定します。
   * 
   * @return
   */
  public void setResults(List<FieldSet> results) {
    this.results = results;
  }
}
