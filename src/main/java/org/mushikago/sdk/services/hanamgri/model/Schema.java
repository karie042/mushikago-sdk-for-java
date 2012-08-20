package org.mushikago.sdk.services.hanamgri.model;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * ドメインのスキーマを表すクラスです
 * 
 * @author ope
 */
public class Schema {
  private final List<Field> fields;

  public Schema() {
    this.fields = new ArrayList<Field>();
  }

  public List<Field> getFields() {
    return fields;
  }

  public void addField(Field field) {
    fields.add(field);
  }

  public JSONObject toJSONObject() {
    JSONObject json = new JSONObject();
    JSONArray fieldArray = new JSONArray();
    for(Field field : fields) {
      fieldArray.add(field.toJSONObject());
    }
    json.put("fields", fieldArray);
    return json;
  }

  @Override
  public String toString() {
    return toJSONObject().toString();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fields == null) ? 0 : fields.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    Schema other = (Schema) obj;
    if (fields == null) {
      if (other.fields != null) return false;
    }
    else if (!fields.equals(other.fields)) return false;
    return true;
  }
  
  
}
