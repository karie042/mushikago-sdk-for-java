package org.mushikago.sdk.services.hotaru.model.text.list;

/**
 * テキスト一覧で取得できるテキスト情報。<br>
 * @author miningbrownie
 *
 */
public class Text {
  
  /**
   * テキストのステータス定義。<br>
   * @author miningbrownie
   *
   */
  public static enum Status {
    
    ACCEPTED("accepted"),
    PREPROCESSING("preprocessing"),
    WAIT_PROCESSING("wait_processing"),
    PROCESSING("processing"),
    COMPLETE("complete"),
    ERROR("error");
    
    private final String key;
    private Status(String key) { this.key = key; }
    @Override public String toString() { return this.key; }
  }
  
  /**
   * テキストID
   */
  private final String textId;
  
  /**
   * テキスト説明文
   */
  private final String description;
  
  /**
   * タグ一覧
   */
  private final String[] tags;
  
  /**
   * 作成日
   */
  private final String createDate;
  
  /**
   * ステータス
   */
  private final Status status;
  
  /**
   * 指定されたID、説明文、タグ一覧、作成日、ステータスを使用して、Textを構築します。<br>
   * @param id テキストID
   * @param description 説明文
   * @param tags タグ一覧
   * @param createDate 作成日
   * @param status ステータス
   */
  public Text(String id, String description, String[] tags, String createDate, Status status) {
    this.textId = id;
    this.description = description;
    this.tags = tags;
    this.createDate = createDate;
    this.status = status;
  }
  
  /**
   * テキストIDを取得します。<br>
   * @return テキストID
   */
  public String getTextId() { return this.textId; }
  
  /**
   * 説明文を取得します。<br>
   * @return テキスト説明文
   */
  public String getDescription() { return this.description; }
  
  /**
   * タグ一覧を取得します。<br>
   * @return タグ一覧
   */
  public String[] getTags() { return this.tags.clone(); }
  
  /**
   * 作成日を取得します。<br>
   * @return 作成日
   */
  public String getCreateDate() { return this.createDate; }
  
  /**
   * ステータスを取得します。<br>
   * @return ステータス
   */
  public Status getStatus() { return this.status; }
}
