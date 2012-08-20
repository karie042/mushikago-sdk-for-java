package org.mushikago.sdk.services.hanamgri.sample;

import org.mushikago.sdk.services.hanamgri.HanamgriClient;
import org.mushikago.sdk.services.hanamgri.model.SaveDictionaryRequest;

public class SaveDictionarySample extends HanamgriSample {

  public static void main(String[] args) throws Exception {
    String domainName = "ECProducts";
    String description = "辞書の説明";
    
    SaveDictionaryRequest request = new SaveDictionaryRequest();
    request.setDomainName(domainName);
    request.setDescription(description);
    
    HanamgriClient hanamgri = newHanamgriClient();
    hanamgri.saveDictionary(request);
  }
}
