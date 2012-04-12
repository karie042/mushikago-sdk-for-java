package org.mushikago.sdk.services.hotaru.samples;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.mushikago.sdk.common.APICallException;
import org.mushikago.sdk.common.RequestException;
import org.mushikago.sdk.common.auth.Credentials;
import org.mushikago.sdk.common.broker.BrokerException;
import org.mushikago.sdk.common.model.MushikagoResponse;
import org.mushikago.sdk.services.hotaru.HotaruClient;
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
import org.mushikago.sdk.services.hotaru.model.collocation.get.Word;
import org.mushikago.sdk.services.hotaru.model.collocation.list.Collocation;
import org.mushikago.sdk.services.hotaru.model.collocation.list.CollocationListRequest;
import org.mushikago.sdk.services.hotaru.model.collocation.list.CollocationListResponse;
import org.mushikago.sdk.services.hotaru.model.collocation.wordlist.CollocationWordlistRequest;
import org.mushikago.sdk.services.hotaru.model.collocation.wordlist.CollocationWordlistResponse;
import org.mushikago.sdk.services.hotaru.model.dictionary.delete.DictionaryDeleteRequest;
import org.mushikago.sdk.services.hotaru.model.dictionary.delete.DictionaryDeleteResponse;
import org.mushikago.sdk.services.hotaru.model.dictionary.list.Dictionary;
import org.mushikago.sdk.services.hotaru.model.dictionary.list.DictionaryListRequest;
import org.mushikago.sdk.services.hotaru.model.dictionary.list.DictionaryListResponse;
import org.mushikago.sdk.services.hotaru.model.dictionary.put.DictionaryPutRequest;
import org.mushikago.sdk.services.hotaru.model.dictionary.put.DictionaryPutResponse;
import org.mushikago.sdk.services.hotaru.model.domain.create.DomainCreateRequest;
import org.mushikago.sdk.services.hotaru.model.domain.create.DomainCreateRequest.Splitter;
import org.mushikago.sdk.services.hotaru.model.domain.create.DomainCreateResponse;
import org.mushikago.sdk.services.hotaru.model.domain.delete.DomainDeleteRequest;
import org.mushikago.sdk.services.hotaru.model.domain.delete.DomainDeleteResponse;
import org.mushikago.sdk.services.hotaru.model.domain.info.DomainInfoRequest;
import org.mushikago.sdk.services.hotaru.model.domain.info.DomainInfoResponse;
import org.mushikago.sdk.services.hotaru.model.domain.list.Domain;
import org.mushikago.sdk.services.hotaru.model.domain.list.DomainListRequest;
import org.mushikago.sdk.services.hotaru.model.domain.list.DomainListResponse;
import org.mushikago.sdk.services.hotaru.model.tag.delete.TagDeleteRequest;
import org.mushikago.sdk.services.hotaru.model.tag.delete.TagDeleteResponse;
import org.mushikago.sdk.services.hotaru.model.tag.list.Tag;
import org.mushikago.sdk.services.hotaru.model.tag.list.TagListRequest;
import org.mushikago.sdk.services.hotaru.model.tag.list.TagListResponse;
import org.mushikago.sdk.services.hotaru.model.tag.put.TagPutRequest;
import org.mushikago.sdk.services.hotaru.model.tag.put.TagPutResponse;
import org.mushikago.sdk.services.hotaru.model.text.delete.TextDeleteRequest;
import org.mushikago.sdk.services.hotaru.model.text.delete.TextDeleteResponse;
import org.mushikago.sdk.services.hotaru.model.text.get.TextGetRequest;
import org.mushikago.sdk.services.hotaru.model.text.get.TextGetResponse;
import org.mushikago.sdk.services.hotaru.model.text.list.Text;
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

public class HotaruApiSample {
  
  private final HotaruClient client;
  
  public HotaruApiSample(String apiKey, String secretKey) {
    
    Credentials credentials = new Credentials(apiKey, secretKey);
    this.client = new HotaruClient(credentials);
  }
  
  private void printResponseMetaData(MushikagoResponse response) {
    System.out.println("status:" + response.getStatus());
    System.out.println("message:" + response.getMessage());
  }
  
  public void domainListSample() throws RequestException, BrokerException, APICallException {
    DomainListRequest req = new DomainListRequest();
    DomainListResponse res = this.client.domainList(req);
    this.printResponseMetaData(res);
    System.out.println("total:" + res.getTotal());
    for(Domain domain : res.getDomains()) {
      System.out.println(domain.getName() + "," + domain.getDescription() + "," + domain.getCreateDate() + "," + domain.getStatus());
    }
  }
  
  public void domainCreateSample(String domainName) throws RequestException, BrokerException, APICallException {
    DomainCreateRequest req = new DomainCreateRequest();
    req.setDescription("テスト用ドメインです");
    req.setDomainName(domainName);
    req.setSplitter(Splitter.MORPH);
    DomainCreateResponse res = this.client.domainCreate(req);
    this.printResponseMetaData(res);
  }
  
  public void domainDeleteSample(String domainName) throws RequestException, BrokerException, APICallException {
    DomainDeleteRequest req = new DomainDeleteRequest();
    req.setDomainName(domainName);
    DomainDeleteResponse res = this.client.domainDelete(req);
    this.printResponseMetaData(res);
  }
  
  public void domainInfoSample(String domainName) throws RequestException, BrokerException, APICallException {
    DomainInfoRequest req = new DomainInfoRequest();
    req.setDomainName(domainName);
    DomainInfoResponse res = this.client.domainInfo(req);
    this.printResponseMetaData(res);
    System.out.println("description:" + res.getDescription());
    System.out.println("splitter:" + res.getSplitter());
    for(String id : res.getDictionaryIds()) System.out.println("dictionary id:" + id);
    System.out.println("domain status:" + res.getDomainStatus());
    System.out.println("create date:" + res.getCreateDate());
    System.out.println("usage update:" + res.getUsageUpdate());
    System.out.println("usage:" + res.getUsage());
  }
  
  public void textListSample(String domainName) throws RequestException, BrokerException, APICallException {
    TextListRequest req = new TextListRequest();
    req.setDomainName(domainName);
    TextListResponse res = this.client.textList(req);
    this.printResponseMetaData(res);
    for(Text text : res.getTexts()) {
      System.out.println("  " + text.getTextId() + "," + text.getDescription() + "," + text.getCreateDate() + "," + text.getStatus());
      for(String tag : text.getTags()) System.out.println("    tag:" + tag);
    }
  }
  
  public void textPutSample(String domainName, String tag, String filePath, String description) throws RequestException, BrokerException, APICallException, IOException {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
    StringBuilder buffer = new StringBuilder();
    while(true) {
      String s = br.readLine();
      if(null == s) break;
      buffer.append(s + "\n");
    }
    br.close();
    String text = buffer.toString();
    
    TextPutRequest req = new TextPutRequest();
    req.setDomainName(domainName);
    req.setTags(tag);
    req.setText(text);
    req.setDescription(description);
    TextPutResponse res = this.client.textPut(req);
    this.printResponseMetaData(res);
    System.out.println("text_id:" + res.getTextId());
  }
  
  public void tagListSample(String domainName) throws RequestException, BrokerException, APICallException {
    TagListRequest req = new TagListRequest();
    req.setDomainName(domainName);
    TagListResponse res = this.client.tagList(req);
    this.printResponseMetaData(res);
    System.out.println("total:" + res.getTotal());
    for(Tag tag : res.getTags()) {
      System.out.println(" tag:" + tag.getName() + "," + tag.getCount());
    }
  }
  
  public void tagPutSample(String domainName, String tag) throws RequestException, BrokerException, APICallException {
    TagPutRequest req = new TagPutRequest();
    req.setDomainName(domainName);
    req.setTag(tag);
    
    TagPutResponse res = this.client.tagPut(req);
    this.printResponseMetaData(res);
  }
  
  public void tagDeleteSample(String domainName, String tag) throws RequestException, BrokerException, APICallException {
    TagDeleteRequest req = new TagDeleteRequest();
    req.setDomainName(domainName);
    req.setTag(tag);
    TagDeleteResponse res = this.client.tagDelete(req);
    this.printResponseMetaData(res);
  }
  
  public void textDeleteSample(String domainName, String textId) throws RequestException, BrokerException, APICallException {
    TextDeleteRequest req = new TextDeleteRequest();
    req.setDomainName(domainName);
    req.setTextId(textId);
    TextDeleteResponse res = this.client.textDelete(req);
    this.printResponseMetaData(res);
  }
  
  public void textGetSample(String domainName, String textId) throws RequestException, BrokerException, APICallException {
    TextGetRequest req = new TextGetRequest();
    req.setDomainName(domainName);
    req.setTextId(textId);
    TextGetResponse res = this.client.textGet(req);
    this.printResponseMetaData(res);
    System.out.println("text name:" + res.getDescription());
    System.out.println("url:" + res.getUrl());
    for(String tag : res.getTags()) {
      System.out.println("  tag:" + tag);
    }
  }
  
  public void textTagsetSample(String domainName, String textId, String tags, boolean replace) throws RequestException, BrokerException, APICallException {
    TextTagsetRequest req = new TextTagsetRequest();
    req.setDomainName(domainName);
    req.setTextId(textId);
    req.setTags(tags);
    TextTagsetResponse res = this.client.textTagset(req);
    this.printResponseMetaData(res);
  }
  
  public void dictionaryListSample() throws RequestException, BrokerException, APICallException {
    DictionaryListRequest req = new DictionaryListRequest();
    DictionaryListResponse res =  this.client.dictionaryList(req);
    this.printResponseMetaData(res);
    System.out.println("total:" + res.getTotal());
    for(Dictionary dic : res.getDictionaries()) {
      System.out.println(dic.getId() + "," + dic.getName() + "," + dic.getDescription() + "," + dic.getCreateDate() + "," + dic.getSize());
    }
  }
  
  public void dictionaryPutSample(File file) throws RequestException, BrokerException, APICallException {
    DictionaryPutRequest req = new DictionaryPutRequest();
    req.setDictionaryFile(file);
    DictionaryPutResponse res = this.client.dictionaryPut(req);
    this.printResponseMetaData(res);
    System.out.println("dictionary id:" + res.getId());
  }
  
  public void dictionaryDeleteSample(String id) throws RequestException, BrokerException, APICallException {
    DictionaryDeleteRequest req = new DictionaryDeleteRequest();
    req.setId(id);
    DictionaryDeleteResponse res = this.client.dictionaryDelete(req);
    this.printResponseMetaData(res);
  }
  
  public void classifierJudgeSample(String domainName, String text) throws RequestException, BrokerException, APICallException {
    ClassifierJudgeRequest req = new ClassifierJudgeRequest();
    req.setDomainName(domainName);
    req.setText(text);
    ClassifierJudgeResponse res = this.client.classifierJudge(req);
    this.printResponseMetaData(res);
    for(org.mushikago.sdk.services.hotaru.model.classifier.judge.Tag tag : res.getTags()) {
      System.out.println("  tag:" + tag.getName() + "," + tag.getScore());
    }
  }
  
  public void collocationListSample(String domainName) throws RequestException, BrokerException, APICallException {
    CollocationListRequest req = new CollocationListRequest();
    req.setDomainName(domainName);
    CollocationListResponse res = this.client.collocationList(req);
    this.printResponseMetaData(res);
    for(Collocation collocation : res.getCollocations()) {
      System.out.println("  id:" + collocation.getId());
      System.out.println("  name:" + collocation.getName());
      for(String tag : collocation.getTags()) System.out.println("    tag:" + tag);
      for(String partsOfSpeech : collocation.getPartsOfSpeech()) System.out.println("    partsOfSpeech:" + partsOfSpeech);
      System.out.println("  lower threshold:" + collocation.getLowerThreshold());
      System.out.println("  upper threshold:" + collocation.getUpperThreshold());
      System.out.println("  create date:" + collocation.getCreateDate());
      System.out.println("  status:" + collocation.getStatus().toString());
    }
  }
  
  public void collocationCreateSample(String domainName, String tags) throws RequestException, BrokerException, APICallException {
    CollocationCreateRequest req = new CollocationCreateRequest();
    req.setDomainName(domainName);
    req.setTags(tags);
    CollocationCreateResponse res = this.client.collocationCreate(req);
    this.printResponseMetaData(res);
    System.out.println("collocation id:" + res.getCollocationId());
  }
  
  public void collocationGetSample(String domainName, String collocationId, String word) throws RequestException, BrokerException, APICallException {
    CollocationGetRequest req = new CollocationGetRequest();
    req.setDomainName(domainName);
    req.setCollocationId(collocationId);
    req.setWord(word);
    CollocationGetResponse res = this.client.collocationGet(req);
    this.printResponseMetaData(res);
    System.out.println("target word:" + res.getTargetWord());
    for(Word _word : res.getWords()) {
      System.out.println("  " + _word.getName() + "," + _word.getScore());
    }
  }
  
  public void collocationDownload(String domainName, String collocationId) throws RequestException, BrokerException, APICallException {
    CollocationDownloadRequest req = new CollocationDownloadRequest();
    req.setDomainName(domainName);
    req.setCollocationId(collocationId);
    CollocationDownloadResponse res = this.client.collocationDownload(req);
    this.printResponseMetaData(res);
    System.out.println("collocation name:" + res.getCollocationName());
    System.out.println("url:" + res.getUrl());
  }
  
  public void collocationDeleteSample(String domainName, String collocationId) throws RequestException, BrokerException, APICallException {
    CollocationDeleteRequest req = new CollocationDeleteRequest();
    req.setDomainName(domainName);
    req.setCollocationId(collocationId);
    CollocationDeleteResponse res = this.client.collocationDelete(req);
    this.printResponseMetaData(res);
  }
  
  public void collocationWordlistSample(String domainName, String collocationId) throws APICallException, RequestException, BrokerException {
    CollocationWordlistRequest req = new CollocationWordlistRequest();
    req.setDomainName(domainName);
    req.setCollocationId(collocationId);
    
    int offset = 0;
    while(true) {
      req.setOffset(offset);
      CollocationWordlistResponse res = this.client.collocationWordlist(req);
      this.printResponseMetaData(res);
      System.out.println("total:" + res.getTotal() + ", offset:" + offset);
      if(0 == res.getWords().size()) break;
      for(String word : res.getWords()) {
        System.out.println("  word:" + word);
      }
      
      offset += res.getWords().size();
    }
    
    System.out.println("get total:" + offset);
  }
  
  public void wordListSample(String domainName) throws RequestException, BrokerException, APICallException {
    WordListRequest req = new WordListRequest();
    req.setDomainName(domainName);
    WordListResponse res = this.client.wordList(req);
    this.printResponseMetaData(res);
    System.out.println("total:" + res.getTotal());
    for(org.mushikago.sdk.services.hotaru.model.word.Word word : res.getWords()) {
      System.out.println("  word:" + word.getName() + "," + word.getCount());
    }
  }
  
  public void wordGetSample(String domainName, String word) throws RequestException, BrokerException, APICallException {
    WordGetRequest req = new WordGetRequest();
    req.setDomainName(domainName);
    req.setWord(word);
    WordGetResponse res = this.client.wordGet(req);
    this.printResponseMetaData(res);
    System.out.println("word:" + res.getWord());
    System.out.println("count:" + res.getCount());
    for(org.mushikago.sdk.services.hotaru.model.word.Tag tag : res.getTags()) {
      System.out.println("  tag:" + tag.getName() + "," + tag.getCount());
    }
  }
  
  public static void main(String[] args) throws Exception {
    
    String apiKey = "";     // your api key
    String secretKey = "";    // your secret key
    
    HotaruApiSample sample = new HotaruApiSample(apiKey, secretKey);
    
//    sample.domainListSample();
//    sample.domainCreateSample("test");
//    sample.domainDeleteSample("test");
//    sample.domainInfoSample("test");
//    sample.textPutSample("test", "夏目漱石,小説", "/tmp/test.data", "wagahai");
//    sample.textListSample("test");
//    sample.textGetSample("test", "c8359525-4326-4f1a-9050-61ae084018a6");
//    sample.textDeleteSample("test", "b0a6d758-d60f-4963-bd7c-84be7d9385cb");
//    sample.tagListSample("test");
//    sample.tagPutSample("test", "小説");
//    sample.tagDeleteSample("test", "夏目漱石,日本,小説");
//    sample.textTagsetSample("test", "c8359525-4326-4f1a-9050-61ae084018a6", "タグ", false);
//    sample.dictionaryListSample();
//    sample.dictionaryPutSample(new File("/tmp/test.txt"));
//    sample.dictionaryDeleteSample("dA41BEEAE");
//    sample.classifierJudgeSample("test", "吾輩");
//    sample.collocationListSample("test");
//    sample.collocationCreateSample("test", "夏目漱石,日本");
//    sample.collocationGetSample("test", "19f882e8-da0a-443c-95e3-6c352bce33e7", "猫");
//    sample.collocationDownload("test", "19f882e8-da0a-443c-95e3-6c352bce33e7");
//    sample.collocationDeleteSample("test", "c28fdc0f-7ae8-4984-8893-e71a753a4ef0");
//    sample.collocationWordlistSample("test", "19f882e8-da0a-443c-95e3-6c352bce33e7");
//    sample.wordListSample("test");
//    sample.wordGetSample("test", "猫");
  }
}
