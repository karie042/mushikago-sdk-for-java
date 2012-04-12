package org.mushikago.sdk.services.mitsubachi.model.project.update;

import java.io.UnsupportedEncodingException;
import java.util.TreeMap;

import org.apache.http.client.methods.HttpRequestBase;
import org.mushikago.sdk.common.RequestException;
import org.mushikago.sdk.common.auth.MushikagoAuth;
import org.mushikago.sdk.common.util.ConnectInfo;
import org.mushikago.sdk.common.util.ParamUtils;
import org.mushikago.sdk.services.mitsubachi.model.MitsubachiRequest;

/**
 * プロジェクト更新のリクエスト。<br>
 * @author miningbrownie
 *
 */
public class ProjectUpdateRequest extends MitsubachiRequest {
	
	/**
	 * リクエスト先のパス。<br>
	 */
	public static final String URI_PATH = "/1/mitsubachi/project/update";
	
	/**
	 * リクエストにproject_nameパラメータを指定するキー。<br>
	 */
	public static final String PARAM_KEY_PROJECT_NAME = "project_name";
	
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
	private Boolean isStdoutOut = null;
	
	/**
	 * リクエスト(stderr)。<br>
	 */
	private Boolean isStderrOut = null;
	
	/**
	 * リクエスト(system_log)。<br>
	 */
	private Boolean isSystemLogOut = null;
	
	/**
	 * リクエスト(max_lead_time)。<br>
	 */
	private Integer maxLeadTime = null;
	
	/**
	 * リクエスト(log_prefix)。<br>
	 */
	private String logPrefix = null;
	
	/**
	 * ProjectUpdateRequestを構築します。<br>
	 */
	public ProjectUpdateRequest() {
		
		super();
	}
	
	/**
	 * 指定されたプロジェクト名を使用して、ProjectUpdateRequestを構築します。<br>
	 * @param projectName
	 */
	public ProjectUpdateRequest(String projectName) {
		
		super();
		
		this.projectName = projectName;
	}
	
	/**
	 * プロジェクト名の設定。<br>
	 * @param name プロジェクト名
	 */
	public void setProjectName(String name) { this.projectName = name; }
	
	/**
	 * プロジェクト名の取得。
	 * @return プロジェクト名
	 */
	public String getProjectName() { return this.projectName; }
	
	/**
	 * 標準出力の出力フラグの設定。<br>
	 * trueが設定されれば出力を行い、falseなら出力はしません。<br>
	 * デフォルトではfalseが設定されています
	 * @param out 出力フラグ
	 */
	public void setStdoutOut(boolean out) { this.isStdoutOut = out; }
	
	/**
	 * 標準出力の出力フラグの取得。<br>
	 * @return 出力フラグ
	 */
	public Boolean getStdoutOut() { return this.isStdoutOut; }
	
	/**
	 * 標準エラーの出力フラグの設定。<br>
	 * trueが設定されれば出力を行い、falseなら出力はしません。<br>
	 * デフォルトではfalseが設定されています
	 * @param out 出力フラグ
	 */
	public void setStderrOut(boolean out) { this.isStderrOut = out; }
	
	/**
	 * 標準エラーの出力フラグを取得。<br>
	 * @return 出力フラグ
	 */
	public Boolean getStderrOut() { return this.isStderrOut; }
	
	/**
	 * システムログの出力フラグの設定。<br>
	 * trueが設定されれば出力を行い、falseなら出力はしません。<br>
	 * デフォルトではfalseが設定されています
	 * @param out 出力フラグ
	 */
	public void setSystemLogOut(boolean out) { this.isSystemLogOut = out; }
	
	/**
	 * システムログの出力フラグを取得。<br>
	 * @return 出力フラグ
	 */
	public Boolean getSystemLogOut() { return this.isSystemLogOut; }
	
	/**
	 * 実行開始保証時間の設定。<br>
	 * @param time 時間（分）
	 */
	public void setMaxLeadTime(int time) { this.maxLeadTime = time; }
	
	/**
	 * 実行開始保証時間の取得
	 * @return 時間（分）
	 */
	public Integer getMaxLeadTime() { return this.maxLeadTime; }
	
	/**
	 * ログのプリフィックスの設定。<br>
	 * @param prefix プリフィックス
	 */
	public void setLogPrefix(String prefix) { this.logPrefix = prefix; }
	
	/**
	 * ログのプリフィックス取得。<br>
	 * @return プリフィックス
	 */
	public String getLogPrefix() { return this.logPrefix; }
	
	/**
	 * org.apache.http.client.methods.HttpRequestBaseの作成。<br>
	 * HttpRequestBaseの作成に失敗した場合、又は以下の必須パラメータを指定していない場合、ProjectUpdateExceptionが送出されます。<br>
	 * ・projectName<br>
	 * @throws MushikagoException 必須パラメータを指定していない場合
	 */
	@Override
	public HttpRequestBase toHttpMethod(MushikagoAuth auth, ConnectInfo ci) throws RequestException {
		
		try {
			TreeMap<String, String> requestParams = new TreeMap<String, String>();
			
			if(null == this.projectName) { throw new RequestException("ProjectNameを指定してください"); }
			requestParams.put(PARAM_KEY_PROJECT_NAME, ParamUtils.paramEncode(this.projectName));
			
			if(null != this.maxLeadTime) requestParams.put(PARAM_KEY_MAX_LEAD_TIME, ParamUtils.paramEncode(String.valueOf(this.maxLeadTime)));
			if(null != this.isStdoutOut) requestParams.put(PARAM_KEY_STDOUT, ParamUtils.paramEncode(this.isStdoutOut ? "1" : "0"));
			if(null != this.isStderrOut) requestParams.put(PARAM_KEY_STDERR, ParamUtils.paramEncode(this.isStderrOut ? "1" : "0"));
			if(null != this.isSystemLogOut) requestParams.put(PARAM_KEY_SYSTEM_LOG, ParamUtils.paramEncode(this.isSystemLogOut ? "1" : "0"));
			if(null != this.logPrefix) requestParams.put(PARAM_KEY_LOG_PREFIX, ParamUtils.paramEncode(this.logPrefix));
			
			return this.toHttpPostMethod(auth, ci, URI_PATH, requestParams);
		}
		catch(UnsupportedEncodingException e) {
			throw new RequestException(e.getMessage());
		}
	}
}
