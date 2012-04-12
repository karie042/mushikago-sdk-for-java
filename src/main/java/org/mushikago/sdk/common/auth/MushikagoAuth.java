package org.mushikago.sdk.common.auth;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.TreeMap;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mushikago.sdk.common.util.ParamUtils;

/**
 * mushikagoサービスとの通信で使用する認証処理を行うクラス。<br>
 * @author miningbrownie
 *
 */
public class MushikagoAuth {
	
  /**
   * ロガー。<br>
   */
  private static Log logger = LogFactory.getLog(MushikagoAuth.class);
  
	/**
	 * APIキー。<br>
	 */
	private final String apiKey;
	
	/**
	 * シークレットキー。<br>
	 */
	private final String secretKey;
	
	/**
	 * 暗号化アルゴリズム。<br>
	 */
	private final String cryptoAlgorithm;
	
	/**
	 * 指定された認証用オブジェクトを使用して、MushikagoAuthを構築します。<br>
	 * @param credentials 認証用オブジェクト
	 */
	public MushikagoAuth(Credentials credentials) {
		
		this.apiKey = credentials.getApiKey();
		this.secretKey = credentials.getSecretKey();
		
		this.cryptoAlgorithm = "HmacSHA256";
	}
	
	/**
	 * APIキー取得。<br>
	 * @return APIキー
	 */
	public String getApiKey() {
		return this.apiKey;
	}
	
	/**
	 * Signatureを作成。<br>
	 * @param httpMethod HTTPメソッド
	 * @param endpoint 通信先のURL
	 * @param requestPath 通信先のパス
	 * @param requestParams パラメータ
	 * @return Signature
	 * @throws AuthException 暗号化に失敗した場合
	 */
	public String toSignature(String httpMethod, String endpoint, String requestPath, TreeMap<String, String> requestParams) throws AuthException {
		
		String result = null;
		
		final String data = String.format("%s\n%s\n%s\n%s", httpMethod, endpoint, requestPath, ParamUtils.mapToString(requestParams));
		logger.debug(data);
		
		try {
			result = this.toSignature(data);
		}
		catch(InvalidKeyException e) {
			throw new AuthException(String.format("class[%s],message[%s]", e.getClass(), e.getMessage()));
		}
		catch(NoSuchAlgorithmException e) {
			throw new AuthException(String.format("class[%s],message[%s]", e.getClass(), e.getMessage()));
		}
		catch(UnsupportedEncodingException e) {
			throw new AuthException(String.format("class[%s],message[%s]", e.getClass(), e.getMessage()));
		}
		
		return result;
	}
	
	public String makeTimeStamp() {
		
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd'T'HH:mm:ss'Z'");
		df.setTimeZone(cal.getTimeZone());
		return df.format(cal.getTime());
	}
	
	private String toSignature(String data) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
		return ParamUtils.paramEncode(this.signature(data).replaceAll("\r\n$", ""));
	}
	
  private String signature(String data) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
    String encode = "UTF-8";
    Mac mac = Mac.getInstance(this.cryptoAlgorithm);
    mac.init(new SecretKeySpec(this.secretKey.getBytes(encode), mac.getAlgorithm()));
    byte[] bytes = mac.doFinal(data.getBytes(encode));
    return new String(new Base64().encode(bytes), encode);
  }
}
