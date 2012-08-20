package org.mushikago.sdk.services.hanamgri.sample;

import org.mushikago.sdk.services.hanamgri.HanamgriClient;

public class UpdateDomainSample extends HanamgriSample {
  public static void main(String[] args) throws Exception {
    HanamgriClient client = newHanamgriClient();
    String domainName = "ECProducts"; // ドメイン名
    String description = "ECサイトの情報を取得する"; // ドメインの説明文
    client.updateDomain(domainName, description);
  }
}
