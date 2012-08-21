package org.mushikago.sdk.services.tombo.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.Before;
import org.mushikago.sdk.common.auth.Credentials;
import org.mushikago.sdk.common.auth.MushikagoAuth;
import org.mushikago.sdk.common.util.ConnectInfo;

public class TomboRequestTestBase {
  protected MushikagoAuth auth;
  protected ConnectInfo ci;

  @Before
  public void setup() {
    auth = new MushikagoAuth(new Credentials("apikey", "secretkey"));
    ci = new ConnectInfo("api.mushikago.org");
  }

  /**
   * @param input
   * @return
   * @throws IOException
   */
  protected static String streamToString(InputStream input) throws IOException {
    StringBuilder buf = new StringBuilder();
    BufferedReader br = new BufferedReader(new InputStreamReader(input));
    try {
      String line = null;
      while ((line = br.readLine()) != null) {
        buf.append(line);
      }
    }
    finally {
      if (br != null) {
        br.close();
      }
    }
    return buf.toString();
  }
}
