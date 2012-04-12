package org.mushikago.sdk.services.hotaru.model.classifier.judge;

/**
 * タグ判定で返されるタグを表現します。<br>
 * @author miningbrownie
 *
 */
public class Tag {
  
  /**
   * 名称
   */
  private final String name;
  
  /**
   * スコア
   */
  private final int score;
  
  /**
   * 指定された名称、スコアを使用してTagを構築します。<br>
   * @param name 名称
   * @param score スコア
   */
  public Tag(String name, int score) {
    this.name = name;
    this.score = score;
  }
  
  /**
   * 名称を取得します。<br>
   * @return 名称
   */
  public String getName() { return this.name; }
  
  /**
   * スコアを取得します。<br>
   * @return スコア
   */
  public int getScore() { return this.score; }
}
