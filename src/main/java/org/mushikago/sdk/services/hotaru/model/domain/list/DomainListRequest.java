package org.mushikago.sdk.services.hotaru.model.domain.list;

import java.io.UnsupportedEncodingException;
import java.util.TreeMap;

import org.apache.http.client.methods.HttpRequestBase;
import org.mushikago.sdk.common.RequestException;
import org.mushikago.sdk.common.auth.MushikagoAuth;
import org.mushikago.sdk.common.util.ConnectInfo;
import org.mushikago.sdk.common.util.ParamUtils;
import org.mushikago.sdk.services.hotaru.model.domain.DomainRequest;

/**
 * ドメイン一覧取得リクエスト。<br>
 * @author miningbrownie
 */
public class DomainListRequest extends DomainRequest {
  
  /**
   * 操作名
   */
  public static final String OPERATION_NAME = "list";
  
  /**
   * リクエストパラメータ名(limit)
   */
  public static final String PARAM_KEY_LIMIT = "limit";
  
  /**
   * リクエストパラメータ名(offset)
   */
  public static final String PARAM_KEY_OFFSET = "offset";
  
  /**
   * リクエストパラメータ名(filter)
   */
  public static final String PARAM_KEY_FILTER = "filter";
  
  /**
   * リクエストパラメータ名(status)
   */
  public static final String PARAM_KEY_STATUS = "status";
  
  /**
   * リクエストパラメータ(status)の一覧。<br>
   * @author miningbrownie
   *
   */
  public static enum Status {
    
    ACCEPTED("accepted"),
    RETRYING("retrying"),
    PROCESSING("processing"),
    COMPLETE("complete"),
    ERROR("error");
    
    private final String key;
    private Status(String key) { this.key = key; }
    @Override public String toString() { return this.key; }
  }
  
  /**
   * デフォルトのリクエストパラメータでDomainListRequestオブジェクトを構築します。<br>
   */
  public DomainListRequest() {}
  
  /**
   * 指定されたlimit,offset,filter,statusを使用して、DomainListRequestオブジェクトを構築します。<br>
   * @param limit 取得件数
   * @param offset 開始位置
   * @param filter 検索文字列（先頭一致）
   * @param status 検索したいステータス（指定無しの場合は全て）
   */
  public DomainListRequest(int limit, int offset, String filter, Status status) {
    this.limit = limit;
    this.offset = offset;
    this.filter = filter;
    this.status = status;
  }
  
  /**
   * 取得件数。<br>
   */
  private int limit = 20;
  
  /**
   * 開始位置。<br>
   */
  private int offset = 0;
  
  /**
   * 検索文字列（先頭一致）。<br>
   */
  private String filter = null;
  
  /**
   * 検索したいステータス。<br>
   */
  private Status status = null;
  
  /**
   * 取得件数を取得します。<br>
   * @return 取得件数
   */
  int getLimit() { return this.limit; }
  
  /**
   * 開始位置を取得します。<br>
   * @return 開始位置
   */
  int getOffset() { return this.offset; }
  
  /**
   * 検索文字列を取得します。<br>
   * @return 検索文字列
   */
  String getFilter() { return this.filter; }
  
  /**
   * ステータスを取得します。<br>
   * @return ステータス
   */
  Status getStatus() { return this.status; }
  
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
  
  /**
   * ステータスを設定します。<br>
   * @param status ステータス
   */
  public void setStatus(Status status) { this.status = status; }
  
  @Override
  public HttpRequestBase toHttpMethod(MushikagoAuth auth, ConnectInfo ci) throws RequestException {
    
    try {
      TreeMap<String, String> requestParams = new TreeMap<String, String>();
      requestParams.put(PARAM_KEY_LIMIT, ParamUtils.paramEncode(String.valueOf(this.limit)));
      requestParams.put(PARAM_KEY_OFFSET, ParamUtils.paramEncode(String.valueOf(this.offset)));
      if(null != this.filter) requestParams.put(PARAM_KEY_FILTER, ParamUtils.paramEncode(this.filter));
      if(null != this.status) requestParams.put(PARAM_KEY_STATUS, ParamUtils.paramEncode(this.status.toString()));
      
      return this.toHttpGetMethod(auth, ci, this.path(this.serviceName, this.categoryName, OPERATION_NAME), requestParams);
    }
    catch(UnsupportedEncodingException e) {
      throw new RequestException(e.getMessage());
    }
  }
}
