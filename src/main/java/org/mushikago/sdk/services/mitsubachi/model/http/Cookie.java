package org.mushikago.sdk.services.mitsubachi.model.http;

import net.sf.json.JSONObject;

public class Cookie {
  private String name;
  private String value;
  private String domain;
  private String path;
  private boolean isSecure;

  public Cookie(String name, String value, String domain, String path, boolean isSecure) {
    this.name = name;
    this.value = value;
    this.domain = domain;
    this.path = path;
    this.isSecure = isSecure;
  }

  public String getName() {
    return name;
  }

  public String getValue() {
    return value;
  }

  public String getDomain() {
    return domain;
  }

  public String getPath() {
    return path;
  }

  public boolean isSecure() {
    return isSecure;
  }
  
  public String toJSONString() {
    JSONObject cookie = JSONObject.fromObject(this);
    return cookie.toString();
  }
}
