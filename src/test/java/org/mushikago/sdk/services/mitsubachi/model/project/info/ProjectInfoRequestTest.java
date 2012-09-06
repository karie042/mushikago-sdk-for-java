package org.mushikago.sdk.services.mitsubachi.model.project.info;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.net.URI;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.junit.Test;
import org.mushikago.sdk.common.RequestException;
import org.mushikago.sdk.services.mitsubachi.AbstractMitsubachiTest;

public class ProjectInfoRequestTest extends AbstractMitsubachiTest {

  private static final String URI = "/1/mitsubachi/project/info";
  private static final String PROJECT_NAME01 = "project01";

  @Test
  public void testConstruct() {
    ProjectInfoRequest request = new ProjectInfoRequest();
    assertNull(request.getProjectName());
  }
  
  @Test
  public void testConstructWithRequiredParameters(){
    ProjectInfoRequest request = new ProjectInfoRequest(PROJECT_NAME01);
    assertEquals(PROJECT_NAME01, request.getProjectName());
  }

  @Test
  public void testSetterAndGetterForProjectName() {
    ProjectInfoRequest request = new ProjectInfoRequest();
    request.setProjectName(PROJECT_NAME01);
    assertEquals(PROJECT_NAME01, request.getProjectName());
  }
  
  @Test(expected = RequestException.class)
  public void testThrowExceptionIfProjectNameIsNull() throws RequestException {
    ProjectInfoRequest request = new ProjectInfoRequest(null);
    request.toHttpMethod(auth, ci);
  }
  
  private HttpRequestBase callToHttpMethod(String projectName) throws RequestException {
    ProjectInfoRequest request = new ProjectInfoRequest(projectName);
    return request.toHttpMethod(auth, ci);
  }

  @Test
  public void testHttpmethodIsHttpGet() throws RequestException {
    HttpRequestBase request = callToHttpMethod(PROJECT_NAME01);
    assertTrue(request instanceof HttpGet);
  }

  @Test
  public void testHttpmethodPathAndQueryContainsParameters() throws RequestException {
    HttpRequestBase request = callToHttpMethod(PROJECT_NAME01);
    URI uri = request.getURI();
    assertEquals(URI, uri.getRawPath());
  }
}
