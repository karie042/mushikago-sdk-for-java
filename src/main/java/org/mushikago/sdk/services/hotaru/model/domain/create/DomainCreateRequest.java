package org.mushikago.sdk.services.hotaru.model.domain.create;

import java.io.UnsupportedEncodingException;
import java.util.TreeMap;

import org.apache.http.client.methods.HttpRequestBase;
import org.mushikago.sdk.common.RequestException;
import org.mushikago.sdk.common.auth.MushikagoAuth;
import org.mushikago.sdk.common.util.ConnectInfo;
import org.mushikago.sdk.common.util.ParamUtils;
import org.mushikago.sdk.services.hotaru.model.domain.DomainRequest;

/**
 * ドメイン作成リクエスト。<br>
 * @author miningbrownie
 */
public class DomainCreateRequest extends DomainRequest {
  
  /**
   * テキストの解析種類一覧
   * @author miningbrownie
   *
   */
  public static enum Splitter {
    
    GRAM1("1gram"), GRAM2("2gram"), GRAM3("3gram"), GRAM4("4gram"), GRAM5("5gram"), GRAM6("6gram"), GRAM7("7gram"), GRAM8("8gram"), GRAM9("9gram"),
    MORPH("morph"),
    ENGLISH("english");
    
    private final String key;
    private Splitter(String key) { this.key = key; }
    @Override public String toString() { return this.key; }
  }
  
  /**
   * 操作名
   */
  public static final String OPERATION_NAME = "create";
  
  /**
   * リクエストパラメータ(domain_name)
   */
  public static final String PARAM_KEY_DOMAIN_NAME = "domain_name";
  
  /**
   * リクエストパラメータ(splitter)
   */
  public static final String PARAM_KEY_SPLITTER = "splitter";
  
  /**
   * リクエストパラメータ(description)
   */
  public static final String PARAM_KEY_DESCRIPTION = "description";
  
  /**
   * リクエストパラメータ(dictionary_ids)
   */
  public static final String PARAM_KEY_DICTIONARY_IDS = "dictionary_ids";
  
  /**
   * リクエストパラメータ(tags)
   */
  public static final String PARAM_KEY_TAGS = "tags";
  
  /**
   * リクエストパラメータ（ドメイン名）
   */
  private String domainName = null;
  
  /**
   * リクエストパラメータ（説明文）
   */
  private String description = null;
  
  /**
   * リクエストパラメータ（解析の種類）
   */
  private Splitter splitter = null;
  
  /**
   * リクエストパラメータ（辞書IDの一覧）
   */
  private String dictionaryIds = null;
  
  /**
   * リクエストパラメータ（タグの一覧）
   */
  private String tags = null;
  
  /**
   * デフォルトのパラメータを使用して、DomainCreateRequestオブジェクトを構築します。<br>
   */
  public DomainCreateRequest() {}
  
  /**
   * 指定されたドメイン名、解析種類を使用して、DomainCreateRequestオブジェクトを構築します。<br>
   * @param domainName ドメイン名
   * @param splitter 解析の種類
   */
  public DomainCreateRequest(String domainName, Splitter splitter) {
    this.domainName = domainName;
    this.splitter = splitter;
  }
  
  /**
   * 指定されたドメイン名、解析種類、説明文、辞書ID一覧、タグ一覧を使用して、DomainCreateRequestを構築します。<br>
   * @param domainName ドメイン名
   * @param splitter 解析の種類
   * @param description 説明文
   * @param dictionaryIds 辞書IDの一覧
   * @param tags タグの一覧
   */
  public DomainCreateRequest(String domainName, Splitter splitter, String description, String dictionaryIds, String tags) {
    this(domainName, splitter);
    this.description = description;
    this.dictionaryIds = dictionaryIds;
    this.tags = tags;
  }
  
  /**
   * ドメイン名を取得します。<br>
   * @return ドメイン名
   */
  public String getDomainName() { return this.domainName; }
  
  /**
   * 解析種類を取得します。<br>
   * @return 解析種類
   */
  public Splitter getSplitter() { return this.splitter; }
  
  /**
   * 説明文を取得します。<br>
   * @return 説明文
   */
  public String getDescription() { return this.description; }
  
  /**
   * 辞書IDの一覧を取得します。<br>
   * @return 辞書IDの一覧
   */
  public String getDictionaryIds() { return this.dictionaryIds; }
  
  /**
   * タグの一覧を取得します。<br>
   * @return タグの一覧
   */
  public String getTags() { return this.tags; }
  
  /**
   * ドメイン名を設定します。<br>
   * @param name ドメイン名
   */
  public void setDomainName(String name) { this.domainName = name; }
  
  /**
   * テキストの解析種類を設定します。<br>
   * @param splitter 解析種類
   */
  public void setSplitter(Splitter splitter) { this.splitter = splitter; }
  
  /**
   * 説明文を設定します。<br>
   * @param description 説明文
   */
  public void setDescription(String description) { this.description = description; }
  
  /**
   * 辞書IDの一覧を設定します。<br>
   * @param dictionaryIds 辞書IDの一覧
   */
  public void setDictionaryIds(String dictionaryIds) { this.dictionaryIds = dictionaryIds; }
  
  /**
   * タグの一覧を設定します。<br>
   * @param tags タグの一覧
   */
  public void setTags(String tags) { this.tags = tags; }
  
  @Override
  public HttpRequestBase toHttpMethod(MushikagoAuth auth, ConnectInfo ci) throws RequestException {
    
    try {
      TreeMap<String, String> requestParams = new TreeMap<String, String>();
      
      if(null == this.domainName) throw new RequestException("ドメイン名を指定してください");
      requestParams.put(PARAM_KEY_DOMAIN_NAME, ParamUtils.paramEncode(this.domainName));
      if(null == this.splitter) throw new RequestException("Splitterを指定してください");
      requestParams.put(PARAM_KEY_SPLITTER, ParamUtils.paramEncode(this.splitter.toString()));
      
      if(null != this.description) requestParams.put(PARAM_KEY_DESCRIPTION, ParamUtils.paramEncode(this.description));
      if(null != this.dictionaryIds) requestParams.put(PARAM_KEY_DICTIONARY_IDS, ParamUtils.paramEncode(this.dictionaryIds));
      if(null != this.tags) requestParams.put(PARAM_KEY_TAGS, ParamUtils.paramEncode(this.tags));
      
      return this.toHttpPostMethod(auth, ci, this.path(this.serviceName, this.categoryName, OPERATION_NAME), requestParams);
    }
    catch(UnsupportedEncodingException e) {
      throw new RequestException(e.getMessage());
    }
  }
}
