package org.mushikago.sdk.services.hotaru.model.tag.list;

/**
 * タグ一覧で取得できるTag。<br>
 * @author miningbrownie
 *
 */
public class Tag {
  
  /**
   * タグ名
   */
  private final String name;
  
  /**
   * 出現回数
   */
  private final int count;
  
  /**
   * 指定されたタグ名、出現数を使用して、Tagを構築します。<br>
   * @param name タグ名
   * @param count 出現数
   */
  public Tag(String name, int count) {
    this.name = name;
    this.count = count;
  }
  
  /**
   * タグ名を取得します。<br>
   * @return タグ名
   */
  public String getName() { return this.name; }
  
  /**
   * 出現回数を取得します。<br>
   * @return 出現回数
   */
  public int getCount() { return this.count; }
}
