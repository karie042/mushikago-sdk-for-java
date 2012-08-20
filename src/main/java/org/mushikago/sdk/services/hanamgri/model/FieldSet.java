package org.mushikago.sdk.services.hanamgri.model;

import java.util.HashMap;
import java.util.Map;

/**
 * スキーマの一つのフィールドをHashMapで表すクラスです
 * @author miningbrownie
 *
 */
public class FieldSet {
  private final Map<String, String> fieldSet;
  
  public FieldSet(Map<String,String> field){
    this.fieldSet = field;
  }
  
  public FieldSet() {
    this(new HashMap<String, String>());
  }

  /**
   * フィールドセットを返します。
   */
  public Map<String,String> getFieldSet() {
    return fieldSet;
  }

  /**
   * フィールドのキーで検索した値を返します。
   * @param 検索するキー
   * @return キーに関連付けられた値
   */
  public String get(String key){
    return fieldSet.get(key);
    
  }
  
  public void put(String key, String value) {
    fieldSet.put(key, value);
  }
}
