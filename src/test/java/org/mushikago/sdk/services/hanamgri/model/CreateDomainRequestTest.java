package org.mushikago.sdk.services.hanamgri.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.net.URI;

import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.junit.Test;
import org.mushikago.sdk.common.RequestException;
import org.mushikago.sdk.services.hanamgri.model.Field.Type;

public class CreateDomainRequestTest extends HanamgriRequestTestBase {

  @Test
  public void testConstruct() {
    CreateDomainRequest request = new CreateDomainRequest();
    assertNull(request.getDomainName());
    assertNull(request.getSeeds());
    assertNull(request.getDescription());
  }

  @Test
  public void testConstructWithDomainNameAndSeeds() {
    CreateDomainRequest request = new CreateDomainRequest("domain", "seeds", new Schema());
    assertEquals("domain", request.getDomainName());
    assertEquals("seeds", request.getSeeds());
    assertNotNull(request.getSchema());
    assertNull(request.getDescription());
  }

  @Test
  public void testSetterAndGetterForDomainName() {
    CreateDomainRequest request = new CreateDomainRequest();
    request.setDomainName("domain");
    assertEquals("domain", request.getDomainName());
  }

  @Test
  public void testSetterAndGetterForSeeds() {
    CreateDomainRequest request = new CreateDomainRequest();
    request.setSeeds("seeds");
    assertEquals("seeds", request.getSeeds());
  }

  @Test
  public void testSetterAndGetterForDescription() {
    CreateDomainRequest request = new CreateDomainRequest();
    request.setDescription("description");
    assertEquals("description", request.getDescription());
  }
  @Test
  public void testSetterAndGetterForDictionaryName() {
    CreateDomainRequest request = new CreateDomainRequest();
    request.setDictionaryName("dictionary_name");
    assertEquals("dictionary_name", request.getDictionaryName());
  }

  @Test(expected = RequestException.class)
  public void testThrowExceptionIfDomainNameIsNull() throws RequestException {
    CreateDomainRequest request = new CreateDomainRequest(null, "seeds", new Schema());
    request.toHttpMethod(auth, ci);
  }

  @Test(expected = RequestException.class)
  public void testThrowExceptionIfSeedsIsNull() throws RequestException {
    CreateDomainRequest request = new CreateDomainRequest("domain", null, new Schema());
    request.toHttpMethod(auth, ci);
  }

  @Test(expected = RequestException.class)
  public void testThrowExceptionIfSchemaIsNull() throws RequestException {
    CreateDomainRequest request = new CreateDomainRequest("domain", "seeds", null);
    request.toHttpMethod(auth, ci);
  }

  private HttpRequestBase callToHttpMethod(String domainName, String seeds, String description, String dictionaryName) throws RequestException {
    Schema schema = new Schema();
    schema.addField(new Field("name", true, Type.String));
    CreateDomainRequest request = new CreateDomainRequest(domainName, seeds, schema);
    request.setDescription(description);
    request.setDictionaryName(dictionaryName);
    return request.toHttpMethod(auth, ci);
  }

  @Test
  public void testHttpMethodIsHttpPut() throws RequestException {
    HttpRequestBase request = callToHttpMethod("domain", "seeds", "description", "dictionary");
    assertTrue(request instanceof HttpPut);
  }

  @Test
  public void testHttpMethodPathAndQueryContainsParameters() throws RequestException {
    HttpRequestBase request = callToHttpMethod("domain", "seeds", "description", "dictionary");
    URI uri = request.getURI();
    assertEquals("/1/hanamgri/domains/domain", uri.getRawPath());
  }

  @Test
  public void testHttpMethodPathAndQueryContainsParametersWithJapanese() throws RequestException {
    HttpRequestBase request = callToHttpMethod("domain", "税込", "説明！", "dictionary");
    URI uri = request.getURI();
    assertEquals("/1/hanamgri/domains/domain", uri.getRawPath());
  }
}
