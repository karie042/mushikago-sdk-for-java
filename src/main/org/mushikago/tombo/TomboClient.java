package org.mushikago.tombo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.mushikago.tombo.auth.Credentials;
import org.mushikago.tombo.model.capture.CaptureException;
import org.mushikago.tombo.model.capture.CaptureRequest;
import org.mushikago.tombo.model.capture.CaptureRequest.ImageFormat;
import org.mushikago.tombo.model.capture.CaptureResponse;
import org.mushikago.tombo.model.captures.CapturesException;
import org.mushikago.tombo.model.captures.CapturesRequest;
import org.mushikago.tombo.model.captures.CapturesResponse;
import org.mushikago.tombo.model.captures.CapturesResponseDetail;
import org.mushikago.tombo.model.delete.DeleteException;
import org.mushikago.tombo.model.delete.DeleteRequest;
import org.mushikago.tombo.model.delete.DeleteResponse;
import org.mushikago.tombo.model.info.InfoException;
import org.mushikago.tombo.model.info.InfoRequest;
import org.mushikago.tombo.model.info.InfoResponse;

public class TomboClient {
	
	private final TomboAuth tomboAuth;
	
	public final String infoRequestPath;
	public final String deleteRequestPath;
	public final String capturesRequestPath;
	public final String captureRequestPath;
	
	public TomboClient(Credentials credentials) {
		
		this.tomboAuth = new TomboAuth(credentials);
		
		this.infoRequestPath = "/1/info.json";
		this.deleteRequestPath = "/1/delete.json";
		this.capturesRequestPath = "/1/captures.json";
		this.captureRequestPath = "/1/capture.json";
	}
	
	private JSONObject httpRequest(HttpRequestBase request) throws ClientProtocolException, IOException {
		
		HttpClient http = new DefaultHttpClient();
		HttpResponse res = http.execute(request);
		JSONObject json = JSONObject.fromObject(EntityUtils.toString(res.getEntity()));
		return json;
	}
	
	private JSONObject httpGetRequest(String requestPath, TreeMap<String, String> requestParams) throws ClientProtocolException, IOException, InvalidKeyException, NoSuchAlgorithmException {
		
		String url = this.tomboAuth.makeRequestUrl("GET", requestPath, requestParams);
		String getParamString = ParamUtils.mapToString(requestParams);
		if("" != getParamString) { url = String.format("%s&%s", url, getParamString); }
		
		HttpGet get = new HttpGet(url);
		
		return this.httpRequest(get);
	}
	
	private JSONObject httpPostRequest(String requestPath, TreeMap<String, String> requestParams) throws ClientProtocolException, IOException, InvalidKeyException, NoSuchAlgorithmException {
		
		String url = this.tomboAuth.makeRequestUrl("POST", requestPath, requestParams);
		HttpPost post = new HttpPost(url);
		
		String postParamString = ParamUtils.mapToString(requestParams);
		if("" != postParamString) {
			post.setHeader("Content-Type", "application/x-www-form-urlencoded");
			post.setEntity(new StringEntity(postParamString));
		}
		
		return this.httpRequest(post);
	}
	
	private JSONObject httpDeleteRequest(String requestPath, TreeMap<String, String> requestParams) throws ClientProtocolException, IOException, InvalidKeyException, NoSuchAlgorithmException {
		
		String url = this.tomboAuth.makeRequestUrl("DELETE", requestPath, requestParams);
		
		String getParamString = ParamUtils.mapToString(requestParams);
		if("" != getParamString) { url = String.format("%s&%s", url, getParamString); }
		
		HttpDelete delete = new HttpDelete(url);
		
		return this.httpRequest(delete);
	}
	
	public InfoResponse info(InfoRequest request) throws InfoException {
		
		InfoResponse response = new InfoResponse();
		
		try {
			TreeMap<String, String> requestParams = new TreeMap<String, String>();
			
			JSONObject json = this.httpGetRequest(this.infoRequestPath, requestParams);
			response.setHttpStatus(Integer.valueOf(json.getJSONObject("meta").getString("status")));
			response.setMessage(json.getJSONObject("meta").getString("message"));
			if("null".equals(json.getJSONObject("response").toString())) {
				return response;
			}
			
			response.setApiCount(Integer.valueOf(json.getJSONObject("response").getString("api_count")));
			response.setDiskUsage(Integer.valueOf(json.getJSONObject("response").getString("disk_usage")));
			response.setImageNum(Integer.valueOf(json.getJSONObject("response").getString("image_num")));
		}
		catch(InvalidKeyException e)          { throw new InfoException(e.getMessage()); }
		catch(NoSuchAlgorithmException e)     { throw new InfoException(e.getMessage()); }
		catch(UnsupportedEncodingException e) { throw new InfoException(e.getMessage()); }
		catch(ClientProtocolException e)      { throw new InfoException(e.getMessage()); }
		catch(IOException e)                  { throw new InfoException(e.getMessage()); }
		
		return response;
	}
	
	public DeleteResponse delete(DeleteRequest request) throws DeleteException {
		
		DeleteResponse response = new DeleteResponse();
		
		try {
			TreeMap<String, String> requestParams = new TreeMap<String, String>();
			
			requestParams.put("id", ParamUtils.paramEncode(request.getId()));
			
			JSONObject json = this.httpDeleteRequest(this.deleteRequestPath, requestParams);
			response.setHttpStatus(Integer.valueOf(json.getJSONObject("meta").getString("status")));
			response.setMessage(json.getJSONObject("meta").getString("message"));
			if("null".equals(json.getJSONObject("response").toString())) {
				return response;
			}
			
			response.setId(json.getJSONObject("response").getString("id"));
		}
		catch(InvalidKeyException e)          { throw new DeleteException(e.getMessage()); }
		catch(NoSuchAlgorithmException e)     { throw new DeleteException(e.getMessage()); }
		catch(UnsupportedEncodingException e) { throw new DeleteException(e.getMessage()); }
		catch(ClientProtocolException e)      { throw new DeleteException(e.getMessage()); }
		catch(IOException e)                  { throw new DeleteException(e.getMessage()); }
		
		return response;
	}
	
	public CapturesResponse captures(CapturesRequest request) throws CapturesException {
		
		CapturesResponse response = new CapturesResponse();
		
		try {
			TreeMap<String, String> requestParams = new TreeMap<String, String>();
			
			if(null != request.getId()) requestParams.put("id", ParamUtils.paramEncode(request.getId()));
			requestParams.put("limit", ParamUtils.paramEncode(String.valueOf(request.getLimit())));
			requestParams.put("offset", ParamUtils.paramEncode(String.valueOf(request.getOffset())));
			if(null != request.getDomain()) requestParams.put("domain", ParamUtils.paramEncode(request.getDomain()));
			if(null != request.getTag()) requestParams.put("tag", ParamUtils.paramEncode(request.getTag()));
			
			JSONObject json = this.httpGetRequest(this.capturesRequestPath, requestParams);
			response.setHttpStatus(Integer.valueOf(json.getJSONObject("meta").getString("status")));
			response.setMessage(json.getJSONObject("meta").getString("message"));
			if("null".equals(json.getJSONObject("response").toString())) {
				return response;
			}
			
			response.setTotal(json.getJSONObject("response").getInt("total"));
			JSONArray images = json.getJSONObject("response").getJSONArray("images");
			List<CapturesResponseDetail> details = new ArrayList<CapturesResponseDetail>();
			for(int i = 0; i < images.size(); i++) {
				
				JSONObject image = images.getJSONObject(i);
				
				CapturesResponseDetail detail = new CapturesResponseDetail();
				detail.setId(image.getString("image_id"));
				detail.setImageUrl(image.getString("image_url"));
				detail.setImageSize(image.getLong("image_size"));
				detail.setThumbnailUrl(image.getString("thumbnail_url"));
				detail.setThumbnailSize(image.getLong("thumbnail_size"));
				detail.setSourceUrl(image.getString("source_url"));
				detail.setFormat(ImageFormat.valueOf(image.getString("image_format").toUpperCase()));
				detail.setQuality(image.getInt("image_quality"));
				detail.setState(image.getString("state"));
				
				List<String> tags = new ArrayList<String>();
				JSONArray _tags = image.getJSONArray("tags");
				for(int j = 0; j < _tags.size(); j++) {
					tags.add(_tags.getString(j));
				}
				
				detail.setTags(tags.toArray(new String[tags.size()]));
				
				details.add(detail);
			}
			
			response.setDetails(details);
		}
		catch(InvalidKeyException e)          { throw new CapturesException(e.getMessage()); }
		catch(NoSuchAlgorithmException e)     { throw new CapturesException(e.getMessage()); }
		catch(UnsupportedEncodingException e) { throw new CapturesException(e.getMessage()); }
		catch(ClientProtocolException e)      { throw new CapturesException(e.getMessage()); }
		catch(IOException e)                  { throw new CapturesException(e.getMessage()); }
		
		return response;
	}
	
	public CaptureResponse capture(CaptureRequest request) throws CaptureException {
		
		CaptureResponse response = new CaptureResponse();
		
		try {
			TreeMap<String, String> requestParams = new TreeMap<String, String>();
			
			requestParams.put("url", ParamUtils.paramEncode(request.getUrl()));
			requestParams.put("image_format", ParamUtils.paramEncode(request.getImageFormat().name()));
			requestParams.put("image_quality", ParamUtils.paramEncode(String.valueOf(request.getQuality())));
			requestParams.put("thumbnail", ParamUtils.paramEncode(String.valueOf(request.getRequireThumbnail())));
			requestParams.put("tags", ParamUtils.paramEncode(request.getOfficialTags()));
			
			JSONObject json = this.httpPostRequest(this.captureRequestPath, requestParams);
			response.setHttpStatus(Integer.valueOf(json.getJSONObject("meta").getString("status")));
			response.setMessage(json.getJSONObject("meta").getString("message"));
			if("null".equals(json.getJSONObject("response").toString())) {
				return response;
			}
			
			response.setImageUrl(json.getJSONObject("response").getString("image_url"));
			response.setId(json.getJSONObject("response").getString("id"));
		}
		catch(UnsupportedEncodingException e) { throw new CaptureException(e.getMessage()); }
		catch(NoSuchAlgorithmException e)     { throw new CaptureException(e.getMessage()); }
		catch(InvalidKeyException e)          { throw new CaptureException(e.getMessage()); }
		catch(ClientProtocolException e)      { throw new CaptureException(e.getMessage()); }
		catch(IOException e)                  { throw new CaptureException(e.getMessage()); }
		
		return response;
	}
}
