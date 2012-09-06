package org.mushikago.sdk.services.mitsubachi.samples;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.mushikago.sdk.common.RequestException;
import org.mushikago.sdk.common.auth.Credentials;
import org.mushikago.sdk.common.broker.BrokerException;
import org.mushikago.sdk.common.exception.APICallException;
import org.mushikago.sdk.services.mitsubachi.MitsubachiClient;
import org.mushikago.sdk.services.mitsubachi.model.http.fetch.HttpFetchRequest;
import org.mushikago.sdk.services.mitsubachi.model.http.push.HttpPushRequest;
import org.mushikago.sdk.services.mitsubachi.model.project.create.ProjectCreateRequest;
import org.mushikago.sdk.services.mitsubachi.model.project.create.ProjectCreateResponse;
import org.mushikago.sdk.services.mitsubachi.model.project.delete.ProjectDeleteRequest;
import org.mushikago.sdk.services.mitsubachi.model.project.discontinue.ProjectDiscontinueRequest;
import org.mushikago.sdk.services.mitsubachi.model.project.info.ProjectInfoRequest;
import org.mushikago.sdk.services.mitsubachi.model.project.info.ProjectInfoResponse;
import org.mushikago.sdk.services.mitsubachi.model.project.list.Project;
import org.mushikago.sdk.services.mitsubachi.model.project.list.ProjectListRequest;
import org.mushikago.sdk.services.mitsubachi.model.project.list.ProjectListResponse;
import org.mushikago.sdk.services.mitsubachi.model.project.queues.ProjectQueuesRequest;
import org.mushikago.sdk.services.mitsubachi.model.project.queues.ProjectQueuesResponse;
import org.mushikago.sdk.services.mitsubachi.model.project.update.ProjectUpdateRequest;
import org.mushikago.sdk.services.mitsubachi.model.resource.delete.ResourceDeleteRequest;
import org.mushikago.sdk.services.mitsubachi.model.resource.delete.ResourceDeleteResponse;
import org.mushikago.sdk.services.mitsubachi.model.resource.get.ResourceGetRequest;
import org.mushikago.sdk.services.mitsubachi.model.resource.get.ResourceGetResponse;
import org.mushikago.sdk.services.mitsubachi.model.resource.list.Resource;
import org.mushikago.sdk.services.mitsubachi.model.resource.list.ResourceListRequest;
import org.mushikago.sdk.services.mitsubachi.model.resource.list.ResourceListResponse;
import org.mushikago.sdk.services.mitsubachi.model.resource.store.ResourceStoreRequest;
import org.mushikago.sdk.services.mitsubachi.model.script.delete.ScriptDeleteRequest;
import org.mushikago.sdk.services.mitsubachi.model.script.deploy.ScriptDeployRequest;
import org.mushikago.sdk.services.mitsubachi.model.script.get.ScriptGetRequest;
import org.mushikago.sdk.services.mitsubachi.model.script.get.ScriptGetResponse;
import org.mushikago.sdk.services.mitsubachi.model.script.list.Script;
import org.mushikago.sdk.services.mitsubachi.model.script.list.ScriptListRequest;
import org.mushikago.sdk.services.mitsubachi.model.script.list.ScriptListResponse;

public class MitsubachiApiSample {

  private final MitsubachiClient client;

  private String projectName = null;

  public MitsubachiApiSample(String apiKey, String secretKey) {

    Credentials credentials = new Credentials(apiKey, secretKey);
    this.client = new MitsubachiClient(credentials);
  }

  public void httpFetch() throws RequestException, BrokerException, APICallException {
    String url = "http://***";
    String scriptName = "hoge.rb";

    HttpFetchRequest req = new HttpFetchRequest(url, this.projectName, scriptName);
    req.addHeaderOverWrite("User-Agent", "Mozilla/5.0 (X11; Linux x86_64; rv:11.0) Gecko/20100101 Firefox/11.0");
    req.addParameter("category", "IT");
    this.client.httpFetch(req);
  }

  public void httpPush() throws RequestException, BrokerException, APICallException {
    String url = "http://***";
    String scriptName = "homge.rb";
    String fileName = "hoge.txt";
    String fileInputKey = "hoge";

    HttpPushRequest req = new HttpPushRequest(url, this.projectName, scriptName, fileName, fileInputKey);
    this.client.httpPush(req);
  }

  public void projectCreate() throws RequestException, BrokerException, APICallException {

    ProjectCreateRequest req = new ProjectCreateRequest(this.projectName, true, true, true);
    ProjectCreateResponse res = this.client.projectCreate(req);
    System.out.println(res.getStatus());
    System.out.println(res.getMessage());
  }

  public void projectDelete() throws RequestException, BrokerException, APICallException {
    ProjectDeleteRequest req = new ProjectDeleteRequest();
    req.setProjectName(this.projectName);

    this.client.projectDelete(req);
  }

  public void projectUpdate() throws RequestException, BrokerException, APICallException {
    ProjectUpdateRequest req = new ProjectUpdateRequest();
    req.setProjectName(this.projectName);
    req.setStdoutOut(true);
    req.setStderrOut(true);
    req.setSystemLogOut(true);
    req.setLogPrefix("log/");
    req.setMaxLeadTime(60);

    this.client.projectUpdate(req);
  }

  public void projectInfo() throws RequestException, BrokerException, APICallException {
    ProjectInfoRequest req = new ProjectInfoRequest();
    req.setProjectName(this.projectName);

    ProjectInfoResponse res = this.client.projectInfo(req);
    System.out.println(res.getStoragePrefix());
    System.out.println(res.getDedicated());
    System.out.println(res.getMaxLeadTime());
    System.out.println(res.getStdoutOut());
    System.out.println(res.getStderrOut());
    System.out.println(res.getSystemLogOut());
    System.out.println(res.getLogPrefix());
  }

  public void projectDiscontinue() throws RequestException, BrokerException, APICallException {
    ProjectDiscontinueRequest req = new ProjectDiscontinueRequest();
    req.setProjectName(this.projectName);
    
    this.client.projectDiscontinue(req);
  }

  public void projectQueues() throws RequestException, BrokerException, APICallException {
    ProjectQueuesRequest req = new ProjectQueuesRequest();
    req.setProjectName(this.projectName);

    ProjectQueuesResponse res = this.client.projectQueues(req);
    System.out.println(res.getCount());
  }

  public void projectList() throws RequestException, BrokerException, APICallException {
    ProjectListRequest req = new ProjectListRequest();
    ProjectListResponse res = this.client.projectList(req);
    for(Project project : res.getProjects()) {
      System.out.println(project.getName());
    }
  }

  public void resourceDelete() throws RequestException, BrokerException, APICallException {

    String fileName = "hoge.txt";

    ResourceDeleteRequest req = new ResourceDeleteRequest();
    req.setProjectName(this.projectName);
    req.setFileName(fileName);

    ResourceDeleteResponse res = this.client.resourceDelete(req);
    System.out.println(res.getStatus());
    System.out.println(res.getMessage());
  }

  public void resourceGet() throws RequestException, BrokerException, APICallException {

    String fileName = "hoge.txt";

    ResourceGetRequest req = new ResourceGetRequest();
    req.setProjectName(this.projectName);
    req.setFileName(fileName);

    ResourceGetResponse res = this.client.resourceGet(req);
    
    System.out.println(res.getStatus());
    System.out.println(res.getMessage());
  }

  public void resourceList() throws RequestException, BrokerException, APICallException {
    ResourceListRequest req = new ResourceListRequest();
    req.setProjectName(this.projectName);

    ResourceListResponse res = this.client.resourceList(req);
    for(Resource resource : res.getResources()) {
      System.out.println(resource.getName());
      System.out.println(resource.getSize());
      System.out.println(resource.getContentType());
      System.out.println(resource.getTimestamp());
    }
  }
  
  public void resourceStore() throws RequestException, BrokerException, APICallException {
    String fileName = "hoge.txt";
    String localFilePath = "hoge.txt";
    String contentType = "text/plain";

    ResourceStoreRequest req = new ResourceStoreRequest(this.projectName, fileName, contentType);
    req.setFile(new File(localFilePath));

    this.client.resourceStore(req);
  }

  public void scriptList() throws RequestException, BrokerException, APICallException {
    ScriptListRequest req = new ScriptListRequest();
    req.setProjectName(this.projectName);

    ScriptListResponse res = this.client.scriptList(req);
    for(Script script : res.getScripts()) {
      System.out.println(script.getName());
      System.out.println(script.getSize());
      System.out.println(script.getTimestamp());
    }
    System.out.println(res.hasMoreFile());
  }

  public void scriptDelete() throws RequestException, BrokerException, APICallException {
    String fileName = "mushikago.rb";

    ScriptDeleteRequest req = new ScriptDeleteRequest();
    req.setProjectName(this.projectName);
    req.setFileName(fileName);

    this.client.scriptDelete(req);
  }

  public void scriptDeoloy() throws RequestException, BrokerException, APICallException {
    File script = new File("homge.rb");
    String fileName = script.getName();

    ScriptDeployRequest req = new ScriptDeployRequest(this.projectName, fileName);
    req.setFile(script);

    this.client.scriptDeploy(req);
  }

  public void scriptGet() throws RequestException, BrokerException, APICallException, IOException {
    String fileName = "mushikago.rb";

    ScriptGetRequest req = new ScriptGetRequest(this.projectName);
    req.setFileName(fileName);

    ScriptGetResponse res = this.client.scriptGet(req);
    System.out.println(res.getUrl());
    BufferedReader reader = new BufferedReader(new InputStreamReader(res.getStream()));
    String line = null;
    while((line = reader.readLine()) != null) {
      System.out.println(line);
    }
    reader.close();
  }

  public static void main(String[] args) throws Exception {

    String apiKey = ""; // your api key
    String secretKey = ""; // your secret key

    MitsubachiApiSample sample = new MitsubachiApiSample(apiKey, secretKey);

// sample.httpFetch();
// sample.httpPush();
// sample.projectCreate();
// sample.projectList();
// sample.projectDelete();
// sample.projectQueues();
// sample.projectUpdate();
// sample.projectInfo();
// sample.projectDiscontinue();
// sample.resourceDelete();
// sample.resourceList();
// sample.resourceGet();
// sample.resourceStore();
// sample.scriptList();
// sample.scriptDelete();
// sample.scriptDeoloy();
// sample.scriptGet();
  }
}
