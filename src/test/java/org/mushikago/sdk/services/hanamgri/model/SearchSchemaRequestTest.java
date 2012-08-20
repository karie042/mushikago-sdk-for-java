package org.mushikago.sdk.services.hanamgri.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.net.URI;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.junit.Test;
import org.mushikago.sdk.common.RequestException;

public class SearchSchemaRequestTest extends HanamgriRequestTestBase {

  @Test
  public void testConstruct() {
    SearchSchemaRequest request = new SearchSchemaRequest();
    assertNull(request.getDomainName());
    assertNull(request.getQueryKey());
    assertNull(request.getQueryValue());
  }
  
  @Test
  public void testConstructWitｈRequiredParameters() {
    SearchSchemaRequest request = new SearchSchemaRequest("domain","queryKey", "queryValue");
    assertEquals("domain", request.getDomainName());
    assertEquals("queryKey", request.getQueryKey());
    assertEquals("queryValue", request.getQueryValue());
  }
  
  @Test
  public void testSetterAndGetterFromLimit(){
    SearchSchemaRequest request = new SearchSchemaRequest();
    Integer limit = 10;
    request.setLimit(10);
    assertEquals(limit, request.getLimit());
  }
  
  @Test
  public void teseSetterAndGetterFromOffset(){
    SearchSchemaRequest request = new SearchSchemaRequest();
    Integer offset = 20;
    request.setOffset(20);
    assertEquals(offset, request.getOffset());
  }
  
  @Test(expected = RequestException.class)
  public void testThrowEceptionIfDomainNameIsNull() throws RequestException {
    SearchSchemaRequest request = new SearchSchemaRequest(null,"queryKey","queryValue");
    request.toHttpMethod(auth, ci);
  }
  
  @Test(expected = RequestException.class)
  public void testThrowEceptionIfQueryKeyIsNull() throws RequestException {
    SearchSchemaRequest request = new SearchSchemaRequest("domain",null,"queryValue");
    request.toHttpMethod(auth, ci);
  }
  
  @Test(expected = RequestException.class)
  public void testThrowEceptionIfQueryValueIsNull() throws RequestException {
    SearchSchemaRequest request = new SearchSchemaRequest("domain","queryKey",null);
    request.toHttpMethod(auth, ci);
  }
  
  private HttpRequestBase callToHttpMethod(String domainName, String queryKey, String queryValue)
      throws RequestException {
    SearchSchemaRequest request = new SearchSchemaRequest(domainName,queryKey,queryValue);
    return request.toHttpMethod(auth, ci);
  }
  
  @Test
  public void testHttpMethodIsHttpGet() throws RequestException {
    HttpRequestBase request = callToHttpMethod("domain_name","queryKey","queryValue");
    assertTrue(request instanceof HttpGet);
  }

  @Test
  public void testHttpMethodPathAndQueryContainsParameters() throws RequestException {
    HttpRequestBase request = callToHttpMethod("domain_name","queryKey","queryValue");
    URI uri = request.getURI();
    assertEquals("/1/hanamgri/domains/domain_name/schema/search", uri.getRawPath());
  }

  
}
