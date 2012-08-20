package org.mushikago.sdk.services.hanamgri.model;

import net.sf.json.JSONObject;

public class RequestAnalysisResponse extends HanamgriResponse {
  /**
   * パラメータのキー
   */
  private static final String PARAMETER_KEY_REQUEST_ID = "request_id";

  /**
   * リクエストID
   */
  private String requestId;

  /**
   * リクエストIDを取得します
   * 
   * @return
   */
  public String getRequestId() {
    return requestId;
  }

  /**
   * リクエストIDを設定します
   * 
   * @return
   */
  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }

  /**
   * @param json
   */
  public RequestAnalysisResponse(JSONObject json) {
    super(json);
    requestId = getString(PARAMETER_KEY_REQUEST_ID);
  }
}
