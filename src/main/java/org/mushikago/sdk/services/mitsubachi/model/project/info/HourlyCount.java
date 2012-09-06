package org.mushikago.sdk.services.mitsubachi.model.project.info;

import java.util.List;

/**
 * プロジェクト情報で取得できるHourlyCountの表現。<br>
 * 
 * @author miningbrownie
 */

public class HourlyCount {

  /**
   * レスポンス(api_name)。<br>
   */
  private final String name;

  /**
   * レスポンス(callCounts)。<br>
   */
  private final List<Integer> callCounts;

  /**
   * 指定されたAPI名とコール数の配列を使用して、HourlyCountを構築します。<br>
   */
  public HourlyCount(String name, List<Integer> callCounts) {
    this.name = name;
    this.callCounts = callCounts;
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
   * APIコール数の配列の取得。<br>
   * 
   * @return APIコール数の配列
   */
  public List<Integer> getCallCounts() {
    return callCounts;
  }

}
