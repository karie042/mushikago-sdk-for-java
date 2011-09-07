package org.mushikago.tombo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;

public class ParamUtils {
	
	public static String mapToString(Map<String, String> map) {
		
		if(0 >= map.size()) return "";
		StringBuilder buffer = new StringBuilder();
		for(Entry<String, String> param : map.entrySet()) { buffer.append(String.format("&%s=%s", param.getKey(), param.getValue())); }
		return buffer.substring(1);
	}
	
	public static String paramEncode(String param) throws UnsupportedEncodingException {
		return URLEncoder.encode(param, "UTF-8");
	}
}
