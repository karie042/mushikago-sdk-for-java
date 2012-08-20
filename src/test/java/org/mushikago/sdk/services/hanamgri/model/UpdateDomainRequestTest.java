package org.mushikago.sdk.services.hanamgri.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.net.URI;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.junit.Test;
import org.mushikago.sdk.common.RequestException;

public class UpdateDomainRequestTest extends HanamgriRequestTestBase {

  @Test
  public void testConstruct() {
    UpdateDomainRequest request = new UpdateDomainRequest();
    assertNull(request.getDomainName());
    assertNull(request.getDescription());
  }

  @Test
  public void testConstructWithDomainNameAndDescription() {
    UpdateDomainRequest request = new UpdateDomainRequest("domain", "description");
    assertEquals("domain", request.getDomainName());
    assertEquals("description", request.getDescription());
  }

  @Test
  public void testSetterAndGetterForDomainName() {
    UpdateDomainRequest request = new UpdateDomainRequest();
    request.setDomainName("domain");
    assertEquals("domain", request.getDomainName());
  }

  @Test
  public void testSetterAndGetterForDescription() {
    UpdateDomainRequest request = new UpdateDomainRequest();
    request.setDescription("description");
    assertEquals("description", request.getDescription());
  }

  @Test(expected = RequestException.class)
  public void testThrowExceptionIfDomainNameIsNull() throws RequestException {
    UpdateDomainRequest request = new UpdateDomainRequest(null, "description");
    request.toHttpMethod(auth, ci);
  }

  @Test(expected = RequestException.class)
  public void testThrowExceptionIfDescriptionIsNull() throws RequestException {
    UpdateDomainRequest request = new UpdateDomainRequest("domain", null);
    request.toHttpMethod(auth, ci);
  }

  private HttpRequestBase callToHttpMethod(String domainName, String description) throws RequestException {
    UpdateDomainRequest request = new UpdateDomainRequest(domainName, description);
    request.setDescription(description);
    return request.toHttpMethod(auth, ci);
  }

  @Test
  public void testHttpMethodIsHttpPost() throws RequestException {
    HttpRequestBase request = callToHttpMethod("domain", "description");
    assertTrue(request instanceof HttpPost);
  }

  @Test
  public void testHttpMethodPathAndQueryContainsParameters() throws RequestException {
    HttpRequestBase request = callToHttpMethod("domain", "description");
    URI uri = request.getURI();
    assertEquals("/1/hanamgri/domains/domain", uri.getRawPath());
  }

  @Test
  public void testHttpMethodPathAndQueryContainsParametersWithJapanese() throws RequestException {
    HttpRequestBase request = callToHttpMethod("domain", "説明！");
    URI uri = request.getURI();
    assertEquals("/1/hanamgri/domains/domain", uri.getRawPath());
  }
}
