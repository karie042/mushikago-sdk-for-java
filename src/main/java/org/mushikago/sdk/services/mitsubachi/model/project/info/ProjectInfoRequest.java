package org.mushikago.sdk.services.mitsubachi.model.project.info;

import java.io.UnsupportedEncodingException;
import java.util.TreeMap;

import org.apache.http.client.methods.HttpRequestBase;
import org.mushikago.sdk.common.RequestException;
import org.mushikago.sdk.common.auth.MushikagoAuth;
import org.mushikago.sdk.common.util.ConnectInfo;
import org.mushikago.sdk.common.util.ParamUtils;
import org.mushikago.sdk.services.mitsubachi.model.MitsubachiRequest;

/**
 * プロジェクト情報を取得するリクエスト。<br>
 * @author miningbrownie
 *
 */
public class ProjectInfoRequest extends MitsubachiRequest {
	
	/**
	 * リクエスト先のパス。<br>
	 */
	public static final String URI_PATH = "/1/mitsubachi/project/info";
	
	/**
	 * リクエストにproject_nameパラメータを指定するキー。<br>
	 */
	public static final String PARAM_KEY_PROJECT_NAME = "project_name";
	
	/**
	 * リクエストにtimeパラメータを指定するキー。<br>
	 */
	public static final String PARAM_KEY_TIME = "time";
	
	/**
	 * リクエスト(project_name)。<br>
	 */
	private String projectName = null;
	
	/**
	 * リクエスト(time)。<br>
	 */
	private Integer time = null;
	
	/**
	 * ProjectInfoRequestを構築します。<br>
	 */
	public ProjectInfoRequest() {
		
		super();
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
	 * 取得対象時間。<br>
	 * @param time 時間（分）
	 */
	public void setTime(int time) { this.time = time; }
	
	/**
	 * 取得対象時間の取得。<br>
	 * @return 時間（分）
	 */
	public Integer getTime() { return this.time; }
	
	/**
	 * org.apache.http.client.methods.HttpRequestBaseの作成。<br>
	 * HttpRequestBaseの作成に失敗した場合、又は以下の必須パラメータを指定していない場合、ProjectInfoExceptionが送出されます。<br>
	 * ・projectName<br>
	 * @throws MushikagoException 必須パラメータを指定していない場合
	 */
	@Override
	public HttpRequestBase toHttpMethod(MushikagoAuth auth, ConnectInfo ci) throws RequestException {
		
		try {
			TreeMap<String, String> requestParams = new TreeMap<String, String>();
			
			if(null == this.projectName) { throw new RequestException("ProjectNameを指定してください"); }
			requestParams.put(PARAM_KEY_PROJECT_NAME, ParamUtils.paramEncode(this.projectName));
			
			if(null != this.time) requestParams.put(PARAM_KEY_TIME, ParamUtils.paramEncode(String.valueOf(this.time)));
			
			return this.toHttpGetMethod(auth, ci, URI_PATH, requestParams);
		}
		catch(UnsupportedEncodingException e) {
			throw new RequestException(e.getMessage());
		}
	}
}
