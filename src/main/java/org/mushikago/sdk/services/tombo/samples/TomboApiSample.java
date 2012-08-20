package org.mushikago.sdk.services.tombo.samples;

import org.mushikago.sdk.common.RequestException;
import org.mushikago.sdk.common.auth.Credentials;
import org.mushikago.sdk.common.broker.BrokerException;
import org.mushikago.sdk.common.exception.APICallException;
import org.mushikago.sdk.services.tombo.TomboClient;
import org.mushikago.sdk.services.tombo.model.capture.CaptureRequest;
import org.mushikago.sdk.services.tombo.model.capture.CaptureResponse;
import org.mushikago.sdk.services.tombo.model.captures.CapturesRequest;
import org.mushikago.sdk.services.tombo.model.captures.CapturesResponse;
import org.mushikago.sdk.services.tombo.model.captures.CapturesResponseDetail;
import org.mushikago.sdk.services.tombo.model.delete.DeleteRequest;
import org.mushikago.sdk.services.tombo.model.delete.DeleteResponse;
import org.mushikago.sdk.services.tombo.model.info.InfoRequest;
import org.mushikago.sdk.services.tombo.model.info.InfoResponse;

public class TomboApiSample {

  TomboClient tombo;

  public TomboApiSample(String apiKey, String secretKey) {

    Credentials credentials = new Credentials(apiKey, secretKey);
    this.tombo = new TomboClient(credentials);
  }

  public void capturesSample(String id) throws RequestException, BrokerException, APICallException {

    CapturesRequest req = new CapturesRequest().withId(id);
    CapturesResponse res = this.tombo.captures(req);

    System.out.println("http status:" + res.getStatus());
    System.out.println("message:" + res.getMessage());
    System.out.println("total:" + res.getTotal());
    for (CapturesResponseDetail detail : res.getDetails()) {

      System.out.println("--------------------------------");
      System.out.println(" id:" + detail.getId());
      System.out.println(" source url:" + detail.getSourceUrl());
      System.out.println(" image url:" + detail.getImageUrl());
      System.out.println(" image size:" + detail.getImageSize());
      System.out.println(" thumbnail url:" + detail.getThumbnailUrl());
      System.out.println(" thumbnail size:" + detail.getThumbnailSize());
      System.out.println(" format:" + detail.getFormat());
      System.out.println(" quality:" + detail.getQuality());
      System.out.println(" state:" + detail.getState());
      System.out.println(" updated at:" + detail.getUpdataedAt());

      for (String tag : detail.getTags()) {
        System.out.println("  tag:" + tag);
      }
    }
  }

  public String captureSample() throws RequestException, BrokerException, APICallException {

    CaptureRequest req = new CaptureRequest("http://yahoo.co.jp").withTag("test");
    CaptureResponse res = this.tombo.capture(req);

    System.out.println("http status:" + res.getStatus());
    System.out.println("message:" + res.getMessage());
    System.out.println("id:" + res.getId());
    System.out.println("image url:" + res.getImageUrl());

    return res.getId();
  }

  public void deleteSample(String id) throws RequestException, BrokerException, APICallException {

    DeleteRequest req = new DeleteRequest(id);
    DeleteResponse res = this.tombo.delete(req);

    System.out.println("http status:" + res.getStatus());
    System.out.println("message:" + res.getMessage());
    System.out.println("id:" + res.getId());
  }

  public void infoSample() throws RequestException, BrokerException, APICallException {

    InfoRequest req = new InfoRequest();
    InfoResponse res = this.tombo.info(req);

    System.out.println("http status:" + res.getStatus());
    System.out.println("message:" + res.getMessage());
    System.out.println("api count:" + res.getApiCount());
    System.out.println("disk usage:" + res.getDiskUsage());
    System.out.println("image num:" + res.getImageNum());
  }

  public static void main(String[] args) throws Exception {

    // 以下のコードでログ出力を停止できます
// System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
// System.setProperty("org.apache.commons.logging.simplelog.log.org.mushikago", "off");

    String apiKey = ""; // your api key
    String secretKey = ""; // your secret key
    TomboApiSample sample = new TomboApiSample(apiKey, secretKey);

    System.out.println("#################################################");
    System.out.println("# capture");
    System.out.println("#################################################");
    String id = sample.captureSample();

    System.out.println("#################################################");
    System.out.println("# captures");
    System.out.println("#################################################");
    sample.capturesSample(id);

    System.out.println("#################################################");
    System.out.println("# info");
    System.out.println("#################################################");
    sample.infoSample();

    System.out.println("#################################################");
    System.out.println("# delete");
    System.out.println("#################################################");
    sample.deleteSample(id);
  }
}
