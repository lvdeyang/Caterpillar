package com.sumavision.tetris.util.http;

import java.net.URI;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

/**
 * http delete 带body<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年10月28日 下午2:12:02
 */
public class HttpDeleteWithBody extends HttpEntityEnclosingRequestBase{

	public static final String METHOD_NAME = "DELETE";
	
	@Override
	public String getMethod() {
		return METHOD_NAME;
	}
	
	public HttpDeleteWithBody(final String uri){
		super();
		setURI(URI.create(uri));
	}
	
	public HttpDeleteWithBody(final URI uri){
		super();
		setURI(uri);
	}
	
	public HttpDeleteWithBody(){
		super();
	}

}
