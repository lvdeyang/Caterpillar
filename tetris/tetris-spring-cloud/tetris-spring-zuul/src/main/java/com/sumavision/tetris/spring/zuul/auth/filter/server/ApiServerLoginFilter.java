package com.sumavision.tetris.spring.zuul.auth.filter.server;

import javax.servlet.http.HttpServletRequest;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.http.HttpServletRequestWrapper;
import com.sumavision.tetris.auth.login.LoginService;
import com.sumavision.tetris.commons.context.SpringContext;
import com.sumavision.tetris.commons.util.uri.UriUtil;
import com.sumavision.tetris.commons.util.wrapper.StringBufferWrapper;
import com.sumavision.tetris.mvc.constant.HttpConstant;

/**
 * 服务端端拦截器<br/>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年3月12日 下午1:34:26
 */
public class ApiServerLoginFilter extends ZuulFilter{

	private String[] ignores = new String[]{
		"/api/server/cms/*", 
		"/api/server/compress/*",
		"/api/server/article/generate/with/internal/template"
	};
	
	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		
		//不需要登录访问
		String requestUri = request.getRequestURI();
		requestUri = requestUri.replace(new StringBufferWrapper().append("/").append(requestUri.split("/")[1]).toString(), "");
		if(UriUtil.match(requestUri, ignores)) return null;
		
		try{
			//登录校验
			String appId = request.getParameter("appId");
			String timestamp = request.getParameter("timestamp");
			String sign = request.getParameter("sign");
			
			if (appId == null && timestamp == null && sign == null) {
				HttpServletRequestWrapper httpServletRequestWrapper = (HttpServletRequestWrapper) request;
				HttpServletRequest servletRequest = httpServletRequestWrapper.getRequest();
				appId = servletRequest.getParameter("appId");
				timestamp = servletRequest.getParameter("timestamp");
				sign = servletRequest.getParameter("sign");
			}
			
			LoginService loginService = SpringContext.getBean(LoginService.class);
			
			String token = loginService.doAppSecretLogin(appId, timestamp, sign);
			
			//写入开发者登录信息
			ctx.addZuulRequestHeader(HttpConstant.HEADER_AUTH_TOKEN, token);
			ctx.addZuulRequestHeader(HttpConstant.HEADER_SESSION_ID, appId);
			
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
		if(requestUri.startsWith("/api/server")){
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
