package org.mushikago.sdk.services.tombo.model;

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
}
