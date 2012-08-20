package org.mushikago.sdk.services.hanamgri.sample;

import org.mushikago.sdk.common.auth.Credentials;
import org.mushikago.sdk.services.hanamgri.HanamgriClient;

public class HanamgriSample {
  protected static HanamgriClient newHanamgriClient() {
    String apiKey = System.getProperty("sample.apiKey");
    String secretKey = System.getProperty("sample.secretKey");
    String endpoint = System.getProperty("sample.endpoint");
    boolean useSsl = Boolean.valueOf(System.getProperty("sample.endpoint", "true"));
    return new HanamgriClient(new Credentials(apiKey, secretKey), endpoint, useSsl);
  }
}
