package org.mushikago.sdk.services.hanamgri.sample;

import org.mushikago.sdk.services.hanamgri.HanamgriClient;
import org.mushikago.sdk.services.hanamgri.model.AnalysisData;
import org.mushikago.sdk.services.hanamgri.model.GetAnalysisResponse;

public class GetAnalysisSample extends HanamgriSample {
  public static void main(String[] args) throws Exception {
    HanamgriClient client = newHanamgriClient();

    String domainName = "ECProducts"; // ドメイン名
    String requestId = "xxxxxx-xxxxxx-xxxxxx"; // 解析結果取得用のID

    GetAnalysisResponse response = client.getAnalysis(domainName, requestId);
    AnalysisData analysisData = response.getAnalysisData();
    System.out.println(analysisData.getBlocks()); // 解析により抽出したHTMLのブロックが返ってきます
    for(String block : analysisData.getBlocks()){
        System.out.println(block); //解析により抽出したHTML
    }
  }
}
