package org.mushikago.sdk.services.hanamgri.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.net.URI;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.junit.Test;
import org.mushikago.sdk.common.RequestException;

public class GetQueueSizeRequestTest extends HanamgriRequestTestBase {
  @Test
  public void testConstruct() {
    GetQueueSizeRequest request = new GetQueueSizeRequest();
    assertNull(request.getDomainName());
  }

  @Test
  public void testConstructWithRequiredParameters() {
    GetQueueSizeRequest request = new GetQueueSizeRequest("domamin_name");
    assertEquals("domamin_name", request.getDomainName());
  }

  @Test
  public void testSetterAndGetterForDomainName() {
    GetQueueSizeRequest request = new GetQueueSizeRequest();
    request.setDomainName("domain");
    assertEquals("domain", request.getDomainName());
  }

  @Test(expected = RequestException.class)
  public void testThrowExceptionIfDomainNameIsNull() throws RequestException {
    GetQueueSizeRequest request = new GetQueueSizeRequest(null);
    request.toHttpMethod(auth, ci);
  }

  private HttpRequestBase callToHttpMethod(String domainName)
      throws RequestException {
    GetQueueSizeRequest request = new GetQueueSizeRequest(domainName);
    return request.toHttpMethod(auth, ci);
  }

  @Test
  public void testHttpMethodIsHttpGet() throws RequestException {
    HttpRequestBase request = callToHttpMethod("domain");
    assertTrue(request instanceof HttpGet);
  }

  @Test
  public void testHttpMethodPathAndQueryContainsParameters() throws RequestException {
    HttpRequestBase request = callToHttpMethod("domain");
    URI uri = request.getURI();
    assertEquals("/1/hanamgri/domains/domain/queues", uri.getRawPath());
  }

}
