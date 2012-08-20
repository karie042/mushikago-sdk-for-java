package org.mushikago.sdk.services.hanamgri.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class SchemaTest {

  @Test
  public void testToStringWithEmptySchema() {
    Schema schema = new Schema();
    assertEquals("{\"fields\":[]}", schema.toString());
  }

  @Test
  public void testToStringWithSingleField() {
    Schema schema = new Schema();
    schema.addField(new Field("name", true, Field.Type.String, "mushikago/ec/price"));
    assertEquals("{\"fields\":[{\"name\":\"name\",\"required\":true,\"type\":\"string\",\"knowledge_name\":\"mushikago/ec/price\"}]}", schema.toString());
  }
}
