package org.mushikago.sdk.services.hotaru.model.collocation.create;

import java.io.UnsupportedEncodingException;
import java.util.TreeMap;

import org.apache.http.client.methods.HttpRequestBase;
import org.mushikago.sdk.common.RequestException;
import org.mushikago.sdk.common.auth.MushikagoAuth;
import org.mushikago.sdk.common.util.ConnectInfo;
import org.mushikago.sdk.common.util.ParamUtils;
import org.mushikago.sdk.services.hotaru.model.collocation.CollocationRequest;

/**
 * 共起グラフ作成リクエスト。<br>
 * @author miningbrownie
 */
public class CollocationCreateRequest extends CollocationRequest {
  
  /**
   * 品詞の指定する際に使用できる定数です。<br>
   * SMALL("名詞,動詞,形容詞")<br>
   * MEDIUM("名詞,動詞,形容詞,助動詞,感動詞,副詞,接頭詞")<br>
   * LARGE("名詞,動詞,形容詞,助動詞,感動詞,副詞,接頭詞,連体詞,接続詞,助詞,未知語")
   * @author miningbrownie
   *
   */
  public static enum PartOfSpeech {
    
    SMALL("名詞,動詞,形容詞"),
    MEDIUM("名詞,動詞,形容詞,助動詞,感動詞,副詞,接頭詞"),
    LARGE("名詞,動詞,形容詞,助動詞,感動詞,副詞,接頭詞,連体詞,接続詞,助詞,未知語");
    
    private final String key;
    private PartOfSpeech(String key) { this.key = key; }
    @Override public String toString() { return this.key; }
  }
  
  /**
   * 操作名
   */
  public static final String OPERATION_NAME = "create";
  
  /**
   * パラメータ名（ドメイン名）
   */
  public static final String PARAM_KEY_DOMAIN_NAME = "domain_name";
  
  /**
   * パラメータ名（タグ一覧）
   */
  public static final String PARAM_KEY_TAGS = "tags";
  
  /**
   * パラメータ名（品詞）
   */
  public static final String PARAM_KEY_PARTS_OF_SPEECH = "parts_of_speech";
  
  /**
   * パラメータ名（共起グラフ名）
   */
  public static final String PARAM_KEY_COLLOCATION_NAME = "collocation_name";
  
  /**
   * パラメータ名（単語出現数下限）
   */
  public static final String PARAM_KEY_LOWER_THRESHOLD = "lower_threshold";
  
  /**
   * パラメータ名（単語出現数上限）
   */
  public static final String PARAM_KEY_UPPER_THRESHOLD = "upper_threshold";
  
  /**
   * パラメータ名（説明文）
   */
  public static final String PARAM_KEY_DESCRIPTION = "description";
  
  /**
   * パラメータ（ドメイン名）
   */
  public String domainName = null;
  
  /**
   * パラメータ（タグ一覧）
   */
  public String tags = null;
  
  /**
   * パラメータ（品詞）
   */
  public String partsOfSpeech = null;
  
  /**
   * パラメータ（共起グラフ名）
   */
  public String collocationName = null;
  
  /**
   * パラメータ（単語出現数下限）
   */
  public int lowerThreshold = 1;
  
  /**
   * パラメータ（単語出現数上限）
   */
  public int upperThreshold = 0;
  
  /**
   * パラメータ（説明文）
   */
  public String description = null;
  
  /**
   * パラメータを持たない、空のCollocationCreateRequestを構築します。<br>
   */
  public CollocationCreateRequest() {}
  
  /**
   * 指定されたドメイン名を使用して、CollocationCreateRequestを構築します。<br>
   * @param domainName ドメイン名
   */
  public CollocationCreateRequest(String domainName) {
    this.domainName = domainName;
  }
  
  /**
   * 指定されたドメイン名、タグ一覧、品詞、共起グラフ名、単語出現数下限、単語出現数上限、説明文を使用して、CollocationCreateRequestを構築します。<br>
   * @param domainName ドメイン名
   * @param tags タグ一覧
   * @param partsOfSpeech 品詞
   * @param collocationName 共起グラフ名
   * @param lowerThreshold 単語出現数下限
   * @param upperThreshold 単語出現数上限
   * @param description 説明文
   */
  public CollocationCreateRequest(
    String domainName,
    String tags,
    String partsOfSpeech,
    String collocationName,
    int lowerThreshold,
    int upperThreshold,
    String description
  ) {
    this.domainName = domainName;
    this.tags = tags;
    this.partsOfSpeech = partsOfSpeech;
    this.collocationName = collocationName;
    this.lowerThreshold = lowerThreshold;
    this.upperThreshold = upperThreshold;
    this.description = description;
  }
  
  /**
   * ドメイン名を取得します。<br>
   * @return ドメイン名
   */
  public String getDomainName() { return this.domainName; }
  
  /**
   * タグ一覧を取得します。<br>
   * @return タグ一覧
   */
  public String getTags() { return this.tags; }
  
  /**
   * 品詞を取得します。<br>
   * @return 品詞
   */
  public String getPartsOfSpeech() { return this.partsOfSpeech; }
  
  /**
   * 共起グラフ名を取得します。<br>
   * @return 共起グラフ名
   */
  public String getCollocationName() { return this.collocationName; }
  
  /**
   * 単語出現数下限を取得します。<br>
   * @return 単語出現数下限
   */
  public int getLowerThreshold() { return this.lowerThreshold; }
  
  /**
   * 単語出現数上限を取得します。<br>
   * @return 単語出現数上限
   */
  public int getUpperThreshold() { return this.upperThreshold; }
  
  /**
   * 説明文を取得します。<br>
   * @return 説明文
   */
  public String getDescription() { return this.description; }
  
  /**
   * ドメイン名を設定します。<br>
   * @param name ドメイン名
   */
  public void setDomainName(String name) { this.domainName = name; }
  
  /**
   * タグ一覧を設定します。<br>
   * @param tags タグ一覧
   */
  public void setTags(String tags) { this.tags = tags; }
  
  /**
   * 品詞を設定します。<br>
   * @param partsOfSpeech 品詞
   */
  public void setPartsOfSpeech(String partsOfSpeech) { this.partsOfSpeech = partsOfSpeech; }
  
  /**
   * 共起グラフ名を設定します。<br>
   * @param name 共起グラフ名
   */
  public void setCollocationName(String name) { this.collocationName = name; }
  
  /**
   * 単語出現数下限を設定します。<br>
   * @param threshold 単語出現数下限
   */
  public void setLowerThreshold(int threshold) { this.lowerThreshold = threshold; }
  
  /**
   * 単語出現数上限を設定します。<br>
   * @param threshold 単語出現数上限
   */
  public void setUpperThreshold(int threshold) { this.upperThreshold = threshold; }
  
  /**
   * 説明文を設定します。<br>
   * @param description 説明文
   */
  public void setDescription(String description) { this.description = description; }
  
  @Override
  public HttpRequestBase toHttpMethod(MushikagoAuth auth, ConnectInfo ci) throws RequestException {
    
    try {
      TreeMap<String, String> requestParams = new TreeMap<String, String>();
      if(null == this.domainName) throw new RequestException("ドメイン名を指定してください");
      requestParams.put(PARAM_KEY_DOMAIN_NAME, ParamUtils.paramEncode(this.domainName));
      if(null == this.tags) throw new RequestException("タグを指定してください");
      requestParams.put(PARAM_KEY_TAGS, ParamUtils.paramEncode(this.tags));
      
      if(null != this.partsOfSpeech) requestParams.put(PARAM_KEY_PARTS_OF_SPEECH, ParamUtils.paramEncode(this.partsOfSpeech));
      if(null != this.collocationName) requestParams.put(PARAM_KEY_PARTS_OF_SPEECH, ParamUtils.paramEncode(this.partsOfSpeech));
      
      requestParams.put(PARAM_KEY_LOWER_THRESHOLD, ParamUtils.paramEncode(String.valueOf(this.lowerThreshold)));
      requestParams.put(PARAM_KEY_UPPER_THRESHOLD, ParamUtils.paramEncode(String.valueOf(this.upperThreshold)));
      if(null != this.description) requestParams.put(PARAM_KEY_DESCRIPTION, ParamUtils.paramEncode(this.description));
      return this.toHttpPostMethod(auth, ci, this.path(this.serviceName, this.categoryName, OPERATION_NAME), requestParams);
    }
    catch(UnsupportedEncodingException e) {
      throw new RequestException(e.getMessage());
    }
  }
}
