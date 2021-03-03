package com.sumavision.tetris.mvc.wrapper;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sumavision.tetris.commons.util.wrapper.StringBuilderWrapper;

/**
 * 将请求解析成json<br/>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2018年11月30日 下午1:53:32
 */
public class JSONHttpServletRequestWrapper extends HttpServletRequestWrapper{

	private HttpServletRequest nativeRequest;
	
	private CachedHttpServletRequestWrapper cachedRequest;
	
	public HttpServletRequest getNativeRequest() {
		return nativeRequest;
	}

	public CachedHttpServletRequestWrapper getCachedRequest() {
		return cachedRequest;
	}

	private JSONObject params;
	
	public JSONHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
		super(request);
		this.nativeRequest = request;
		this.cachedRequest = new CachedHttpServletRequestWrapper(request);
		parse();
	}
	
	public JSONHttpServletRequestWrapper(CachedHttpServletRequestWrapper request) throws IOException{
		super(request.getNativeRequest());
		this.nativeRequest = request.getNativeRequest();
		this.cachedRequest = request;
		parse();
	}
	
	private void parse() throws IOException{
		BufferedReader br = null;
		try {
			br = this.cachedRequest.getReader();
			String line = null;
			StringBuilderWrapper sb = new StringBuilderWrapper();
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			this.params = JSON.parseObject(sb.toString());
		}finally{
			if(br != null) br.close();
		}
	}

	@Override
	public String getParameter(String name) {
		return this.params.getString(name);
	}
	
	public Boolean getBoolean(String name){
		return this.params.getBoolean(name);
	}
	
	public boolean getBooleanValue(String name){
		return this.params.getBooleanValue(name);
	}
	
	public String getString(String name){
		return this.params.getString(name);
	}
	
	public Integer getInteger(String name){
		return this.params.getInteger(name);
	}
	
	public int getIntValue(String name){
		return this.params.getIntValue(name);
	}
	
	public Long getLong(String name){
		return this.params.getLong(name);
	}
	
	public long getLongValue(String name){
		return this.params.getLongValue(name);
	}
	
	public Float getFloat(String name){
		return this.params.getFloat(name);
	}
	
	public float getFloatValue(String name){
		return this.params.getFloatValue(name);
	}
	
	public String getParametersStr() {
		return this.params.toJSONString();
	}
	
}
