package org.mushikago.sdk.services.mitsubachi.model.project.info;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.Test;

public class ProjectInfoResponseTest {
  private JSONObject getValidTestData() {
    JSONArray callCounts = new JSONArray();
    for (int i = 0; i < 24; i++) {
      callCounts.add("0");
    }
    JSONObject fetchHourlyCount = new JSONObject();
    fetchHourlyCount.put("name", "fetch");
    fetchHourlyCount.put("call_counts", callCounts);
    
    JSONObject pushHourlyCount = new JSONObject();
    pushHourlyCount.put("name", "fetch");
    pushHourlyCount.put("call_counts", callCounts);
    
    JSONObject totalHourlyCount = new JSONObject();
    totalHourlyCount.put("name", "fetch");
    totalHourlyCount.put("call_counts", callCounts);
    
    JSONArray hourlyCounts = new JSONArray();
    hourlyCounts.add(fetchHourlyCount);
    hourlyCounts.add(pushHourlyCount);
    hourlyCounts.add(totalHourlyCount);
    
    JSONObject fetchDailyCount = new JSONObject();
    fetchDailyCount.put("name", "fetch");
    fetchDailyCount.put("call_count", "10");

    JSONObject pushDailyCount = new JSONObject();
    pushDailyCount.put("name", "push");
    pushDailyCount.put("call_count", "10");

    JSONObject totalDailyCount = new JSONObject();
    totalDailyCount.put("name", "total");
    totalDailyCount.put("call_count", "20");

    JSONArray dailyCounts = new JSONArray();
    dailyCounts.add(fetchDailyCount);
    dailyCounts.add(pushDailyCount);
    dailyCounts.add(totalDailyCount);

    JSONObject fetchMonthlyCount = new JSONObject();
    fetchMonthlyCount.put("name", "fetch");
    fetchMonthlyCount.put("call_count", "10");

    JSONObject pushMonthlyCount = new JSONObject();
    pushMonthlyCount.put("name", "push");
    pushMonthlyCount.put("call_count", "10");

    JSONObject totalMonthlyCount = new JSONObject();
    totalMonthlyCount.put("name", "total");
    totalMonthlyCount.put("call_count", "20");

    JSONArray monthlyCounts = new JSONArray();
    monthlyCounts.add(fetchMonthlyCount);
    monthlyCounts.add(pushMonthlyCount);
    monthlyCounts.add(totalMonthlyCount);

    JSONObject responseJson = new JSONObject();
    responseJson.put("meta", new JSONObject());
    responseJson.getJSONObject("meta").put("status", "200");
    responseJson.getJSONObject("meta").put("message", "OK");
    responseJson.put("response", new JSONObject());
    responseJson.getJSONObject("response").put("storage_prefix", "/hoge");
    responseJson.getJSONObject("response").put("dedicated", "1");
    responseJson.getJSONObject("response").put("max_lead_time", "60");
    responseJson.getJSONObject("response").put("stdout", "1");
    responseJson.getJSONObject("response").put("stderr", "1");
    responseJson.getJSONObject("response").put("system_log", "1");
    responseJson.getJSONObject("response").put("log_prefix", "/fuga");
    responseJson.getJSONObject("response").put("hourly_counts", hourlyCounts);
    responseJson.getJSONObject("response").put("daily_counts", dailyCounts);
    responseJson.getJSONObject("response").put("monthly_counts", monthlyCounts);
    return responseJson;
  }
  
  @Test
  public void testValidtestData() {
    JSONObject testData = getValidTestData();
    ProjectInfoResponse response = new ProjectInfoResponse(testData);
    assertEquals("/hoge", response.getStoragePrefix());
    assertTrue(response.getDedicated());
    assertEquals(60, response.getMaxLeadTime());
    assertTrue(response.getStdoutOut());
    assertTrue(response.getStderrOut());
    assertTrue(response.getSystemLogOut());
    assertEquals("/fuga", response.getLogPrefix());
    assertEquals(3, response.getHourlyCounts().size());
    assertEquals(24, response.getHourlyCounts().get(0).getCallCounts().size());
    assertEquals(24, response.getHourlyCounts().get(1).getCallCounts().size());
    assertEquals(24, response.getHourlyCounts().get(2).getCallCounts().size());
    assertEquals(3, response.getDailyCounts().size());
    assertEquals(3, response.getMonthlyCounts().size());
    // TODO getfetchCountとか足したい
    assertEquals(20, response.getDailyCounts().get(2).getCount());
  }
  

}
