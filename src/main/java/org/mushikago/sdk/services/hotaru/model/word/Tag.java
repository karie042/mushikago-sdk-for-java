package org.mushikago.sdk.services.hotaru.model.word;

/**
 * word_getで取得できるタグを表現します。<br>
 * @author miningbrownie
 *
 */
public class Tag {
  
  /**
   * タグの名称。<br>
   */
  private final String name;
  
  /**
   * タグがつけられた数
   */
  private final int count;
  
  /**
   * 指定された名称、タグ数を使用して、Tagを構築します。<br>
   * @param name 名称
   * @param count タグがつけられた数
   */
  public Tag(String name, int count) {
    this.name = name;
    this.count = count;
  }
  
  /**
   * 名称を取得します。<br>
   * @return 名称
   */
  public String getName() { return this.name; }
  
  /**
   * タグが付けられた数を取得します。<br>
   * @return タグが付けられた数
   */
  public int  getCount() { return this.count; }
}
