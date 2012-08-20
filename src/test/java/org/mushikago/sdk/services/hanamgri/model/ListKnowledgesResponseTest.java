package org.mushikago.sdk.services.hanamgri.model;

import static org.junit.Assert.assertEquals;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.Test;

public class ListKnowledgesResponseTest {

  private JSONObject getValidTestData() {
    JSONObject knowledge = new JSONObject();
    knowledge.put("knowledge_name", "knowledgename");
    knowledge.put("description", "desc");
    knowledge.put("status", "complete");
    knowledge.put("created_at", "aa");

    JSONArray knowledges = new JSONArray();
    knowledges.add(knowledge);

    JSONObject responseJson = new JSONObject();
    responseJson.put("meta", new JSONObject());
    responseJson.getJSONObject("meta").put("status", "200");
    responseJson.getJSONObject("meta").put("message", "OK");
    responseJson.put("response", new JSONObject());
    responseJson.getJSONObject("response").put("knowledges", knowledges);
    responseJson.getJSONObject("response").put("total", "10");

    return responseJson;
  }

  @Test
  public void testValidTestData() {
    JSONObject testData = getValidTestData();
    ListKnowledgesResponse response = new ListKnowledgesResponse(testData);
    Knowledge knowledge = response.getKnowledges().get(0);
    assertEquals(1, response.getKnowledges().size());
    assertEquals(10, response.getTotal());
    assertEquals("knowledgename", knowledge.getKnowledgeName());
    assertEquals("desc", knowledge.getDescription());
    assertEquals("complete", knowledge.getStatus());
    assertEquals("aa", knowledge.getCreatedAt());
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
  public void testInvalidTestData() {
    JSONObject testData = getInvalidTestData();
    new ListKnowledgesResponse(testData);
  }

}
