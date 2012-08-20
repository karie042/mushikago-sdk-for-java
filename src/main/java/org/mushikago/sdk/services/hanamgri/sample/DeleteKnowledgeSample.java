package org.mushikago.sdk.services.hanamgri.sample;

import org.mushikago.sdk.services.hanamgri.HanamgriClient;

public class DeleteKnowledgeSample extends HanamgriSample {
  public static void main(String[] args) throws Exception {
    HanamgriClient hanamgri = newHanamgriClient();
    String knowledgeName = "ec/price-uuid";
    hanamgri.deleteKnowledge(knowledgeName);
  }
}
