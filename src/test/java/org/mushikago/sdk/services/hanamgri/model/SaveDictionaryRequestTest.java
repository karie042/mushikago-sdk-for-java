package org.mushikago.sdk.services.hanamgri.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.net.URI;

import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.junit.Test;
import org.mushikago.sdk.common.RequestException;

public class SaveDictionaryRequestTest extends HanamgriRequestTestBase {

  @Test
  public void testConstruct() {
    SaveDictionaryRequest request = new SaveDictionaryRequest();
    assertNull(request.getDomainName());
    assertNull(request.getDescription());
  }

  @Test
  public void testConstructWithDomainName() {
    SaveDictionaryRequest request = new SaveDictionaryRequest("domain");
    assertEquals("domain", request.getDomainName());
    assertNull(request.getDescription());
  }

  @Test
  public void testRequestWithDomainNameAndDescription() {
    SaveDictionaryRequest request = new SaveDictionaryRequest("domain");
    request.setDescription("description");
    assertEquals("domain", request.getDomainName());
    assertEquals("description", request.getDescription());
  }

  @Test
  public void testSetterAndGetterForDomainName() {
    SaveDictionaryRequest request = new SaveDictionaryRequest();
    request.setDomainName("domain");
    assertEquals("domain", request.getDomainName());
  }

  @Test
  public void testSetterAndGetterForDescription() {
    SaveDictionaryRequest request = new SaveDictionaryRequest();
    request.setDescription("description");
    assertEquals("description", request.getDescription());
  }

  @Test(expected = RequestException.class)
  public void testThrowExceptionIfDomainNameIsNull() throws RequestException {
    SaveDictionaryRequest request = new SaveDictionaryRequest(null);
    request.toHttpMethod(auth, ci);
  }

  private HttpRequestBase callToHttpMethod(String domainName, String description) throws RequestException {
    SaveDictionaryRequest request = new SaveDictionaryRequest(domainName);
    request.setDescription(description);
    return request.toHttpMethod(auth, ci);
  }

  @Test
  public void testHttpMethodIsHttpPut() throws RequestException {
    HttpRequestBase request = callToHttpMethod("domain", "description");
    assertTrue(request instanceof HttpPut);
  }

  @Test
  public void testHttpMethodPathAndQueryContainsParameters() throws RequestException {
    HttpRequestBase request = callToHttpMethod("domain", "description");
    URI uri = request.getURI();
    assertEquals("/1/hanamgri/dictionary", uri.getRawPath());
  }

  @Test
  public void testHttpMethodPathAndQueryContainsParametersWithJapanese() throws RequestException {
    HttpRequestBase request = callToHttpMethod("domain", "説明！");
    URI uri = request.getURI();
    assertEquals("/1/hanamgri/dictionary", uri.getRawPath());
  }
}
