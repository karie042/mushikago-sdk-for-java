package org.mushikago.sdk.services.hanamgri.sample;

import org.mushikago.sdk.services.hanamgri.HanamgriClient;

public class DeleteDomainSample extends HanamgriSample {
  public static void main(String[] args) throws Exception {
    HanamgriClient hanamgri = newHanamgriClient();
    String domainName = "ECProducts";
    hanamgri.deleteDomain(domainName);
  }
}
