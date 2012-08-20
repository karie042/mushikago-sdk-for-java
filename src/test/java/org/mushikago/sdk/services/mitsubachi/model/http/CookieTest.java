package org.mushikago.sdk.services.mitsubachi.model.http;

import static org.junit.matchers.JUnitMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class CookieTest {
  @Test
  public void testToJSONString() {
    Cookie cookie = new Cookie("name", "value", "domain", "path", true);
    assertThat(cookie.toJSONString(), containsString("\"domain\":\"domain\""));
    assertThat(cookie.toJSONString(), containsString("\"name\":\"name\""));
    assertThat(cookie.toJSONString(), containsString("\"path\":\"path\""));
    assertThat(cookie.toJSONString(), containsString("\"secure\":true"));
    assertThat(cookie.toJSONString(), containsString("\"value\":\"value\""));
  }
}
