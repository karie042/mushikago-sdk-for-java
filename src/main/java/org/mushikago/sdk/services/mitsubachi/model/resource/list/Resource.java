package org.mushikago.sdk.services.mitsubachi.model.resource.list;

/**
 * リソース一覧で取得できるリソースの表現。<br>
 * @author miningbrownie
 *
 */
public class Resource {
	
	/**
	 * リソース名。<br>
	 * スラッシュ区切りの場合はスラッシュが含まれます
	 */
	private final String name;
	
	/**
	 * ファイルサイズ。<br>
	 */
	private final int size;
	
	/**
	 * タイムスタンプ。<br>
	 * 例「2011-12-20T08:37:44+00:00」
	 */
	private final String timestamp;
	
	/**
	 * コンテンツタイプ。<br>
	 */
	private final String contentType;
	
	/**
	 * 指定されたリソース名、ファイルサイズ、タイムスタンプを使用して、Resourceを構築します。<br>
	 * @param name リソース名
	 * @param size ファイルサイズ
	 * @param timestamp タイムスタンプ
	 */
	Resource(String name, int size, String timestamp, String contentType) {
		
		this.name = name;
		this.size = size;
		this.timestamp = timestamp;
		this.contentType = contentType;
	}
	
	/**
	 * リソース名の取得。<br>
	 * @return リソース名
	 */
	public String getName() { return name; }
	
	/**
	 * ファイルサイズの取得。<br>
	 * @return ファイルサイズ
	 */
	public int getSize() { return this.size; }
	
	/**
	 * タイムスタンプの取得。<br>
	 * @return タイムスタンプ
	 */
	public String getTimestamp() { return this.timestamp; }
	
	/**
	 * コンテンツタイプの取得。<br>
	 * @return コンテンツタイプ
	 */
	public String getContentType() { return this.contentType; }
}
