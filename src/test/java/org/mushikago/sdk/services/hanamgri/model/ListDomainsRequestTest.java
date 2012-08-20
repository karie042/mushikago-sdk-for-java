package org.mushikago.sdk.services.hanamgri.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.net.URI;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.junit.Test;
import org.mushikago.sdk.common.RequestException;

public class ListDomainsRequestTest extends HanamgriRequestTestBase {
    
  @Test
  public void testConstructWithRequiredParameters() {
    ListDomainsRequest request = new ListDomainsRequest(5, 5, "filter");
    Integer limit = 5;
    Integer offset = 5;
    assertEquals(limit, request.getLimit());
    assertEquals(offset, request.getOffset());
    assertEquals("filter", request.getFilter());
  }
  
  @Test
  public void testSetterAndGetterForLimit(){
    ListDomainsRequest request = new ListDomainsRequest();
    Integer limit = 10;
    request.setLimit(10);
    assertEquals(limit, request.getLimit());
  }
  
  @Test
  public void testSetterAndGetterForOffset(){
    ListDomainsRequest request = new ListDomainsRequest();
    Integer offset = 20;
    request.setOffset(20);
    assertEquals(offset, request.getOffset());
  }
  
  @Test
  public void testSetterAndGetterForFilter(){
    ListDomainsRequest request = new ListDomainsRequest();
    request.setFilter("filter");
    assertEquals("filter", request.getFilter());
  }
  
  private HttpRequestBase callToHttpMethod(Integer limit, Integer offset, String filter)
      throws RequestException {
    ListDomainsRequest request = new ListDomainsRequest(limit, offset, filter);
    return request.toHttpMethod(auth, ci);
  }
  
  @Test
  public void testHttpMethodIsHttpGet() throws RequestException {
    HttpRequestBase request = callToHttpMethod(1,10,"filter");
    assertTrue(request instanceof HttpGet);
  }

  @Test
  public void testHttpMethodPathAndQueryContainsParameters() throws RequestException {
    HttpRequestBase request = callToHttpMethod(1,10,"filter");
    URI uri = request.getURI();
    assertEquals("/1/hanamgri/domains", uri.getRawPath());
  }
}
