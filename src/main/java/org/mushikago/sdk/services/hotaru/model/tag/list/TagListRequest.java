package org.mushikago.sdk.services.hotaru.model.tag.list;

import java.io.UnsupportedEncodingException;
import java.util.TreeMap;

import org.apache.http.client.methods.HttpRequestBase;
import org.mushikago.sdk.common.RequestException;
import org.mushikago.sdk.common.auth.MushikagoAuth;
import org.mushikago.sdk.common.util.ConnectInfo;
import org.mushikago.sdk.common.util.ParamUtils;
import org.mushikago.sdk.services.hotaru.model.tag.TagRequest;

/**
 * タグ一覧取得リクエスト。<br>
 * @author miningbrownie
 */
public class TagListRequest extends TagRequest {
  
  /**
   * 操作名
   */
  public static final String OPERATION_NAME = "list";
  
  /**
   * リクエストパラメータ名（ドメイン名）
   */
  public static final String PARAM_KEY_DOMAIN_NAME = "domain_name";
  
  /**
   * リクエストパラメータ名（検索文字列）
   */
  public static final String PARAM_KEY_FILTER = "filter";
  
  /**
   * リクエストパラメータ（ドメイン名）
   */
  private String domainName = null;
  
  /**
   * リクエストパラメータ（検索文字列）
   */
  private String filter = null;
  
  /**
   * パラメータを持たない、空のTagListRequestを構築します。<br>
   */
  public TagListRequest() {}
  
  /**
   * 指定されたドメイン名を使用して、TagListRequestを構築します。<br>
   * @param domainName ドメイン名
   */
  public TagListRequest(String domainName) {
    this.domainName = domainName;
  }
  
  /**
   * 指定されたドメイン名、検索文字列を使用して、TagListRequestを構築します。<br>
   * @param domainName ドメイン名
   * @param filter 検索文字列
   */
  public TagListRequest(String domainName, String filter) {
    this.domainName = domainName;
    this.filter = filter;
  }
  
  /**
   * ドメイン名を取得します。<br>
   * @return ドメイン名
   */
  public String getDomainName() { return this.domainName; }
  
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
      if(null != this.filter) requestParams.put(PARAM_KEY_FILTER, ParamUtils.paramEncode(this.filter));
      return this.toHttpGetMethod(auth, ci, this.path(this.serviceName, this.categoryName, OPERATION_NAME), requestParams);
    }
    catch(UnsupportedEncodingException e) {
      throw new RequestException(e.getMessage());
    }
  }
}
