package org.mushikago.sdk.common.util;

/**
 * ２つの値(keyとvalue)を保持する構造体。<br>
 * key(キー)という名称であるが、重複不可等の特別な意味はありません。<br>
 * 単に２つのデータを保持するだけのクラスです。
 * @author miningbrownie
 *
 */
public class KeyValuePair {
	
	/**
	 * １つ目のデータ。<br>
	 */
	private String key = null;
	
	/**
	 * ２つ目のデータ。<br>
	 */
	private String value = null;
	
	/**
	 * 指定された２つのパラメータを使用して、KeyValuePairを構築します。<br>
	 * @param key １つ目のデータ
	 * @param value ２つ目のデータ
	 */
	public KeyValuePair(String key, String value) {
		
		this.key = key;
		this.value = value;
	}
	
	/**
	 * keyの設定。<br>
	 * @param key 設定値
	 */
	public void setKey(String key) { this.key = key; }
	
	/**
	 * valueの設定。<br>
	 * @param value 設定値
	 */
	public void setValue(String value) { this.value = value; }
	
	/**
	 * keyの取得。<br>
	 * @return key
	 */
	public String getKey() { return this.key; }
	
	/**
	 * valueの取得。<br>
	 * @return value
	 */
	public String getValue() { return this.value; }
}
