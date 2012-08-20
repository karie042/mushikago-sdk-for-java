package org.mushikago.sdk.services.hanamgri.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.net.URI;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.junit.Test;
import org.mushikago.sdk.common.RequestException;

public class GetInformationRequestTest extends HanamgriRequestTestBase {
  
  private static final String URI = "/1/hanamgri/domains/";
  private static final String PARAMETER_VALUE_DOMAIN_NAME = "domain";
  
  @Test
  public void testConstruct() {
    GetInformationRequest request = new GetInformationRequest();
    assertNull(request.getDomainName());
  }

  @Test
  public void testConstructWithRequiredParameters() {
    GetInformationRequest request = new GetInformationRequest(PARAMETER_VALUE_DOMAIN_NAME);
    assertEquals(PARAMETER_VALUE_DOMAIN_NAME, request.getDomainName());
  }

  @Test
  public void testSetterAndGetterForDomainName() {
    GetInformationRequest request = new GetInformationRequest();
    request.setDomainName(PARAMETER_VALUE_DOMAIN_NAME);
    assertEquals(PARAMETER_VALUE_DOMAIN_NAME, request.getDomainName());
  }

  @Test(expected = RequestException.class)
  public void testThrowExceptionIfDomainNameIsNull() throws RequestException {
    GetInformationRequest request = new GetInformationRequest(null);
    request.toHttpMethod(auth, ci);
  }

  private HttpRequestBase callToHttpMethod(String domainName)
      throws RequestException {
    GetInformationRequest request = new GetInformationRequest(domainName);
    return request.toHttpMethod(auth, ci);
  }

  @Test
  public void testHttpMethodIsHttpGet() throws RequestException {
    HttpRequestBase request = callToHttpMethod(PARAMETER_VALUE_DOMAIN_NAME);
    assertTrue(request instanceof HttpGet);
  }

  @Test
  public void testHttpMethodPathAndQueryContainsParameters() throws RequestException {
    HttpRequestBase request = callToHttpMethod(PARAMETER_VALUE_DOMAIN_NAME);
    URI uri = request.getURI();
    assertEquals(URI+PARAMETER_VALUE_DOMAIN_NAME, uri.getRawPath());
  }

}
