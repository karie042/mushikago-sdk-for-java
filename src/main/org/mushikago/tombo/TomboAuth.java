package org.mushikago.tombo;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.TreeMap;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.mushikago.tombo.auth.Credentials;

public class TomboAuth {
	
	private final String apiKey;
	private final String secretKey;
	
	private final String cryptoAlgorithm;
	
	public TomboAuth(Credentials credentials) {
		
		this.apiKey = credentials.getApiKey();
		this.secretKey = credentials.getSecretKey();
		
		this.cryptoAlgorithm = "HmacSHA256";
	}
	
	public String getApiKey() {
		return this.apiKey;
	}
	
	public String toSignature(String httpMethod, String endpoint, String requestPath, TreeMap<String, String> requestParams) throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {
		
		String data = String.format("%s\n%s\n%s\n%s", httpMethod, endpoint, requestPath, ParamUtils.mapToString(requestParams));
		return this.toSignature(data);
	}
	
	public String makeTimeStamp() {
		
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd'T'HH:mm:ss'Z'");
		df.setTimeZone(cal.getTimeZone());
		return df.format(cal.getTime());
	}
	
	private String toSignature(String data) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
		
		String encode = "UTF-8";
		Mac mac = Mac.getInstance(this.cryptoAlgorithm);
		mac.init(new SecretKeySpec(this.secretKey.getBytes(encode), mac.getAlgorithm()));
		byte[] bytes = mac.doFinal(data.getBytes(encode));
		data = new String(new Base64().encode(bytes), encode);
		return ParamUtils.paramEncode(data);
	}
}
