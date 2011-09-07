package org.mushikago.tombo.model.delete;

import static org.junit.Assert.*;

import org.junit.Test;

public class DeleteResponseTest {
	
	@Test
	public void testGetSetId() {
		DeleteResponse delete = new DeleteResponse();
		assertEquals(null, delete.getId());
		String id = "abcde";
		delete.setId(id);
		assertEquals(id, delete.getId());
		id = "12345";
		delete.setId(id);
		assertEquals(id, delete.getId());
	}
}
