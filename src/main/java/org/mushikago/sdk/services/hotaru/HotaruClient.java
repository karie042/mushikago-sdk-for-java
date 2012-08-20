package org.mushikago.sdk.services.hotaru;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.mushikago.sdk.common.MushikagoClient;
import org.mushikago.sdk.common.RequestException;
import org.mushikago.sdk.common.auth.Credentials;
import org.mushikago.sdk.common.auth.UnauthorizedException;
import org.mushikago.sdk.common.broker.BrokerException;
import org.mushikago.sdk.common.exception.APICallException;
import org.mushikago.sdk.common.exception.BadRequestException;
import org.mushikago.sdk.common.exception.ConflictException;
import org.mushikago.sdk.common.exception.ForbiddenException;
import org.mushikago.sdk.common.exception.InternalErrorException;
import org.mushikago.sdk.common.exception.InternalServerErrorException;
import org.mushikago.sdk.common.exception.NotFoundException;
import org.mushikago.sdk.common.exception.RequestEntityTooLargeException;
import org.mushikago.sdk.common.exception.RequestURITooLongException;
import org.mushikago.sdk.common.exception.ServiceUnavailableException;
import org.mushikago.sdk.services.hotaru.model.classifier.judge.ClassifierJudgeRequest;
import org.mushikago.sdk.services.hotaru.model.classifier.judge.ClassifierJudgeResponse;
import org.mushikago.sdk.services.hotaru.model.collocation.create.CollocationCreateRequest;
import org.mushikago.sdk.services.hotaru.model.collocation.create.CollocationCreateResponse;
import org.mushikago.sdk.services.hotaru.model.collocation.delete.CollocationDeleteRequest;
import org.mushikago.sdk.services.hotaru.model.collocation.delete.CollocationDeleteResponse;
import org.mushikago.sdk.services.hotaru.model.collocation.download.CollocationDownloadRequest;
import org.mushikago.sdk.services.hotaru.model.collocation.download.CollocationDownloadResponse;
import org.mushikago.sdk.services.hotaru.model.collocation.get.CollocationGetRequest;
import org.mushikago.sdk.services.hotaru.model.collocation.get.CollocationGetResponse;
import org.mushikago.sdk.services.hotaru.model.collocation.list.CollocationListRequest;
import org.mushikago.sdk.services.hotaru.model.collocation.list.CollocationListResponse;
import org.mushikago.sdk.services.hotaru.model.collocation.wordlist.CollocationWordlistRequest;
import org.mushikago.sdk.services.hotaru.model.collocation.wordlist.CollocationWordlistResponse;
import org.mushikago.sdk.services.hotaru.model.dictionary.delete.DictionaryDeleteRequest;
import org.mushikago.sdk.services.hotaru.model.dictionary.delete.DictionaryDeleteResponse;
import org.mushikago.sdk.services.hotaru.model.dictionary.list.DictionaryListRequest;
import org.mushikago.sdk.services.hotaru.model.dictionary.list.DictionaryListResponse;
import org.mushikago.sdk.services.hotaru.model.dictionary.put.DictionaryPutRequest;
import org.mushikago.sdk.services.hotaru.model.dictionary.put.DictionaryPutResponse;
import org.mushikago.sdk.services.hotaru.model.domain.create.DomainCreateRequest;
import org.mushikago.sdk.services.hotaru.model.domain.create.DomainCreateResponse;
import org.mushikago.sdk.services.hotaru.model.domain.delete.DomainDeleteRequest;
import org.mushikago.sdk.services.hotaru.model.domain.delete.DomainDeleteResponse;
import org.mushikago.sdk.services.hotaru.model.domain.info.DomainInfoRequest;
import org.mushikago.sdk.services.hotaru.model.domain.info.DomainInfoResponse;
import org.mushikago.sdk.services.hotaru.model.domain.list.DomainListRequest;
import org.mushikago.sdk.services.hotaru.model.domain.list.DomainListResponse;
import org.mushikago.sdk.services.hotaru.model.tag.delete.TagDeleteRequest;
import org.mushikago.sdk.services.hotaru.model.tag.delete.TagDeleteResponse;
import org.mushikago.sdk.services.hotaru.model.tag.list.TagListRequest;
import org.mushikago.sdk.services.hotaru.model.tag.list.TagListResponse;
import org.mushikago.sdk.services.hotaru.model.tag.put.TagPutRequest;
import org.mushikago.sdk.services.hotaru.model.tag.put.TagPutResponse;
import org.mushikago.sdk.services.hotaru.model.text.delete.TextDeleteRequest;
import org.mushikago.sdk.services.hotaru.model.text.delete.TextDeleteResponse;
import org.mushikago.sdk.services.hotaru.model.text.get.TextGetRequest;
import org.mushikago.sdk.services.hotaru.model.text.get.TextGetResponse;
import org.mushikago.sdk.services.hotaru.model.text.list.TextListRequest;
import org.mushikago.sdk.services.hotaru.model.text.list.TextListResponse;
import org.mushikago.sdk.services.hotaru.model.text.put.TextPutRequest;
import org.mushikago.sdk.services.hotaru.model.text.put.TextPutResponse;
import org.mushikago.sdk.services.hotaru.model.text.tagset.TextTagsetRequest;
import org.mushikago.sdk.services.hotaru.model.text.tagset.TextTagsetResponse;
import org.mushikago.sdk.services.hotaru.model.word.get.WordGetRequest;
import org.mushikago.sdk.services.hotaru.model.word.get.WordGetResponse;
import org.mushikago.sdk.services.hotaru.model.word.list.WordListRequest;
import org.mushikago.sdk.services.hotaru.model.word.list.WordListResponse;

/**
 * hotaruAPIサーバと通信を行うクライアント。<br>
 * 各APIに対応したメソッドをカテゴリ名(text,domain,collocation・・・)から始まるメソッドとして提供します。<br>
 * <br>
 * メソッドには、HotaruRequestを継承した各リクエストを引数として受けとります。<br>
 * リクエストは、APIに対するパラメータを自身のフィールドとして保持し、それに設定する為、セッターメソッドを提供します。<br>
 * <br>
 * 通信が正常に終了すると、HotaruResponseを継承した各レスポンスが返却されます。<br>
 * レスポンスは、APIサーバから受け取ったパラメータ(JSON形式のデータ)を、自身のフィールドとして保持し、ゲッターメソッドを提供します。<br>
 * <br>
 * 通信が正常に終了しなかった、又は返却されたパラメータによっては、HotaruExceptionを継承した各例外が送出されます。<br>
 * 例外は、Exceptionクラスを基底クラスとし、詳細メッセージを保持します。<br>
 * @author miningbrownie
 *
 */
public class HotaruClient extends MushikagoClient {
  
  /**
   * 指定されたCredentialsを使用して、HotaruClientを構築します。<br>
   * @param credentials 認証用オブジェクト
   */
  public HotaruClient(Credentials credentials) {
    super(credentials, "api.mushikago.org");
  }
  
  /**
   * 
   * APIサーバから返ってきたレスポンスをチェックします。
   * 
   * @param json APIサーバから返ってきたレスポンス
   * @throws APICallException
   */
  protected void checkResponse(JSONObject json) throws APICallException {
    
    try {
      
      final int status = json.getJSONObject("meta").getInt("status");
      switch(status) {
      case 400: throw new BadRequestException(json);
      case 401: throw new UnauthorizedException(json);
      case 403: throw new ForbiddenException(json);
      case 404: throw new NotFoundException(json);
      case 409: throw new ConflictException(json);
      case 413: throw new RequestEntityTooLargeException(json);
      case 414: throw new RequestURITooLongException(json);
      case 500: throw new InternalErrorException(json);
      case 503: throw new ServiceUnavailableException(json);
      }
    }
    catch(JSONException e) {
      JSONObject detail = new JSONObject();
      detail.put("error", json.toString());
      throw new InternalServerErrorException(detail);
    }
  }
  
  /**
   * ドメインの一覧を取得します。<br>
   * @param request リクエスト
   * @return レスポンス
   * @throws RequestException
   * @throws BrokerException
   * @throws APICallException
   */
  public DomainListResponse domainList(DomainListRequest request) throws RequestException, BrokerException, APICallException {
    JSONObject json = this.broker.request(request, this.ci);
    this.checkResponse(json);
    return new DomainListResponse(json);
  }
  
  /**
   * ドメインを作成します。<br>
   * @param request リクエスト
   * @return レスポンス
   * @throws RequestException
   * @throws BrokerException
   * @throws APICallException
   */
  public DomainCreateResponse domainCreate(DomainCreateRequest request) throws RequestException, BrokerException, APICallException {
    JSONObject json = this.broker.request(request, this.ci);
    this.checkResponse(json);
    return new DomainCreateResponse(json);
  }
  
  /**
   * ドメインを削除します。<br>
   * @param request リクエスト
   * @return レスポンス
   * @throws RequestException
   * @throws BrokerException
   * @throws APICallException
   */
  public DomainDeleteResponse domainDelete(DomainDeleteRequest request) throws RequestException, BrokerException, APICallException {
    JSONObject json = this.broker.request(request, this.ci);
    this.checkResponse(json);
    return new DomainDeleteResponse(json);
  }
  
  /**
   * ドメインの詳細情報を取得します。<br>
   * @param request リクエスト
   * @return レスポンス
   * @throws RequestException
   * @throws BrokerException
   * @throws APICallException
   */
  public DomainInfoResponse domainInfo(DomainInfoRequest request) throws RequestException, BrokerException, APICallException {
    JSONObject json = this.broker.request(request, this.ci);
    this.checkResponse(json);
    return new DomainInfoResponse(json);
  }
  
  /**
   * テキストの一覧を取得します。<br>
   * @param request リクエスト
   * @return レスポンス
   * @throws RequestException
   * @throws BrokerException
   * @throws APICallException
   */
  public TextListResponse textList(TextListRequest request) throws RequestException, BrokerException, APICallException {
    JSONObject json = this.broker.request(request, this.ci);
    this.checkResponse(json);
    return new TextListResponse(json);
  }
  
  /**
   * テキストを登録します。<br>
   * @param request リクエスト
   * @return レスポンス
   * @throws RequestException
   * @throws BrokerException
   * @throws APICallException
   */
  public TextPutResponse textPut(TextPutRequest request) throws RequestException, BrokerException, APICallException {
    JSONObject json = this.broker.request(request, this.ci);
    this.checkResponse(json);
    return new TextPutResponse(json);
  }
  
  /**
   * タグの一覧を取得します。<br>
   * @param request リクエスト
   * @return レスポンス
   * @throws RequestException
   * @throws BrokerException
   * @throws APICallException
   */
  public TagListResponse tagList(TagListRequest request) throws RequestException, BrokerException, APICallException {
    JSONObject json = this.broker.request(request, this.ci);
    this.checkResponse(json);
    return new TagListResponse(json);
  }
  
  /**
   * タグを登録します。<br>
   * @param request リクエスト
   * @return レスポンス
   * @throws RequestException
   * @throws BrokerException
   * @throws APICallException
   */
  public TagPutResponse tagPut(TagPutRequest request) throws RequestException, BrokerException, APICallException {
    JSONObject json = this.broker.request(request, this.ci);
    this.checkResponse(json);
    return new TagPutResponse(json);
  }
  
  /**
   * タグを削除します。<br>
   * @param request リクエスト
   * @return レスポンス
   * @throws RequestException
   * @throws BrokerException
   * @throws APICallException
   */
  public TagDeleteResponse tagDelete(TagDeleteRequest request) throws RequestException, BrokerException, APICallException {
    JSONObject json = this.broker.request(request, this.ci);
    this.checkResponse(json);
    return new TagDeleteResponse(json);
  }
  
  /**
   * テキストを削除します。<br>
   * @param request リクエスト
   * @return レスポンス
   * @throws RequestException
   * @throws BrokerException
   * @throws APICallException
   */
  public TextDeleteResponse textDelete(TextDeleteRequest request) throws RequestException, BrokerException, APICallException {
    JSONObject json = this.broker.request(request, this.ci);
    this.checkResponse(json);
    return new TextDeleteResponse(json);
  }
  
  /**
   * テキストを取得します。<br>
   * @param request リクエスト
   * @return レスポンス
   * @throws RequestException
   * @throws BrokerException
   * @throws APICallException
   */
  public TextGetResponse textGet(TextGetRequest request) throws RequestException, BrokerException, APICallException {
    JSONObject json = this.broker.request(request, this.ci);
    this.checkResponse(json);
    return new TextGetResponse(json);
  }
  
  /**
   * タグの追加・変更をします。<br>
   * @param request リクエスト
   * @return レスポンス
   * @throws RequestException
   * @throws BrokerException
   * @throws APICallException
   */
  public TextTagsetResponse textTagset(TextTagsetRequest request) throws RequestException, BrokerException, APICallException {
    JSONObject json = this.broker.request(request, this.ci);
    this.checkResponse(json);
    return new TextTagsetResponse(json);
  }
  
  /**
   * 辞書の一覧を取得します。<br>
   * @param request リクエスト
   * @return レスポンス
   * @throws RequestException
   * @throws BrokerException
   * @throws APICallException
   */
  public DictionaryListResponse dictionaryList(DictionaryListRequest request) throws RequestException, BrokerException, APICallException {
    JSONObject json = this.broker.request(request, this.ci);
    this.checkResponse(json);
    return new DictionaryListResponse(json);
  }
  
  /**
   * 辞書の登録をします。<br>
   * @param request リクエスト
   * @return レスポンス
   * @throws RequestException
   * @throws BrokerException
   * @throws APICallException
   */
  public DictionaryPutResponse dictionaryPut(DictionaryPutRequest request) throws RequestException, BrokerException, APICallException {
    JSONObject json = this.broker.request(request, this.ci);
    this.checkResponse(json);
    return new DictionaryPutResponse(json);
  }
  
  /**
   * 辞書を削除します。<br>
   * @param request リクエスト
   * @return レスポンス
   * @throws RequestException
   * @throws BrokerException
   * @throws APICallException
   */
  public DictionaryDeleteResponse dictionaryDelete(DictionaryDeleteRequest request) throws RequestException, BrokerException, APICallException {
    JSONObject json = this.broker.request(request, this.ci);
    this.checkResponse(json);
    return new DictionaryDeleteResponse(json);
  }
  
  /**
   * 指定されたテキストより、ナイーブベイズ分類器スコア付けされたタグのリストを取得します。<br>
   * @param request リクエスト
   * @return レスポンス
   * @throws RequestException
   * @throws BrokerException
   * @throws APICallException
   */
  public ClassifierJudgeResponse classifierJudge(ClassifierJudgeRequest request) throws RequestException, BrokerException, APICallException {
    JSONObject json = this.broker.request(request, this.ci);
    this.checkResponse(json);
    return new ClassifierJudgeResponse(json);
  }
  
  /**
   * 共起グラフの一覧を取得します。<br>
   * @param request リクエスト
   * @return レスポンス
   * @throws RequestException
   * @throws BrokerException
   * @throws APICallException
   */
  public CollocationListResponse collocationList(CollocationListRequest request) throws RequestException, BrokerException, APICallException {
    JSONObject json = this.broker.request(request, this.ci);
    this.checkResponse(json);
    return new CollocationListResponse(json);
  }
  
  /**
   * 共起グラフを作成します。<br>
   * @param request リクエスト
   * @return レスポンス
   * @throws RequestException
   * @throws BrokerException
   * @throws APICallException
   */
  public CollocationCreateResponse collocationCreate(CollocationCreateRequest request) throws RequestException, BrokerException, APICallException {
    JSONObject json = this.broker.request(request, this.ci);
    this.checkResponse(json);
    return new CollocationCreateResponse(json);
  }
  
  /**
   * 共起グラフから、指定した単語と相関の強い単語を取得します。<br>
   * @param request リクエスト
   * @return レスポンス
   * @throws RequestException
   * @throws BrokerException
   * @throws APICallException
   */
  public CollocationGetResponse collocationGet(CollocationGetRequest request) throws RequestException, BrokerException, APICallException {
    JSONObject json = this.broker.request(request, this.ci);
    this.checkResponse(json);
    return new CollocationGetResponse(json);
  }
  
  /**
   * 共起グラフをダウンロードできるURLを取得します。<br>
   * @param request リクエスト
   * @return レスポンス
   * @throws RequestException
   * @throws BrokerException
   * @throws APICallException
   */
  public CollocationDownloadResponse collocationDownload(CollocationDownloadRequest request) throws RequestException, BrokerException, APICallException {
    JSONObject json = this.broker.request(request, this.ci);
    this.checkResponse(json);
    return new CollocationDownloadResponse(json);
  }
  
  /**
   * 共起グラフを削除します。<br>
   * @param request リクエスト
   * @return レスポンス
   * @throws RequestException
   * @throws BrokerException
   * @throws APICallException
   */
  public CollocationDeleteResponse collocationDelete(CollocationDeleteRequest request) throws RequestException, BrokerException, APICallException {
    JSONObject json = this.broker.request(request, this.ci);
    this.checkResponse(json);
    return new CollocationDeleteResponse(json);
  }
  
  /**
   * 共起グラフ内の単語一覧を取得します。<br>
   * @param request リクエスト
   * @return レスポンス
   * @throws APICallException
   * @throws RequestException
   * @throws BrokerException
   */
  public CollocationWordlistResponse collocationWordlist(CollocationWordlistRequest request) throws APICallException, RequestException, BrokerException {
    JSONObject json = this.broker.request(request, this.ci);
    this.checkResponse(json);
    return new CollocationWordlistResponse(json);
  }
  
  /**
   * 単語一覧を取得します。<br>
   * @param request リクエスト
   * @return レスポンス
   * @throws RequestException
   * @throws BrokerException
   * @throws APICallException
   */
  public WordListResponse wordList(WordListRequest request) throws RequestException, BrokerException, APICallException {
    JSONObject json = this.broker.request(request, this.ci);
    this.checkResponse(json);
    return new WordListResponse(json);
  }
  
  /**
   * 単語の詳細な情報を取得します。<br>
   * @param request リクエスト
   * @return レスポンス
   * @throws RequestException
   * @throws BrokerException
   * @throws APICallException
   */
  public WordGetResponse wordGet(WordGetRequest request) throws RequestException, BrokerException, APICallException {
    JSONObject json = this.broker.request(request, this.ci);
    this.checkResponse(json);
    return new WordGetResponse(json);
  }
}
