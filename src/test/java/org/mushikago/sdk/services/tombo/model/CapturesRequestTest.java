package org.mushikago.sdk.services.tombo.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.net.URI;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.junit.Test;
import org.mushikago.sdk.common.RequestException;
import org.mushikago.sdk.services.tombo.model.captures.CapturesRequest;

public class CapturesRequestTest extends TomboRequestTestBase {

  @Test
  public void testSetterAndGetterForId() {
    CapturesRequest request = new CapturesRequest();
    String id = "id";
    request.setId(id);
    assertEquals(id, request.getId());
  }

  @Test
  public void testSetterAndGetterForLimit() {
    CapturesRequest request = new CapturesRequest();
    int limit = 10;
    request.setLimit(10);
    assertEquals(limit, request.getLimit());
  }

  @Test
  public void testSetterAndGetterForOffset() {
    CapturesRequest request = new CapturesRequest();
    int offset = 20;
    request.setOffset(20);
    assertEquals(offset, request.getOffset());
  }

  @Test
  public void testSetterAndGetterForDomain() {
    CapturesRequest request = new CapturesRequest();
    String domain = "miningbrownie.co.jp";
    request.setDomain(domain);
    assertEquals(domain, request.getDomain());
  }

  @Test
  public void testSetterAndGetterForTag() {
    CapturesRequest request = new CapturesRequest();
    String tag = "test";
    request.setTag(tag);
    assertEquals(tag, request.getTag());
  }

  @Test
  public void testSetterAndGetterForSourceUrl() {
    CapturesRequest request = new CapturesRequest();
    String sourceUrl = "http://www.miningbrownie.co.jp";
    request.setSourceUrl(sourceUrl);
    assertEquals(sourceUrl, request.getSourceUrl());
  }

  private HttpRequestBase callToHttpMethod() throws RequestException {
    CapturesRequest request = new CapturesRequest();
    return request.toHttpMethod(auth, ci);
  }

  @Test
  public void testHttpMethodIsHttpGet() throws RequestException {
    HttpRequestBase request = callToHttpMethod();
    assertTrue(request instanceof HttpGet);
  }

  @Test
  public void testHttpmethodPathAndQueryContainsParameters() throws RequestException {
    HttpRequestBase request = callToHttpMethod();
    URI uri = request.getURI();
    assertEquals("/1/tombo/captures.json", uri.getRawPath());
  }

}
