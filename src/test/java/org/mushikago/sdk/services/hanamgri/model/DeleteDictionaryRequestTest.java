package org.mushikago.sdk.services.hanamgri.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpRequestBase;
import org.junit.Test;
import org.mushikago.sdk.common.RequestException;

public class DeleteDictionaryRequestTest extends HanamgriRequestTestBase {

  @Test
  public void testConstruct() {
    DeleteDictionaryRequest request = new DeleteDictionaryRequest();
    assertNull(request.getDictionaryName());
  }

  @Test
  public void testConstructWithRequiredParameters() {
    DeleteDictionaryRequest request = new DeleteDictionaryRequest("dictionary");
    assertEquals("dictionary", request.getDictionaryName());
  }

  @Test
  public void testSetterAndGetterForDictionaryName() {
    DeleteDictionaryRequest request = new DeleteDictionaryRequest();
    request.setDictionaryName("dictionary");
    assertEquals("dictionary", request.getDictionaryName());
  }

  @Test(expected = RequestException.class)
  public void testThrowExceptionIfDictionaryNameIsNull() throws RequestException {
    DeleteDictionaryRequest request = new DeleteDictionaryRequest(null);
    request.toHttpMethod(auth, ci);
  }

  private HttpRequestBase callToHttpMethod(String dictionaryName) throws RequestException {
    DeleteDictionaryRequest request = new DeleteDictionaryRequest(dictionaryName);
    return request.toHttpMethod(auth, ci);
  }

  @Test
  public void testHttpMethodIsHttpDelete() throws RequestException {
    HttpRequestBase request = callToHttpMethod("dictionary");
    assertTrue(request instanceof HttpDelete);
  }
}
