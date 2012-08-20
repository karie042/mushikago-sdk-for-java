package org.mushikago.sdk.services.hanamgri;

import net.sf.json.JSONObject;

import org.mushikago.sdk.common.MushikagoClient;
import org.mushikago.sdk.common.RequestException;
import org.mushikago.sdk.common.auth.Credentials;
import org.mushikago.sdk.common.broker.BrokerException;
import org.mushikago.sdk.common.exception.APICallException;
import org.mushikago.sdk.services.hanamgri.model.CreateDomainRequest;
import org.mushikago.sdk.services.hanamgri.model.CreateDomainResponse;
import org.mushikago.sdk.services.hanamgri.model.DeleteDictionaryRequest;
import org.mushikago.sdk.services.hanamgri.model.DeleteDictionaryResponse;
import org.mushikago.sdk.services.hanamgri.model.DeleteDomainRequest;
import org.mushikago.sdk.services.hanamgri.model.DeleteDomainResponse;
import org.mushikago.sdk.services.hanamgri.model.DeleteKnowledgeRequest;
import org.mushikago.sdk.services.hanamgri.model.DeleteKnowledgeResponse;
import org.mushikago.sdk.services.hanamgri.model.GetAnalysisRequest;
import org.mushikago.sdk.services.hanamgri.model.GetAnalysisResponse;
import org.mushikago.sdk.services.hanamgri.model.GetInformationRequest;
import org.mushikago.sdk.services.hanamgri.model.GetInformationResponse;
import org.mushikago.sdk.services.hanamgri.model.GetQueueSizeRequest;
import org.mushikago.sdk.services.hanamgri.model.GetQueueSizeResponse;
import org.mushikago.sdk.services.hanamgri.model.ListAnalysesRequest;
import org.mushikago.sdk.services.hanamgri.model.ListAnalysesResponse;
import org.mushikago.sdk.services.hanamgri.model.ListDictionariesRequest;
import org.mushikago.sdk.services.hanamgri.model.ListDictionariesResponse;
import org.mushikago.sdk.services.hanamgri.model.ListDomainsRequest;
import org.mushikago.sdk.services.hanamgri.model.ListDomainsResponse;
import org.mushikago.sdk.services.hanamgri.model.ListKnowledgesRequest;
import org.mushikago.sdk.services.hanamgri.model.ListKnowledgesResponse;
import org.mushikago.sdk.services.hanamgri.model.RequestAnalysisRequest;
import org.mushikago.sdk.services.hanamgri.model.RequestAnalysisResponse;
import org.mushikago.sdk.services.hanamgri.model.SaveDictionaryRequest;
import org.mushikago.sdk.services.hanamgri.model.SaveDictionaryResponse;
import org.mushikago.sdk.services.hanamgri.model.SaveKnowledgeRequest;
import org.mushikago.sdk.services.hanamgri.model.SaveKnowledgeResponse;
import org.mushikago.sdk.services.hanamgri.model.Schema;
import org.mushikago.sdk.services.hanamgri.model.SearchSchemaRequest;
import org.mushikago.sdk.services.hanamgri.model.SearchSchemaResponse;
import org.mushikago.sdk.services.hanamgri.model.TrainRequest;
import org.mushikago.sdk.services.hanamgri.model.TrainResponse;
import org.mushikago.sdk.services.hanamgri.model.TrainingData;
import org.mushikago.sdk.services.hanamgri.model.UpdateDomainRequest;
import org.mushikago.sdk.services.hanamgri.model.UpdateDomainResponse;

public class HanamgriClient extends MushikagoClient {
  private static final String DEFAULT_ENDPOINT = "api.mushikago.org";
  private static final boolean DEFAULT_USE_SSL = true;

  /**
   * デフォルトのエンドポイントを指定してHanamgriClientを作成します。
   * 
   * @param credentials
   */
  public HanamgriClient(Credentials credentials) {
    this(credentials, DEFAULT_ENDPOINT);
  }

  /**
   * エンドポイントを指定してHanamgriClientを作成します。
   * 
   * @param credentials
   * @param endpoint
   *          &lt;host&gt;[:&lt;port&gt;]
   */
  public HanamgriClient(Credentials credentials, String endpoint) {
    this(credentials, endpoint, DEFAULT_USE_SSL);
  }

  /**
   * エンドポイントとSSLの使用／不使用を指定してHanamgriClientを作成します。
   * 
   * @param credentials
   * @param endpoint
   *          &lt;host&gt;[:&lt;port&gt;]
   * @param useSsl
   *          SSLを使用する場合true
   */
  public HanamgriClient(Credentials credentials, String endpoint, boolean useSsl) {
    super(credentials, createConnectInfo(endpoint, useSsl));
  }

  /**
   * CreateDomainリクエストを要求します。<br>
   * 
   * @param request
   *          リクエスト
   * @return レスポンス
   * @throws RequestException
   *           リクエスト関係でエラーが発生した場合
   * @throws BrokerException
   *           通信でエラーが発生した場合
   * @throws APICallException
   *           パラメータの指定等がおかしい場合
   */
  public CreateDomainResponse createDomain(CreateDomainRequest request)
      throws RequestException, BrokerException, APICallException {
    JSONObject json = sendRequest(request);
    return new CreateDomainResponse(json);
  }

  /**
   * CreateDomainリクエストを要求します。<br>
   * 
   * @param domainName
   * @param seeds
   * @param schema
   * @return レスポンス
   * @throws RequestException
   *           リクエスト関係でエラーが発生した場合
   * @throws BrokerException
   *           通信でエラーが発生した場合
   * @throws APICallException
   *           パラメータの指定等がおかしい場合
   */
  public CreateDomainResponse createDomain(String domainName, String seeds, Schema schema)
      throws RequestException, BrokerException, APICallException {
    return createDomain(new CreateDomainRequest(domainName, seeds, schema));
  }

  /**
   * DeleteDomainリクエストを要求します。<br>
   * 
   * @param request
   *          リクエスト
   * @return レスポンス
   * @throws RequestException
   *           リクエスト関係でエラーが発生した場合
   * @throws BrokerException
   *           通信でエラーが発生した場合
   * @throws APICallException
   *           パラメータの指定等がおかしい場合
   */
  public DeleteDomainResponse deleteDomain(DeleteDomainRequest request)
      throws RequestException, BrokerException, APICallException {
    JSONObject json = sendRequest(request);
    return new DeleteDomainResponse(json);
  }

  /**
   * DeleteDomainリクエストを要求します。<br>
   * 
   * @param domainName
   * @return レスポンス
   * @throws RequestException
   *           リクエスト関係でエラーが発生した場合
   * @throws BrokerException
   *           通信でエラーが発生した場合
   * @throws APICallException
   *           パラメータの指定等がおかしい場合
   */
  public DeleteDomainResponse deleteDomain(String domainName)
      throws RequestException, BrokerException, APICallException {
    return deleteDomain(new DeleteDomainRequest(domainName));
  }

  /**
   * ListDomainsリクエストを要求します。<br>
   * 
   * @return レスポンス
   * @throws RequestException
   *           リクエスト関係でエラーが発生した場合
   * @throws BrokerException
   *           通信でエラーが発生した場合
   * @throws APICallException
   *           パラメータの指定等がおかしい場合
   */
  public ListDomainsResponse listDomains()
      throws RequestException, BrokerException, APICallException {
    return listDomains(new ListDomainsRequest());
  }

  /**
   * ListDomainsリクエストを要求します。<br>
   * 
   * @param request
   *          リクエスト
   * @return レスポンス
   * @throws RequestException
   *           リクエスト関係でエラーが発生した場合
   * @throws BrokerException
   *           通信でエラーが発生した場合
   * @throws APICallException
   *           パラメータの指定等がおかしい場合
   */
  public ListDomainsResponse listDomains(ListDomainsRequest request)
      throws RequestException, BrokerException, APICallException {
    JSONObject json = sendRequest(request);
    return new ListDomainsResponse(json);
  }

  /**
   * RequestAnalysisリクエストを要求します。<br>
   * 
   * @param request
   *          リクエスト
   * @return レスポンス
   * @throws RequestException
   *           リクエスト関係でエラーが発生した場合
   * @throws BrokerException
   *           通信でエラーが発生した場合
   * @throws APICallException
   *           パラメータの指定等がおかしい場合
   */
  public RequestAnalysisResponse requestAnalysis(RequestAnalysisRequest request)
      throws RequestException, BrokerException, APICallException {
    JSONObject json = sendRequest(request);
    return new RequestAnalysisResponse(json);
  }

  /**
   * RequestAnalysisリクエストを要求します。<br>
   * 
   * @param domainName
   * @param url
   * @return レスポンス
   * @throws RequestException
   *           リクエスト関係でエラーが発生した場合
   * @throws BrokerException
   *           通信でエラーが発生した場合
   * @throws APICallException
   *           パラメータの指定等がおかしい場合
   */
  public RequestAnalysisResponse requestAnalysis(String domainName, String url)
      throws RequestException, BrokerException, APICallException {
    return requestAnalysis(new RequestAnalysisRequest(domainName, url));
  }

  /**
   * GetAnalysisリクエストを要求します。<br>
   * 
   * @param request
   *          リクエスト
   * @return レスポンス
   * @throws RequestException
   *           リクエスト関係でエラーが発生した場合
   * @throws BrokerException
   *           通信でエラーが発生した場合
   * @throws APICallException
   *           パラメータの指定等がおかしい場合
   */
  public GetAnalysisResponse getAnalysis(GetAnalysisRequest request)
      throws RequestException, BrokerException, APICallException {
    JSONObject json = sendRequest(request);
    return new GetAnalysisResponse(json);
  }

  /**
   * GetAnalysisリクエストを要求します。<br>
   * 
   * @param domainName
   * @param requestId
   * @return レスポンス
   * @throws RequestException
   *           リクエスト関係でエラーが発生した場合
   * @throws BrokerException
   *           通信でエラーが発生した場合
   * @throws APICallException
   *           パラメータの指定等がおかしい場合
   */
  public GetAnalysisResponse getAnalysis(String domainName, String requestId)
      throws RequestException, BrokerException, APICallException {
    return getAnalysis(new GetAnalysisRequest(domainName, requestId));
  }

  /**
   * GetQueueSizeリクエストを要求します。<br>
   * 
   * @param request
   *          リクエスト
   * @return レスポンス
   * @throws RequestException
   *           リクエスト関係でエラーが発生した場合
   * @throws BrokerException
   *           通信でエラーが発生した場合
   * @throws APICallException
   *           パラメータの指定等がおかしい場合
   */
  public GetQueueSizeResponse getQueueSize(GetQueueSizeRequest request)
      throws RequestException, BrokerException, APICallException {
    JSONObject json = sendRequest(request);
    return new GetQueueSizeResponse(json);
  }

  /**
   * GetQueueSizeリクエストを要求します。<br>
   * 
   * @param domainName
   * @return レスポンス
   * @throws RequestException
   *           リクエスト関係でエラーが発生した場合
   * @throws BrokerException
   *           通信でエラーが発生した場合
   * @throws APICallException
   *           パラメータの指定等がおかしい場合
   */
  public GetQueueSizeResponse getQueueSize(String domainName)
      throws RequestException, BrokerException, APICallException {
    return getQueueSize(new GetQueueSizeRequest(domainName));
  }

  /**
   * GetInformationリクエストを要求します。<br>
   * 
   * @param request
   *          リクエスト
   * @return レスポンス
   * @throws RequestException
   *           リクエスト関係でエラーが発生した場合
   * @throws BrokerException
   *           通信でエラーが発生した場合
   * @throws APICallException
   *           パラメータの指定等がおかしい場合
   */
  public GetInformationResponse getInformation(GetInformationRequest request)
      throws RequestException, BrokerException, APICallException {
    JSONObject json = sendRequest(request);
    return new GetInformationResponse(json);
  }

  /**
   * GetInformationリクエストを要求します。<br>
   * 
   * @param domainName
   * @return レスポンス
   * @throws RequestException
   *           リクエスト関係でエラーが発生した場合
   * @throws BrokerException
   *           通信でエラーが発生した場合
   * @throws APICallException
   *           パラメータの指定等がおかしい場合
   */
  public GetInformationResponse getInformation(String domainName)
      throws RequestException, BrokerException, APICallException {
    return getInformation(new GetInformationRequest(domainName));
  }

  /**
   * UpdateDomainリクエストを要求します。<br>
   * 
   * @param request
   *          リクエスト
   * @return レスポンス
   * @throws RequestException
   *           リクエスト関係でエラーが発生した場合
   * @throws BrokerException
   *           通信でエラーが発生した場合
   * @throws APICallException
   *           パラメータの指定等がおかしい場合
   */
  public UpdateDomainResponse updateDomain(UpdateDomainRequest request)
      throws RequestException, BrokerException, APICallException {
    JSONObject json = sendRequest(request);
    return new UpdateDomainResponse(json);
  }

  /**
   * UpdateDomainリクエストを要求します。<br>
   * 
   * @param domainName
   * @param description
   * @return レスポンス
   * @throws RequestException
   *           リクエスト関係でエラーが発生した場合
   * @throws BrokerException
   *           通信でエラーが発生した場合
   * @throws APICallException
   *           パラメータの指定等がおかしい場合
   */
  public UpdateDomainResponse updateDomain(String domainName, String description)
      throws RequestException, BrokerException, APICallException {
    return updateDomain(new UpdateDomainRequest(domainName, description));
  }

  /**
   * ListAnalysesリクエストを要求します。<br>
   * 
   * @param domainName
   * @return レスポンス
   * @throws RequestException
   *           リクエスト関係でエラーが発生した場合
   * @throws BrokerException
   *           通信でエラーが発生した場合
   * @throws APICallException
   *           パラメータの指定等がおかしい場合
   */
  public ListAnalysesResponse listAnalyses(String domainName)
      throws RequestException, BrokerException, APICallException {
    return listAnalyses(new ListAnalysesRequest(domainName));
  }

  /**
   * ListAnalysesリクエストを要求します。<br>
   * 
   * @param request
   *          リクエスト
   * @return レスポンス
   * @throws RequestException
   *           リクエスト関係でエラーが発生した場合
   * @throws BrokerException
   *           通信でエラーが発生した場合
   * @throws APICallException
   *           パラメータの指定等がおかしい場合
   */
  public ListAnalysesResponse listAnalyses(ListAnalysesRequest request)
      throws RequestException, BrokerException, APICallException {
    JSONObject json = sendRequest(request);
    return new ListAnalysesResponse(json);
  }

  /**
   * SearchSchemaリクエストを要求します。<br>
   * 
   * @param request
   *          リクエスト
   * @return レスポンス
   * @throws RequestException
   *           リクエスト関係でエラーが発生した場合
   * @throws BrokerException
   *           通信でエラーが発生した場合
   * @throws APICallException
   *           パラメータの指定等がおかしい場合
   */
  public SearchSchemaResponse searchSchema(SearchSchemaRequest request)
      throws RequestException, BrokerException, APICallException {
    JSONObject json = sendRequest(request);
    return new SearchSchemaResponse(json);
  }

  /**
   * SearchSchemaリクエストを要求します。<br>
   * 
   * @param domainName
   * @param queryKey
   * @param query_value
   * @return レスポンス
   * @throws RequestException
   *           リクエスト関係でエラーが発生した場合
   * @throws BrokerException
   *           通信でエラーが発生した場合
   * @throws APICallException
   *           パラメータの指定等がおかしい場合
   */
  public SearchSchemaResponse searchSchema(String domainName, String queryKey, String queryValue)
      throws RequestException, BrokerException, APICallException {
    return searchSchema(new SearchSchemaRequest(domainName, queryKey, queryValue));
  }

  /**
   * Trainリクエストを要求します。<br>
   * 
   * @param request
   *          リクエスト
   * @return レスポンス
   * @throws RequestException
   *           リクエスト関係でエラーが発生した場合
   * @throws BrokerException
   *           通信でエラーが発生した場合
   * @throws APICallException
   *           パラメータの指定等がおかしい場合
   */
  public TrainResponse train(TrainRequest request)
      throws RequestException, BrokerException, APICallException {
    JSONObject json = sendRequest(request);
    return new TrainResponse(json);
  }

  /**
   * Trainリクエストを要求します。<br>
   * 
   * @param domainName
   * @param urlOrHtml
   * @param trainingData
   * @return レスポンス
   * @throws RequestException
   *           リクエスト関係でエラーが発生した場合
   * @throws BrokerException
   *           通信でエラーが発生した場合
   * @throws APICallException
   *           パラメータの指定等がおかしい場合
   */
  public TrainResponse train(String domainName, String urlOrHtml, TrainingData trainingData)
      throws RequestException, BrokerException, APICallException {
    return train(new TrainRequest(domainName, urlOrHtml, trainingData));
  }

  /**
   * ListDictionariesリクエストを要求します。<br>
   * 
   * @return レスポンス
   * @throws RequestException
   *           リクエスト関係でエラーが発生した場合
   * @throws BrokerException
   *           通信でエラーが発生した場合
   * @throws APICallException
   *           パラメータの指定等がおかしい場合
   */
  public ListDictionariesResponse listDictionaries()
      throws RequestException, BrokerException, APICallException {
    return listDictionaries(new ListDictionariesRequest());
  }

  /**
   * ListDictionariesリクエストを要求します。<br>
   * 
   * @param request
   *           リクエスト
   * @return レスポンス
   * @throws RequestException
   *           リクエスト関係でエラーが発生した場合
   * @throws BrokerException
   *           通信でエラーが発生した場合
   * @throws APICallException
   *           パラメータの指定等がおかしい場合
   */
  public ListDictionariesResponse listDictionaries(ListDictionariesRequest request)
      throws RequestException, BrokerException, APICallException {
    JSONObject json = sendRequest(request);
    return new ListDictionariesResponse(json);
  }

  /**
   * SaveDictionaryリクエストを要求します。<br>
   * 
   * @param request
   *          リクエスト
   * @return レスポンス
   * @throws RequestException
   *           リクエスト関係でエラーが発生した場合
   * @throws BrokerException
   *           通信でエラーが発生した場合
   * @throws APICallException
   *           パラメータの指定等がおかしい場合
   */
  public SaveDictionaryResponse saveDictionary(SaveDictionaryRequest request)
      throws RequestException, BrokerException, APICallException {
    JSONObject json = sendRequest(request);
    return new SaveDictionaryResponse(json);
  }

  /**
   * SaveDictionaryリクエストを要求します。<br>
   * 
   * @param domainName
   * @return レスポンス
   * @throws RequestException
   *           リクエスト関係でエラーが発生した場合
   * @throws BrokerException
   *           通信でエラーが発生した場合
   * @throws APICallException
   *           パラメータの指定等がおかしい場合
   */
  public SaveDictionaryResponse saveDictionary(String domainName)
      throws RequestException, BrokerException, APICallException {
    return saveDictionary(new SaveDictionaryRequest(domainName));
  }
  
  /**
   * DeleteDictionaryリクエストを要求します。<br>
   * 
   * @param request
   *          リクエスト
   * @return レスポンス
   * @throws RequestException
   *           リクエスト関係でエラーが発生した場合
   * @throws BrokerException
   *           通信でエラーが発生した場合
   * @throws APICallException
   *           パラメータの指定等がおかしい場合
   */
  public DeleteDictionaryResponse deleteDictionary(DeleteDictionaryRequest request)
      throws RequestException, BrokerException, APICallException {
    JSONObject json = sendRequest(request);
    return new DeleteDictionaryResponse(json);
  }
  
  /**
   * DeleteDictionaryリクエストを要求します。<br>
   * 
   * @param dictionaryName
   * @return レスポンス
   * @throws RequestException
   *           リクエスト関係でエラーが発生した場合
   * @throws BrokerException
   *           通信でエラーが発生した場合
   * @throws APICallException
   *           パラメータの指定等がおかしい場合
   */
  public DeleteDictionaryResponse deleteDictionary(String dictionaryName)
      throws RequestException, BrokerException, APICallException {
    return deleteDictionary(new DeleteDictionaryRequest(dictionaryName));
  }

  /**
   * SaveKnowledgeリクエストを要求します。<br>
   * 
   * @param request
   *          リクエスト
   * @return レスポンス
   * @throws RequestException
   *           リクエスト関係でエラーが発生した場合
   * @throws BrokerException
   *           通信でエラーが発生した場合
   * @throws APICallException
   *           パラメータの指定等がおかしい場合
   */
  public SaveKnowledgeResponse saveKnowledge(SaveKnowledgeRequest request)
      throws RequestException, BrokerException, APICallException {
    JSONObject json = sendRequest(request);
    return new SaveKnowledgeResponse(json);
  }

  /**
   * SaveKnowledgeリクエストを要求します。<br>
   * 
   * @param domainName
   * @param fieldName
   * @return レスポンス
   * @throws RequestException
   *           リクエスト関係でエラーが発生した場合
   * @throws BrokerException
   *           通信でエラーが発生した場合
   * @throws APICallException
   *           パラメータの指定等がおかしい場合
   */
  public SaveKnowledgeResponse saveKnowledge(String domainName, String fieldName)
      throws RequestException, BrokerException, APICallException {
    return saveKnowledge(new SaveKnowledgeRequest(domainName, fieldName));
  }
  
  /**
   * ListKnowledgesリクエストを要求します。<br>
   * 
   * @param request
   *          リクエスト
   * @return レスポンス
   * @throws RequestException
   *           リクエスト関係でエラーが発生した場合
   * @throws BrokerException
   *           通信でエラーが発生した場合
   * @throws APICallException
   *           パラメータの指定等がおかしい場合
   */
  public ListKnowledgesResponse listKnowledges(ListKnowledgesRequest request)
      throws RequestException, BrokerException, APICallException {
    JSONObject json = sendRequest(request);
    return new ListKnowledgesResponse(json);
  }
  
  /**
   * ListKnowledgesリクエストを要求します。<br>
   * 
   * @return レスポンス
   * @throws RequestException
   *           リクエスト関係でエラーが発生した場合
   * @throws BrokerException
   *           通信でエラーが発生した場合
   * @throws APICallException
   *           パラメータの指定等がおかしい場合
   */
  public ListKnowledgesResponse listKnowledges()
      throws RequestException, BrokerException, APICallException {
    return listKnowledges(new ListKnowledgesRequest());
  }
  
  /**
   * DeleteKnowledgeリクエストを要求します。<br>
   * 
   * @param request
   *          リクエスト
   * @return レスポンス
   * @throws RequestException
   *           リクエスト関係でエラーが発生した場合
   * @throws BrokerException
   *           通信でエラーが発生した場合
   * @throws APICallException
   *           パラメータの指定等がおかしい場合
   */
  public DeleteKnowledgeResponse deleteKnowledge(DeleteKnowledgeRequest request)
      throws RequestException, BrokerException, APICallException {
    JSONObject json = sendRequest(request);
    return new DeleteKnowledgeResponse(json);
  }
  
  /**
   * DeleteKnowledgeリクエストを要求します。<br>
   * 
   * @param knowledgeName
   * @return レスポンス
   * @throws RequestException
   *           リクエスト関係でエラーが発生した場合
   * @throws BrokerException
   *           通信でエラーが発生した場合
   * @throws APICallException
   *           パラメータの指定等がおかしい場合
   */
  public DeleteKnowledgeResponse deleteKnowledge(String knowledgeName)
      throws RequestException, BrokerException, APICallException {
    return deleteKnowledge(new DeleteKnowledgeRequest(knowledgeName));
  }
}
