package org.mushikago.tombo.auth;

public class Credentials {
	
	private final String apiKey;
	private final String secretKey;
	
	public Credentials(String apiKey, String secretKey) {
		this.apiKey = apiKey;
		this.secretKey = secretKey;
	}
	
	public String getApiKey() { return this.apiKey; }
	public String getSecretKey() { return this.secretKey; }
}
