package org.mushikago.sdk.services.mitsubachi.model.script.delete;

import java.io.UnsupportedEncodingException;
import java.util.TreeMap;

import org.apache.http.client.methods.HttpRequestBase;
import org.mushikago.sdk.common.RequestException;
import org.mushikago.sdk.common.auth.MushikagoAuth;
import org.mushikago.sdk.common.util.ConnectInfo;
import org.mushikago.sdk.common.util.ParamUtils;
import org.mushikago.sdk.services.mitsubachi.model.MitsubachiRequest;

/**
 * スクリプト削除のリクエスト。<br>
 * @author miningbrownie
 *
 */
public class ScriptDeleteRequest extends MitsubachiRequest {
	
	/**
	 * リクエスト先のパス。<br>
	 */
	public static final String URI_PATH = "/1/mitsubachi/script/delete";
	
	/**
	 * リクエストにproject_nameパラメータを指定するキー。<br>
	 */
	public static final String PARAM_KEY_PROJECT_NAME = "project_name";
	
	/**
	 * リクエストにscript_nameパラメータを指定するキー。<br>
	 */
	public static final String PARAM_KEY_FILE_NAME = "script_name";
	
	/**
	 * リクエスト(project_name)。<br>
	 */
	private String projectName = null;
	
	/**
	 * リクエスト(file_name)。<br>
	 */
	private String fileName = null;
	
	/**
	 * ScriptDeleteRequestを構築します。<br>
	 */
	public ScriptDeleteRequest() {
		
		super();
	}
	
	/**
	 * 指定されたプロジェクト名を使用して、ScriptDeleteRequestを構築します。<br>
	 * @param projectName
	 */
	public ScriptDeleteRequest(String projectName) {
		
		super();
		
		this.projectName = projectName;
	}
	
	/**
	 * プロジェクト名の設定。<br>
	 * @param name プロジェクト名
	 */
	public void setProjectName(String name) { this.projectName = name; }
	
	/**
	 * プロジェクト名の取得。<br>
	 * @return プロジェクト名
	 */
	public String getProjectName() { return this.projectName; }
	
	/**f
	 * ファイル名の設定。<br>
	 * @param name ファイル名
	 */
	public void setFileName(String name) { this.fileName = name; }
	
	/**
	 * ファイル名の取得。<br>
	 * @return ファイル名
	 */
	public String getFileName() { return this.fileName; }
	
	/**
	 * org.apache.http.client.methods.HttpRequestBaseの作成。<br>
	 * HttpRequestBaseの作成に失敗した場合、又は以下の必須パラメータを指定していない場合、ScriptDeleteExceptionが送出されます。<br>
	 * ・projectName<br>
	 * ・fileName<br>
	 * @throws MushikagoException 必須パラメータを指定していない場合
	 */
	@Override
	public HttpRequestBase toHttpMethod(MushikagoAuth auth, ConnectInfo ci) throws RequestException {
		
		try {
			TreeMap<String, String> requestParams = new TreeMap<String, String>();
			
			if(null == this.projectName) { throw new RequestException("ProjectNameを指定してください"); }
			requestParams.put(PARAM_KEY_PROJECT_NAME, ParamUtils.paramEncode(this.projectName));
			
			if(null == this.fileName) { throw new RequestException("FileNameを指定してください"); }
			requestParams.put(PARAM_KEY_FILE_NAME, ParamUtils.paramEncode(this.fileName));
			
			return this.toHttpDeleteMethod(auth, ci, URI_PATH, requestParams);
		}
		catch(UnsupportedEncodingException e) {
			throw new RequestException(e.getMessage());
		}
	}
}
