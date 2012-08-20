package org.mushikago.sdk.services.hanamgri.sample;

import org.mushikago.sdk.services.hanamgri.HanamgriClient;
import org.mushikago.sdk.services.hanamgri.model.SearchSchemaRequest;
import org.mushikago.sdk.services.hanamgri.model.SearchSchemaResponse;

public class SearchSchemaSample extends HanamgriSample {
  public static void main(String[] args) throws Exception {
    HanamgriClient client = newHanamgriClient();
    String domainName = "ECProducts"; // ドメイン名
    String queryKey = "name"; // 検索対象のフィールド名
    String queryValue = "something"; // 検索対象のフィールド値（部分一致）
    
    SearchSchemaRequest request = new SearchSchemaRequest();
    request.setDomainName(domainName);
    request.setQueryKey(queryKey);
    request.setQueryValue(queryValue);
    
    SearchSchemaResponse response = client.searchSchema(request);
    System.out.println(response.getResults().toString()); // 検索結果が返ってきます
    System.out.println(response.getTotal()); // 検索結果の合計数が返ってきます
  }
}
