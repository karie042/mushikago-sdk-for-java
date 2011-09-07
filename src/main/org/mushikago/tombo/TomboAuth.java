package org.mushikago.tombo;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
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
	
	private final String serverHost;
	private final String cryptoAlgorithm;
	
	public TomboAuth(Credentials credentials) {
		
		this.apiKey = credentials.getApiKey();
		this.secretKey = credentials.getSecretKey();
		
		this.serverHost = "tombo.mushikago.org";
		this.cryptoAlgorithm = "HmacSHA256";
	}
	
	public String makeRequestUrl(String httpMethod, String requestPath, TreeMap<String, String> requestParams) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
		
		TreeMap<String, String> _requestParams = new TreeMap<String, String>();
		_requestParams.putAll(requestParams);
		
		String apiKey = ParamUtils.paramEncode(this.apiKey);
		String timestamp = ParamUtils.paramEncode(this.makeTimeStamp());
		
		_requestParams.put("api_key",   apiKey);
		_requestParams.put("timestamp", timestamp);
		
		String data = String.format("%s\n%s\n%s\n%s", httpMethod, this.serverHost, requestPath, ParamUtils.mapToString(_requestParams));
		String signature = this.toSignature(data);
		
		return String.format("http://%s%s?api_key=%s&timestamp=%s&signature=%s", this.serverHost, requestPath, apiKey, timestamp, signature);
	}
	
	@SuppressWarnings("deprecation")
	private String makeTimeStamp() {
		
		SimpleDateFormat result = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		Date date = cal.getTime();
//		date.setTime(cal.getTimeInMillis());
		date.setHours(cal.get(Calendar.HOUR_OF_DAY));
		date.setDate(cal.get(Calendar.DAY_OF_MONTH));
		return result.format(date);

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
