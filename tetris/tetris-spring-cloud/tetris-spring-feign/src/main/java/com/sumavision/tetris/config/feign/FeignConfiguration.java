package com.sumavision.tetris.config.feign;

import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sumavision.tetris.mvc.constant.HttpConstant;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@Configuration
public class FeignConfiguration implements RequestInterceptor{

	private static final Logger LOG = LoggerFactory.getLogger(FeignConfiguration.class);
	
	@Override
	public void apply(RequestTemplate template) {
		ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		
		//内部feign调用id识别
    	template.header(HttpConstant.HEADER_FEIGN_CLIENT, HttpConstant.HEADER_FEIGN_CLIENT_KEY);
		
		if(attributes != null){
			HttpServletRequest request = attributes.getRequest();
	    	Enumeration<String> headerNames = request.getHeaderNames();
	        if (headerNames != null) {
	            while (headerNames.hasMoreElements()) {
	                String name = headerNames.nextElement();
	                String values = request.getHeader(name);
	                template.header(name, values);
	            }
	            //LOG.info("feign interceptor header:{}",template);
	        }
		}else{
			LOG.info("当前线程没有绑定请求（系统初始化）：", template);
			return;
		}
       
	}

}
