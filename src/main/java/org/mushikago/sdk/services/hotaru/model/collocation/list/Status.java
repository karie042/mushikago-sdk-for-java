package org.mushikago.sdk.services.hotaru.model.collocation.list;

/**
 * 共起グラフのステータスを定義します。<br>
 * @author miningbrownie
 *
 */
public enum Status {
  
  ACCEPTED("accepted"),
  PROCESSING("processing"),
  COMPLETE("complete"),
  ERROR("error");
  
  private final String key;
  private Status(String key) { this.key = key; }
  @Override public String toString() { return this.key; }
}
