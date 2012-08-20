package org.mushikago.sdk.services.hanamgri.model;

import net.sf.json.JSONObject;

public class GetQueueSizeResponse extends HanamgriResponse {
  private static final String PARAMETER_KEY_QUEUE_SIZE = "queue_size";
  
  private int queueSize;

  public GetQueueSizeResponse(JSONObject json) {
    super(json);
    queueSize = getInteger(PARAMETER_KEY_QUEUE_SIZE, 0);
  }

  public int getQueueSize() {
    return queueSize;
  }

  public void setQueueSize(int queueSize) {
    this.queueSize = queueSize;
  }

}
