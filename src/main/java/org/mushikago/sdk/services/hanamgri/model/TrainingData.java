package org.mushikago.sdk.services.hanamgri.model;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

public class TrainingData {
  private final Map<String, String> data;

  public TrainingData() {
    data = new HashMap<String, String>();
  }

  public void put(String key, String value) {
    data.put(key, value);
  }

  public void remove(String key) {
    data.remove(key);
  }

  @Override
  public String toString() {
    return JSONObject.fromObject(data).toString();
  }
}
