package com.sumavision.tetris.spring.zuul.servlet.filter;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import com.alibaba.fastjson.JSONObject;
import com.sumavision.tetris.commons.context.SpringContext;
import com.sumavision.tetris.commons.exception.code.StatusCode;
import com.sumavision.tetris.mvc.constant.HttpConstant;
import com.sumavision.tetris.mvc.ext.request.RequestResouceTypeAnalyzer;

/**
 * 网关安全访问<br/>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年4月3日 上午8:56:37
 */
@Order(0)
@WebFilter(urlPatterns = "/*", filterName = "com.sumavision.tetris.spring.zuul.servlet.filter.AccessSecurityFilter")
public class AccessSecurityFilter implements Filter{

	private static final Logger LOG = LoggerFactory.getLogger(AccessSecurityFilter.class);
	
	private RequestResouceTypeAnalyzer resouceTypeAnalyzer;
	
	public void initBean(){
		if(resouceTypeAnalyzer==null) resouceTypeAnalyzer = SpringContext.getBean(RequestResouceTypeAnalyzer.class);
	}
	
	@Override
	public void doFilter(ServletRequest nativeRequest, ServletResponse nativeResponse, FilterChain chain)
			throws IOException, ServletException {
		
		initBean();
		
		HttpServletRequest request = (HttpServletRequest)nativeRequest;
		HttpServletResponse response = (HttpServletResponse)nativeResponse;
		
		//允许feign调用
		String clientKey = request.getHeader(HttpConstant.HEADER_FEIGN_CLIENT);
		if(HttpConstant.HEADER_FEIGN_CLIENT_KEY.equals(clientKey)){
			chain.doFilter(request, response);
			return;
		}
		
		String requestUri = request.getRequestURI();
		
		//允许静态资源访问
		if(resouceTypeAnalyzer.isStaticResource(requestUri)){
			chain.doFilter(request, response);
			return;
		}
		
		JSONObject jsonResult = new JSONObject();
		
		if(!requestUri.startsWith("/tetris-spring-eureka") &&
				!requestUri.startsWith("/tetris-spring-zull") && 
				!requestUri.startsWith("/tetris-menu") && 
				!requestUri.startsWith("/tetris-user") && 
				!requestUri.startsWith("/tetris-mims") && 
				!requestUri.startsWith("/tetris-cms") && 
				!requestUri.startsWith("/tetris-easy-process") && 
				!requestUri.startsWith("/tetris-media-editor") && 
				!requestUri.startsWith("/tetris-capacity") &&
				!requestUri.startsWith("/tetris-cs")){
			
			LOG.error("----------------------------");
			LOG.error(requestUri);
			jsonResult.put("status", StatusCode.FORBIDDEN.getCode());
			jsonResult.put("message", "非法访问！");
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.write(jsonResult.toJSONString());
			writer.close();
			
		}else{
			chain.doFilter(request, response);
			return;
		}
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException{}
	
	@Override
	public void destroy(){}
	
}
