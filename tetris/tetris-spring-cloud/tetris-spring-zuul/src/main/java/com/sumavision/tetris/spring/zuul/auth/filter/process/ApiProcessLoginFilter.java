package com.sumavision.tetris.spring.zuul.auth.filter.process;

import javax.servlet.http.HttpServletRequest;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.sumavision.tetris.auth.login.LoginService;
import com.sumavision.tetris.commons.context.SpringContext;
import com.sumavision.tetris.commons.util.uri.UriUtil;
import com.sumavision.tetris.commons.util.wrapper.StringBufferWrapper;
import com.sumavision.tetris.mvc.constant.HttpConstant;

/**
 * 流程客户端拦截器<br/>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年3月12日 下午1:34:26
 */
public class ApiProcessLoginFilter extends ZuulFilter{

	private String[] ignores = new String[]{
		
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
			if(HttpConstant.HEADER_PROCESS_CLIENT_KEY.equals(request.getHeader(HttpConstant.HEADER_PROCESS_CLIENT))){
				//做用户id强制登录
				String userId = request.getHeader(HttpConstant.HEADER_PROCESS_DO_USER_ID_LOGIN);
				LoginService loginService = SpringContext.getBean(LoginService.class);
				String token = loginService.doUserIdLogin(Long.valueOf(userId));
				
				//写入开发者登录信息
				ctx.addZuulRequestHeader(HttpConstant.HEADER_AUTH_TOKEN, token);
			}else{
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
		if(requestUri.startsWith("/api/process")){
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
