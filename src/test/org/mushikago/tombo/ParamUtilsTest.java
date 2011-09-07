package org.mushikago.tombo;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

public class ParamUtilsTest {
	
	@Test
	public void testMapToString() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		assertEquals("", ParamUtils.mapToString(map));
		map.put("A", "1");
		assertEquals("A=1", ParamUtils.mapToString(map));
		map.put("B", "2");
		assertEquals("A=1&B=2", ParamUtils.mapToString(map));
		map.put("C", "3");
		assertEquals("A=1&B=2&C=3", ParamUtils.mapToString(map));
	}
	
	@Test
	public void testParamEncode() throws UnsupportedEncodingException {
		assertEquals("%E3%81%82%26A%26%E3%81%84", ParamUtils.paramEncode("あ&A&い"));
	}
}
