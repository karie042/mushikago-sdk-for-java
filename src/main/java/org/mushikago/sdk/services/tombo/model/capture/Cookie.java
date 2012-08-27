package org.mushikago.sdk.services.tombo.model.capture;

import net.sf.json.JSONObject;

public class Cookie {
  private String name;
  private String value;

  public Cookie(String name, String value) {
    this.name = name;
    this.value = value;
  }

  public String getName() {
    return name;
  }

  public String getValue() {
    return value;
  }

  public String toJSONString() {
    JSONObject cookie = JSONObject.fromObject(this);
    return cookie.toString();
  }
}
