package org.mushikago.sdk.services.hotaru.model.word;

/**
 * 単語を表現します。<br>
 * @author miningbrownie
 *
 */
public class Word {
  
  /**
   * 名称
   */
  private final String name;
  
  /**
   * 出現数
   */
  private final int count;
  
  /**
   * 指定された名称、出現数を使用して、Wordを構築します。<br>
   * @param name 名称
   * @param count 出現数
   */
  public Word(String name, int count) {
    this.name = name;
    this.count = count;
  }
  
  /**
   * 名称を取得します。<br>
   * @return 名称
   */
  public String getName() { return this.name; }
  
  /**
   * 出現数を取得します。<br>
   * @return 出現数
   */
  public int getCount() { return this.count; }
}
