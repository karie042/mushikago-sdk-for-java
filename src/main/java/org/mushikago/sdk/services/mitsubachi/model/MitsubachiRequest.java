package org.mushikago.sdk.services.mitsubachi.model;

import org.apache.http.message.BasicNameValuePair;
import org.mushikago.sdk.common.RequestException;
import org.mushikago.sdk.common.model.MushikagoRequest;

/**
 * mitsubachiAPIのリクエストの基底クラス。<br>
 * @author miningbrownie
 */
public abstract class MitsubachiRequest extends MushikagoRequest {
  protected BasicNameValuePair createNameValuePair(String name, String value, boolean reqired) throws RequestException {
    if (reqired && value == null) {
      throw new RequestException(name + "を指定してください");
    }
    return new BasicNameValuePair(name, value);
  }

  protected BasicNameValuePair createNameValuePair(String name, String value) throws RequestException {
    return createNameValuePair(name, value, false);
  }
}
