package org.mushikago.sdk.services.hanamgri.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpRequestBase;
import org.junit.Test;
import org.mushikago.sdk.common.RequestException;

public class DeleteKnowledgeRequestTest extends HanamgriRequestTestBase {

  @Test
  public void testConstruct() {
    DeleteKnowledgeRequest request = new DeleteKnowledgeRequest();
    assertNull(request.getKnowledgeName());
  }

  @Test
  public void testConstructWithRequiredParameters() {
    DeleteKnowledgeRequest request = new DeleteKnowledgeRequest("myec/price-yyyymmddhhmmss");
    assertEquals("myec/price-yyyymmddhhmmss", request.getKnowledgeName());
  }

  @Test
  public void testSetterAndGetterForKnowledgeName() {
    DeleteKnowledgeRequest request = new DeleteKnowledgeRequest();
    request.setKnowledgeName("myec/price-yyyymmddhhmmss");
    assertEquals("myec/price-yyyymmddhhmmss", request.getKnowledgeName());
  }

  @Test(expected = RequestException.class)
  public void testThrowExceptionIfKnowledgeNameIsNull() throws RequestException {
    DeleteKnowledgeRequest request = new DeleteKnowledgeRequest(null);
    request.toHttpMethod(auth, ci);
  }

  private HttpRequestBase callToHttpMethod(String knowledgeName) throws RequestException {
    DeleteKnowledgeRequest request = new DeleteKnowledgeRequest(knowledgeName);
    return request.toHttpMethod(auth, ci);
  }

  @Test
  public void testHttpMethodIsHttpDelete() throws RequestException {
    HttpRequestBase request = callToHttpMethod("myec/price-yyyymmddhhmmss");
    assertTrue(request instanceof HttpDelete);
  }
}
