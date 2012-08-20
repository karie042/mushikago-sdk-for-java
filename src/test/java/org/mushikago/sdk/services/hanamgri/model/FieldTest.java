package org.mushikago.sdk.services.hanamgri.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mushikago.sdk.services.hanamgri.model.Field.Type;

public class FieldTest {

  @Test
  public void testToString() {
    Field field = new Field("name", true, Type.String, "mushikago/ec/price");
    assertEquals("{\"name\":\"name\",\"required\":true,\"type\":\"string\",\"knowledge_name\":\"mushikago/ec/price\"}", field.toString());
  }

}
