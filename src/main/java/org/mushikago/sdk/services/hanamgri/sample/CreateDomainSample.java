package org.mushikago.sdk.services.hanamgri.sample;

import org.mushikago.sdk.services.hanamgri.HanamgriClient;
import org.mushikago.sdk.services.hanamgri.model.CreateDomainRequest;
import org.mushikago.sdk.services.hanamgri.model.Field;
import org.mushikago.sdk.services.hanamgri.model.Schema;

public class CreateDomainSample extends HanamgriSample {
  public static void main(String[] args) throws Exception {
    HanamgriClient hanamgri = newHanamgriClient();
    String domainName = "ECProducts";
    String seeds = "税込";
    Schema schema = new Schema();
    schema.addField(new Field("name", true, Field.Type.String));
    schema.addField(new Field("price", true, Field.Type.Number, "mushikago/ec/price"));
    CreateDomainRequest request = new CreateDomainRequest(domainName, seeds, schema);
    request.setDictionaryName("mushikago/ec");
    hanamgri.createDomain(request);
  }
}
