package org.mushikago.sdk.services.mitsubachi.model.http;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import net.sf.json.JSONObject;

import org.junit.Test;
import org.mushikago.sdk.services.mitsubachi.model.http.push.HttpPushResponse;

public class HttpPushResponseTest {

  @Test
  public void test() {
    JSONObject responseJson = new JSONObject();
    responseJson.put("meta", new JSONObject());
    responseJson.getJSONObject("meta").put("status", "200");
    responseJson.getJSONObject("meta").put("message", "OK");
    responseJson.put("response", new JSONObject());
    responseJson.getJSONObject("response").put("request_id", "request_id");
    HttpPushResponse response = new HttpPushResponse(responseJson);
    assertThat(response.getRequestId(), is("request_id"));
  }

}
