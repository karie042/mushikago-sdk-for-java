package org.mushikago.sdk.services.mitsubachi.model.project.info;

/**
 * プロジェクト情報で取得できるMonthlyCountの表現。<br>
 * 
 * @author miningbrownie
 */
public class MonthlyCount {
  /**
   * レスポンス(api_name)。<br>
   */
  private final String name;

  /**
   * レスポンス(count)。<br>
   */
  private final int count;

  public MonthlyCount(String name, int count) {
    this.name = name;
    this.count = count;
  }

  /**
   * API名の取得。<br>
   * 
   * @return API名
   */
  public String getName() {
    return name;
  }

  /**
   * APIコール数の取得。<br>
   * 
   * @return APIコール数
   */
  public int getCount() {
    return count;
  }
}
