package org.mushikago.sdk.services.mitsubachi.model.http.push;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import net.sf.json.JSONArray;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.mushikago.sdk.common.RequestException;
import org.mushikago.sdk.common.auth.AuthException;
import org.mushikago.sdk.common.auth.MushikagoAuth;
import org.mushikago.sdk.common.util.ConnectInfo;
import org.mushikago.sdk.common.util.ParamUtils;
import org.mushikago.sdk.services.mitsubachi.model.http.HttpRequest;

/**
 * HttpPushのリクエスト。<br>
 * mime_typeは、デフォルトで「text/plain」が設定されています。<br>
 * 
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
	 * リクエスト時のGETパラメータのエンコードを指定するキー。<br/>
	 */
	public static final String PARAM_KEY_URLENCODING_CHARSET = "encode";

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
	 * リクエストの文字コード
	 */
	private String encode = "UTF-8";

	/**
	 * HttpPushRequestを構築します。<br>
	 */
	public HttpPushRequest() {

		super();
	}

	/**
	 * 指定されたURL、プロジェクト名、スクリプト名を使用して、HttpPushRequestを構築します。<br>
	 * 
	 * @param url
	 *            URL
	 * @param projectName
	 *            プロジェクト名
	 * @param scriptName
	 *            スクリプト名
	 */
	public HttpPushRequest(String url, String projectName, String scriptName) {

		super(url, projectName, scriptName);
	}

	/**
	 * 指定されたURL、プロジェクト名、スクリプト名、送信するファイル名、ファイルインプットキーを使用して、HttpPushRequestを構築します。<br>
	 * 
	 * @param url
	 *            URL
	 * @param projectName
	 *            プロジェクト名
	 * @param scriptName
	 *            スクリプト名
	 * @param fileName
	 *            ファイル名
	 * @param fileInputKey
	 *            ファイルインプットキー
	 */
	public HttpPushRequest(String url, String projectName, String scriptName, String fileName, String fileInputKey) {

		this(url, projectName, scriptName);

		this.fileName = fileName;
		this.fileInputKey = fileInputKey;
	}

	/**
	 * ファイル名を設定。<br>
	 * 
	 * @param name
	 *            ファイル名
	 */
	public void setFileName(String name) {
		this.fileName = name;
	}

	/**
	 * ファイル名の取得。<br>
	 * 
	 * @return ファイル名
	 */
	public String getFileName() {
		return this.fileName;
	}

	/**
	 * ファイルインプットキーの設定。<br>
	 * 
	 * @param key
	 *            ファイルインプットキー
	 */
	public void setFileInputKey(String key) {
		this.fileInputKey = key;
	}

	/**
	 * ファイルインプットキーの取得。<br>
	 * 
	 * @return ファイルインプットキー
	 */
	public String getFileInputKey() {
		return this.fileInputKey;
	}

	/**
	 * MIME Typeの設定。<br>
	 * 
	 * @param mimeType
	 *            MIME Type
	 */
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	/**
	 * MIME Typeの取得。<br>
	 * 
	 * @return MIME Type
	 */
	public String getMimeType() {
		return this.mimeType;
	}
	

	/**
	 * URLエンコーディングのcharsetを設定
	 * 
	 * @param urlEncodingChareset
	 */
	public void setEncode(String encode) {
		this.encode = encode;
	}

	/**
	 * org.apache.http.client.methods.HttpRequestBaseの作成。<br>
	 * HttpRequestBaseの作成に失敗した場合、又は以下の必須パラメータを指定していない場合、HttpPushExceptionが送出されます
	 * 。<br>
	 * ・url<br>
	 * ・projectName<br>
	 * ・scriptName<br>
	 * ・fileName<br>
	 * ・fileInputKey<br>
	 * 
	 * @throws MushikagoException
	 *             必須パラメータを指定していない場合
	 */
	@Override
	public HttpRequestBase toHttpMethod(MushikagoAuth auth, ConnectInfo ci) throws RequestException {

		List<NameValuePair> rawParams = new ArrayList<NameValuePair>();

		rawParams.add(createNameValuePair(PARAM_KEY_URL, this.url, true));
		rawParams.add(createNameValuePair(PARAM_KEY_PROJECT_NAME, this.projectName, true));
		rawParams.add(createNameValuePair(PARAM_KEY_SCRIPT_NAME, this.scriptName, true));
		rawParams.add(createNameValuePair(PARAM_KEY_FILE_NAME, this.fileName, true));
		rawParams.add(createNameValuePair(PARAM_KEY_FILE_INPUT_KEY, this.fileInputKey, true));

		rawParams.add(createNameValuePair(PARAM_KEY_ENTITY_PARAMETER, this.toEntityParameterString()));
		rawParams.add(createNameValuePair(PARAM_KEY_PARAMETERS, this.toParameterString()));
		rawParams.add(createNameValuePair(PARAM_KEY_HEADER_OVERWRITE, this.toHeaderOverWriteString()));
		rawParams.add(createNameValuePair(PARAM_KEY_MIME_TYPE, this.mimeType));

		if (this.parentRequestId != null) {
			rawParams.add(createNameValuePair(PARAM_KEY_PARENT_REQUEST_ID, this.parentRequestId));
		}
		if (this.groupId != null) {
			rawParams.add(createNameValuePair(PARAM_KEY_GROUP_ID, this.groupId));
		}
		if (this.uniqueKey != null) {
			rawParams.add(createNameValuePair(PARAM_KEY_UNIQUE_KEY, this.uniqueKey));
		}
		if (this.uniqueKeyExpires != null) {
			String uniqueKeyExpires = this.uniqueKeyExpires == null ? null : this.uniqueKeyExpires.toString();
			rawParams.add(createNameValuePair(PARAM_KEY_UNIQUE_KEY_EXPIRES, uniqueKeyExpires));
		}

		if (this.checkCharacterCode(this.encode)) {
			String encode = this.encode.toString();
			rawParams.add(createNameValuePair(PARAM_KEY_URLENCODING_CHARSET, encode));
		}

		JSONArray cookiejarJson = JSONArray.fromObject(this.cookiejar);
		rawParams.add(createNameValuePair(PARAM_KEY_COOKIEJAR, cookiejarJson.toString()));

		try {
			TreeMap<String, String> requestParams = new TreeMap<String, String>();
			for (NameValuePair rawParam : rawParams) {
				requestParams.put(rawParam.getName(), ParamUtils.paramEncode(rawParam.getValue()));
			}
			HttpRequestBase httpRequest = new HttpPost();
			String uri = makeRequestUrl(auth, ci, httpRequest.getMethod(), URI_PATH, requestParams);
			httpRequest.setURI(new URI(uri));
			HttpEntity entity = new UrlEncodedFormEntity(rawParams);
			((HttpEntityEnclosingRequestBase) httpRequest).setEntity(entity);
			return httpRequest;
		} catch (AuthException e) {
			throw new RequestException(e.getMessage());
		} catch (URISyntaxException e) {
			throw new RequestException(e.getMessage());
		} catch (UnsupportedEncodingException e) {
			throw new RequestException(e.getMessage());
		}
	}
}
