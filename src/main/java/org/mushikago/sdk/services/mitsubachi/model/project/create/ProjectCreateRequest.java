package org.mushikago.sdk.services.mitsubachi.model.project.create;

import java.io.UnsupportedEncodingException;
import java.util.TreeMap;

import org.apache.http.client.methods.HttpRequestBase;
import org.mushikago.sdk.common.RequestException;
import org.mushikago.sdk.common.auth.MushikagoAuth;
import org.mushikago.sdk.common.util.ConnectInfo;
import org.mushikago.sdk.common.util.ParamUtils;
import org.mushikago.sdk.services.mitsubachi.model.MitsubachiRequest;

/**
 * プロジェクト作成リクエスト。<br>
 * @author miningbrownie
 */
public class ProjectCreateRequest extends MitsubachiRequest {
	
	/**
	 * リクエスト先のパス。<br>
	 */
	public static final String URI_PATH = "/1/mitsubachi/project/create";
	
	/**
	 * リクエストにproject_nameパラメータを指定するキー。<br>
	 */
	public static final String PARAM_KEY_PROJECT_NAME = "project_name";
	
	/**
	 * リクエストにdedicatedパラメータを指定するキー。<br>
	 */
	public static final String PARAM_KEY_DEDICATED = "dedicated";
	
	/**
	 * リクエストにmax_lead_timeパラメータを指定するキー。<br>
	 */
	public static final String PARAM_KEY_MAX_LEAD_TIME = "max_lead_time";
	
	/**
	 * リクエストにstdoutパラメータを指定するキー。<br>
	 */
	public static final String PARAM_KEY_STDOUT = "stdout";
	
	/**
	 * リクエストにstderrパラメータを指定するキー。<br>
	 */
	public static final String PARAM_KEY_STDERR = "stderr";
	
	/**
	 * リクエストにsystem_logパラメータを指定するキー。<br>
	 */
	public static final String PARAM_KEY_SYSTEM_LOG = "system_log";
	
	/**
	 * リクエストにlog_prefixパラメータを指定するキー。<br>
	 */
	public static final String PARAM_KEY_LOG_PREFIX = "log_prefix";
	
	/**
	 * リクエスト(project_name)。<br>
	 */
	private String projectName = null;
	
	/**
	 * リクエスト(stdout)。<br>
	 */
	private boolean isStdoutOut = false;
	
	/**
	 * リクエスト(stderr)。<br>
	 */
	private boolean isStderrOut = false;
	
	/**
	 * リクエスト(system_log)。<br>
	 */
	private boolean isSystemLogOut = false;
	
	/**
	 * リクエスト(dedicated)。<br>
	 */
	private boolean isDedicated = false;
	
	/**
	 * リクエスト(project_name)。<br>
	 */
	private Integer maxLeadTime = null;
	
	/**
	 * リクエスト(log_prefix)。<br>
	 */
	private String logPrefix = null;
	
	/**
	 * ProjectCreateRequestを構築します。<br>
	 */
	public ProjectCreateRequest() {}
	
	/**
	 * 指定された以下のパラメータを使用して、ProjectCreateRequestを構築します。<br>
	 * @param projectName プロジェクト名
	 * @param isStdoutOut 標準出力の出力フラグ
	 * @param isStderrOut 標準エラーの出力フラグ
	 * @param isSystemLogOut システムログの出力フラグ
	 */
	public ProjectCreateRequest(String projectName, boolean isStdoutOut, boolean isStderrOut, boolean isSystemLogOut) {
		
		super();
		
		this.projectName = projectName;
		this.isStdoutOut = isStdoutOut;
		this.isStderrOut = isStderrOut;
		this.isSystemLogOut = isSystemLogOut;
	}
	
	/**
	 * プロジェクト名を設定。<br>
	 * @param name プロジェクト名
	 */
	public void setProjectName(String name) { this.projectName = name; }
	
	/**
	 * プロジェクト名を取得。<br>
	 * @return プロジェクト名
	 */
	public String getProjectName() { return this.projectName; }
	
	/**
	 * 標準出力の出力フラグを設定。<br>
	 * trueが設定されれば出力を行い、falseなら出力はしません。<br>
	 * デフォルトではfalseが設定されています
	 * @param out 出力フラグ
	 */
	public void setStdoutOut(boolean out) { this.isStdoutOut = out; }
	
	/**
	 * 標準出力の出力フラグを取得。<br>
	 * @return 出力フラグ
	 */
	public boolean isStdoutOut() { return this.isStdoutOut; }
	
	/**
	 * 標準エラーの出力フラグを設定。<br>
	 * trueが設定されれば出力を行い、falseなら出力はしません。<br>
	 * デフォルトではfalseが設定されています
	 * @param out 出力フラグ
	 */
	public void setStderrOut(boolean out) { this.isStderrOut = out; }
	
	/**
	 * 標準エラーの出力フラグを取得。<br>
	 * @return 出力フラグ
	 */
	public boolean isStderrOut() { return this.isStderrOut; }
	
	/**
	 * システムログの出力フラグを設定。<br>
	 * trueが設定されれば出力を行い、falseなら出力はしません。<br>
	 * デフォルトではfalseが設定されています。
	 * @param out 出力フラグ
	 */
	public void setSystemLogOut(boolean out) { this.isSystemLogOut = out; }
	
	/**
	 * システムログの出力フラグを取得。<br>
	 * @return 出力フラグ
	 */
	public boolean isSystemLogOut() { return this.isSystemLogOut; }
	
	/**
	 * 専用か共用かを設定。<br>
	 * true => 専用、false => 共用<br>
	 * デフォルトではfalseが設定されています。
	 * @param dedicated true => 専用、false => 共用
	 */
	public void setDedicated(boolean dedicated) { this.isDedicated = dedicated; }
	
	/**
	 * 専用か共用かの設定値を取得。<br>
	 * @return 設定値
	 */
	public boolean getDedicated() { return this.isDedicated; }
	
	/**
	 * 実行開始保証時間を設定。<br>
	 * @param time 時間（分）
	 */
	public void setMaxLeadTime(int time) { this.maxLeadTime = time; }
	
	/**
	 * 実行開始保証時間を取得。<br>
	 * @return 時間（分）
	 */
	public Integer getMaxLeadTime() { return this.maxLeadTime; }
	
	/**
	 * ログのプレフィックスを設定。<br>
	 * @param prefix プレフィックス
	 */
	public void setLogPrefix(String prefix) { this.logPrefix = prefix; }
	
	/**
	 * ログのプレフィックスを取得。<br>
	 * @return プレフィックス
	 */
	public String getLogPrefix() { return this.logPrefix; }
	
	/**
	 * org.apache.http.client.methods.HttpRequestBaseの作成。<br>
	 * HttpRequestBaseの作成に失敗した場合、又は以下の必須パラメータを指定していない場合、ProjectCreateExceptionが送出されます。<br>
	 * ・projectName<br>
	 * @throws MushikagoException 必須パラメータを指定していない場合
	 */
	@Override
	public HttpRequestBase toHttpMethod(MushikagoAuth auth, ConnectInfo ci) throws RequestException {
		
		try {
			TreeMap<String, String> requestParams = new TreeMap<String, String>();
			
			if(null == this.projectName) { throw new RequestException("ProjectNameを指定してください"); }
			requestParams.put(PARAM_KEY_PROJECT_NAME, ParamUtils.paramEncode(this.projectName));
			
			requestParams.put(PARAM_KEY_STDOUT, ParamUtils.paramEncode(this.isStdoutOut ? "1" : "0"));
			requestParams.put(PARAM_KEY_STDERR, ParamUtils.paramEncode(this.isStderrOut ? "1" : "0"));
			requestParams.put(PARAM_KEY_SYSTEM_LOG, ParamUtils.paramEncode(this.isSystemLogOut ? "1" : "0"));
			requestParams.put(PARAM_KEY_DEDICATED, ParamUtils.paramEncode(this.isDedicated ? "1" : "0"));
			
			if(null != this.maxLeadTime) requestParams.put(PARAM_KEY_MAX_LEAD_TIME, ParamUtils.paramEncode(String.valueOf(this.maxLeadTime)));
			if(null != this.logPrefix) requestParams.put(PARAM_KEY_LOG_PREFIX, ParamUtils.paramEncode(this.logPrefix));
			
			return this.toHttpPostMethod(auth, ci, URI_PATH, requestParams);
		}
		catch(UnsupportedEncodingException e) {
			throw new RequestException(e.getMessage());
		}
	}
}
