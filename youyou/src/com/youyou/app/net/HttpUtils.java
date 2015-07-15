package com.youyou.app.net;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.youyou.app.YouYouApplication;
import com.youyou.app.utils.PreManager;

import android.preference.PreferenceActivity.Header;
import android.text.TextUtils;

public class HttpUtils {

	/**
	 * 网络连接处理
	 * @param url
	 * @param map
	 * @return
	 */
	public static String doPost(String url,HashMap<String,String> map,boolean needCookie) {
		String result = "";
		try{
	        HttpPost httpost = new HttpPost(url);
	        List<NameValuePair> nvps = new ArrayList<NameValuePair>();

	        for(String key : map.keySet()) {
	        	nvps.add(new BasicNameValuePair(key, (String)map.get(key)));	
	        }
	        httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
	        if (needCookie) {
		        String localCookie = PreManager.instance().getCookie(YouYouApplication.instance.getApplicationContext());
		        if (!TextUtils.isEmpty(localCookie)) {
		        	httpost.addHeader("Cookie", localCookie);				
				}				
			}
	        DefaultHttpClient httpclient = new DefaultHttpClient();
	        HttpParams params = httpclient.getParams();
	        HttpConnectionParams.setConnectionTimeout(params, TIMEOUT_CONNECTION);
	        HttpConnectionParams.setSoTimeout(params, TIMEOUT_SO);
	        
	        HttpResponse response = null;
	        response = httpclient.execute(httpost);
	        if (response.containsHeader("Set-Cookie")) {
	        	String localCookie = PreManager.instance().getCookie(YouYouApplication.instance.getApplicationContext());
	        	String cookie = response.getFirstHeader("Set-Cookie").getValue();
		        if (TextUtils.isEmpty(localCookie) || !localCookie.equals(cookie)) {
					PreManager.instance().saveCookie(YouYouApplication.instance.getApplicationContext(), cookie);
				}				
			}
	        
			if(isResponseAvailable(response)){
				try {
					result = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static String doGetString(String preUrl,HashMap<String, String> map) {
		StringBuffer sbBuffer = new StringBuffer();
		sbBuffer.append(preUrl);
        for(String key : map.keySet()) {
        	sbBuffer.append("&").append(key).append("=").append((String)map.get(key));	
        }
		return doGetString(sbBuffer.toString());
	}
	
	public static String doGetString(String strUrl){
		String strResult = null;
		HttpResponse response = doConnection(strUrl);
		if(isResponseAvailable(response)){
			try {
				strResult = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return strResult;
	}
	
	private static boolean	isResponseAvailable(HttpResponse response){
		if(response != null && 
				response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
			return true;
		}
		return false;
	}
	
	private static final int TIMEOUT_CONNECTION = 8000;
	private static final int TIMEOUT_SO = 30000;
	
	private static HttpResponse doConnection(String strUrl){
		HttpResponse response = null;
		try {
			final URI uri = new URI(strUrl);
			HttpGet httpGet = new HttpGet(uri);
	        String localCookie = PreManager.instance().getCookie(YouYouApplication.instance.getApplicationContext());
	        if (!TextUtils.isEmpty(localCookie)) {
	        	httpGet.addHeader("Cookie", localCookie);				
			}
	        HttpClient httpClient = new DefaultHttpClient();
	        HttpParams params = httpClient.getParams();
	        HttpConnectionParams.setConnectionTimeout(params, TIMEOUT_CONNECTION);
	        HttpConnectionParams.setSoTimeout(params, TIMEOUT_SO);
	        
	        response = httpClient.execute(httpGet);
	        if (response.containsHeader("Set-Cookie")) {
	        	String cookie = response.getFirstHeader("Set-Cookie").getValue();
		        if (TextUtils.isEmpty(localCookie) || !localCookie.equals(cookie)) {
					PreManager.instance().saveCookie(YouYouApplication.instance.getApplicationContext(), cookie);
				}				
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return response;
	}
	
	public static String doUpload(String url,HashMap<String,Object> map,boolean needCookie){
		String result = "";
		try {
			HttpPost httppost = new HttpPost(url);        
	        MultipartEntity reqEntity = new MultipartEntity();
	        for(String key : map.keySet()) {
	        	if("file".equals(key)) {
	        		File file= (File) map.get(key);
	        		FileBody body = new FileBody(file);
	        		reqEntity.addPart(key, body);
	        	}else {
	        		StringBody body = new StringBody((String) map.get(key));
					reqEntity.addPart(key, body);
	        	}
	        }
	        httppost.setEntity(reqEntity);
	        if (needCookie) {
		        String localCookie = PreManager.instance().getCookie(YouYouApplication.instance.getApplicationContext());
		        if (!TextUtils.isEmpty(localCookie)) {
		        	httppost.addHeader("Cookie", localCookie);				
				}				
			}        
	        DefaultHttpClient httpclient = new DefaultHttpClient();
	        HttpParams params = httpclient.getParams();
	        HttpConnectionParams.setConnectionTimeout(params, TIMEOUT_CONNECTION);
	        HttpConnectionParams.setSoTimeout(params, TIMEOUT_SO);
	        
	        HttpResponse response = httpclient.execute(httppost);
	        if (response.containsHeader("Set-Cookie")) {
	        	String localCookie = PreManager.instance().getCookie(YouYouApplication.instance.getApplicationContext());
	        	String cookie = response.getFirstHeader("Set-Cookie").getValue();
		        if (TextUtils.isEmpty(localCookie) || !localCookie.equals(cookie)) {
					PreManager.instance().saveCookie(YouYouApplication.instance.getApplicationContext(), cookie);
				}				
			}    
	        
			if(isResponseAvailable(response)){
				try {
					result = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}	
}
