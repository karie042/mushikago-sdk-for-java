package org.mushikago.sdk.services.hotaru.model.dictionary.list;

public enum DictionaryStatus {
  
  ACCEPTED("accepted"),
  PROCESSING("processing"),
  COMPLETE("complete"),
  ERROR("error");
  
  private final String key;
  private DictionaryStatus(String key) { this.key = key; }
  @Override public String toString() { return this.key; }
}
