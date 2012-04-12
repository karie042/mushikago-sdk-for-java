package org.mushikago.sdk.services.hotaru.model.collocation.get;

/**
 * collocation_getで指定した単語に対してスコアを持つ単語を表現します。<br>
 * @author miningbrownie
 *
 */
public class Word {
  
  /**
   * 名称
   */
  private final String name;
  
  /**
   * スコア
   */
  private final double score;
  
  /**
   * 指定された名称、スコアを使用して、Wordを構築します。<br>
   * @param name 名称
   * @param score スコア
   */
  public Word(String name, double score) {
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
  public double getScore() { return this.score; }
}
