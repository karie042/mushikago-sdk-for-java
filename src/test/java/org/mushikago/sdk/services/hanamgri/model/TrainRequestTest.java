package org.mushikago.sdk.services.hanamgri.model;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.net.URI;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.junit.Test;
import org.mushikago.sdk.common.RequestException;

public class TrainRequestTest extends HanamgriRequestTestBase {

  private static final String DOMAIN = "ECProducts";
  private static final String URL = "http://www.mushikago.org/";
  private static final String HTML = "<div>hello mushikago</div>";

  @Test
  public void testConstruct() {
    TrainRequest request = new TrainRequest();
    assertNull(request.getDomainName());
    assertNull(request.getUrlOrHtml());
    assertNull(request.getTrainingData());
  }

  @Test
  public void testConstructWithDomainNameAndUrl() {
    TrainRequest request = new TrainRequest(DOMAIN, URL, new TrainingData());
    assertEquals(DOMAIN, request.getDomainName());
    assertEquals(URL, request.getUrlOrHtml());
    assertNotNull(request.getTrainingData());
  }

  @Test
  public void testSetterAndGetterForDomainName() {
    TrainRequest request = new TrainRequest();
    request.setDomainName(DOMAIN);
    assertEquals(DOMAIN, request.getDomainName());
  }

  @Test
  public void testSetterAndGetterForUrl() {
    TrainRequest request = new TrainRequest();
    request.setUrlOrHtml(URL);
    assertEquals(URL, request.getUrlOrHtml());
  }

  @Test
  public void testSetterAndGetterForHtml() {
    TrainRequest request = new TrainRequest();
    request.setUrlOrHtml(HTML);
    assertEquals(HTML, request.getUrlOrHtml());
  }

  @Test
  public void testSetterAndGetterForTrainingData() {
    TrainRequest request = new TrainRequest();
    TrainingData trainingData = new TrainingData();
    request.setTrainingData(trainingData);
    assertEquals(trainingData, request.getTrainingData());
  }

  @Test(expected = RequestException.class)
  public void testThrowExceptionIfDomainNameIsNull() throws RequestException {
    TrainRequest request = new TrainRequest(null, URL, new TrainingData());
    request.toHttpMethod(auth, ci);
  }

  @Test(expected = RequestException.class)
  public void testThrowExceptionIfUrlIsNull() throws RequestException {
    TrainRequest request = new TrainRequest(DOMAIN, null, new TrainingData());
    request.toHttpMethod(auth, ci);
  }

  @Test(expected = RequestException.class)
  public void testThrowExceptionIfTrainingDataIsNull() throws RequestException {
    TrainRequest request = new TrainRequest(DOMAIN, URL, null);
    request.toHttpMethod(auth, ci);
  }

  private HttpRequestBase callToHttpMethod(String domainName, String url) throws RequestException {
    TrainingData trainingData = new TrainingData();
    trainingData.put("item_name", "おいしく炊ける炊飯器");
    trainingData.put("price", "15,480");
    TrainRequest request = new TrainRequest(domainName, url, trainingData);
    return request.toHttpMethod(auth, ci);
  }

  @Test
  public void testHttpMethodIsHttpPost() throws RequestException {
    HttpRequestBase request = callToHttpMethod(DOMAIN, URL);
    assertTrue(request instanceof HttpPost);
  }

  @Test
  public void testHttpMethodPathAndQueryContainsParameters() throws RequestException {
    HttpRequestBase request = callToHttpMethod(DOMAIN, URL);
    URI uri = request.getURI();
    String rawQuery = uri.getRawQuery();
    System.out.println(rawQuery);
    assertEquals("/1/hanamgri/domains/" + DOMAIN + "/train", uri.getRawPath());
  }

  @Test
  public void testHttpMethodPathAndQueryContainsParametersWithHtml() throws RequestException {
    HttpRequestBase request = callToHttpMethod(DOMAIN, HTML);
    URI uri = request.getURI();
    String rawQuery = uri.getRawQuery();
    System.out.println(rawQuery);
    assertEquals("/1/hanamgri/domains/" + DOMAIN + "/train", uri.getRawPath());
  }

  @Test
  public void testTrainingDataToString() {
    TrainingData trainingData = new TrainingData();
    trainingData.put("item_name", "おいしく炊ける炊飯器");
    trainingData.put("price", "15,480");
    assertThat(trainingData.toString(), is("{\"price\":\"15,480\",\"item_name\":\"おいしく炊ける炊飯器\"}"));
  }
}
