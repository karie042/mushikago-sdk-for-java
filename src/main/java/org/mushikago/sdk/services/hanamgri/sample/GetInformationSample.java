package org.mushikago.sdk.services.hanamgri.sample;

import org.mushikago.sdk.services.hanamgri.HanamgriClient;
import org.mushikago.sdk.services.hanamgri.model.GetInformationResponse;

public class GetInformationSample extends HanamgriSample {
  public static void main(String[] args) throws Exception {
    HanamgriClient client = newHanamgriClient();

    String domainName = "ECProducts"; // ドメイン名

    GetInformationResponse response = client.getInformation(domainName);
    System.out.println(response.getDomainName()); // ドメイン名が返ってきます
    System.out.println(response.getDescription()); // ドメインの説明文が返ってきます
    System.out.println(response.getSeeds().toString()); // 解析時に元にするキーワードとして最初に登録する単語が返ってきます
    System.out.println(response.getCreatedAt()); // ドメイン作成日が返ってきます
    System.out.println(response.getUpdatedAt()); // ドメイン更新日が返ってきます
    System.out.println(response.getSchema()); // 抜き出す情報の属性の定義が返ってきます
  }
}
