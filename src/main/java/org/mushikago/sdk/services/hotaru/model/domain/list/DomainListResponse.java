package org.mushikago.sdk.services.hotaru.model.domain.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.mushikago.sdk.services.hotaru.model.HotaruResponse;
import org.mushikago.sdk.services.hotaru.model.domain.list.DomainListRequest.Status;

import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

/**
 * ドメイン一覧取得リクエストの結果
 * @author miningbrownie
 */
public class DomainListResponse extends HotaruResponse {
  
  /**
   * パラメータ名（ドメイン名）
   */
  public static final String PARAM_KEY_NAME = "name";
  
  /**
   * パラメータ名（説明文）
   */
  public static final String PARAM_KEY_DESCRIPTION = "description";
  
  /**
   * パラメータ名（作成日）
   */
  public static final String PARAM_KEY_CREATE_DATE = "create_date";
  
  /**
   * パラメータ名（ステータス）
   */
  public static final String PARAM_KEY_STATUS = "status";
  
  /**
   * パラメータ名（ドメイン数）
   */
  public static final String PARAM_KEY_TOTAL = "total";
  
  /**
   * パラメータ（ドメイン数）
   */
  private final int total;
  
  /**
   * パラメータ（ドメイン一覧）
   */
  private final List<Domain> domains = new ArrayList<Domain>();
  
  /**
   * DomainListResponseを構築します。<br>
   * @param json APIサーバからのレスポンス。<br>
   */
  public DomainListResponse(JSONObject json) {
    
    super(json);
    
    final JSONObject response = json.getJSONObject("response");
    
    this.total = response.getInt(PARAM_KEY_TOTAL);
    
    final JSONArray domainJsons = response.getJSONArray("domains");
    for(int i = 0; i < domainJsons.size(); i++) {
      
      final JSONObject domainJson = domainJsons.getJSONObject(i);
      final Domain domain = new Domain(
        domainJson.getString(PARAM_KEY_NAME),
        JSONNull.getInstance().equals(domainJson.get(PARAM_KEY_DESCRIPTION)) ? "" : domainJson.getString(PARAM_KEY_DESCRIPTION),
        domainJson.getString(PARAM_KEY_CREATE_DATE),
        Status.valueOf(domainJson.getString(PARAM_KEY_STATUS).toUpperCase())
      );
      
      this.domains.add(domain);
    }
  }
  
  /**
   * ドメイン数を取得します。<br>
   * @return ドメイン数
   */
  public int getTotal() { return this.total; }
  
  /**
   * ドメインの一覧を取得します。<br>
   * @return ドメイン一覧
   */
  public List<Domain> getDomains() { return Collections.unmodifiableList(this.domains); }
}
