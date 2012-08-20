package org.mushikago.sdk.services.hanamgri.model;

import static org.junit.Assert.*;
import net.sf.json.JSONObject;

import org.junit.Test;

public class RequestAnalysisResponseTest {
  private JSONObject getValidTestData(String requestId) {
    JSONObject responseJson = new JSONObject();
    responseJson.put("meta", new JSONObject());
    responseJson.getJSONObject("meta").put("status", "200");
    responseJson.getJSONObject("meta").put("message", "OK");
    responseJson.put("response", new JSONObject());
    responseJson.getJSONObject("response").put("request_id", requestId);
    return responseJson;
  }

  @Test
  public void testValidTestData() {
    JSONObject testData = getValidTestData("sampleRequestId");
    RequestAnalysisResponse response = new RequestAnalysisResponse(testData);
    assertEquals("sampleRequestId", response.getRequestId());
  }

  private JSONObject getInValidTestData(String requestId) {
    JSONObject responseJson = new JSONObject();
    responseJson.put("meta", new JSONObject());
    responseJson.getJSONObject("meta").put("status", "400");
    responseJson.getJSONObject("meta").put("message", "BadRequest");
    responseJson.put("response", null);
    return responseJson;
  }

  @Test
  public void testInvalidTestData() {
    JSONObject testData = getInValidTestData("sampleRequestId");
    RequestAnalysisResponse response = new RequestAnalysisResponse(testData);
    assertNull(response.getRequestId());
  }
}
