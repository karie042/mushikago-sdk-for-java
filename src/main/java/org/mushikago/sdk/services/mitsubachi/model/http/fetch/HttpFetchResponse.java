package org.mushikago.sdk.services.mitsubachi.model.http.fetch;

import net.sf.json.JSONObject;

import org.mushikago.sdk.services.mitsubachi.model.MitsubachiResponse;

/**
 * HttpFetchのレスポンス。<br>
 * 
 * @author miningbrownie
 */
public class HttpFetchResponse extends MitsubachiResponse {

  /**
   * レスポンスからrequest_idパラメータを取得するキー。<br>
   */
  public static final String PARAM_KEY_REQUEST_ID = "request_id";
  
  /**
   * リクエストID
   */
  private String requestId;

  /**
   * HttpFetchResponseを構築します。<br>
   * 
   * @param json
   *          APIサーバからのレスポンス。<br>
   */
  public HttpFetchResponse(JSONObject json) {
    super(json);
    JSONObject response = json.getJSONObject("response");
    if(response.containsKey(PARAM_KEY_REQUEST_ID)) {
      requestId = response.getString(PARAM_KEY_REQUEST_ID);
    }
  }
  
  /**
   * リクエストIDを取得します。
   * @return
   */
  public String getRequestId() {
    return requestId;
  }

  /**
   * リクエストIDを設定します。
   * @param requestId
   */
  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }
}
