package org.mushikago.sdk.services.hotaru.model.tag.put;

import java.io.UnsupportedEncodingException;
import java.util.TreeMap;

import org.apache.http.client.methods.HttpRequestBase;
import org.mushikago.sdk.common.RequestException;
import org.mushikago.sdk.common.auth.MushikagoAuth;
import org.mushikago.sdk.common.util.ConnectInfo;
import org.mushikago.sdk.common.util.ParamUtils;
import org.mushikago.sdk.services.hotaru.model.tag.TagRequest;

/**
 * タグ登録リクエスト。<br>
 * @author miningbrownie
 */
public class TagPutRequest extends TagRequest {
  
  /**
   * 操作名
   */
  public static final String OPERATION_NAME = "put";
  
  /**
   * パラメータ名（ドメイン名）
   */
  public static final String PARAM_KEY_DOMAIN_NAME = "domain_name";
  
  /**
   * パラメータ名（タグ）
   */
  public static final String PARAM_KEY_TAG = "tag";
  
  /**
   * パラメータ（ドメイン名）
   */
  private String domainName = null;
  
  /**
   * パラメータ（タグ）
   */
  private String tag = null;
  /**
   * パラメータを持たない、空のTagPutRequestを構築します。<br>
   */
  public TagPutRequest() {}
  
  /**
   * 指定されたドメイン名を使用して、TagPutRequestを構築します。<br>
   * @param domainName ドメイン名
   */
  public TagPutRequest(String domainName) {
    this.domainName = domainName;
  }
  
  /**
   * 指定されたドメイン名、タグを使用して、TagPutRequestを構築します。<br>
   * @param domainName ドメイン名
   * @param tag タグ
   */
  public TagPutRequest(String domainName, String tag) {
    this.domainName = domainName;
    this.tag = tag;
  }
  
  /**
   * ドメイン名を取得します。<br>
   * @return ドメイン名
   */
  public String getDomainName() { return this.domainName; }
  
  /**
   * タグを取得します。<br>
   * @return タグ
   */
  public String getTag() { return this.tag; }
  
  /**
   * ドメイン名を設定します。<br>
   * @param name ドメイン名
   */
  public void setDomainName(String name) { this.domainName = name; }
  
  /**
   * タグを設定します。<br>
   * @param tag タグ
   */
  public void setTag(String tag) { this.tag = tag; }
  
  @Override
  public HttpRequestBase toHttpMethod(MushikagoAuth auth, ConnectInfo ci) throws RequestException {
    
    try {
      TreeMap<String, String> requestParams = new TreeMap<String, String>();
      if(null == this.domainName) throw new RequestException("ドメイン名を指定してください");
      requestParams.put(PARAM_KEY_DOMAIN_NAME, ParamUtils.paramEncode(this.domainName));
      if(null == this.tag) throw new RequestException("タグを指定してください");
      requestParams.put(PARAM_KEY_TAG, ParamUtils.paramEncode(this.tag));
      return this.toHttpPutMethod(auth, ci, this.path(this.serviceName, this.categoryName, OPERATION_NAME), requestParams);
    }
    catch(UnsupportedEncodingException e) {
      throw new RequestException(e.getMessage());
    }
  }
}
