package org.mushikago.sdk.services.hanamgri.sample;

import org.mushikago.sdk.services.hanamgri.HanamgriClient;
import org.mushikago.sdk.services.hanamgri.model.SaveKnowledgeRequest;

public class SaveKnowledgeSample extends HanamgriSample {

  public static void main(String[] args) throws Exception {
    String domainName = "ECProducts";
    String fieldName = "name";
    String description = "学習データの説明";
    
    SaveKnowledgeRequest request = new SaveKnowledgeRequest();
    request.setDomainName(domainName);
    request.setFieldName(fieldName);
    request.setDescription(description);
    
    HanamgriClient hanamgri = newHanamgriClient();
    hanamgri.saveKnowledge(request);
  }
}
