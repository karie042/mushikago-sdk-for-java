package org.mushikago.sdk.services.hanamgri.model;

import static org.junit.Assert.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.Test;

public class ListDictionariesResponseTest {

  private JSONObject getValidTestData() {
    JSONObject dictionary = new JSONObject();
    dictionary.put("dictionary_name", "dicname");
    dictionary.put("description", "desc");
    dictionary.put("created_at", "aa");

    JSONArray dictionaries = new JSONArray();
    dictionaries.add(dictionary);

    JSONObject responseJson = new JSONObject();
    responseJson.put("meta", new JSONObject());
    responseJson.getJSONObject("meta").put("status", "200");
    responseJson.getJSONObject("meta").put("message", "OK");
    responseJson.put("response", new JSONObject());
    responseJson.getJSONObject("response").put("dictionaries", dictionaries);
    responseJson.getJSONObject("response").put("total", "10");

    return responseJson;
  }

  @Test
  public void testValidTestData() {
    JSONObject testData = getValidTestData();
    ListDictionariesResponse response = new ListDictionariesResponse(testData);
    Dictionary dictionary = response.getDictionaries().get(0);
    assertEquals(1, response.getDictionaries().size());
    assertEquals(10, response.getTotal());
    assertEquals("dicname", dictionary.getDictionaryName());
    assertEquals("desc", dictionary.getDescription());
    assertEquals("aa", dictionary.getCreatedAt());
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
    new ListDictionariesResponse(testData);
  }

}
