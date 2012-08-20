package org.mushikago.sdk.services.hanamgri.sample;

import org.mushikago.sdk.services.hanamgri.HanamgriClient;
import org.mushikago.sdk.services.hanamgri.model.RequestAnalysisRequest;
import org.mushikago.sdk.services.hanamgri.model.RequestAnalysisResponse;

public class RequestAnalysisSample extends HanamgriSample {
  public static void main(String[] args) throws Exception {
    HanamgriClient client = newHanamgriClient();

    String domainName = "ECProducts"; // ドメイン名
    String url = "http://shop.example.com/"; // 解析対象URL
    RequestAnalysisRequest request = new RequestAnalysisRequest(domainName, url);
    request.setTag("EcHanamgri"); //解析結果を検索する際の目印
    request.setAutoFeedback(false); //解析のみで、学習はさせない。
    
    RequestAnalysisResponse response = client.requestAnalysis(request);
    System.out.println(response.getRequestId()); // リクエストIDが返ってきます
  }
}
