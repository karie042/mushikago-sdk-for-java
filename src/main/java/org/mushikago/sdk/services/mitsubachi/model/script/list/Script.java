package org.mushikago.sdk.services.mitsubachi.model.script.list;

/**
 * スクリプトファイルのメタ情報を表現するクラスです
 * @author miningbrownie
 */
public class Script {
	
	/**
	 * @serial スクリプト名
	 * スラッシュ区切りの場合はスラッシュが含まれます
	 */
	private final String name;
	
	/**
	 * @serial ファイルサイズ
	 */
	private final int size;
	
	/**
	 * @serial タイムスタンプ
	 * 例「2011-12-20T08:37:44+00:00」
	 */
	private final String timestamp;
	
	/**
	 * 指定されたスクリプト名、ファイルサイズ、タイムスタンプを使用して、Scriptを構築します。<br>
	 * @param name スクリプト名
	 * @param size ファイルサイズ
	 * @param timestamp タイムスタンプ
	 */
	Script(String name, int size, String timestamp) {
		
		this.name = name;
		this.size = size;
		this.timestamp = timestamp;
	}
	
	/**
	 * スクリプト名を取得
	 * @return スクリプト名
	 */
	public String getName() { return name; }
	
	/**
	 * ファイルサイズを取得
	 * @return ファイルサイズ
	 */
	public int getSize() { return this.size; }
	
	/**
	 * タイムスタンプを取得
	 * @return タイムスタンプ
	 */
	public String getTimestamp() { return this.timestamp; }
}
