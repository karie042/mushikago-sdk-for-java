package org.mushikago.sdk.services.hanamgri.sample;

import org.mushikago.sdk.services.hanamgri.HanamgriClient;
import org.mushikago.sdk.services.hanamgri.model.Domain;
import org.mushikago.sdk.services.hanamgri.model.ListDomainsResponse;

public class ListDomainsSample extends HanamgriSample {
  public static void main(String[] args) throws Exception {
    HanamgriClient client = newHanamgriClient();
    
    ListDomainsResponse response = client.listDomains();
    for(Domain dm : response.getDomains()){
        System.out.println(dm.getDomainName()); //ドメイン名
        System.out.println(dm.getDescription()); //ドメインの説明
        System.out.println(dm.getUpdatedAt()); //ドメインの更新日
    }
    System.out.println(response.getTotal()); //ドメインの合計数が返ってきます
  }
}
