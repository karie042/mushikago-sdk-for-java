package org.mushikago.sdk.services.hanamgri.model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.Test;

public class GetInformationResponseTest {

  private static final String PARAMETER_KEY_DOMAIN_NAME = "domain_name";
  private static final String PARAMETER_KEY_DESCRIPTION = "description";
  private static final String PARAMETER_KEY_SEEDS = "seeds";
  private static final String PARAMETER_KEY_CREATED_AT = "created_at";
  private static final String PARAMETER_KEY_UPDATED_AT = "updated_at";
  private static final String PARAMETER_KEY_SCHEMA = "schema";

  private static final String PARAMETER_VALUE_DOMAIN_NAME = "domain_name";
  private static final String PARAMETER_VALUE_DESCRIPTION = "description";
  
  List<String> seeds = new ArrayList<String>() {/**
     * 
     */
    private static final long serialVersionUID = 1L;

  {
    add("税抜");
  }};
  private List<String> PARAMETER_VALUE_SEEDS = seeds;
  private static final String PARAMETER_VALUE_CREATED_AT = "created_at";
  private static final String PARAMETER_VALUE_UPDATED_AT = "updated_at";
  private static final Schema PARAMETER_VALUE_SCHEMA = new Schema();
  
  public GetInformationResponseTest(){ 
    Field field1 = new Field("product",true,"string");
    Field field2 = new Field("price",false,"number");
    PARAMETER_VALUE_SCHEMA.addField(field1);
    PARAMETER_VALUE_SCHEMA.addField(field2);
  }
  
  private JSONObject getValidTestData() {
    JSONObject responseJson = new JSONObject();
    responseJson.put("meta", new JSONObject());
    responseJson.getJSONObject("meta").put("status", "200");
    responseJson.getJSONObject("meta").put("message", "OK");
    
    JSONArray seedsJson = new JSONArray();
    seedsJson.add("税抜");
    
    responseJson.put("response", new JSONObject());
    responseJson.getJSONObject("response").put(PARAMETER_KEY_DOMAIN_NAME, PARAMETER_VALUE_DOMAIN_NAME);
    responseJson.getJSONObject("response").put(PARAMETER_KEY_DESCRIPTION, PARAMETER_VALUE_DESCRIPTION);
    responseJson.getJSONObject("response").put(PARAMETER_KEY_SEEDS, seedsJson);
    responseJson.getJSONObject("response").put(PARAMETER_KEY_CREATED_AT, PARAMETER_VALUE_CREATED_AT);
    responseJson.getJSONObject("response").put(PARAMETER_KEY_UPDATED_AT, PARAMETER_VALUE_UPDATED_AT);
    responseJson.getJSONObject("response").put(PARAMETER_KEY_SCHEMA, PARAMETER_VALUE_SCHEMA);
    return responseJson;
  }


  @Test
  public void testValidTestData() {
    JSONObject testData = getValidTestData();
    GetInformationResponse response = new GetInformationResponse(testData);
    assertEquals(PARAMETER_VALUE_DOMAIN_NAME, response.getDomainName());
    assertEquals(PARAMETER_VALUE_DESCRIPTION, response.getDescription());
    assertEquals(PARAMETER_VALUE_SEEDS, response.getSeeds());
    assertEquals(PARAMETER_VALUE_CREATED_AT, response.getCreatedAt());
    assertEquals(PARAMETER_VALUE_UPDATED_AT, response.getUpdatedAt());
    assertEquals(PARAMETER_VALUE_SCHEMA, response.getSchema());
  }


  private JSONObject getInValidTestData() {
    JSONObject responseJson = new JSONObject();
    responseJson.put("meta", new JSONObject());
    responseJson.getJSONObject("meta").put("status", "400");
    responseJson.getJSONObject("meta").put("message", "BadRequest");
    responseJson.put("response", null);
    return responseJson;
  }

  @Test(expected = NullPointerException.class)
  public void testInvalidTestData() {
    JSONObject testData = getInValidTestData();
    GetInformationResponse response = new GetInformationResponse(testData);
    assertEquals(null, response.getDomainName());
  }

}
