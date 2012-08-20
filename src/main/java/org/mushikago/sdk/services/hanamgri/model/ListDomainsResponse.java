package org.mushikago.sdk.services.hanamgri.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * ドメイン一覧取得リクエストの結果
 * @author miningbrownie
 *
 */

public class ListDomainsResponse extends HanamgriResponse{
  /**
   * レスポンスからdomainsパラメータを取得するキー
   */
  public static final String PARAMETER_KEY_DOMAINS = "domains";
  
  /**
   * レスポンスからdomain_nameパラメータを取得するキー
   */
  public static final String PARAMETER_KEY_DOMAIN_NAME = "domain_name";
  
  /**
   * レスポンスからdescriptionパラメータを取得するキー
   */
  public static final String PARAMETER_KEY_DESCRIPTION = "description";
  
  /**
   * レスポンスからupdated_atパラメーメタを取得するキー
   */
  public static final String PARAMETER_KEY_UPDATED_AT = "updated_at";
  
  /**
   * レスポンスからtotalパラメータを取得するキー
   */
  public static final String PARAMETER_KEY_TOTAL = "total";
  
  /**
   * パラメータ(ドメイン一覧)
   */
  private final List<Domain> domains = new ArrayList<Domain>();
  
  /**
   * パラメータ(合計)
   */
  private final int total;
  
  /**
   * ListDomainsResponseを構築します。<br>
   * @param json APIサーバからのレスポンス。<br>
   */
  public ListDomainsResponse(JSONObject json){
    super(json);
    
    this.total = getInteger(PARAMETER_KEY_TOTAL, 0);
    
    JSONArray domainJsons = getJSONArray(PARAMETER_KEY_DOMAINS);
    for(int i = 0; i < domainJsons.size(); i++){
      JSONObject domainJson = domainJsons.getJSONObject(i);
      Domain domain = new Domain(
        domainJson.getString(PARAMETER_KEY_DOMAIN_NAME),
        domainJson.getString(PARAMETER_KEY_DESCRIPTION),
        domainJson.getString(PARAMETER_KEY_UPDATED_AT)
      );
      
      this.domains.add(domain);
    }
  }
  
  /**
   * ドメイン数を取得します。<br>
   * @return ドメイン数
   */
  public int getTotal() { return this.total; }
  
  public List<Domain> getDomains() { return Collections.unmodifiableList(this.domains); }
}
