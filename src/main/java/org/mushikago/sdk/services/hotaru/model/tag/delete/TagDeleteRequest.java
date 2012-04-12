package org.mushikago.sdk.services.hotaru.model.tag.delete;

import java.io.UnsupportedEncodingException;
import java.util.TreeMap;

import org.apache.http.client.methods.HttpRequestBase;
import org.mushikago.sdk.common.RequestException;
import org.mushikago.sdk.common.auth.MushikagoAuth;
import org.mushikago.sdk.common.util.ConnectInfo;
import org.mushikago.sdk.common.util.ParamUtils;
import org.mushikago.sdk.services.hotaru.model.tag.TagRequest;

public class TagDeleteRequest extends TagRequest {
  
  public static final String OPERATION_NAME = "delete";
  
  public static final String PARAM_KEY_DOMAIN_NAME = "domain_name";
  public static final String PARAM_KEY_TAG = "tag";
  
  private String domainName = null;
  private String tag = null;
  
  public TagDeleteRequest() {}
  
  public TagDeleteRequest(String domainName) {
    this.domainName = domainName;
  }
  
  public TagDeleteRequest(String domainName, String tag) {
    this.domainName = domainName;
    this.tag = tag;
  }
  
  public String getDomainName() { return this.domainName; }
  public String getTag() { return this.tag; }
  
  public void setDomainName(String name) { this.domainName = name; }
  public void setTag(String tag) { this.tag = tag; }
  
  @Override
  public HttpRequestBase toHttpMethod(MushikagoAuth auth, ConnectInfo ci) throws RequestException {
    
    try {
      TreeMap<String, String> requestParams = new TreeMap<String, String>();
      if(null == this.domainName) throw new RequestException("ドメイン名を指定してください");
      requestParams.put(PARAM_KEY_DOMAIN_NAME, ParamUtils.paramEncode(this.domainName));
      if(null == this.tag) throw new RequestException("タグを指定してください");
      requestParams.put(PARAM_KEY_TAG, ParamUtils.paramEncode(this.tag));
      return this.toHttpDeleteMethod(auth, ci, this.path(this.serviceName, this.categoryName, OPERATION_NAME), requestParams);
    }
    catch(UnsupportedEncodingException e) {
      throw new RequestException(e.getMessage());
    }
  }
}
