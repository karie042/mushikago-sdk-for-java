package org.mushikago.sdk.services.mitsubachi.model.http;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.junit.Test;
import org.mushikago.sdk.services.mitsubachi.AbstractMitsubachiTest;
import org.mushikago.sdk.services.mitsubachi.model.http.push.HttpPushRequest;

public class HttpPushRequestTest extends AbstractMitsubachiTest {
  /**
   * 
   */
  private static final String URL01 = "http://www.mushikago.org/";

  /**
   * 
   */
  private static final String PROJECT_NAME01 = "project01";

  /**
   * 
   */
  private static final String SCRIPT_NAME01 = "script01";

  /**
   * 
   */
  private static final String FILE_NAME01 = "file01";

  /**
   * 
   */
  private static final String File_INPUT_KEY01 = "filekey01";

  @Test
  public void testWithCookie() throws Exception {
    HttpPushRequest request = new HttpPushRequest(URL01, PROJECT_NAME01, SCRIPT_NAME01, FILE_NAME01, File_INPUT_KEY01);
    request.addCookie("cookie1", "value1", "www.mushikago.org", "/", false);
    HttpRequestBase requestBase = request.toHttpMethod(auth, ci);
    assertThat(requestBase, is(HttpPost.class));

    HttpPost postMethod = (HttpPost) requestBase;
    HttpEntity entity = postMethod.getEntity();
    assertThat(entity, is(UrlEncodedFormEntity.class));

    String requestBody = streamToString(entity.getContent());
    assertThat(requestBody, containsString("url=http%3A%2F%2Fwww.mushikago.org%2F"));
    assertThat(requestBody, containsString("project_name=project01"));
    assertThat(requestBody, containsString("script_name=script01"));
    assertThat(requestBody, containsString("file_name=file01"));
    assertThat(requestBody, containsString("file_input_key=filekey01"));
    assertThat(requestBody, containsString("cookiejar=%5B%7B"));
    assertThat(requestBody, containsString("%22domain%22%3A%22www.mushikago.org%22"));
    assertThat(requestBody, containsString("%22name%22%3A%22cookie1%22"));
    assertThat(requestBody, containsString("%22path%22%3A%22%2F%22"));
    assertThat(requestBody, containsString("%22secure%22%3Afalse"));
    assertThat(requestBody, containsString("%22value%22%3A%22value1%22"));
    assertThat(requestBody, containsString("%7D%5D"));
  }

  public void testWithParentRequestIdEtc() throws Exception {
    HttpPushRequest request = new HttpPushRequest(URL01, PROJECT_NAME01, SCRIPT_NAME01, FILE_NAME01, File_INPUT_KEY01);
    request.setParentRequestId("parent_request_id");
    request.setGroupId("group_id");
    request.setUniqueKey("unique_key");
    request.setUniqueKeyExpires(10L);

    HttpRequestBase requestBase = request.toHttpMethod(auth, ci);
    assertThat(requestBase, is(HttpPost.class));
    HttpPost postMethod = (HttpPost) requestBase;
    HttpEntity entity = postMethod.getEntity();
    assertThat(entity, is(UrlEncodedFormEntity.class));

    String requestBody = streamToString(entity.getContent());
    assertThat(requestBody, containsString("parent_request_id=script01"));
    assertThat(requestBody, containsString("group_id=script01"));
    assertThat(requestBody, containsString("unique_key=script01"));
    assertThat(requestBody, containsString("unique_key_expires=script01"));
  }
}
