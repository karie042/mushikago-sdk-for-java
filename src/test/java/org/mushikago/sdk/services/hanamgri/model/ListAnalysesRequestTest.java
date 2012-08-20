package org.mushikago.sdk.services.hanamgri.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.net.URI;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.junit.Test;
import org.mushikago.sdk.common.RequestException;

public class ListAnalysesRequestTest extends HanamgriRequestTestBase {
    
  @Test
  public void testConstructWithRequiredParameters() {
    ListAnalysesRequest request = new ListAnalysesRequest("domain_name");    
    assertEquals("domain_name", request.getDomainName());
  }
  
  @Test
  public void testSetterAndGetterForLimit(){
    ListAnalysesRequest request = new ListAnalysesRequest();
    Integer limit = 10;
    request.setLimit(10);
    assertEquals(limit, request.getLimit());
  }
  
  @Test
  public void testSetterAndGetterForOffset(){
    ListAnalysesRequest request = new ListAnalysesRequest();
    Integer offset = 20;
    request.setOffset(20);
    assertEquals(offset, request.getOffset());
  }
  
  @Test
  public void testSetterAndGetterForStatus(){
    ListAnalysesRequest request = new ListAnalysesRequest();
    request.setStatus("complete");
    assertEquals("complete", request.getStatus());
  }

  @Test(expected = RequestException.class)
  public void testThrowExceptionIfDomainNameIsNull() throws RequestException {
    ListAnalysesRequest request = new ListAnalysesRequest(null);
    request.toHttpMethod(auth, ci);
  }

  private HttpRequestBase callToHttpMethod(String domainName)
      throws RequestException {
    ListAnalysesRequest request = new ListAnalysesRequest(domainName);
    return request.toHttpMethod(auth, ci);
  }
  
  @Test
  public void testHttpMethodIsHttpGet() throws RequestException {
    HttpRequestBase request = callToHttpMethod("domain_name");
    assertTrue(request instanceof HttpGet);
  }

  @Test
  public void testHttpMethodPathAndQueryContainsParameters() throws RequestException {
    HttpRequestBase request = callToHttpMethod("domain_name");
    URI uri = request.getURI();
    assertEquals("/1/hanamgri/domains/domain_name/analyses", uri.getRawPath());
  }
}
