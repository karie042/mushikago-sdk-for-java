package org.mushikago.sdk.services.tombo.model.capture;

import net.sf.json.JSONObject;

import org.mushikago.sdk.services.tombo.model.TomboResponse;

public class CaptureResponse extends TomboResponse {

  public CaptureResponse(JSONObject json) {
    super(json);

    JSONObject response = json.getJSONObject("response");
    if ("null".equals(response.toString())) {
      return;
    }

    this.imageUrl = response.getString("image_url");
    if (response.containsKey("thumbnail_url")) {
      this.thumbnailUrl = response.getString("thumbnail_url");
    }
    else {
      this.thumbnailUrl = null;
    }
    this.id = response.getString("id");
  }

  protected String imageUrl;
  protected String thumbnailUrl;
  protected String id;

  public String getImageUrl() {
    return this.imageUrl;
  }

  public String getThumbnailUrl() {
    return this.thumbnailUrl;
  }

  public String getId() {
    return this.id;
  }
}
