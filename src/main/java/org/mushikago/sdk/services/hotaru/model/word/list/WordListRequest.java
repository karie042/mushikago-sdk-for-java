package org.mushikago.sdk.services.hotaru.model.word.list;

import java.io.UnsupportedEncodingException;
import java.util.TreeMap;

import org.apache.http.client.methods.HttpRequestBase;
import org.mushikago.sdk.common.RequestException;
import org.mushikago.sdk.common.auth.MushikagoAuth;
import org.mushikago.sdk.common.util.ConnectInfo;
import org.mushikago.sdk.common.util.ParamUtils;
import org.mushikago.sdk.services.hotaru.model.word.WordRequest;

/**
 * 単語一覧取得リクエスト。<br>
 * @author miningbrownie
 */
public class WordListRequest extends WordRequest {
  
  /**
   * 操作名
   */
  public static final String OPERATION_NAME = "list";
  
  /**
   * パラメータ名（ドメイン名）
   */
  public static final String PARAM_KEY_DOMAIN_NAME = "domain_name";
  
  /**
   * パラメータ名（取得件数）
   */
  public static final String PARAM_KEY_LIMIT = "limit";
  
  /**
   * パラメータ名（開始位置）
   */
  public static final String PARAM_KEY_OFFSET = "offset";
  
  /**
   * パラメータ名（検索文字列）
   */
  public static final String PARAM_KEY_FILTER = "filter";
  
  /**
   * パラメータ（ドメイン名）
   */
  private String domainName = null;
  
  /**
   * パラメータ（取得件数）
   */
  private int limit = 20;
  
  /**
   * パラメータ（開始位置）
   */
  private int offset = 0;
  
  /**
   * パラメータ（検索文字列）
   */
  private String filter = null;
  
  /**
   * パラメータを持たない、空のWordListRequestを構築します。<br>
   */
  public WordListRequest() {}
  
  /**
   * 指定されたドメイン名を使用して、WordListRequestを構築します。<br>
   * @param domainName ドメイン名
   */
  public WordListRequest(String domainName) {
    this.domainName = domainName;
  }
  
  /**
   * 指定されたドメイン名、取得件数、開始位置、検索文字列を使用して、WordListRequestを構築します。<br>
   * @param domainName ドメイン名
   * @param limit 取得件数
   * @param offset 開始位置
   * @param filter 検索文字列
   */
  public WordListRequest(String domainName, int limit, int offset, String filter) {
    this.domainName = domainName;
    this.limit = limit;
    this.offset = offset;
    this.filter = filter;
  }
  
  /**
   * ドメイン名を取得します。<br>
   * @return ドメイン名
   */
  public String getDomainName() { return this.domainName; }
  
  /**
   * 取得件数を取得します。<br>
   * @return 取得件数
   */
  public int getLimit() { return this.limit; }
  
  /**
   * 開始位置を取得します。<br>
   * @return 開始位置
   */
  public int getOffset() { return this.offset; }
  
  /**
   * 検索文字列を取得します。<br>
   * @return 検索文字列
   */
  public String getFilter() { return this.filter; }
  
  /**
   * ドメイン名を設定します。<br>
   * @param name ドメイン名
   */
  public void setDomainName(String name) { this.domainName = name; }
  
  /**
   * 取得件数を設定します。<br>
   * @param limit 取得件数
   */
  public void setLimit(int limit) { this.limit = limit; }
  
  /**
   * 開始位置を設定します。<br>
   * @param offset 開始位置
   */
  public void setOffset(int offset) { this.offset = offset; }
  
  /**
   * 検索文字列を設定します。<br>
   * @param filter 検索文字列
   */
  public void setFilter(String filter) { this.filter = filter; }
  
  @Override
  public HttpRequestBase toHttpMethod(MushikagoAuth auth, ConnectInfo ci) throws RequestException {
    
    try {
      TreeMap<String, String> requestParams = new TreeMap<String, String>();
      if(null == this.domainName) throw new RequestException("ドメイン名を指定してください");
      requestParams.put(PARAM_KEY_DOMAIN_NAME, ParamUtils.paramEncode(this.domainName));
      requestParams.put(PARAM_KEY_LIMIT, ParamUtils.paramEncode(String.valueOf(this.limit)));
      requestParams.put(PARAM_KEY_OFFSET, ParamUtils.paramEncode(String.valueOf(this.offset)));
      if(null != this.filter) requestParams.put(PARAM_KEY_FILTER, ParamUtils.paramEncode(this.filter));
      return this.toHttpGetMethod(auth, ci, this.path(this.serviceName, this.categoryName, OPERATION_NAME), requestParams);
    }
    catch(UnsupportedEncodingException e) {
      throw new RequestException(e.getMessage());
    }
  }
}
