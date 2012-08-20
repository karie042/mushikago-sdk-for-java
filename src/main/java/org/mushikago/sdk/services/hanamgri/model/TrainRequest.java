package org.mushikago.sdk.services.hanamgri.model;

import org.apache.http.client.methods.HttpPost;
import org.mushikago.sdk.common.annotation.RequestMetaData;
import org.mushikago.sdk.common.annotation.RequestParameter;

/**
 * Trainリクエストを表すクラスです
 * 
 * @author ope
 */
@RequestMetaData(path = "/1/hanamgri/domains/:domain_name/train", httpMethodClass = HttpPost.class)
public class TrainRequest extends HanamgriRequest {

  /**
   * ドメイン名
   */
  @RequestParameter(name = "domain_name", isRequired = true, isPartOfURI = true)
  private String domainName = null;

  /**
   * 学習対象のURLもしくはHTML文字列
   */
  @RequestParameter(name = "url_or_html", isRequired = true)
  private String urlOrHtml = null;

  /**
   * 教師データ
   */
  @RequestParameter(name = "training_data", isRequired = true)
  private TrainingData trainingData = null;

  /**
   * 学習対象のURLもしくはHTML文字列の文字コード
   */
  @RequestParameter(name = "charset", isRequired = false)
  private TrainingData charset = null;

  /**
   * 空のTrainリクエストを作成します
   */
  public TrainRequest() {
    this(null, null, null);
  }

  /**
   * ドメイン名と学習対象のurlもしくはHTML文字列、教師データを指定してTrainリクエストを作成します
   * 
   * @param domainName
   * @param url
   * @param trainingData
   */
  public TrainRequest(String domainName, String urlOrHtml, TrainingData trainingData) {
    super();
    this.domainName = domainName;
    this.urlOrHtml = urlOrHtml;
    this.setTrainingData(trainingData);
  }

  /**
   * domain_nameを取得します
   * 
   * @return
   */
  public String getDomainName() {
    return domainName;
  }

  /**
   * domain_nameを設定します
   * 
   * @param domainName
   */
  public void setDomainName(String domainName) {
    this.domainName = domainName;
  }

  /**
   * URLもしくはHTML文字列を取得します
   * 
   * @return
   */
  public String getUrlOrHtml() {
    return urlOrHtml;
  }

  /**
   * URLもしくはHTML文字列を設定します
   * 
   * @param urlOrHtml
   */
  public void setUrlOrHtml(String urlOrHtml) {
    this.urlOrHtml = urlOrHtml;
  }

  /**
   * TrainingDataを取得します
   * 
   * @return
   */
  public TrainingData getTrainingData() {
    return trainingData;
  }

  /**
   * TrainingDataを設定します
   * 
   * @param trainingData
   */
  public void setTrainingData(TrainingData trainingData) {
    this.trainingData = trainingData;
  }

  /**
   * charsetを取得します
   * @return
   */
  public TrainingData getCharset() {
    return charset;
  }

  /**
   * charsetを設定します
   * @param charset
   */
  public void setCharset(TrainingData charset) {
    this.charset = charset;
  }

}
