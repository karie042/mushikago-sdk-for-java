package org.mushikago.sdk.services.tombo.model;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.json.JSONObject;

import org.junit.Test;
import org.mushikago.sdk.services.tombo.model.capture.Cookie;

public class CookieTest {
  @Test
  public void testCookieConstructor() {
    Cookie cookie = new Cookie("hoge", "fuga");
    assertThat(cookie.getName(), is("hoge"));
    assertThat(cookie.getValue(), is("fuga"));
  }

  @Test
  public void testCookieToJson() {
    Cookie cookie = new Cookie("hoge", "fuga");
    JSONObject expected = new JSONObject();
    expected.put("name", "hoge");
    expected.put("value", "fuga");
    assertThat(cookie.toJSONString(), is(expected.toString()));
  }
}
