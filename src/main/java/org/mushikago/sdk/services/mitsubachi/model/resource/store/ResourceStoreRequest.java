package org.mushikago.sdk.services.mitsubachi.model.resource.store;

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
 * リソースストアのリクエスト。<br>
 * @author miningbrownie
 *
 */
public class ResourceStoreRequest extends MitsubachiRequest {
	
	/**
	 * リクエスト先のパス。<br>
	 */
	public static final String URI_PATH = "/1/mitsubachi/resource/store";
	
	/**
	 * リクエストにproject_nameパラメータを指定するキー。<br>
	 */
	public static final String PARAM_KEY_PROJECT_NAME = "project_name";
	
	/**
	 * リクエストにfile_nameパラメータを指定するキー。<br>
	 */
	public static final String PARAM_KEY_FILE_NAME = "file_name";
	
	/**
	 * リクエストにcontent_typeパラメータを指定するキー。<br>
	 */
	public static final String PARAM_KEY_CONTENT_TYPE = "content_type";
	
	/**
	 * リクエストにfileパラメータを指定するキー。<br>
	 */
	public static final String PARAM_KEY_FILE = "file";
	
	/**
	 * リクエストにpublicパラメータを指定するキー。<br>
	 */
	public static final String PARAM_KEY_PUBLIC = "public";
	
	/**
	 * リクエスト(project_name)。<br>
	 */
	private String projectName = null;
	
	/**
	 * リクエスト(file_name)。<br>
	 */
	private String fileName = null;
	
	/**
	 * リクエスト(content_type)。<br>
	 */
	private String contentType = null;
	
	/**
	 * リクエスト(file)。<br>
	 */
	private File file = null;
	
	/**
	 * リクエスト(public)。<br>
	 */
	private boolean isPublic = false;
	
	/**
	 * ResourceStoreRequestを構築します。<br>
	 */
	public ResourceStoreRequest() {
		
		super();
	}
	
	/**
	 * 指定されたプロジェクト名、ファイル名、コンテンツタイプを使用して、ResourceStoreRequestを構築します。<br>
	 * @param projectName プロジェクト名
	 * @param fileName ファイル名
	 * @param contentType コンテンツタイプ
	 */
	public ResourceStoreRequest(String projectName, String fileName, String contentType) {
		
		super();
		
		this.projectName = projectName;
		this.fileName = fileName;
		this.contentType = contentType;
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
	 * @param name ファイル名
	 */
	public void setFileName(String name) { this.fileName = name; }
	
	/**
	 * ファイル名の取得。<br>
	 * @return ファイル名
	 */
	public String getFileName() { return this.fileName; }
	
	/**
	 * コンテンツタイプの設定。<br>
	 * @param type コンテンツタイプ
	 */
	public void setContentType(String type) { this.contentType = type; }
	
	/**
	 * コンテンツタイプの取得。<br>
	 * @return コンテンツタイプ
	 */
	public String getContentType() { return this.contentType; }
	
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
	 * 可視性を設定します。<br>
	 * @param isPublic true => 誰でも参照可能になる。false => プロジェクトの作成ユーザしか参照できない。
	 */
	public void setPublic(boolean isPublic) { this.isPublic = isPublic; }
	
	/**
	 * 可視性を取得します。<br>
	 * @return public
	 */
	public boolean isPublic() { return this.isPublic; }
	
	/**
	 * org.apache.http.client.methods.HttpRequestBaseの作成。<br>
	 * HttpRequestBaseの作成に失敗した場合、又は以下の必須パラメータを指定していない場合、ResourceStoreExceptionが送出されます。<br>
	 * ・projectName<br>
	 * ・fileName<br>
	 * ・file<br>
	 * ・contentType<br>
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
			
			if(null == this.contentType) { throw new RequestException("ContentTypeを指定してください"); }
			requestParams.put(PARAM_KEY_CONTENT_TYPE, ParamUtils.paramEncode(String.valueOf(this.contentType)));
			
			requestParams.put(PARAM_KEY_PUBLIC, ParamUtils.paramEncode(this.isPublic ? "1" : "0"));
			
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
