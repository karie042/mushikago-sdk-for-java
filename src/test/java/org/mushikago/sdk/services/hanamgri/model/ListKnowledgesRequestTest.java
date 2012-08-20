package org.mushikago.sdk.services.hanamgri.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.net.URI;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.junit.Test;
import org.mushikago.sdk.common.RequestException;

public class ListKnowledgesRequestTest extends HanamgriRequestTestBase {

  @Test
  public void testSetterAndGetterForLimit() {
    ListKnowledgesRequest request = new ListKnowledgesRequest();
    Integer limit = 10;
    request.setLimit(10);
    assertEquals(limit, request.getLimit());
  }

  @Test
  public void testSetterAndGetterForOffset() {
    ListKnowledgesRequest request = new ListKnowledgesRequest();
    Integer offset = 20;
    request.setOffset(20);
    assertEquals(offset, request.getOffset());
  }

  @Test
  public void testSetterAndGetterForStatus() {
    ListKnowledgesRequest request = new ListKnowledgesRequest();
    String status = "complete";
    request.setStatus("complete");
    assertEquals(status, request.getStatus());
  }

  private HttpRequestBase callToHttpMethod(int limit, int offset, String status)
      throws RequestException {
    ListKnowledgesRequest request = new ListKnowledgesRequest();
    request.setLimit(limit);
    request.setOffset(offset);
    request.setStatus(status);
    return request.toHttpMethod(auth, ci);
  }

  @Test
  public void testHttpMethodIsHttpGet() throws RequestException {
    HttpRequestBase request = callToHttpMethod(10, 20, "complete");
    assertTrue(request instanceof HttpGet);
  }

  @Test
  public void testHttpMethodPathAndQueryContainsParameters() throws RequestException {
    HttpRequestBase request = callToHttpMethod(10, 20, "complete");
    URI uri = request.getURI();
    assertEquals("/1/hanamgri/knowledges", uri.getRawPath());
    String rawQuery = uri.getRawQuery();
    assertTrue(rawQuery.contains("limit=10"));
    assertTrue(rawQuery.contains("offset=20"));
    assertTrue(rawQuery.contains("status=complete"));
  }
}
