package org.mushikago.sdk.services.mitsubachi.model.project.info;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.mushikago.sdk.services.mitsubachi.model.MitsubachiResponse;

/**
 * プロジェクトの情報取得に対するレスポンス。<br>
 * プロジェクトの設定と使用APIコール数を取得することができます。<br>
 * 
 * @author miningbrownie
 */
public class ProjectInfoResponse extends MitsubachiResponse {

  /**
   * レスポンスからcountパラメータを取得するキー。<br>
   */
  public static final String PARAM_KEY_COUNT = "count";

  /**
   * レスポンスからdedicatedパラメータを取得するキー。<br>
   */
  public static final String PARAM_KEY_DEDICATED = "dedicated";

  /**
   * レスポンスからmax_lead_timeパラメータを取得するキー。<br>
   */
  public static final String PARAM_KEY_MAX_LEAD_TIME = "max_lead_time";

  /**
   * レスポンスからstdoutパラメータを取得するキー。<br>
   */
  public static final String PARAM_KEY_STDOUT = "stdout";

  /**
   * レスポンスからstderrパラメータを取得するキー。<br>
   */
  public static final String PARAM_KEY_STDERR = "stderr";

  /**
   * レスポンスからsystem_logパラメータを取得するキー。<br>
   */
  public static final String PARAM_KEY_SYSTEMLOG = "system_log";

  /**
   * レスポンスからlog_prefixパラメータを取得するキー。<br>
   */
  public static final String PARAM_KEY_LOG_PREFIX = "log_prefix";

  /**
   * レスポンスからstorage_prefixパラメータを取得するキー。<br>
   */
  public static final String PARAM_KEY_STORAGE_PREFIX = "storage_prefix";

  /**
   * レスポンスからhourly_countsパラメータを取得するキー。<br>
   */
  public static final String PARAM_KEY_HOURLY_COUNTS = "hourly_counts";

  /**
   * レスポンスからdaily_countsパラメータを取得するキー。<br>
   */
  public static final String PARAM_KEY_DAILY_COUNTS = "daily_counts";

  /**
   * レスポンスからmonthly_countsパラメータを取得するキー。<br>
   */
  public static final String PARAM_KEY_MONTHLY_COUNTS = "monthly_counts";

  /**
   * レスポンスからnameパラメータを取得するキー。<br>
   */
  public static final String PARAM_KEY_NAME = "name";

  /**
   * レスポンスからcall_countsパラメータを取得するキー。<br>
   */
  public static final String PARAM_KEY_CALL_COUNTS = "call_counts";

  /**
   * レスポンスからcall_countパラメータを取得するキー。<br>
   */
  public static final String PARAM_KEY_CALL_COUNT = "call_count";

  /**
   * レスポンス(dedicated)。<br>
   */
  private final boolean isDedicated;

  /**
   * レスポンス(max_lead_time)。<br>
   */
  private final int maxLeadTime;

  /**
   * レスポンス(stdout)。<br>
   */
  private final boolean isStdoutOut;

  /**
   * レスポンス(stderr)。<br>
   */
  private final boolean isStderrOut;

  /**
   * レスポンス(system_log)。<br>
   */
  private final boolean isSystemLogOut;

  /**
   * レスポンス(log_prefix)。<br>
   */
  private final String logPrefix;

  /**
   * レスポンス(storage_prefix)。<br>
   */
  private final String storagePrefix;

  /**
   * レスポンス(hourly_counts)。<br>
   */
  private final List<HourlyCount> hourlyCounts = new ArrayList<HourlyCount>();

  /**
   * レスポンス(daily_counts)。<br>
   */
  private final List<DailyCount> dailyCounts = new ArrayList<DailyCount>();

  /**
   * レスポンス(monthly_counts)。<br>
   */
  private final List<MonthlyCount> monthlyCounts = new ArrayList<MonthlyCount>();

  /**
   * ProjectInfoResponseを構築します。<br>
   * 
   * @param json
   *          APIサーバからのレスポンス
   * @throws ProjectInfoException
   *           詳細情報が無かった場合
   */
  public ProjectInfoResponse(JSONObject json) {

    super(json);

    final JSONObject response = json.getJSONObject("response");

    this.isDedicated = "1".equals(response.getString(PARAM_KEY_DEDICATED));
    this.maxLeadTime = response.getInt(PARAM_KEY_MAX_LEAD_TIME);
    this.isStdoutOut = "1".equals(response.getString(PARAM_KEY_STDOUT));
    this.isStderrOut = "1".equals(response.getString(PARAM_KEY_STDERR));
    this.isSystemLogOut = "1".equals(response.getString(PARAM_KEY_SYSTEMLOG));
    this.logPrefix = response.getString(PARAM_KEY_LOG_PREFIX);
    this.storagePrefix = response.getString(PARAM_KEY_STORAGE_PREFIX);

    JSONArray hourlyCountsJsons = response.getJSONArray(PARAM_KEY_HOURLY_COUNTS);
    for (int i = 0; i < hourlyCountsJsons.size(); i++) {
      JSONObject hourlyCountJson = hourlyCountsJsons.getJSONObject(i);

      List<Integer> callCounts = new ArrayList<Integer>();
      JSONArray callCountsJsons = hourlyCountJson.getJSONArray(PARAM_KEY_CALL_COUNTS);
      for (int j = 0; j < callCountsJsons.size(); j++) {
        callCounts.add(callCountsJsons.getInt(j));
      }
      this.hourlyCounts.add(new HourlyCount(hourlyCountJson.getString(PARAM_KEY_NAME), callCounts));
    }

    JSONArray dailyCountsJsons = response.getJSONArray(PARAM_KEY_DAILY_COUNTS);
    for (int i = 0; i < dailyCountsJsons.size(); i++) {
      JSONObject dailyCountJson = dailyCountsJsons.getJSONObject(i);

      this.dailyCounts.add(new DailyCount(
        dailyCountJson.getString(PARAM_KEY_NAME),
        dailyCountJson.getInt(PARAM_KEY_CALL_COUNT))
                      );
    }

    JSONArray monthlyCountsJsons = response.getJSONArray(PARAM_KEY_MONTHLY_COUNTS);
    for (int i = 0; i < monthlyCountsJsons.size(); i++) {
      JSONObject monthlyCountJson = monthlyCountsJsons.getJSONObject(i);

      this.monthlyCounts.add(new MonthlyCount(
        monthlyCountJson.getString(PARAM_KEY_NAME),
        monthlyCountJson.getInt(PARAM_KEY_CALL_COUNT))
                      );
    }
  }

  /**
   * 専用プロジェクトか共用プロジェクトかを取得。<br>
   * 
   * @return true => 専用。false => 共用
   */
  public boolean getDedicated() {
    return this.isDedicated;
  }

  /**
   * 実行開始保証時間を取得。<br>
   * 
   * @return 実行開始保証時間
   */
  public int getMaxLeadTime() {
    return this.maxLeadTime;
  }

  /**
   * 標準出力の出力フラグを取得。<br>
   * 
   * @return true => 出力。false => 出力しない
   */
  public boolean getStdoutOut() {
    return this.isStdoutOut;
  }

  /**
   * 標準エラーの出力フラグを取得。<br>
   * 
   * @return true => 出力。false => 出力しない
   */
  public boolean getStderrOut() {
    return this.isStderrOut;
  }

  /**
   * システムログの出力フラグを取得。<br>
   * 
   * @return true => 出力。false => 出力しない
   */
  public boolean getSystemLogOut() {
    return this.isSystemLogOut;
  }

  /**
   * ログのプリフィックスを取得。<br>
   * 
   * @return プリフィックス
   */
  public String getLogPrefix() {
    return this.logPrefix;
  }

  /**
   * storage_prefixを取得。<br>
   * 
   * @return storage_prefix
   */
  public String getStoragePrefix() {
    return this.storagePrefix;
  }

  /**
   * hourly_countsを取得。<br>
   * 
   * @return hourly_counts
   */
  public List<HourlyCount> getHourlyCounts() {
    return this.hourlyCounts;
  }

  /**
   * daily_countsを取得。<br>
   * 
   * @return daily_counts
   */
  public List<DailyCount> getDailyCounts() {
    return this.dailyCounts;
  }

  /**
   * monthly_countsを取得。<br>
   * 
   * @return storage_prefix
   */
  public List<MonthlyCount> getMonthlyCounts() {
    return this.monthlyCounts;
  }

}
