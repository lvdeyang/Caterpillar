package com.sumavision.tetris.capacity.util.record;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.sumavision.tetris.commons.exception.BaseException;
import com.sumavision.tetris.commons.exception.code.StatusCode;
import com.sumavision.tetris.commons.util.wrapper.StringBufferWrapper;

/**
 * 能力服务器录制相关<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月25日 下午3:26:13
 */
public class RecordUtil {

	/** http客户端 */
	private CloseableHttpClient httpClient;
	
	/** http 配置参数 */
	private RequestConfig httpConfig;
	
	private static RecordUtil instance;
	
	private RecordUtil() {
		try {
			this.httpClient = HttpClients.createDefault();
			this.httpConfig = RequestConfig.custom()
									       .setConnectionRequestTimeout(6000)
									       .setConnectTimeout(6000)
									       .setSocketTimeout(12000)
									       .build();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static RecordUtil getInstance(){
		if(instance == null){
			instance = new RecordUtil();
		}
		return instance;
	}
	
	/**
	 * 获取录制vod.m3u8所在的文件夹名<br/>
	 * <b>作者:</b>wjw<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年11月25日 下午3:02:29
	 * @param String url record.xml所在路径
	 * @return String 文件夹名
	 */
	public String getVodFolderName(String url) throws Exception{
		
		HttpGet post = new HttpGet(url);
		post.setConfig(this.httpConfig);
		CloseableHttpResponse response = this.httpClient.execute(post);
		
		InputStreamReader inputStreamReader = null;
		BufferedReader reader = null;
		StringBufferWrapper sb = new StringBufferWrapper();
		try{
			if(response.getStatusLine().getStatusCode() == 200){
				inputStreamReader = new InputStreamReader(response.getEntity().getContent());
				reader = new BufferedReader(inputStreamReader);
				String line = "";
				while(true){
					line = reader.readLine();
					if(line == null) break;
					sb.append(line);
				}
			}else{
				throw new BaseException(StatusCode.FORBIDDEN, "record.xml请求失败！");
			}
		}finally{
			if(inputStreamReader != null) inputStreamReader.close();
			if(reader != null) reader.close();
			EntityUtils.consume(response.getEntity());
		}
		
		String folderName = "";
		try {
			folderName = sb.toString().split("</folder>")[0].split("<folder>")[1];
		} catch (Exception e) {
			throw new BaseException(StatusCode.FORBIDDEN, "录制文件不存在");
		}
		
		return folderName;
		
	}
	
}
