package org.mushikago.sdk.common.broker;

import java.io.IOException;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.util.EntityUtils;
import org.mushikago.sdk.common.RequestException;
import org.mushikago.sdk.common.auth.Credentials;
import org.mushikago.sdk.common.model.MushikagoRequest;
import org.mushikago.sdk.common.util.ConnectInfo;

/**
 * HTTPリクエストを代行。<br>
 * @author miningbrownie
 *
 */
public class HttpRequestBroker extends RequestBroker {
	
	/**
	 * ロガー。<br>
	 */
	private static Log log = LogFactory.getLog(HttpRequestBroker.class);
	
	/**
	 * 指定された認証用オブジェクトを使用して、HttpRequestBrokerを構築します。<br>
	 * @param credentials 認証用オブジェクト
	 */
	public HttpRequestBroker(Credentials credentials) {
		
		super(credentials);
	}
	
	/**
	 * HTTPリクエストを実行します。<br>
	 * @param request リクエスト
	 * @param ci 通信先情報
	 * @return mushikagoサービスのAPIサーバから返されるJSONデータ
	 * @throws RequestException リクエスト関係の例外が発生した場合
	 * @throws BrokerException 通信関係の例外が発生した場合
	 */
	public JSONObject request(MushikagoRequest request, ConnectInfo ci) throws RequestException, BrokerException {
		
		try {
			HttpRequestBase method = request.toHttpMethod(this.mushikagoAuth, ci);
			HttpClient http = new DefaultHttpClient();
			((AbstractHttpClient) http).setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler(3, false)); 
			log.info(String.format("request[%s],URI[%s]", request.getClass().toString(), method.getURI()));
			
			HttpResponse res = http.execute(method);
			
			String str = EntityUtils.toString(res.getEntity(), "UTF-8");
			log.info(String.format("response[%s]", str));
			JSONObject json = JSONObject.fromObject(str);
			
			return json;
		}
		catch(ClientProtocolException e){ throw new BrokerException(String.format("%s:%s", e.getClass().getName(), e.getMessage())); }
		catch(IOException e) { throw new BrokerException(String.format("%s:%s", e.getClass().getName(), e.getMessage())); }
	}
}
