package org.mushikago.sdk.services.hanamgri.sample;

import org.mushikago.sdk.services.hanamgri.HanamgriClient;

public class DeleteDictionarySample extends HanamgriSample {
  public static void main(String[] args) throws Exception {
    HanamgriClient hanamgri = newHanamgriClient();
    String dictionaryName = "mushikago/ec";
    hanamgri.deleteDictionary(dictionaryName);
  }
}
