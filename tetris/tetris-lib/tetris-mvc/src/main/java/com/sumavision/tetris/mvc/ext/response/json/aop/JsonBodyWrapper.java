package com.sumavision.tetris.mvc.ext.response.json.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.sumavision.tetris.commons.exception.BaseException;
import com.sumavision.tetris.commons.exception.code.StatusCode;
import com.sumavision.tetris.commons.util.wrapper.StringBufferWrapper;
import com.sumavision.tetris.mvc.ext.response.json.exception.NoCallbackFoundForJsonBodyInDebugModeException;
import com.sumavision.tetris.mvc.ext.response.json.exception.NoRequestFoundForJsonBodyInDebugModeException;
import com.sumavision.tetris.mvc.wrapper.UrlDecoderHttpServletRequestWrapper;

/**
 * json 返回包装
 * lvdeyang 2018年01月10日
 */

@Component  
@Aspect	
@Order(100)
public class JsonBodyWrapper {
	
	private static final Logger LOG = LoggerFactory.getLogger(JsonBodyWrapper.class);
	
	public static final boolean DEBUG = false;
	
	//@Pointcut("(execution(public void *(..)) || execution(public java.lang.Object *(..))) && @annotation(com.suma.tetris.mvc.ext.response.json.aop.annotation.JsonBody)")
	@Pointcut("execution(public java.lang.Object *(..)) && @annotation(com.sumavision.tetris.mvc.ext.response.json.aop.annotation.JsonBody)")
    public void jsonBody(){}
	
	@Around("jsonBody()")
	public Object wrap(ProceedingJoinPoint joinPoint) throws Exception{
		
		//json返回
		JSONObject jsonResult = new JSONObject();
		
		//jsonp返回
		StringBufferWrapper jsonpResult = new StringBufferWrapper();
		
		//切入点参数
		Object[] args = joinPoint.getArgs();
		
		//jsonp模式需要将handler中传入HttpServletRequest
		HttpServletRequest request = null;
		
		if(DEBUG){
			for(Object arg: args){
				if(arg instanceof HttpServletRequest){
					request = (HttpServletRequest)arg;
					break;
				}
			}
		}
		
		if(DEBUG && request==null){
			String target = joinPoint.toString();
			throw new NoRequestFoundForJsonBodyInDebugModeException(target.substring(13, target.length()-1));
		}
		
		//jsonp回调
		String jsonp = null;
		
		if(DEBUG){
			UrlDecoderHttpServletRequestWrapper requestWrapper = new UrlDecoderHttpServletRequestWrapper(request);
			jsonp = requestWrapper.getParameter("jsonp");
			if(jsonp == null){
				String target = joinPoint.toString();
				throw new NoCallbackFoundForJsonBodyInDebugModeException(target.substring(13, target.length()-1));
			}
		}
		
		try{
			// 执行原代码
			Object data = joinPoint.proceed(args);
			
			//返回成功状态
			jsonResult.put("status", StatusCode.SUCCESS.getCode());
			jsonResult.put("message", "操作成功");
			jsonResult.put("data", data);
			
			//debug模式变成jsonp返回
			if(DEBUG && request!=null){
				//处理jsonp回调
				jsonpResult.append(jsonp).append("(").append(jsonResult.toJSONString()).append(")");
			}
			
		} catch (Throwable e) {
			
			//打印异常
			LOG.error("发生异常啦！", e);
			
			//返回错误信息
			if(e instanceof BaseException){
				BaseException bex = (BaseException)e;
				jsonResult.put("status", bex.getCode().getCode());
				jsonResult.put("message", bex.getMessage());
				if(bex.getForwardPath() != null){
					jsonResult.put("redirect", bex.getForwardPath());
				}
			}else{
				jsonResult.put("status", StatusCode.ERROR.getCode());
				jsonResult.put("message", "服务器端异常");
			}
			
			//debug模式变成jsonp返回
			if(DEBUG && request!=null){
				//处理jsonp回调
				jsonpResult.append(jsonp).append("(").append(jsonResult.toJSONString()).append(")");
			}
			
		}
		
		if(DEBUG){
			return jsonpResult.toString();
		}else{
			return jsonResult;
		}
	}
	
}
