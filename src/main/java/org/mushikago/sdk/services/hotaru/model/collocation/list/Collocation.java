package org.mushikago.sdk.services.hotaru.model.collocation.list;

/**
 * 共起グラフを表現します。<br>
 * @author miningbrownie
 *
 */
public class Collocation {
  
  /**
   * 共起グラフID
   */
  private final String id;
  
  /**
   * 共起グラフ名
   */
  private final String name;
  
  /**
   * タグ一覧
   */
  private final String[] tags;
  
  /**
   * 品詞一覧
   */
  private final String[] partsOfSpeech;
  
  /**
   * 単語出現数下限
   */
  private final int lowerThreshold;
  
  /**
   * 単語出現数上限
   */
  private final int upperThreshold;
  
  /**
   * 説明文
   */
  private final String description;
  
  /**
   * 作成日
   */
  private final String createDate;
  
  /**
   * ステータス
   */
  private final Status status;
  
  /**
   * 指定されたID、名称、タグ一覧、品詞一覧、単語出現数下限、単語出現数上限、説明文、作成日、ステータスを使用して、Collocationを構築します。<br>
   * @param id
   * @param name
   * @param tags
   * @param partsOfSpeech
   * @param lowerThreshold
   * @param upperThreshold
   * @param description
   * @param createDate
   * @param status
   */
  public Collocation(
    String id,
    String name,
    String[] tags,
    String[] partsOfSpeech,
    int lowerThreshold,
    int upperThreshold,
    String description,
    String createDate,
    Status status
  ) {
    this.id = id;
    this.name = name;
    this.tags = tags;
    this.partsOfSpeech = partsOfSpeech;
    this.lowerThreshold = lowerThreshold;
    this.upperThreshold = upperThreshold;
    this.description = description;
    this.createDate = createDate;
    this.status = status;
  }
  
  /**
   * 共起グラフIDを取得します。<br>
   * @return 共起グラフID
   */
  public String getId() { return this.id; }
  
  /**
   * 共起グラフ名を取得します。<br>
   * @return 共起グラフ名
   */
  public String getName() { return this.name; }
  
  /**
   * タグ一覧を取得します。<br>
   * @return タグ一覧
   */
  public String[] getTags() { return this.tags.clone(); }
  
  /**
   * 品詞一覧を取得します。<br>
   * @return 品詞一覧
   */
  public String[] getPartsOfSpeech() { return this.partsOfSpeech; }
  
  /**
   * 単語出現数下限を取得します。<br>
   * @return 単語出現数下限
   */
  public int getLowerThreshold() { return this.lowerThreshold; }
  
  /**
   * 単語出現数上限を取得します。<br>
   * @return 単語出現数上限
   */
  public int getUpperThreshold() { return this.upperThreshold; }
  
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
   * ステータスを取得します。<br>
   * @return ステータス
   */
  public Status getStatus() { return this.status; }
}
