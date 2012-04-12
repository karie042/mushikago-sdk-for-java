package org.mushikago.sdk.common.auth;

/**
 * mushikagoAPIサーバと通信する為の認証用クラス。<br>
 * @author miningbrownie
 *
 */
public class Credentials {
	
	/**
	 * APIキー。<br>
	 * 株式会社マイニングブラウニーより発行されたAPIキー。<br>
	 */
	private final String apiKey;
	
	/**
	 * シークレットキー。<br>
	 * APIキーに対応するシークレットキー。<br>
	 */
	private final String secretKey;
	
	/**
	 * 指定されたAPIキー、シークレットキーを使用して、Credentialsを構築します。<br>
	 * @param apiKey APIキー
	 * @param secretKey シークレットキー
	 */
	public Credentials(String apiKey, String secretKey) {
		
		this.apiKey = apiKey;
		this.secretKey = secretKey;
	}
	
	/**
	 * APIキーを取得。<br>
	 * @return APIキー
	 */
	public String getApiKey() { return this.apiKey; }
	
	/**
	 * シークレットキーを取得。<br>
	 * @return シークレットキー
	 */
	public String getSecretKey() { return this.secretKey; }
}
