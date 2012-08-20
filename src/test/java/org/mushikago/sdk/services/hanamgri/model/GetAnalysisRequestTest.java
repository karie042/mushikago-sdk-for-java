package org.mushikago.sdk.services.hanamgri.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.net.URI;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.junit.Test;
import org.mushikago.sdk.common.RequestException;

public class GetAnalysisRequestTest extends HanamgriRequestTestBase {

  @Test
  public void testConstruct() {
    GetAnalysisRequest request = new GetAnalysisRequest();
    assertNull(request.getDomainName());
    assertNull(request.getRequestId());
  }

  @Test
  public void testConstructWithRequiredParameters() {
    GetAnalysisRequest request = new GetAnalysisRequest("domamin_name", "request_id");
    assertEquals("domamin_name", request.getDomainName());
    assertEquals("request_id", request.getRequestId());
  }

  @Test
  public void testSetterAndGetterForDomainName() {
    GetAnalysisRequest request = new GetAnalysisRequest();
    request.setDomainName("domain");
    assertEquals("domain", request.getDomainName());
  }

  @Test
  public void testSetterAndGetterForRequestId() {
    GetAnalysisRequest request = new GetAnalysisRequest();
    request.setRequestId("request_id");
    assertEquals("request_id", request.getRequestId());
  }

  @Test(expected = RequestException.class)
  public void testThrowExceptionIfDomainNameIsNull() throws RequestException {
    GetAnalysisRequest request = new GetAnalysisRequest(null, "request_id");
    request.toHttpMethod(auth, ci);
  }

  @Test(expected = RequestException.class)
  public void testThrowExceptionIfRequestIdIsNull() throws RequestException {
    GetAnalysisRequest request = new GetAnalysisRequest("domain", null);
    request.toHttpMethod(auth, ci);
  }

  private HttpRequestBase callToHttpMethod(String domainName, String requestId)
      throws RequestException {
    GetAnalysisRequest request = new GetAnalysisRequest(domainName, requestId);
    return request.toHttpMethod(auth, ci);
  }

  @Test
  public void testHttpMethodIsHttpGet() throws RequestException {
    HttpRequestBase request = callToHttpMethod("domain", "request_id");
    assertTrue(request instanceof HttpGet);
  }

  @Test
  public void testHttpMethodPathAndQueryContainsParameters() throws RequestException {
    HttpRequestBase request = callToHttpMethod("domain", "request");
    URI uri = request.getURI();
    assertEquals("/1/hanamgri/domains/domain/analyses/request", uri.getRawPath());
  }

}
