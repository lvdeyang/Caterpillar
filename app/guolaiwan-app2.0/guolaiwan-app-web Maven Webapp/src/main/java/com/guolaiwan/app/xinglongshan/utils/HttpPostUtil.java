package com.guolaiwan.app.xinglongshan.utils;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;

public class HttpPostUtil {
	/**
	 * POST 请求
	 * @param uri 请求地址
 	 * @param headers 请求头
	 * @param content 请求内容
	 * @param charset 请求/响应字符集
	 * @return 回传字符串
	 */
	public static String post(String uri, Map<String, String> headers, String content, String charset) {
		BasicHttpParams httpParams = new BasicHttpParams();
		httpParams.setParameter(HttpProtocolParams.HTTP_CONTENT_CHARSET, charset);
		httpParams.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 30000);
		httpParams.setParameter(CoreConnectionPNames.SO_TIMEOUT,new Integer(30000));
		HttpClient httpClient = new DefaultHttpClient(httpParams);
		HttpPost httpPost = new HttpPost(uri);
		try {
			HttpEntity httpEntity = new StringEntity(content, charset);
			httpPost.setEntity(httpEntity);
			if (headers != null) {
				for (Map.Entry<String, String> entry : headers.entrySet()) {
					httpPost.addHeader(entry.getKey(), entry.getValue());
				}
			}
			HttpResponse responseBody = httpClient.execute(httpPost);
			return EntityUtils.toString(responseBody.getEntity(),charset);
		} catch (Exception e) {
			throw new BusinessException("连接异常");
		}finally{
			httpClient.getConnectionManager().shutdown();
		}
	}
	/**
	 * 以UTF-8字符集请求
	 * @param uri 请求地址
	 * @param headers 请求头
	 * @param content 请求内容 
	 * @return 回传字符串
	 */
	public static String postUTF_8(String uri, Map<String, String> headers, String content) {
		return post(uri, headers, content, "UTF-8");
	}
	/**
	 * 键值对的 POST请求
	 * @param uri 请求路径
	 * @param headers 请求头
	 * @param nameValue 请求键值
	 * @return 回传字符串
	 */
	public static String postNameValue(String uri,Map<String,String> headers,Map<String,String[]> nameValue,String charset){
		BasicHttpParams httpParams = new BasicHttpParams();
		httpParams.setParameter(HttpProtocolParams.HTTP_CONTENT_CHARSET, charset);
		httpParams.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 30000);
		httpParams.setParameter(CoreConnectionPNames.SO_TIMEOUT,new Integer(30000));
		HttpClient httpClient = new DefaultHttpClient(httpParams);
		HttpPost httpPost = new HttpPost(uri);
		   List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		   if(nameValue!=null){
			   for(Map.Entry<String, String[]> entry:nameValue.entrySet())
			   { 
				   for(String value:entry.getValue()){
					   parameters.add(new BasicNameValuePair(entry.getKey(), value));
				   }
			   }
		   }
		try {
		    HttpEntity httpEntity = new UrlEncodedFormEntity(parameters, charset);
			httpPost.setEntity(httpEntity);
			if (headers != null) {
				for (Map.Entry<String, String> entry : headers.entrySet()) {
					httpPost.addHeader(entry.getKey(), entry.getValue());
				}
			}
			HttpResponse responseBody = httpClient.execute(httpPost);
			return EntityUtils.toString(responseBody.getEntity(),charset);
		} catch (ConnectTimeoutException e) {
			throw new BusinessException ("网络超时");
		}  catch (SocketTimeoutException e) {
			throw new BusinessException("网络超时");
		}catch (Exception e) {
			throw new BusinessException("访问异常");
		}finally{
			httpClient.getConnectionManager().shutdown();
		}
	}
	/**
	 * 以UTF-8 字符集请求回传  
	 * @param uri 请求路径
	 * @param headers 请求头
	 * @param nameValue 请求键值
	 * @return  请求回传字符串
	 */
	public static String postNameValueUTF_8(String uri,Map<String,String> headers,Map<String,String[]> nameValue){
		return postNameValue( uri, headers,nameValue,"UTF-8");
	}

}
