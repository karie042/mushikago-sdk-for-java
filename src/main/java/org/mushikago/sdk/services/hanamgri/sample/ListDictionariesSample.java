package org.mushikago.sdk.services.hanamgri.sample;

import org.mushikago.sdk.services.hanamgri.HanamgriClient;
import org.mushikago.sdk.services.hanamgri.model.Dictionary;
import org.mushikago.sdk.services.hanamgri.model.ListDictionariesResponse;

public class ListDictionariesSample extends HanamgriSample {
  public static void main(String[] args) throws Exception {
    HanamgriClient client = newHanamgriClient();

    ListDictionariesResponse response = client.listDictionaries();
    for (Dictionary d : response.getDictionaries()) {
      System.out.println(d.getDictionaryName()); // 辞書名
      System.out.println(d.getDescription()); // 辞書の説明
      System.out.println(d.getCreatedAt()); // 辞書の更新日
    }
    System.out.println(response.getTotal()); // 辞書の合計数が返ってきます
  }
}
