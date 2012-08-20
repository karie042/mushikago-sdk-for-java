package org.mushikago.sdk.services.hanamgri.model;

import static org.junit.Assert.*;

import java.net.URI;

import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.junit.Test;
import org.mushikago.sdk.common.RequestException;

public class SaveKnowledgeRequestTest extends HanamgriRequestTestBase {

  @Test
  public void testConstruct() {
    SaveKnowledgeRequest request = new SaveKnowledgeRequest();
    assertNull(request.getDomainName());
    assertNull(request.getDescription());
  }

  @Test
  public void testConstructWithDomainName() {
    SaveKnowledgeRequest request = new SaveKnowledgeRequest("domain", "field");
    assertEquals("domain", request.getDomainName());
    assertEquals("field", request.getFieldName());
    assertNull(request.getDescription());
  }

  @Test
  public void testRequestWithDomainNameAndDescription() {
    SaveKnowledgeRequest request = new SaveKnowledgeRequest("domain", "field");
    request.setDescription("description");
    assertEquals("domain", request.getDomainName());
    assertEquals("field", request.getFieldName());
    assertEquals("description", request.getDescription());
  }

  @Test
  public void testSetterAndGetterForDomainName() {
    SaveKnowledgeRequest request = new SaveKnowledgeRequest();
    request.setDomainName("domain");
    assertEquals("domain", request.getDomainName());
  }

  @Test
  public void testSetterAndGetterForDescription() {
    SaveKnowledgeRequest request = new SaveKnowledgeRequest();
    request.setDescription("description");
    assertEquals("description", request.getDescription());
  }

  @Test(expected = RequestException.class)
  public void testThrowExceptionIfDomainNameIsNull() throws RequestException {
    SaveKnowledgeRequest request = new SaveKnowledgeRequest();
    request.toHttpMethod(auth, ci);
  }

  private HttpRequestBase callToHttpMethod(String domainName, String description) throws RequestException {
    SaveKnowledgeRequest request = new SaveKnowledgeRequest(domainName, "field");
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
    assertEquals("/1/hanamgri/knowledge", uri.getRawPath());
  }

  @Test
  public void testHttpMethodPathAndQueryContainsParametersWithJapanese() throws RequestException {
    HttpRequestBase request = callToHttpMethod("domain", "説明！");
    URI uri = request.getURI();
    assertEquals("/1/hanamgri/knowledge", uri.getRawPath());
  }
}
