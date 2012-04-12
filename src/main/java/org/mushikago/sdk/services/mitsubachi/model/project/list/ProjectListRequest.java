package org.mushikago.sdk.services.mitsubachi.model.project.list;

import java.io.UnsupportedEncodingException;
import java.util.TreeMap;

import org.apache.http.client.methods.HttpRequestBase;
import org.mushikago.sdk.common.RequestException;
import org.mushikago.sdk.common.auth.MushikagoAuth;
import org.mushikago.sdk.common.util.ConnectInfo;
import org.mushikago.sdk.common.util.ParamUtils;
import org.mushikago.sdk.services.mitsubachi.model.MitsubachiRequest;

/**
 * プロジェクト一覧のリクエスト。<br>
 * @author miningbrownie
 *
 */
public class ProjectListRequest extends MitsubachiRequest {
	
	/**
	 * リクエスト先のパス。<br>
	 */
	public static final String URI_PATH = "/1/mitsubachi/project/list";
	
	/**
	 * リクエストにlimitパラメータを指定するキー。<br>
	 */
	public static final String PARAM_KEY_LIMIT = "limit";
	
	/**
	 * リクエストにoffsetパラメータを指定するキー。<br>
	 */
	public static final String PARAM_KEY_OFFSET = "offset";
	
	/**
	 * リクエストにfilterパラメータを指定するキー。<br>
	 */
	public static final String PARAM_KEY_FILTER = "filter";
	
	/**
	 * レスポンス(limit)。<br>
	 */
	private Integer limit = null;
	
	/**
	 * レスポンス(offset)。<br>
	 */
	private Integer offset = null;
	
	/**
	 * レスポンス(filter)。<br>
	 */
	private String filter = null;
	
	/**
	 * リミットの設定。<br>
	 * @param limit リミット
	 */
	public void setLimit(int limit) { this.limit = limit; }
	
	/**
	 * リミットの取得。<br>
	 * @return リミット
	 */
	public Integer getLimit() { return this.limit; }
	
	/**
	 * オフセットの設定。<br>
	 * @param offset オフセット
	 */
	public void setOffset(int offset) { this.offset = offset; }
	
	/**
	 * オフセットの取得。<br>
	 * @return オフセット
	 */
	public Integer getOffset() { return this.offset; }
	
	/**
	 * フィルタの設定。<br>
	 * @param filter フィルタ
	 */
	public void setFilter(String filter) { this.filter = filter; }
	
	/**
	 * フィルタの取得。<br>
	 * @return フィルタ
	 */
	public String getFilter() { return this.filter; }
	
	/**
	 * org.apache.http.client.methods.HttpRequestBaseの作成。<br>
	 * HttpRequestBaseの作成に失敗した場合、ProjectListExceptionが送出されます。<br>
	 * @throws MushikagoException HttpRequestBaseの作成に失敗した場合
	 */
	@Override
	public HttpRequestBase toHttpMethod(MushikagoAuth auth, ConnectInfo ci) throws RequestException {
		
		try {
			TreeMap<String, String> requestParams = new TreeMap<String, String>();
			
			if(null != this.limit) requestParams.put(PARAM_KEY_LIMIT, ParamUtils.paramEncode(String.valueOf(this.limit)));
			if(null != this.offset) requestParams.put(PARAM_KEY_OFFSET, ParamUtils.paramEncode(String.valueOf(this.offset)));
			if(null != this.filter) requestParams.put(PARAM_KEY_FILTER, ParamUtils.paramEncode(String.valueOf(this.filter)));
			
			return this.toHttpGetMethod(auth, ci, URI_PATH, requestParams);
		}
		catch(UnsupportedEncodingException e) {
			throw new RequestException(e.getMessage());
		}
	}
}
