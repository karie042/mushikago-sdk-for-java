package org.mushikago.sdk.services.hotaru.model.domain.delete;

import java.io.UnsupportedEncodingException;
import java.util.TreeMap;

import org.apache.http.client.methods.HttpRequestBase;
import org.mushikago.sdk.common.RequestException;
import org.mushikago.sdk.common.auth.MushikagoAuth;
import org.mushikago.sdk.common.util.ConnectInfo;
import org.mushikago.sdk.common.util.ParamUtils;
import org.mushikago.sdk.services.hotaru.model.domain.DomainRequest;

/**
 * ドメイン削除リクエスト。<br>
 * @author miningbrownie
 */
public class DomainDeleteRequest extends DomainRequest {
  
  /**
   * 操作名
   */
  public static final String OPERATION_NAME = "delete";
  
  /**
   * リクエストパラメータ名(domain_name)
   */
  public static final String PARAM_KEY_DOMAIN_NAME = "domain_name";
  
  /**
   * リクエストパラメータ（ドメイン名）
   */
  private String domainName = null;
  
  /**
   * パラメータを持たないDomainDeleteRequestオブジェクトを構築します。<br>
   */
  public DomainDeleteRequest() {}
  
  /**
   * 指定されたドメイン名を使用して、DomainDeleteRequestオブジェクトを構築します。<br>
   * @param domainName ドメイン名
   */
  public DomainDeleteRequest(String domainName) {
    this.domainName = domainName;
  }
  
  /**
   * ドメイン名を取得します。<br>
   * @return ドメイン名
   */
  public String getDomainName() { return this.domainName; }
  
  /**
   * ドメイン名を設定します。<br>
   * @param name ドメイン名
   */
  public void setDomainName(String name) { this.domainName = name; }
  
  @Override
  public HttpRequestBase toHttpMethod(MushikagoAuth auth, ConnectInfo ci) throws RequestException {
    
    try {
      TreeMap<String, String> requestParams = new TreeMap<String, String>();
      
      if(null == this.domainName) throw new RequestException("ドメイン名を指定してください");
      requestParams.put(PARAM_KEY_DOMAIN_NAME, ParamUtils.paramEncode(this.domainName));
      
      return this.toHttpDeleteMethod(auth, ci, this.path(this.serviceName, this.categoryName, OPERATION_NAME), requestParams);
    }
    catch(UnsupportedEncodingException e) {
      throw new RequestException(e.getMessage());
    }
  }
}
