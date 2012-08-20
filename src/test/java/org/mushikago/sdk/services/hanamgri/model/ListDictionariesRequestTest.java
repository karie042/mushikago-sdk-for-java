package org.mushikago.sdk.services.hanamgri.model;

import static org.junit.Assert.*;

import java.net.URI;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.junit.Test;
import org.mushikago.sdk.common.RequestException;

public class ListDictionariesRequestTest extends HanamgriRequestTestBase {

  @Test
  public void testSetterAndGetterForLimit() {
    ListDictionariesRequest request = new ListDictionariesRequest();
    Integer limit = 10;
    request.setLimit(10);
    assertEquals(limit, request.getLimit());
  }

  @Test
  public void testSetterAndGetterForOffset() {
    ListDictionariesRequest request = new ListDictionariesRequest();
    Integer offset = 20;
    request.setOffset(20);
    assertEquals(offset, request.getOffset());
  }

  private HttpRequestBase callToHttpMethod(int limit, int offset)
      throws RequestException {
    ListDictionariesRequest request = new ListDictionariesRequest();
    request.setLimit(limit);
    request.setOffset(offset);
    return request.toHttpMethod(auth, ci);
  }

  @Test
  public void testHttpMethodIsHttpGet() throws RequestException {
    HttpRequestBase request = callToHttpMethod(10, 20);
    assertTrue(request instanceof HttpGet);
  }

  @Test
  public void testHttpMethodPathAndQueryContainsParameters() throws RequestException {
    HttpRequestBase request = callToHttpMethod(10, 20);
    URI uri = request.getURI();
    assertEquals("/1/hanamgri/dictionaries", uri.getRawPath());
    String rawQuery = uri.getRawQuery();
    assertTrue(rawQuery.contains("limit=10"));
    assertTrue(rawQuery.contains("offset=20"));
  }
}
