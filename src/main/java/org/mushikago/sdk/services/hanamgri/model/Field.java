package org.mushikago.sdk.services.hanamgri.model;

import net.sf.json.JSONObject;

/**
 * スキーマの一つのフィールドを表すクラスです
 * 
 * @author ope
 */
public class Field {
  public enum Type {
    String("string"),
    Number("number");

    private final String v;

    private Type(String v) {
      this.v = v;
    }

    @Override
    public String toString() {
      return v;
    }
  }

  private final String name;
  private final boolean isRequired;
  private final String type;
  private final String knowledgeName;

  public Field(String name, boolean isRequired, Type type, String knowledgeName) {
    this(name, isRequired, type.toString(), knowledgeName);
  }

  public Field(String name, boolean isRequired, Type type) {
    this(name, isRequired, type.toString(), "");
  }

  public Field(String name, boolean isRequired, String type) {
    this(name, isRequired, type.toString(), "");
  }

  public Field(String name, boolean isRequired, String type, String knowledgeName) {
    this.name = name;
    this.isRequired = isRequired;
    this.type = type;
    this.knowledgeName = knowledgeName;
  }

  public String getName() {
    return name;
  }

  public boolean isRequired() {
    return isRequired;
  }

  public String getType() {
    return type;
  }

  public String getKnowledgeName() {
    return knowledgeName;
  }

  public JSONObject toJSONObject() {
    JSONObject o = new JSONObject();
    o.put("name", name);
    o.put("required", isRequired);
    o.put("type", type);
    o.put("knowledge_name", knowledgeName);
    return o;
  }

  @Override
  public String toString() {
    return toJSONObject().toString();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (isRequired ? 1231 : 1237);
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((type == null) ? 0 : type.hashCode());
    result = prime * result + ((knowledgeName == null) ? 0 : knowledgeName.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    Field other = (Field) obj;
    if (isRequired != other.isRequired) return false;
    if (name == null) {
      if (other.name != null) return false;
    }
    else if (!name.equals(other.name)) return false;
    if (type == null) {
      if (other.type != null) return false;
    }
    else if (!type.equals(other.type)) return false;
    if (knowledgeName == null) {
      if (other.knowledgeName != null) return false;
    }
    else if (!knowledgeName.equals(other.knowledgeName)) return false;
    return true;
  }
  
  
}
