package org.mushikago.sdk.services.hanamgri.model;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ListDomainsResponseTest {
  private JSONObject getValidTestData(){
    JSONObject domain = new JSONObject();
    domain.put("domain_name", "domain_name");
    domain.put("description", "description");
    domain.put("updated_at", "yyyy-MM-dd HH:mm:ss");
    
    JSONArray domains = new JSONArray();
    domains.add(domain);
    
    JSONObject responseJson = new JSONObject();
    responseJson.put("meta", new JSONObject());
    responseJson.getJSONObject("meta").put("status", "200");
    responseJson.getJSONObject("meta").put("message", "OK");
    responseJson.put("response", new JSONObject());
    responseJson.getJSONObject("response").put("domains", domains);
    responseJson.getJSONObject("response").put("total", "10");
    
    return responseJson;
  }
  
  @Test
  public void testValidTestData(){
    JSONObject testData = getValidTestData();
    ListDomainsResponse response = new ListDomainsResponse(testData);
    assertEquals(1, response.getDomains().size());
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
