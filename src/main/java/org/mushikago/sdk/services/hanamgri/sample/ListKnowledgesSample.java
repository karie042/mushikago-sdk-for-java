package org.mushikago.sdk.services.hanamgri.sample;

import org.mushikago.sdk.services.hanamgri.HanamgriClient;
import org.mushikago.sdk.services.hanamgri.model.Knowledge;
import org.mushikago.sdk.services.hanamgri.model.ListKnowledgesResponse;

public class ListKnowledgesSample extends HanamgriSample {
  public static void main(String[] args) throws Exception {
    HanamgriClient client = newHanamgriClient();

    ListKnowledgesResponse response = client.listKnowledges();
    for (Knowledge k : response.getKnowledges()) {
      System.out.println(k.getKnowledgeName()); // 学習データ名
      System.out.println(k.getDescription()); // 学習データの説明
      System.out.println(k.getStatus()); // 学習データ保存の進捗状況
      System.out.println(k.getCreatedAt()); // 学習データの更新日
    }
    System.out.println(response.getTotal()); // 学習データの合計数が返ってきます
  }
}
