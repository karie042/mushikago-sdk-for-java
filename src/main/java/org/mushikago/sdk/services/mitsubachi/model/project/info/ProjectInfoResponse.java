package org.mushikago.sdk.services.mitsubachi.model.project.info;

import net.sf.json.JSONObject;

import org.mushikago.sdk.services.mitsubachi.model.MitsubachiResponse;

/**
 * プロジェクトの取得に対するレスポンス。<br>
 * リクエストで指定した「time」時間（分）、遡っての情報を保持します。<br>
 * つまり、APIコール数や最大ストレージ容量は、指定した時間によって変動する可能性があります。<br>
 * @author miningbrownie
 *
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
	 * レスポンスからusageパラメータを取得するキー。<br>
	 */
	public static final String PARAM_KEY_USAGE = "usage";
	
	/**
	 * レスポンスからstorage_prefixパラメータを取得するキー。<br>
	 */
	public static final String PARAM_KEY_STORAGE_PREFIX = "storage_prefix";
	
	/**
	 * レスポンス(count)。<br>
	 */
	private final long count;
	
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
	 * レスポンス(usage)。<br>
	 */
	private final long usage;
	
	/**
	 * レスポンス(storage_prefix)。<br>
	 */
	private final String storagePrefix;
	
	/**
	 * ProjectInfoResponseを構築します。<br>
	 * @param json APIサーバからのレスポンス
	 * @throws ProjectInfoException 詳細情報が無かった場合
	 */
	public ProjectInfoResponse(JSONObject json) {
		
		super(json);
		
		final JSONObject response = json.getJSONObject("response");
		
		this.count = response.getLong(PARAM_KEY_COUNT);
		this.isDedicated = "1".equals(response.getString(PARAM_KEY_DEDICATED));
		this.maxLeadTime = response.getInt(PARAM_KEY_MAX_LEAD_TIME);
		this.isStdoutOut = "1".equals(response.getString(PARAM_KEY_STDOUT));
		this.isStderrOut = "1".equals(response.getString(PARAM_KEY_STDERR));
		this.isSystemLogOut = "1".equals(response.getString(PARAM_KEY_SYSTEMLOG));
		this.logPrefix = response.getString(PARAM_KEY_LOG_PREFIX);
		this.usage = response.getLong(PARAM_KEY_USAGE);
		this.storagePrefix = response.getString(PARAM_KEY_STORAGE_PREFIX);
	}
	
	/**
	 * APIコール数を取得。<br>
	 * @return APIコール数
	 */
	public long getCount() { return this.count; }
	
	/**
	 * 専用プロジェクトか共用プロジェクトかを取得。<br>
	 * @return true => 専用。false => 共用
	 */
	public boolean getDedicated() { return this.isDedicated; }
	
	/**
	 * 実行開始保証時間を取得。<br>
	 * @return 実行開始保証時間
	 */
	public int getMaxLeadTime() { return this.maxLeadTime; }
	
	/**
	 * 標準出力の出力フラグを取得。<br>
	 * @return true => 出力。false => 出力しない
	 */
	public boolean getStdoutOut() { return this.isStdoutOut; }
	
	/**
	 * 標準エラーの出力フラグを取得。<br>
	 * @return true => 出力。false => 出力しない
	 */
	public boolean getStderrOut() { return this.isStderrOut; }
	
	/**
	 * システムログの出力フラグを取得。<br>
	 * @return true => 出力。false => 出力しない
	 */
	public boolean getSystemLogOut() { return this.isSystemLogOut; }
	
	/**
	 * ログのプリフィックスを取得。<br>
	 * @return プリフィックス
	 */
	public String getLogPrefix() { return this.logPrefix; }
	
	/**
	 * 最大ストレージ容量を取得。<br>
	 * @return 容量
	 */
	public long getUsage() { return this.usage; }
	
	/**
	 * storage_prefixを取得。<br>
	 * @return storage_prefix
	 */
	public String getStoragePrefix() { return this.storagePrefix; }
}
