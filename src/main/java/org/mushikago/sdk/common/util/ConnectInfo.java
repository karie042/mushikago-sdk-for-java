package org.mushikago.sdk.common.util;

/**
 * 通信先の詳細情報を保持します。<br>
 * @author miningbrownie
 *
 */
public class ConnectInfo {
  
  private String endpoint = null;
  
  private Integer port = null;
  
  private String schema = null;
  
  public ConnectInfo(String endpoint) {
    this(endpoint, null);
  }
  
  public ConnectInfo(String endpoint, Integer port) {
    this(endpoint, port, "http");
  }
  
  public ConnectInfo(String endpoint, Integer port, String schema) {
    
    this.endpoint = endpoint;
    this.port = port;
    this.schema = schema;
  }
  
  public String getEndpoint() { return this.endpoint; }
  
  public Integer getPort() { return this.port; }
  
  public String getSchema() { return this.schema; }
  
  public void setEndpoint(String endpoint) { this.endpoint = endpoint; }
  
  public void setPort(Integer port) { this.port = port; }
  
  public void setSchema(String schema) { this.schema = schema; }
}
