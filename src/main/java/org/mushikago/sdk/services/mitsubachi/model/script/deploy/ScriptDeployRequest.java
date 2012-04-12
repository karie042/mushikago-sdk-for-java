package org.mushikago.sdk.services.mitsubachi.model.script.deploy;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.TreeMap;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.mushikago.sdk.common.RequestException;
import org.mushikago.sdk.common.auth.MushikagoAuth;
import org.mushikago.sdk.common.util.ConnectInfo;
import org.mushikago.sdk.common.util.ParamUtils;
import org.mushikago.sdk.services.mitsubachi.model.MitsubachiRequest;

/**
 * スクリプトデプロイのリクエスト。<br>
 * @author miningbrownie
 *
 */
public class ScriptDeployRequest extends MitsubachiRequest {
	
	/**
	 * リクエスト先のパス。<br>
	 */
	public static final String URI_PATH = "/1/mitsubachi/script/deploy";
	
	/**
	 * リクエストにproject_nameパラメータを指定するキー。<br>
	 */
	public static final String PARAM_KEY_PROJECT_NAME = "project_name";
	
	/**
	 * リクエストにscript_nameパラメータを指定するキー。<br>
	 */
	public static final String PARAM_KEY_FILE_NAME = "script_name";
	
	/**
	 * リクエストにfileパラメータを指定するキー。<br>
	 */
	public static final String PARAM_KEY_FILE = "file";
	
	/**
	 * リクエスト(project_name)。<br>
	 */
	private String projectName = null;
	
	/**
	 * リクエスト(file_name)。<br>
	 */
	private String fileName = null;
	
	/**
	 * リクエスト(file)。<br>
	 */
	private File file = null;
	
	/**
	 * ScriptDeployRequestを構築します。<br>
	 */
	public ScriptDeployRequest() {
		
		super();
	}
	
	/**
	 * 指定されたプロジェクト名、ファイル名を使用して、ScriptDeployRequestを構築します。<br>
	 * @param projectName
	 * @param fileName
	 */
	public ScriptDeployRequest(String projectName, String fileName) {
		
		super();
		
		this.projectName = projectName;
		this.fileName = fileName;
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
	
	/**
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
	 * ファイルの設定。<br>
	 * @param file ファイル
	 */
	public void setFile(File file) { this.file = file; }
	
	/**
	 * ファイルの取得。<br>
	 * @return ファイル
	 */
	public File getFile() { return this.file; }
	
	/**
	 * org.apache.http.client.methods.HttpRequestBaseの作成。<br>
	 * HttpRequestBaseの作成に失敗した場合、又は以下の必須パラメータを指定していない場合、ScriptDeployExceptionが送出されます。<br>
	 * ・projectName<br>
	 * ・fileName<br>
	 * ・file<br>
	 * @throws MushikagoException 必須パラメータを指定していない場合
	 */
	@Override
	public HttpRequestBase toHttpMethod(MushikagoAuth auth, ConnectInfo ci) throws RequestException {
		
		try {
			TreeMap<String, String> requestParams = new TreeMap<String, String>();
			
			if(null == this.projectName) { throw new RequestException("ProjectNameを指定してください"); }
			requestParams.put(PARAM_KEY_PROJECT_NAME, ParamUtils.paramEncode(this.projectName));
			
			if(null == this.fileName) { throw new RequestException("FileNameを指定してください"); }
			requestParams.put(PARAM_KEY_FILE_NAME, ParamUtils.paramEncode(String.valueOf(this.fileName)));
			
			if(null == this.file) { throw new RequestException("Fileを指定してください"); }
			
			HttpPost post = this.toHttpPostMethod(auth, ci, URI_PATH, requestParams);
			
			MultipartEntity entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
			entity.addPart(PARAM_KEY_FILE, new FileBody(this.file));
			post.setEntity(entity);
			
			return post;
		}
		catch(UnsupportedEncodingException e) {
			throw new RequestException(e.getMessage());
		}
	}
}
