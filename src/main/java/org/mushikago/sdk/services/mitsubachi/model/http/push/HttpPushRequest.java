package org.mushikago.sdk.services.mitsubachi.model.http.push;

import java.io.UnsupportedEncodingException;
import java.util.TreeMap;

import org.apache.http.client.methods.HttpRequestBase;
import org.mushikago.sdk.common.RequestException;
import org.mushikago.sdk.common.auth.MushikagoAuth;
import org.mushikago.sdk.common.util.ConnectInfo;
import org.mushikago.sdk.common.util.ParamUtils;
import org.mushikago.sdk.services.mitsubachi.model.http.HttpRequest;

/**
 * HttpPushのリクエスト。<br>
 * mime_typeは、デフォルトで「text/plain」が設定されています。<br>
 * @author miningbrownie
 */
public class HttpPushRequest extends HttpRequest {
	
	/**
	 * リクエスト先のパス。<br>
	 */
	public static final String URI_PATH = "/1/mitsubachi/http/push";
	
	/**
	 * リクエストにfile_nameパラメータを指定するキー。<br>
	 */
	public static final String PARAM_KEY_FILE_NAME = "file_name";
	
	/**
	 * リクエストにfile_input_keyパラメータを指定するキー。<br>
	 */
	public static final String PARAM_KEY_FILE_INPUT_KEY = "file_input_key";
	
	/**
	 * リクエストにmime_typeパラメータを指定するキー。<br>
	 */
	public static final String PARAM_KEY_MIME_TYPE = "mime_type";
	
	/**
	 * リクエスト(file_name)。<br>
	 */
	private String fileName = null;
	
	/**
	 * リクエスト(file_inputKey)。<br>
	 */
	private String fileInputKey = null;
	
	/**
	 * リクエスト(mime_type)。<br>
	 */
	private String mimeType = "text/plain";
	
	/**
	 * HttpPushRequestを構築します。<br>
	 */
	public HttpPushRequest() {
		
		super();
	}
	
	/**
	 * 指定されたURL、プロジェクト名、スクリプト名を使用して、HttpPushRequestを構築します。<br>
	 * @param url URL
	 * @param projectName プロジェクト名
	 * @param scriptName スクリプト名
	 */
	public HttpPushRequest(String url, String projectName, String scriptName) {
		
		super(url, projectName, scriptName);
	}
	
	/**
	 * 指定されたURL、プロジェクト名、スクリプト名、送信するファイル名、ファイルインプットキーを使用して、HttpPushRequestを構築します。<br>
	 * @param url URL
	 * @param projectName プロジェクト名
	 * @param scriptName スクリプト名
	 * @param fileName ファイル名
	 * @param fileInputKey ファイルインプットキー
	 */
	public HttpPushRequest(String url, String projectName, String scriptName, String fileName, String fileInputKey) {
		
		this(url, projectName, scriptName);
		
		this.fileName = fileName;
		this.fileInputKey = fileInputKey;
	}
	
	/**
	 * ファイル名を設定。<br>
	 * @param name ファイル名
	 */
	public void setFileName(String name) { this.fileName = name; }
	
	/**
	 * ファイル名の取得。<br>
	 * @return ファイル名
	 */
	public String getFileName() { return this.fileName; }
	
	/**
	 * ファイルインプットキーの設定。<br>
	 * @param key ファイルインプットキー
	 */
	public void setFileInputKey(String key) { this.fileInputKey = key; }
	
	/**
	 * ファイルインプットキーの取得。<br>
	 * @return ファイルインプットキー
	 */
	public String getFileInputKey() { return this.fileInputKey; }
	
	/**
	 * MIME Typeの設定。<br>
	 * @param mimeType MIME Type
	 */
	public void setMimeType(String mimeType) { this.mimeType = mimeType; }
	
	/**
	 * MIME Typeの取得。<br>
	 * @return MIME Type
	 */
	public String getMimeType() { return this.mimeType; }
	
	/**
	 * org.apache.http.client.methods.HttpRequestBaseの作成。<br>
	 * HttpRequestBaseの作成に失敗した場合、又は以下の必須パラメータを指定していない場合、HttpPushExceptionが送出されます。<br>
	 * ・url<br>
	 * ・projectName<br>
	 * ・scriptName<br>
	 * ・fileName<br>
	 * ・fileInputKey<br>
	 * @throws MushikagoException 必須パラメータを指定していない場合
	 */
	@Override
	public HttpRequestBase toHttpMethod(MushikagoAuth auth, ConnectInfo ci) throws RequestException {
		
		try {
			TreeMap<String, String> requestParams = new TreeMap<String, String>();
			
			if(null == this.url) { throw new RequestException("URLを指定してください"); }
			requestParams.put(PARAM_KEY_URL, ParamUtils.paramEncode(this.url));
			
			if(null == this.projectName) { throw new RequestException("ProjectNameを指定してください"); }
			requestParams.put(PARAM_KEY_PROJECT_NAME, ParamUtils.paramEncode(this.projectName));
			
			if(null == this.scriptName) { throw new RequestException("ScriptNameを指定してください"); }
			requestParams.put(PARAM_KEY_SCRIPT_NAME, ParamUtils.paramEncode(this.scriptName));
			
			if(null == this.fileName) { throw new RequestException("FileNameを指定してください"); }
			requestParams.put(PARAM_KEY_FILE_NAME, ParamUtils.paramEncode(this.fileName));
			
			if(null == this.fileInputKey) { throw new RequestException("FileInputKeyを指定してください"); }
			requestParams.put(PARAM_KEY_FILE_INPUT_KEY, ParamUtils.paramEncode(this.fileInputKey));
			
			requestParams.put(PARAM_KEY_ENTITY_PARAMETER, this.toEncodedEntityParameter());
			requestParams.put(PARAM_KEY_PARAMETERS, this.toEncodedParameter());
			requestParams.put(PARAM_KEY_HEADER_OVERWRITE, this.toEncodedHeaderOverWrite());
			requestParams.put(PARAM_KEY_MIME_TYPE, ParamUtils.paramEncode(this.mimeType));
			
			return this.toHttpPostMethod(auth, ci, URI_PATH, requestParams);
		}
		catch(UnsupportedEncodingException e) {
			throw new RequestException(e.getMessage());
		}
	}
}
