package org.mushikago.sdk.services.tombo.model;

import static org.junit.Assert.assertEquals;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.Test;
import org.mushikago.sdk.services.tombo.model.capture.CaptureRequest.ImageFormat;
import org.mushikago.sdk.services.tombo.model.captures.CapturesResponse;
import org.mushikago.sdk.services.tombo.model.captures.CapturesResponseDetail;

public class CapturesResponseTest {
  private JSONObject getValidTestData() {
    JSONArray tags = new JSONArray();
    tags.add("tag1");
    tags.add("tag2");
  
    JSONObject capture = new JSONObject();
    capture.put("image_id", "image_id");
    capture.put("image_url", "image_url");
    capture.put("image_size", 100);
    capture.put("thumbnail_url", "thumbnail_url");
    capture.put("thumbnail_size", 100);
    capture.put("source_url", "source_url");
    capture.put("image_format", "jpg");
    capture.put("image_quality", 80);
    capture.put("state", "state");
    capture.put("updated_at", "updated_at");
    capture.put("tags", tags);
    
    JSONArray captures = new JSONArray();
    captures.add(capture);

    JSONObject responseJson = new JSONObject();
    responseJson.put("meta", new JSONObject());
    responseJson.getJSONObject("meta").put("status", "200");
    responseJson.getJSONObject("meta").put("message", "OK");
    responseJson.put("response", new JSONObject());
    responseJson.getJSONObject("response").put("images", captures);
    responseJson.getJSONObject("response").put("total", "10");

    return responseJson;
  }

  @Test
  public void testValidTestData() {
    JSONObject testData = getValidTestData();
    CapturesResponse response = new CapturesResponse(testData);
    CapturesResponseDetail capture = response.getDetails()[0];
    assertEquals(1, response.getDetails().length);
    assertEquals(10, response.getTotal());
    assertEquals("image_id", capture.getId());
    assertEquals("image_url", capture.getImageUrl());
    assertEquals(100, capture.getImageSize());
    assertEquals("thumbnail_url", capture.getThumbnailUrl());
    assertEquals(100, capture.getThumbnailSize());
    assertEquals("source_url", capture.getSourceUrl());
    assertEquals(ImageFormat.JPG, capture.getFormat());
    assertEquals(80, capture.getQuality());
    assertEquals("state", capture.getState());
    assertEquals("tag1", capture.getTags()[0]);
    assertEquals("tag2", capture.getTags()[1]);
  }

  private JSONObject getInvalidTestData() {
    JSONObject responseJson = new JSONObject();
    responseJson.put("meta", new JSONObject());
    responseJson.getJSONObject("meta").put("status", "400");
    responseJson.getJSONObject("meta").put("message", "BadRequest");
    responseJson.put("response", null);
    return responseJson;
  }

  @Test
  public void testInvalidTestData(){
    JSONObject testData = getInvalidTestData();
    assertEquals(0, new CapturesResponse(testData).getTotal());
  }
}
