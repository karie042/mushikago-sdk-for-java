package org.mushikago.sdk.services.mitsubachi.model.http;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import net.sf.json.JSONObject;

import org.junit.Test;
import org.mushikago.sdk.services.mitsubachi.model.http.fetch.HttpFetchResponse;

public class HttpFetchResponseTest {

  @Test
  public void test() {
    JSONObject responseJson = new JSONObject();
    responseJson.put("meta", new JSONObject());
    responseJson.getJSONObject("meta").put("status", "200");
    responseJson.getJSONObject("meta").put("message", "OK");
    responseJson.put("response", new JSONObject());
    responseJson.getJSONObject("response").put("request_id", "request_id");
    HttpFetchResponse response = new HttpFetchResponse(responseJson);
    assertThat(response.getRequestId(), is("request_id"));
  }

}
