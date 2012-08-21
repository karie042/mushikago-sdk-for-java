package org.mushikago.sdk.services.tombo.model;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.junit.Test;
import org.mushikago.sdk.services.tombo.model.capture.CaptureRequest;

public class CaptureRequestTest extends TomboRequestTestBase {

  @Test
  public void testGetterAndSetterForDisplayWidth() {
    CaptureRequest request = new CaptureRequest("http://www.mushikago.org/");
    request.setDisplayWidth(200);
    assertThat(request.getDisplayWidth(), is(200));
  }

  @Test
  public void testWithDisplayWidth() {
    CaptureRequest request = new CaptureRequest("http://www.mushikago.org/").withDisplayWidth(200);
    assertThat(request.getDisplayWidth(), is(200));
  }

  @Test
  public void testRequestIncludesDisplayWidth() throws Exception {
    CaptureRequest request = new CaptureRequest("http://www.mushikago.org/").withDisplayWidth(200);
    HttpRequestBase requestBase = request.toHttpMethod(auth, ci);
    assertThat(requestBase, is(HttpPost.class));

    HttpPost postMethod = (HttpPost) requestBase;
    HttpEntity entity = postMethod.getEntity();
    assertThat(entity, is(StringEntity.class));

    String requestBody = streamToString(entity.getContent());
    assertThat(requestBody, containsString("url=http%3A%2F%2Fwww.mushikago.org%2F"));
    assertThat(requestBody, containsString("display_width=200"));
  }
}
