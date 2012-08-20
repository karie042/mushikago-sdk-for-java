package org.mushikago.sdk.services.hanamgri.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpRequestBase;
import org.junit.Test;
import org.mushikago.sdk.common.RequestException;

public class DeleteDomainRequestTest extends HanamgriRequestTestBase {

  @Test
  public void testConstruct() {
    DeleteDomainRequest request = new DeleteDomainRequest();
    assertNull(request.getDomainName());
  }

  @Test
  public void testConstructWithRequiredParameters() {
    DeleteDomainRequest request = new DeleteDomainRequest("domain");
    assertEquals("domain", request.getDomainName());
  }

  @Test
  public void testSetterAndGetterForDomainName() {
    DeleteDomainRequest request = new DeleteDomainRequest();
    request.setDomainName("domain");
    assertEquals("domain", request.getDomainName());
  }

  @Test(expected = RequestException.class)
  public void testThrowExceptionIfDomainNameIsNull() throws RequestException {
    DeleteDomainRequest request = new DeleteDomainRequest(null);
    request.toHttpMethod(auth, ci);
  }

  private HttpRequestBase callToHttpMethod(String domainName) throws RequestException {
    DeleteDomainRequest request = new DeleteDomainRequest(domainName);
    return request.toHttpMethod(auth, ci);
  }

  @Test
  public void testHttpMethodIsHttpDelete() throws RequestException {
    HttpRequestBase request = callToHttpMethod("domain");
    assertTrue(request instanceof HttpDelete);
  }
}
