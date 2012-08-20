package org.mushikago.sdk.services.hanamgri.sample;

import org.mushikago.sdk.services.hanamgri.HanamgriClient;
import org.mushikago.sdk.services.hanamgri.model.TrainingData;

public class TrainSample extends HanamgriSample {
  public static void main(String[] args) throws Exception {
    HanamgriClient hanamgri = newHanamgriClient();
    String domainName = "ECProducts";
    String url = "http://www.miningbrownie.co.jp/suihanki/001.html";
    TrainingData trainingData = new TrainingData();
    trainingData.put("item_code", "ZZZZZZZZ");
    trainingData.put("maker", "テストメーカー");
    trainingData.put("item_name", "おいしく炊ける炊飯器");
    trainingData.put("price", "15,480");
    hanamgri.train(domainName, url, trainingData);
  }
}
