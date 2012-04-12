package org.mushikago.sdk.services.mitsubachi.model.http.fetch;

import java.io.UnsupportedEncodingException;
import java.util.TreeMap;

import org.apache.http.client.methods.HttpRequestBase;
import org.mushikago.sdk.common.RequestException;
import org.mushikago.sdk.common.auth.MushikagoAuth;
import org.mushikago.sdk.common.util.ConnectInfo;
import org.mushikago.sdk.common.util.ParamUtils;
import org.mushikago.sdk.services.mitsubachi.model.http.HttpMethod;
import org.mushikago.sdk.services.mitsubachi.model.http.HttpRequest;

/**
 * HttpFetchのリクエスト。<br>
 * @author miningbrownie
 */
public class HttpFetchRequest extends HttpRequest {
	
	/**
	 * リクエスト先のパス。<br>
	 */
	public static final String URI_PATH = "/1/mitsubachi/http/fetch";
	
	/**
	 * リクエストにmethodパラメータを指定するキー。<br>
	 */
	public static final String PARAM_KEY_METHOD = "method";
	
	/**
	 * リクエストにfollow_redirectパラメータを指定するキー。<br>
	 */
	public static final String PARAM_KEY_FOLLOW_REDIRECT = "follow_redirect";
	
	/**
	 * リクエスト(method)。<br>
	 */
	private HttpMethod method = HttpMethod.GET;
	
	/**
	 * リクエスト(follow_redirect)。<br>
	 */
	private boolean followRedirect = true;
	
	/**
	 * HttpFetchRequestを構築します。<br>
	 */
	public HttpFetchRequest() {
		super();
	}
	
	/**
	 * 指定されたURL、プロジェクト名、スクリプト名を使用して、HttpFetchRequestを構築します。<br>
	 * @param url URL
	 * @param projectName プロジェクト名
	 * @param scriptName スクリプト名
	 */
	public HttpFetchRequest(String url, String projectName, String scriptName) {
		
		super(url, projectName, scriptName);
	}
	
	/**
	 * methodを設定。<br>
	 * @param method HTTPメソッド
	 */
	public void setMethod(HttpMethod method) { this.method = method; }
	
	/**
	 * methodを取得。<br>
	 * @return HTTPメソッド
	 */
	public HttpMethod getMethod() { return this.method; }
	
	/**
	 * follow_redirectを取得。<br>
	 * @return follow_redirect
	 */
	public boolean isFollowRedirect() { return this.followRedirect; }
	
	/**
	 * follow_redirectを設定。<br>
	 * trueが設定されるとリダイレクトを許可。<br>
	 * @param follow follow_rediret
	 */
	public void setFollowRedirect(boolean follow) { this.followRedirect = follow; }
	
	/**
	 * org.apache.http.client.methods.HttpRequestBaseの作成。<br>
	 * HttpRequestBaseの作成に失敗した場合、又は以下の必須パラメータを指定していない場合、HttpFetchExceptionが送出されます。<br>
	 * ・url<br>
	 * ・projectName<br>
	 * ・scriptName<br>
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
			
			requestParams.put(PARAM_KEY_METHOD, ParamUtils.paramEncode(this.method.name()));
			requestParams.put(PARAM_KEY_ENTITY_PARAMETER, this.toEncodedEntityParameter());
			requestParams.put(PARAM_KEY_FOLLOW_REDIRECT, ParamUtils.paramEncode(this.followRedirect ? "1" : "0"));
			requestParams.put(PARAM_KEY_PARAMETERS, this.toEncodedParameter());
			requestParams.put(PARAM_KEY_HEADER_OVERWRITE, this.toEncodedHeaderOverWrite());
			
			return this.toHttpPostMethod(auth, ci, URI_PATH, requestParams);
		}
		catch(UnsupportedEncodingException e) {
			throw new RequestException(e.getMessage());
		}
	}
}
