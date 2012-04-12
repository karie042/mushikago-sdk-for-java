package org.mushikago.sdk.services.mitsubachi;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.mushikago.sdk.common.APICallException;
import org.mushikago.sdk.common.MushikagoClient;
import org.mushikago.sdk.common.RequestException;
import org.mushikago.sdk.common.auth.Credentials;
import org.mushikago.sdk.common.auth.UnauthorizedException;
import org.mushikago.sdk.common.broker.BrokerException;
import org.mushikago.sdk.services.mitsubachi.exceptions.FileNotFoundException;
import org.mushikago.sdk.services.mitsubachi.exceptions.InternalErrorException;
import org.mushikago.sdk.services.mitsubachi.exceptions.InternalServerErrorException;
import org.mushikago.sdk.services.mitsubachi.exceptions.InvalidParameterValueException;
import org.mushikago.sdk.services.mitsubachi.exceptions.ProjectAlreadyExistsException;
import org.mushikago.sdk.services.mitsubachi.exceptions.ProjectNotFoundException;
import org.mushikago.sdk.services.mitsubachi.exceptions.UnknownParameterException;
import org.mushikago.sdk.services.mitsubachi.model.http.fetch.HttpFetchRequest;
import org.mushikago.sdk.services.mitsubachi.model.http.fetch.HttpFetchResponse;
import org.mushikago.sdk.services.mitsubachi.model.http.push.HttpPushRequest;
import org.mushikago.sdk.services.mitsubachi.model.http.push.HttpPushResponse;
import org.mushikago.sdk.services.mitsubachi.model.project.create.ProjectCreateRequest;
import org.mushikago.sdk.services.mitsubachi.model.project.create.ProjectCreateResponse;
import org.mushikago.sdk.services.mitsubachi.model.project.delete.ProjectDeleteRequest;
import org.mushikago.sdk.services.mitsubachi.model.project.delete.ProjectDeleteResponse;
import org.mushikago.sdk.services.mitsubachi.model.project.discontinue.ProjectDiscontinueRequest;
import org.mushikago.sdk.services.mitsubachi.model.project.discontinue.ProjectDiscontinueResponse;
import org.mushikago.sdk.services.mitsubachi.model.project.info.ProjectInfoRequest;
import org.mushikago.sdk.services.mitsubachi.model.project.info.ProjectInfoResponse;
import org.mushikago.sdk.services.mitsubachi.model.project.list.ProjectListRequest;
import org.mushikago.sdk.services.mitsubachi.model.project.list.ProjectListResponse;
import org.mushikago.sdk.services.mitsubachi.model.project.queues.ProjectQueuesRequest;
import org.mushikago.sdk.services.mitsubachi.model.project.queues.ProjectQueuesResponse;
import org.mushikago.sdk.services.mitsubachi.model.project.update.ProjectUpdateRequest;
import org.mushikago.sdk.services.mitsubachi.model.project.update.ProjectUpdateResponse;
import org.mushikago.sdk.services.mitsubachi.model.resource.delete.ResourceDeleteRequest;
import org.mushikago.sdk.services.mitsubachi.model.resource.delete.ResourceDeleteResponse;
import org.mushikago.sdk.services.mitsubachi.model.resource.get.ResourceGetRequest;
import org.mushikago.sdk.services.mitsubachi.model.resource.get.ResourceGetResponse;
import org.mushikago.sdk.services.mitsubachi.model.resource.list.ResourceListRequest;
import org.mushikago.sdk.services.mitsubachi.model.resource.list.ResourceListResponse;
import org.mushikago.sdk.services.mitsubachi.model.resource.store.ResourceStoreRequest;
import org.mushikago.sdk.services.mitsubachi.model.resource.store.ResourceStoreResponse;
import org.mushikago.sdk.services.mitsubachi.model.script.delete.ScriptDeleteRequest;
import org.mushikago.sdk.services.mitsubachi.model.script.delete.ScriptDeleteResponse;
import org.mushikago.sdk.services.mitsubachi.model.script.deploy.ScriptDeployRequest;
import org.mushikago.sdk.services.mitsubachi.model.script.deploy.ScriptDeployResponse;
import org.mushikago.sdk.services.mitsubachi.model.script.get.ScriptGetRequest;
import org.mushikago.sdk.services.mitsubachi.model.script.get.ScriptGetResponse;
import org.mushikago.sdk.services.mitsubachi.model.script.list.ScriptListRequest;
import org.mushikago.sdk.services.mitsubachi.model.script.list.ScriptListResponse;

/**
 * mitsubachiAPIサーバと通信を行うクライアント。<br>
 * 各APIに対応したメソッドをカテゴリ名(http,project,resource,script)から始まるメソッドとして提供します。<br>
 * <br>
 * メソッドには、MitsubachiRequestを継承した各リクエストを引数として受けとります。<br>
 * リクエストは、APIに対するパラメータを自身のフィールドとして保持し、それに設定する為、セッターメソッドを提供します。<br>
 * <br>
 * 通信が正常に終了すると、MitsubachiResponseを継承した各レスポンスが返却されます。<br>
 * レスポンスは、APIサーバから受け取ったパラメータ(JSON形式のデータ)を、自身のフィールドとして保持し、ゲッターメソッドを提供します。<br>
 * <br>
 * 通信が正常に終了しなかった、又は返却されたパラメータによっては、MitsubachiExceptionを継承した各例外が送出されます。<br>
 * 例外は、Exceptionクラスを基底クラスとし、詳細メッセージを保持します。<br>
 * @author miningbrownie
 *
 */
public class MitsubachiClient extends MushikagoClient {
	
	/**
	 * 指定されたCredentialsを使用して、MitsubachiClientを構築します。<br>
	 * @param credentials 認証用オブジェクト
	 */
	public MitsubachiClient(Credentials credentials) {
		
		super(credentials, "api.mushikago.org");
	}
	
	/**
	 * 
	 * APIサーバから返ってきたレスポンスをチェックします。
	 * 
	 * @param json APIサーバから返ってきたレスポンス
	 * @throws APICallException
	 */
	@Override
	protected void checkResponse(JSONObject json) throws APICallException {
		
		try {
			
			final int status = json.getJSONObject("meta").getInt("status");
			switch(status) {
			case 401: throw new UnauthorizedException(json);
			case 500: throw new InternalErrorException(json);
			case 561: throw new UnknownParameterException(json);
			case 562: throw new InvalidParameterValueException(json);
			case 563: throw new ProjectNotFoundException(json);
			case 564: throw new FileNotFoundException(json);
			case 565: throw new ProjectAlreadyExistsException(json);
			}
		}
		catch(JSONException e) {
			JSONObject detail = new JSONObject();
			detail.put("error", json.toString());
			throw new InternalServerErrorException(detail);
		}
	}
	
	/**
	 * mitsubachiAPIサーバに、「HttpFetch」リクエストを要求します。<br>
	 * @param request リクエスト
	 * @return レスポンス
	 * @throws RequestException リクエスト関係でエラーが発生した場合
	 * @throws BrokerException 通信でエラーが発生した場合
	 * @throws APICallException パラメータの指定等がおかしい場合
	 */
	public HttpFetchResponse httpFetch(HttpFetchRequest request) throws RequestException, BrokerException, APICallException {
		
		JSONObject json = this.broker.request(request, this.ci);
		this.checkResponse(json);
		return new HttpFetchResponse(json);
	}
	
	/**
	 * mitsubachiAPIサーバに、「HttpPush」リクエストを要求します。<br>
	 * @param request リクエスト
	 * @return レスポンス
	 * @throws RequestException リクエスト関係でエラーが発生した場合
	 * @throws BrokerException 通信でエラーが発生した場合
	 * @throws APICallException パラメータの指定等がおかしい場合
	 */
	public HttpPushResponse httpPush(HttpPushRequest request) throws RequestException, BrokerException, APICallException {
		
		JSONObject json = this.broker.request(request, this.ci);
		this.checkResponse(json);
		return new HttpPushResponse(json);
	}
	
	/**
	 * mitsubachiAPIサーバに、「プロジェクト作成」リクエストを要求します。<br>
	 * @param request リクエスト
	 * @return レスポンス
	 * @throws RequestException リクエスト関係でエラーが発生した場合
	 * @throws BrokerException 通信でエラーが発生した場合
	 * @throws APICallException パラメータの指定等がおかしい場合
	 */
	public ProjectCreateResponse projectCreate(ProjectCreateRequest request) throws RequestException, BrokerException, APICallException {
		
		JSONObject json = this.broker.request(request, this.ci);
		this.checkResponse(json);
		return new ProjectCreateResponse(json);
	}
	
	/**
	 * mitsubachiAPIサーバに、「プロジェクト削除」リクエストを要求します。<br>
	 * @param request リクエスト
	 * @return レスポンス
	 * @throws RequestException リクエスト関係でエラーが発生した場合
	 * @throws BrokerException 通信でエラーが発生した場合
	 * @throws APICallException パラメータの指定等がおかしい場合
	 */
	public ProjectDeleteResponse projectDelete(ProjectDeleteRequest request) throws RequestException, BrokerException, APICallException {
		
		JSONObject json = this.broker.request(request, this.ci);
		this.checkResponse(json);
		return new ProjectDeleteResponse(json);
	}
	
	/**
	 * mitsubachiAPIサーバに、「プロジェクト更新」リクエストを要求します。<br>
	 * @param request リクエスト
	 * @return レスポンス
	 * @throws RequestException リクエスト関係でエラーが発生した場合
	 * @throws BrokerException 通信でエラーが発生した場合
	 * @throws APICallException パラメータの指定等がおかしい場合
	 */
	public ProjectUpdateResponse projectUpdate(ProjectUpdateRequest request) throws RequestException, BrokerException, APICallException {
		
		JSONObject json = this.broker.request(request, this.ci);
		this.checkResponse(json);
		return new ProjectUpdateResponse(json);
	}
	
	/**
	 * mitsubachiAPIサーバに、「プロジェクト情報取得」リクエストを要求します。<br>
	 * @param request リクエスト
	 * @return レスポンス
	 * @throws RequestException リクエスト関係でエラーが発生した場合
	 * @throws BrokerException 通信でエラーが発生した場合
	 * @throws APICallException パラメータの指定等がおかしい場合
	 */
	public ProjectInfoResponse projectInfo(ProjectInfoRequest request) throws RequestException, BrokerException, APICallException {
		
		JSONObject json = this.broker.request(request, this.ci);
		this.checkResponse(json);
		return new ProjectInfoResponse(json);
	}
	
	/**
	 * mitsubachiAPIサーバに、「プロジェクトDiscontinue」リクエストを要求します。<br>
	 * @param request リクエスト
	 * @return レスポンス
	 * @throws RequestException リクエスト関係でエラーが発生した場合
	 * @throws BrokerException 通信でエラーが発生した場合
	 * @throws APICallException パラメータの指定等がおかしい場合
	 */
	public ProjectDiscontinueResponse projectDiscontinue(ProjectDiscontinueRequest request) throws RequestException, BrokerException, APICallException {
		
		JSONObject json = this.broker.request(request, this.ci);
		this.checkResponse(json);
		return new ProjectDiscontinueResponse(json);
	}
	
	/**
	 * mitsubachiAPIサーバに、「プロジェクトQueues」リクエストを要求します。<br>
	 * @param request リクエスト
	 * @return レスポンス
	 * @throws RequestException リクエスト関係でエラーが発生した場合
	 * @throws BrokerException 通信でエラーが発生した場合
	 * @throws APICallException パラメータの指定等がおかしい場合
	 */
	public ProjectQueuesResponse projectQueues(ProjectQueuesRequest request) throws RequestException, BrokerException, APICallException {
		
		JSONObject json = this.broker.request(request, this.ci);
		this.checkResponse(json);
		return new ProjectQueuesResponse(json);
	}
	
	/**
	 * mitsubachiAPIサーバに、「プロジェクト一覧」リクエストを要求します。<br>
	 * @param request リクエスト
	 * @return レスポンス
	 * @throws RequestException リクエスト関係でエラーが発生した場合
	 * @throws BrokerException 通信でエラーが発生した場合
	 * @throws APICallException パラメータの指定等がおかしい場合
	 */
	public ProjectListResponse projectList(ProjectListRequest request) throws RequestException, BrokerException, APICallException {
		
		JSONObject json = this.broker.request(request, this.ci);
		this.checkResponse(json);
		return new ProjectListResponse(json);
	}
	
	/**
	 * mitsubachiAPIサーバに、「リソース削除」リクエストを要求します。<br>
	 * @param request リクエスト
	 * @return レスポンス
	 * @throws RequestException リクエスト関係でエラーが発生した場合
	 * @throws BrokerException 通信でエラーが発生した場合
	 * @throws APICallException パラメータの指定等がおかしい場合
	 */
	public ResourceDeleteResponse resourceDelete(ResourceDeleteRequest request) throws RequestException, BrokerException, APICallException {
		
		JSONObject json = this.broker.request(request, this.ci);
		this.checkResponse(json);
		return new ResourceDeleteResponse(json);
	}
	
	/**
	 * mitsubachiAPIサーバに、「リソース取得」リクエストを要求します。<br>
	 * @param request リクエスト
	 * @return レスポンス
	 * @throws RequestException リクエスト関係でエラーが発生した場合
	 * @throws BrokerException 通信でエラーが発生した場合
	 * @throws APICallException パラメータの指定等がおかしい場合
	 */
	public ResourceGetResponse resourceGet(ResourceGetRequest request) throws RequestException, BrokerException, APICallException {
		
		JSONObject json = this.broker.request(request, this.ci);
		this.checkResponse(json);
		return new ResourceGetResponse(json);
	}
	
	/**
	 * mitsubachiAPIサーバに、「リソース一覧」リクエストを要求します。<br>
	 * @param request リクエスト
	 * @return レスポンス
	 * @throws RequestException リクエスト関係でエラーが発生した場合
	 * @throws BrokerException 通信でエラーが発生した場合
	 * @throws APICallException パラメータの指定等がおかしい場合
	 */
	public ResourceListResponse resourceList(ResourceListRequest request) throws RequestException, BrokerException, APICallException {
		
		JSONObject json = this.broker.request(request, this.ci);
		this.checkResponse(json);
		return new ResourceListResponse(json);
	}
	
	/**
	 * mitsubachiAPIサーバに、「リソースストア」リクエストを要求します。<br>
	 * @param request リクエスト
	 * @return レスポンス
	 * @throws RequestException リクエスト関係でエラーが発生した場合
	 * @throws BrokerException 通信でエラーが発生した場合
	 * @throws APICallException パラメータの指定等がおかしい場合
	 */
	public ResourceStoreResponse resourceStore(ResourceStoreRequest request) throws RequestException, BrokerException, APICallException {
		
		JSONObject json = this.broker.request(request, this.ci);
		this.checkResponse(json);
		return new ResourceStoreResponse(json);
	}
	
	/**
	 * mitsubachiAPIサーバに、「スクリプト一覧」リクエストを要求します。<br>
	 * @param request リクエスト
	 * @return レスポンス
	 * @throws RequestException リクエスト関係でエラーが発生した場合
	 * @throws BrokerException 通信でエラーが発生した場合
	 * @throws APICallException パラメータの指定等がおかしい場合
	 */
	public ScriptListResponse scriptList(ScriptListRequest request) throws RequestException, BrokerException, APICallException {
		
		JSONObject json = this.broker.request(request, this.ci);
		this.checkResponse(json);
		return new ScriptListResponse(json);
	}
	
	/**
	 * mitsubachiAPIサーバに、「スクリプト削除」リクエストを要求します。<br>
	 * @param request リクエスト
	 * @return レスポンス
	 * @throws RequestException リクエスト関係でエラーが発生した場合
	 * @throws BrokerException 通信でエラーが発生した場合
	 * @throws APICallException パラメータの指定等がおかしい場合
	 */
	public ScriptDeleteResponse scriptDelete(ScriptDeleteRequest request) throws RequestException, BrokerException, APICallException {
		
		JSONObject json = this.broker.request(request, this.ci);
		this.checkResponse(json);
		return new ScriptDeleteResponse(json);
	}
	
	/**
	 * mitsubachiAPIサーバに、「スクリプトデプロイ」リクエストを要求します。<br>
	 * @param request リクエスト
	 * @return レスポンス
	 * @throws RequestException リクエスト関係でエラーが発生した場合
	 * @throws BrokerException 通信でエラーが発生した場合
	 * @throws APICallException パラメータの指定等がおかしい場合
	 */
	public ScriptDeployResponse scriptDeploy(ScriptDeployRequest request) throws RequestException, BrokerException, APICallException {
		
		JSONObject json = this.broker.request(request, this.ci);
		this.checkResponse(json);
		return new ScriptDeployResponse(json);
	}
	
	/**
	 * mitsubachiAPIサーバに、「スクリプト取得」リクエストを要求します。<br>
	 * @param request リクエスト
	 * @return レスポンス
	 * @throws RequestException リクエスト関係でエラーが発生した場合
	 * @throws BrokerException 通信でエラーが発生した場合
	 * @throws APICallException パラメータの指定等がおかしい場合
	 */
	public ScriptGetResponse scriptGet(ScriptGetRequest request) throws RequestException, BrokerException, APICallException {
		
		JSONObject json = this.broker.request(request, this.ci);
		this.checkResponse(json);
		return new ScriptGetResponse(json);
	}
}
