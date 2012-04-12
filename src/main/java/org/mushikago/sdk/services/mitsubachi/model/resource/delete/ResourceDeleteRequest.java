package org.mushikago.sdk.services.mitsubachi.model.resource.delete;

import java.io.UnsupportedEncodingException;
import java.util.TreeMap;

import org.apache.http.client.methods.HttpRequestBase;
import org.mushikago.sdk.common.RequestException;
import org.mushikago.sdk.common.auth.MushikagoAuth;
import org.mushikago.sdk.common.util.ConnectInfo;
import org.mushikago.sdk.common.util.ParamUtils;
import org.mushikago.sdk.services.mitsubachi.model.MitsubachiRequest;

/**
 * リソース削除のリクエスト。<br>
 * @author miningbrownie
 *
 */
public class ResourceDeleteRequest extends MitsubachiRequest {
	
	/**
	 * リクエスト先のパス。<br>
	 */
	public static final String URI_PATH = "/1/mitsubachi/resource/delete";
	
	/**
	 * リクエストにproject_nameパラメータを指定するキー。<br>
	 */
	public static final String PARAM_KEY_PROJECT_NAME = "project_name";
	
	/**
	 * リクエストにfile_nameパラメータを指定するキー。<br>
	 */
	public static final String PARAM_KEY_FILE_NAME = "file_name";
	
	/**
	 * リクエスト(project_name)。<br>
	 */
	private String projectName = null;
	
	/**
	 * リクエスト(file_name)。<br>
	 */
	private String file_name = null;
	
	/**
	 * ResourceDeleteRequestを構築します。<br>
	 */
	public ResourceDeleteRequest() {
		
		super();
	}
	
	/**
	 * 指定されたプロジェクト名を使用して、ResourceDeleteRequestを構築します。<br>
	 * @param projectName
	 */
	public ResourceDeleteRequest(String projectName) {
		
		super();
		
		this.projectName = projectName;
	}
	
	/**
	 * プロジェクト名を設定。<br>
	 * @param name プロジェクト名
	 */
	public void setProjectName(String name) { this.projectName = name; }
	
	/**
	 * プロジェクト名の取得。<br>
	 * @return プロジェクト名
	 */
	public String getProjectName() { return this.projectName; }
	
	/**
	 * ファイル名の設定。<br>
	 * @param name
	 */
	public void setFileName(String name) { this.file_name = name; }
	
	/**
	 * ファイル名の取得。<br>
	 * @return ファイル名
	 */
	public String getFileName() { return this.file_name; }
	
	/**
	 * org.apache.http.client.methods.HttpRequestBaseの作成。<br>
	 * HttpRequestBaseの作成に失敗した場合、又は以下の必須パラメータを指定していない場合、ResourceDeleteExceptionが送出されます。<br>
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
			
			if(null == this.file_name) { throw new RequestException("FileNameを指定してください"); }
			requestParams.put(PARAM_KEY_FILE_NAME, ParamUtils.paramEncode(this.file_name));
			
			return this.toHttpDeleteMethod(auth, ci, URI_PATH, requestParams);
		}
		catch(UnsupportedEncodingException e) {
			throw new RequestException(e.getMessage());
		}
	}
}
