package org.mushikago.sdk.services.mitsubachi.model.project.delete;

import java.io.UnsupportedEncodingException;
import java.util.TreeMap;

import org.apache.http.client.methods.HttpRequestBase;
import org.mushikago.sdk.common.RequestException;
import org.mushikago.sdk.common.auth.MushikagoAuth;
import org.mushikago.sdk.common.util.ConnectInfo;
import org.mushikago.sdk.common.util.ParamUtils;
import org.mushikago.sdk.services.mitsubachi.model.MitsubachiRequest;

/**
 * プロジェクト削除のリクエスト。<br>
 * @author miningbownie
 */
public class ProjectDeleteRequest extends MitsubachiRequest {
	
	/**
	 * リクエスト先のパス。<br>
	 */
	public static final String URI_PATH = "/1/mitsubachi/project/delete";
	
	/**
	 * リクエストにproject_nameパラメータを指定するキー。<br>
	 */
	public static final String PARAM_KEY_PROJECT_NAME = "project_name";
	
	/**
	 * リクエストにfourcedeleteパラメータを指定するキー。<br>
	 */
	public static final String PARAM_KEY_FORCE_DELETE = "forcedelete";
	
	/**
	 * リクエスト(project_name)。<br>
	 */
	private String projectName = null;
	
	/**
	 * リクエスト(fourcedelete)。<br>
	 */
	private boolean isForceDelete = false;
	
	/**
	 * ProjectDeleteRequestを構築します。<br>
	 */
	public ProjectDeleteRequest() {
		
		super();
	}
	
	/**
	 * 指定されたプロジェクト名を使用して、ProjectDeleteRequestを構築します。<br>
	 * @param projectName プロジェクト名
	 */
	public ProjectDeleteRequest(String projectName) {
		
		super();
		
		this.projectName = projectName;
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
	 * 強制削除フラグを設定。<br>
	 * true => 強制削除
	 * デフォルトではfalseが設定されています。
	 * @param force 強制削除フラグ
	 */
	public void setForceDelete(boolean force) { this.isForceDelete = force; }
	
	/**
	 * 強制削除フラグを取得。<br>
	 * @return 強制削除フラグ
	 */
	public boolean isForceDelete() { return this.isForceDelete; }
	
	/**
	 * org.apache.http.client.methods.HttpRequestBaseの作成。<br>
	 * HttpRequestBaseの作成に失敗した場合、又は以下の必須パラメータを指定していない場合、ProjectDeleteExceptionが送出されます。<br>
	 * ・projectName<br>
	 * @throws MushikagoException 必須パラメータを指定していない場合
	 */
	@Override
	public HttpRequestBase toHttpMethod(MushikagoAuth auth, ConnectInfo ci) throws RequestException {
		
		try {
			TreeMap<String, String> requestParams = new TreeMap<String, String>();
			
			if(null == this.projectName) { throw new RequestException("ProjectNameを指定してください"); }
			requestParams.put(PARAM_KEY_PROJECT_NAME, ParamUtils.paramEncode(this.projectName));
			
			requestParams.put(PARAM_KEY_FORCE_DELETE, ParamUtils.paramEncode(this.isForceDelete ? "1" : "0"));
			
			return this.toHttpDeleteMethod(auth, ci, URI_PATH, requestParams);
		}
		catch(UnsupportedEncodingException e) {
			throw new RequestException(e.getMessage());
		}
	}
}
