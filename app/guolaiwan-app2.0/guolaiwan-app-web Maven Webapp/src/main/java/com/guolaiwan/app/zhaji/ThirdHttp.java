package com.guolaiwan.app.zhaji;


import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;

public class ThirdHttp {
	private final static  String IP="http://116.63.168.20:8080/checking";
	private final static  String CHECKIN="/checkIn";
	private final static  String CHEKAVAILABLE="/checkAvailable";
	private final static  String HASHEARTBEAT="/hasHeartbeat";
	private  CloseableHttpClient httpClient;
	private RequestConfig requestConfig;
    private static ThirdHttp instance;
    public static ThirdHttp getInstance(){
        synchronized (ThirdHttp.class){
            if (instance==null){
                synchronized (ThirdHttp.class){
                    instance=new ThirdHttp();
                    return instance;
                }
            }
        }
        return instance;
    }
    
    public ThirdHttp(){
        httpClient = HttpClientBuilder.create().build();
	    requestConfig = RequestConfig.custom()
				// 设置连接超时时间(单位毫秒)
				.setConnectTimeout(5000)
				// 设置请求超时时间(单位毫秒)
				.setConnectionRequestTimeout(5000)
				// socket读写超时时间(单位毫秒)
				.setSocketTimeout(5000)
				// 设置是否允许重定向(默认为true)
				.setRedirectsEnabled(true).build();
    }

    
    /**
     * 闸机签到
     * @param uniqueCode
     * @param version
     * @return
     */
    public String checkIn(String uniqueCode,String version){
		// 参数
		StringBuffer params = new StringBuffer();
		params.append("uniqueCode=" +uniqueCode);
		params.append("&");
		params.append("version="+version);
		HttpGet httpGet = new HttpGet(IP+CHECKIN+"?" + params);
		httpGet.setConfig(requestConfig);
		CloseableHttpResponse response = null;
		HttpEntity responseEntity =null;
		try {
			response = httpClient.execute(httpGet);
		    responseEntity = response.getEntity();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {

			if (response != null) {
				response.close();
			}
			return EntityUtils.toString((org.apache.http.HttpEntity) responseEntity);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return null;
		}
    }
    
    
    /**
     * 闸机心跳包
     */
    public String checkHeart(String uniqueCode){
		// 参数
		StringBuffer params = new StringBuffer();
		params.append("uniqueCode=" +uniqueCode);
		HttpGet httpGet = new HttpGet(IP+HASHEARTBEAT+"?" + params);
		httpGet.setConfig(requestConfig);
		CloseableHttpResponse response = null;
		HttpEntity responseEntity =null;
		try {
			response = httpClient.execute(httpGet);
		    responseEntity = response.getEntity();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {

			if (response != null) {
				response.close();
			}
			return EntityUtils.toString((org.apache.http.HttpEntity) responseEntity);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return null;
		}
    }
  
    
    /**
     * 检票
     * @param po
     * @return
     */
    public String checkAvailable(AvailablePo po){
    	HttpPost httpPost = new HttpPost(IP+CHEKAVAILABLE);
    	String jsonString = JSON.toJSONString(po);
		StringEntity entity = new StringEntity(jsonString, "UTF-8"); 
		httpPost.setEntity(entity); 
		httpPost.setHeader("Content-Type", "application/json;charset=utf8");
		CloseableHttpResponse response = null;
		String ResponseStr=null;
		try {
			response = httpClient.execute(httpPost);
			HttpEntity responseEntity =  response.getEntity();
			ResponseStr=EntityUtils.toString((org.apache.http.HttpEntity) responseEntity);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
		
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return ResponseStr;
    }
}
