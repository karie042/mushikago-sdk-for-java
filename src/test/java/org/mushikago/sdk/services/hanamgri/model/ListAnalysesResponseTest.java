package org.mushikago.sdk.services.hanamgri.model;

import static org.junit.Assert.assertEquals;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.Test;

public class ListAnalysesResponseTest {
  private JSONObject getValidTestData(){
    JSONObject analysis = new JSONObject();
    analysis.put("request_id", "test_id");
    analysis.put("save_url", "url");
    analysis.put("updated_at", "date");
    analysis.put("status", "complete");
    analysis.put("tag", "tagtag");
    
    JSONArray analyses = new JSONArray();
    analyses.add(analysis);
    
    JSONObject responseJson = new JSONObject();
    responseJson.put("meta", new JSONObject());
    responseJson.getJSONObject("meta").put("status", "200");
    responseJson.getJSONObject("meta").put("message", "OK");
    responseJson.put("response", new JSONObject());
    responseJson.getJSONObject("response").put("analyses", analyses);
    responseJson.getJSONObject("response").put("total", "10");
    
    return responseJson;
  }
  
  @Test
  public void testValidTestData(){
    JSONObject testData = getValidTestData();
    ListAnalysesResponse response = new ListAnalysesResponse(testData);
    Analysis analysis = response.getAnalyses().get(0);
    assertEquals(1, response.getAnalyses().size());
    assertEquals(10, response.getTotal());
    assertEquals("test_id", analysis.getRequestId());
    assertEquals("url", analysis.getSaveUrl());
    assertEquals("date", analysis.getUpdatedAt());
    assertEquals("complete", analysis.getStatus());
    assertEquals("tagtag", analysis.getTag());
  }

  private JSONObject getInvalidTestData() {
    JSONObject responseJson = new JSONObject();
    responseJson.put("meta", new JSONObject());
    responseJson.getJSONObject("meta").put("status", "400");
    responseJson.getJSONObject("meta").put("message", "BadRequest");
    responseJson.put("response", null);
    return responseJson;
  }
  
  @Test(expected = NullPointerException.class)
  public void testInvalidTestData(){
    JSONObject testData = getInvalidTestData();
    new ListAnalysesResponse(testData);
  }
}
