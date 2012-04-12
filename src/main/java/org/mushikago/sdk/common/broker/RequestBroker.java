package org.mushikago.sdk.common.broker;

import net.sf.json.JSONObject;

import org.mushikago.sdk.common.RequestException;
import org.mushikago.sdk.common.auth.Credentials;
import org.mushikago.sdk.common.auth.MushikagoAuth;
import org.mushikago.sdk.common.model.MushikagoRequest;
import org.mushikago.sdk.common.util.ConnectInfo;

/**
 * mushikagoサービスにリクエストを行う処理を代行。<br>
 * @author miningbrownie
 *
 */
public abstract class RequestBroker {
	
	/**
	 * 認証用オブジェクト。<br>
	 */
	protected final MushikagoAuth mushikagoAuth;
	
	/**
	 * 指定された認証用オブジェクトを使用して、RequestBrokerを構築します。<br>
	 * @param credentials 認証用オブジェクト
	 */
	public RequestBroker(Credentials credentials) {
		
		this.mushikagoAuth = new MushikagoAuth(credentials);
	}
	
	/**
	 * リクエストを行う処理を実装します。<br>
	 * @param request リクエスト
	 * @param ci 通信先情報
	 * @return mushikagoサービスのAPIサーバから返されるJSONデータ
	 * @throws RequestException リクエスト関係の例外が発生した場合
	 * @throws BrokerException 通信関係の例外が発生した場合
	 */
	public abstract JSONObject request(MushikagoRequest request, ConnectInfo ci) throws RequestException, BrokerException;
}
