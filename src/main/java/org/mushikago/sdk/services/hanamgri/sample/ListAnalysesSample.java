package org.mushikago.sdk.services.hanamgri.sample;

import org.mushikago.sdk.services.hanamgri.HanamgriClient;
import org.mushikago.sdk.services.hanamgri.model.Analysis;
import org.mushikago.sdk.services.hanamgri.model.ListAnalysesResponse;

public class ListAnalysesSample extends HanamgriSample {
  public static void main(String[] args) throws Exception {
    HanamgriClient client = newHanamgriClient();
    
    String domainName = "ECProducts"; // ドメイン名
    
    ListAnalysesResponse response = client.listAnalyses(domainName);
    for(Analysis anl : response.getAnalyses()){
        System.out.println(anl.getRequestId()); // 解析結果のリクエストID
        System.out.println(anl.getSaveUrl()); // 解析結果（bolcks）の保存先url
        System.out.println(anl.getStatus()); // 現在の解析の状態
        System.out.println(anl.getUpdatedAt()); // 解析情報の更新日
        System.out.println(anl.getTag()); // 解析結果に設定されているtag
    }
    System.out.println(response.getTotal()); // 解析情報の合計数が返ってきます
  }
}
