package org.mushikago.sdk.services.hotaru.model.dictionary.list;

/**
 * 辞書一覧で返ってくる辞書を現します。<br>
 * @author miningbrownie
 *
 */
public class Dictionary {
  
  /**
   * 辞書ID
   */
  private final String id;
  
  /**
   * 辞書名
   */
  private final String name;
  
  /**
   * 説明文
   */
  private final String description;
  
  /**
   * 作成日
   */
  private final String createDate;
  
  /**
   * サイズ
   */
  private final int size;
  
  /**
   * 指定されたID、辞書名、説明文、作成日、サイズを使用して、Dictionaryを構築します。<br>
   * @param id 辞書ID
   * @param name 辞書名
   * @param description 説明文
   * @param createDate 作成日
   * @param size サイズ
   */
  public Dictionary(String id, String name, String description, String createDate, int size) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.createDate = createDate;
    this.size = size;
  }
  
  /**
   * 辞書IDを取得します。<br>
   * @return 辞書ID
   */
  public String getId() { return this.id; }
  
  /**
   * 辞書名を取得します。<br>
   * @return 辞書名
   */
  public String getName() { return this.name; }
  
  /**
   * 説明文を取得します。<br>
   * @return 説明文
   */
  public String getDescription() { return this.description; }
  
  /**
   * 作成日を取得します。<br>
   * @return 作成日
   */
  public String getCreateDate() { return this.createDate; }
  
  /**
   * サイズを取得します。<br>
   * @return サイズ
   */
  public int getSize() { return this.size; }
}
