package org.mushikago.sdk.common;

import net.sf.json.JSONObject;

import org.mushikago.sdk.common.auth.Credentials;
import org.mushikago.sdk.common.broker.HttpRequestBroker;
import org.mushikago.sdk.common.util.ConnectInfo;

/**
 * 各mushikagoサービスと通信を行うクライアントの基底クラス。<br>
 * <br>
 * 通信先(URL、ポート)を保持します。
 * 通信の際に必要となる認証オブジェクトを保持します。
 * 
 * @author miningbrownie
 *
 */
public abstract class MushikagoClient {
	
	/**
	 * リクエスト代行オブジェクト
	 */
	protected final HttpRequestBroker broker;
	
	/**
	 * 通信先情報
	 */
	protected final ConnectInfo ci;
	
	/**
	 * 指定した認証用オブジェクトと通信先URLを使用して、MushikagoClientを構築します。<br>
	 * @param credentials 認証用オブジェクト
	 * @param endpoint 通信先のURL
	 */
	protected MushikagoClient(Credentials credentials, final String endpoint) {
		
		this.broker = new HttpRequestBroker(credentials);
		this.ci = new ConnectInfo(endpoint, null, "https");
	}
	
  protected abstract void checkResponse(JSONObject json) throws APICallException;
	
	/**
	 * 通信先のURLを設定。<br>
	 * @param endpoint URL
	 */
	public void setEndpoint(String endpoint) { this.ci.setEndpoint(endpoint); }
	
	/**
	 * 通信先のURLを取得。<br>
	 * @return URL
	 */
	public String getEndPoint() { return this.ci.getEndpoint(); }
	
	/**
	 * 通信先のポートを設定。<br>
	 * @param port port番号
	 */
	public void setPort(int port) { this.ci.setPort(port); }
	
	/**
	 * 通信先のポートを取得。<br>
	 * @return ポート番号
	 */
	public int getPort() { return this.ci.getPort(); }
	
	/**
	 * 通信先のスキーマを設定。<br>
	 * @param schema URLのスキーマ
	 */
	public void setSchema(String schema) { this.ci.setSchema(schema); }
	
	/**
	 * 通審査器のスキーマを取得。<br>
	 * @return URLのスキーマ
	 */
	public String getSchema() { return this.ci.getSchema(); }
}
