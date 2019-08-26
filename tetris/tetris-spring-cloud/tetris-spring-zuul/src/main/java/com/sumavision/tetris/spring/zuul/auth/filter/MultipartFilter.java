package com.sumavision.tetris.spring.zuul.auth.filter;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.IS_DISPATCHER_SERVLET_REQUEST_KEY;

import javax.servlet.http.HttpServletRequest;
import org.springframework.cloud.netflix.zuul.filters.pre.ServletDetectionFilter;
import org.springframework.web.servlet.DispatcherServlet;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.http.HttpServletRequestWrapper;

/**
 * 解决中文文件乱码问题<br/>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年5月31日 下午6:22:13
 */
public class MultipartFilter extends ServletDetectionFilter{

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
	    HttpServletRequest request = ctx.getRequest();
	    String contentType = request.getHeader("Content-Type");
	    if(contentType!=null && contentType.indexOf("multipart") >= 0){
	    	ctx.set(IS_DISPATCHER_SERVLET_REQUEST_KEY, false);
	    }else{
	    	if (!(request instanceof HttpServletRequestWrapper) 
			        && isDispatcherServletRequest(request)) {
			    ctx.set(IS_DISPATCHER_SERVLET_REQUEST_KEY, true);
			} else {
			    ctx.set(IS_DISPATCHER_SERVLET_REQUEST_KEY, false);
			}
	    }
	    
		return super.run();
	}

	private boolean isDispatcherServletRequest(HttpServletRequest request) {
        return request.getAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE) != null;
    }  
	
}
