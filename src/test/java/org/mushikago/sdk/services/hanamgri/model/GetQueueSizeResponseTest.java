package org.mushikago.sdk.services.hanamgri.model;

import static org.junit.Assert.assertEquals;
import net.sf.json.JSONObject;

import org.junit.Test;

public class GetQueueSizeResponseTest {
  private JSONObject getValidTestData(int queueSize) {
    JSONObject responseJson = new JSONObject();
    responseJson.put("meta", new JSONObject());
    responseJson.getJSONObject("meta").put("status", "200");
    responseJson.getJSONObject("meta").put("message", "OK");
    responseJson.put("response", new JSONObject());
    responseJson.getJSONObject("response").put("queue_size", String.valueOf(queueSize));
    return responseJson;
  }

  @Test
  public void testValidTestData() {
    JSONObject testData = getValidTestData(10);
    GetQueueSizeResponse response = new GetQueueSizeResponse(testData);
    assertEquals(10, response.getQueueSize());
  }

  private JSONObject getInValidTestData() {
    JSONObject responseJson = new JSONObject();
    responseJson.put("meta", new JSONObject());
    responseJson.getJSONObject("meta").put("status", "400");
    responseJson.getJSONObject("meta").put("message", "BadRequest");
    responseJson.put("response", null);
    return responseJson;
  }

  @Test
  public void testInvalidTestData() {
    JSONObject testData = getInValidTestData();
    GetQueueSizeResponse response = new GetQueueSizeResponse(testData);
    assertEquals(0, response.getQueueSize());
  }
}
