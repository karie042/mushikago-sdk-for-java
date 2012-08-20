package org.mushikago.sdk.services.hanamgri.model;

import static org.junit.Assert.assertEquals;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.Test;

public class SearchSchemaRsponseTest {
  private JSONObject getValidTestData() {
    JSONObject field = new JSONObject();
    field.put("name", "sample");
    field.put("price", "1000");
    field.put("point", "10");

    JSONArray results = new JSONArray();
    results.add(field);

    JSONObject responseJson = new JSONObject();
    responseJson.put("meta", new JSONObject());
    responseJson.getJSONObject("meta").put("status", "200");
    responseJson.getJSONObject("meta").put("message", "OK");
    responseJson.put("response", new JSONObject());
    responseJson.getJSONObject("response").put("results", results);
    responseJson.getJSONObject("response").put("total", "1");

    return responseJson;
  }

  @Test
  public void testValidTestData(){
    JSONObject testData = getValidTestData();
    SearchSchemaResponse response = new SearchSchemaResponse(testData);
    assertEquals(1, response.getResults().size());
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
    new ListDomainsResponse(testData);
  }
  
}
