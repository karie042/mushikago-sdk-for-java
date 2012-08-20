package org.mushikago.sdk.services.hanamgri.sample;

import org.mushikago.sdk.services.hanamgri.HanamgriClient;
import org.mushikago.sdk.services.hanamgri.model.GetQueueSizeResponse;

public class GetQueueSizeSample extends HanamgriSample {
  public static void main(String[] args) throws Exception {
    HanamgriClient client = newHanamgriClient();
    
    String domainName = "ECProducts"; // ドメイン名
    
    GetQueueSizeResponse response = client.getQueueSize(domainName);
    System.out.println(response.getQueueSize()); // タスクキューのサイズが返ってきます
  }
}
