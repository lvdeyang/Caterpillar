package com.sumavision.tetris.spring.zuul.auth.filter.android;

import javax.servlet.http.HttpServletRequest;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.sumavision.tetris.commons.context.SpringContext;
import com.sumavision.tetris.commons.util.uri.UriUtil;
import com.sumavision.tetris.commons.util.wrapper.StringBufferWrapper;
import com.sumavision.tetris.mvc.constant.HttpConstant;
import com.sumavision.tetris.mvc.ext.request.RequestUserAgentAnalyzer;
import com.sumavision.tetris.user.UserQuery;

/**
 * android采集系统拦截器<br/>
 * <b>作者:</b>lzp<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年6月4日 下午1:34:26
 */
public class ApiAndroidLoginFilter extends ZuulFilter{

	private String[] ignores = new String[]{
		"", 
		"/api/android/auth/*"
	};
	
	@Override
	public Object run() {
		RequestUserAgentAnalyzer analyzer = SpringContext.getBean(RequestUserAgentAnalyzer.class);
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		
		//不需要登录访问
		String requestUri = request.getRequestURI();
		requestUri = requestUri.replace(new StringBufferWrapper().append("/").append(requestUri.split("/")[1]).toString(), "");
		if(UriUtil.match(requestUri, ignores)) return null;
		
		//登录校验
		String token = request.getHeader(HttpConstant.HEADER_AUTH_TOKEN);
		UserQuery userQuery = SpringContext.getBean(UserQuery.class);
		
		try{
			boolean result = userQuery.checkToken(token);
			if(!result){
				ctx.setResponseStatusCode(403);
				ctx.setSendZuulResponse(false);
			}
		}catch(Exception e){
			e.printStackTrace();
			ctx.setResponseStatusCode(403);
			ctx.setSendZuulResponse(false);
		}
		
		return null;
	}

	@Override
	public boolean shouldFilter() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		String requestUri = request.getRequestURI();
		requestUri = requestUri.replace(new StringBufferWrapper().append("/").append(requestUri.split("/")[1]).toString(), "");
		if(requestUri.startsWith("/api/android")){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public String filterType() {
		return "pre";
	}

}
