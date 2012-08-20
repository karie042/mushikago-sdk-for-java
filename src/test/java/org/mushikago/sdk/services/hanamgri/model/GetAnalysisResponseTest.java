package org.mushikago.sdk.services.hanamgri.model;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.Test;

public class GetAnalysisResponseTest {
  private JSONObject getValidTestData() {
    JSONArray blocks = new JSONArray();
    blocks.add("<div>hoge</div>");
    blocks.add("<div>fuga</div>");

    JSONObject analysis_data = new JSONObject();
    analysis_data.put("blocks", blocks);

    JSONArray results = new JSONArray();
    JSONObject fieldSet1 = new JSONObject();
    fieldSet1.put("key1", "value1");
    fieldSet1.put("key2", "value2");
    results.add(fieldSet1);
    analysis_data.put("results", results);
    JSONObject responseJson = new JSONObject();
    responseJson.put("meta", new JSONObject());
    responseJson.getJSONObject("meta").put("status", "200");
    responseJson.getJSONObject("meta").put("message", "OK");
    responseJson.put("response", new JSONObject());
    responseJson.getJSONObject("response").put("analysis_data", analysis_data);
    responseJson.getJSONObject("response").put("url", "http://www.miningbrownie.co.jp/");
    responseJson.getJSONObject("response").put("created_at", "Tue, 07 Aug 2012 19:46:22 +0900");
    return responseJson;
  }

  @Test
  public void testValidTestData() {
    JSONObject testData = getValidTestData();
    GetAnalysisResponse response = new GetAnalysisResponse(testData);
    assertEquals(2, response.getAnalysisData().getBlocks().size());
    assertEquals(1, response.getAnalysisData().getResults().size());
    assertEquals("http://www.miningbrownie.co.jp/", response.getUrl());
    assertEquals("Tue, 07 Aug 2012 19:46:22 +0900", response.getCreatedAt());
    for (FieldSet fieldSet : response.getAnalysisData().getResults()) {
      assertThat(fieldSet.get("key1"), is("value1"));
      assertThat(fieldSet.get("key2"), is("value2"));
    }
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
    System.out.println(new GetAnalysisResponse(testData));
    new GetAnalysisResponse(testData);
  }
}
