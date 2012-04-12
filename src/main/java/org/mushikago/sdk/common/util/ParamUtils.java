package org.mushikago.sdk.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;

/**
 * パラメータのデータ変換に関するユーティリティ。<br>
 * @author miningbrownie
 *
 */
public class ParamUtils {
	
	/**
	 * Map形式のデータを「*=*&*=*&・・・」形式の文字列に変換したデータを作成します。<br>
	 * @param map 作成したいデータ
	 * @return 作成したデータ
	 */
	public static String mapToString(Map<String, String> map) {
		
		if(0 >= map.size()) return "";
		StringBuilder buffer = new StringBuilder();
		for(Entry<String, String> param : map.entrySet()) { buffer.append(String.format("&%s=%s", param.getKey(), param.getValue())); }
		return buffer.substring(1);
	}
	
	/**
	 * パラメータをURLエンコードしたデータを作成します。+は「%20」、*は「%2A」に変換されます。<br>
	 * @param param 作成したいデータ
	 * @return 作成したデータ
	 * @throws UnsupportedEncodingException エンコードができなかった場合
	 */
	public static String paramEncode(String param) throws UnsupportedEncodingException {
		return URLEncoder.encode(param, "UTF-8").replaceAll("\\+", "%20").replaceAll("\\*", "%2A");
	}
	
	/**
	 * パラメータをURLデコードしたデータを作成します。「%20」は+、「%2A」は*に変換されます。<br>
	 * @param param 作成したいデータ
	 * @return 作成したデータ
	 * @throws UnsupportedEncodingException エンコードができなかった場合
	 */
	public static String paramDecode(String param) throws UnsupportedEncodingException {
	  return URLDecoder.decode(param.replaceAll("%20", "+").replaceAll("%2A", "*"), "UTF-8");
	}
}
