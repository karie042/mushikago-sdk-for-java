package org.mushikago.sdk.services.hanamgri.model;

import static org.junit.Assert.*;

import java.net.URI;

import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.junit.Test;
import org.mushikago.sdk.common.RequestException;

public class RequestAnalysisRequestTest extends HanamgriRequestTestBase {

  @Test
  public void testConstruct() {
    RequestAnalysisRequest request = new RequestAnalysisRequest();
    assertNull(request.getDomainName());
    assertNull(request.getUrl());
    assertNull(request.getCharset());
    assertNull(request.getTag());
  }

  @Test
  public void testConstructWithRequiredParameters() {
    RequestAnalysisRequest request = new RequestAnalysisRequest("domain", "url");
    assertEquals("domain", request.getDomainName());
    assertEquals("url", request.getUrl());
    assertNull(request.getCharset());
    assertNull(request.getTag());
  }

  @Test
  public void testSetterAndGetterForDomainName() {
    RequestAnalysisRequest request = new RequestAnalysisRequest();
    request.setDomainName("domain");
    assertEquals("domain", request.getDomainName());
  }

  @Test
  public void testSetterAndGetterForUrl() {
    RequestAnalysisRequest request = new RequestAnalysisRequest();
    request.setUrl("url");
    assertEquals("url", request.getUrl());
  }

  @Test
  public void testSetterAndGetterForCharset() {
    RequestAnalysisRequest request = new RequestAnalysisRequest();
    request.setCharset("charset");
    assertEquals("charset", request.getCharset());
  }

  @Test
  public void testSetterAndGetterForTag() {
    RequestAnalysisRequest request = new RequestAnalysisRequest();
    request.setTag("tag");
    assertEquals("tag", request.getTag());
  }
  
  @Test
  public void testSetterAndGetterForAutoFeedback() {
    RequestAnalysisRequest request = new RequestAnalysisRequest();
    request.setAutoFeedback(false);
    assertEquals(false, request.getAutoFeedback());
  }

  @Test(expected = RequestException.class)
  public void testThrowExceptionIfDomainNameIsNull() throws RequestException {
    RequestAnalysisRequest request = new RequestAnalysisRequest(null, "url");
    request.toHttpMethod(auth, ci);
  }

  @Test(expected = RequestException.class)
  public void testThrowExceptionIfSeedsIsNull() throws RequestException {
    RequestAnalysisRequest request = new RequestAnalysisRequest("domain", null);
    request.toHttpMethod(auth, ci);
  }

  private HttpRequestBase callToHttpMethod(String domainName, String url, String charset, String tag, Boolean autoFeedback)
      throws RequestException {
    RequestAnalysisRequest request = new RequestAnalysisRequest(domainName, url);
    request.setCharset(charset);
    request.setTag(tag);
    return request.toHttpMethod(auth, ci);
  }

  @Test
  public void testHttpMethodIsHttpPut() throws RequestException {
    HttpRequestBase request = callToHttpMethod("domain", "url", "charset", "tag", false);
    assertTrue(request instanceof HttpPut);
  }

  @Test
  public void testHttpMethodPathAndQueryContainsParameters() throws RequestException {
    HttpRequestBase request = callToHttpMethod("domain", "url", "charset", "tag", false);
    URI uri = request.getURI();
    assertEquals("/1/hanamgri/domains/domain/analysis", uri.getRawPath());
  }
}
