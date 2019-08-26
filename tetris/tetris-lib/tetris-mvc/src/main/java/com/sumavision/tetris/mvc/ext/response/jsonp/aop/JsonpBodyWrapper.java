package com.sumavision.tetris.mvc.ext.response.jsonp.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.sumavision.tetris.commons.exception.BaseException;
import com.sumavision.tetris.commons.exception.code.StatusCode;
import com.sumavision.tetris.commons.util.wrapper.StringBufferWrapper;
import com.sumavision.tetris.mvc.ext.response.jsonp.exception.NoCallbackFoundForJsonpBodyException;
import com.sumavision.tetris.mvc.ext.response.jsonp.exception.NoRequestFoundForJsonpBodyException;

/**
 * jsonp 返回包装
 * lvdeyang 2018年01月10日
 */

@Component  
@Aspect	   
public class JsonpBodyWrapper {
	
	private static final Logger LOG = LoggerFactory.getLogger(JsonpBodyWrapper.class);
	
	//@Pointcut("(execution(public void *(..)) || execution(public java.lang.Object *(..))) && @annotation(com.suma.tetris.mvc.ext.response.jsonp.aop.annotation.JsonpBody)")
	@Pointcut("execution(public java.lang.Object *(..)) && @annotation(com.sumavision.tetris.mvc.ext.response.jsonp.aop.annotation.JsonpBody)")
	public void jsonpBody(){}
	
	@Around("jsonpBody()")
	public Object wrap(ProceedingJoinPoint joinPoint) throws Exception{
		
		//json返回
		JSONObject jsonResult = new JSONObject();
		
		//jsonp返回
		StringBufferWrapper jsonpResult = new StringBufferWrapper();
		
		//切入点参数
		Object[] args = joinPoint.getArgs();
		
		//jsonp模式需要将handler中传入HttpServletRequest
		HttpServletRequest request = null;
		
		for(Object arg: args){
			if(arg instanceof HttpServletRequest){
				request = (HttpServletRequest)arg;
				break;
			}
		}
		
		if(request==null){
			String target = joinPoint.toString();
			throw new NoRequestFoundForJsonpBodyException(target.substring(13, target.length()-1));
		}
		
		//jsonp回调
		String jsonp = request.getParameter("jsonp");
		if(jsonp == null){
			String target = joinPoint.toString();
			throw new NoCallbackFoundForJsonpBodyException(target.substring(13, target.length()-1));
		}
		
		try{
			// 执行原代码
			Object data = joinPoint.proceed(args);
			
			//返回成功状态
			jsonResult.put("status", StatusCode.SUCCESS.getCode());
			jsonResult.put("message", "操作成功");
			if(data!=null) jsonResult.put("data", data);
			
			//处理jsonp回调
			jsonpResult.append(jsonp).append("(").append(jsonResult.toJSONString()).append(")");
			
		} catch (Throwable e) {
			
			//打印异常
			LOG.error("发生异常啦！", e);
			
			//返回错误信息
			if(e instanceof BaseException){
				BaseException bex = (BaseException)e;
				jsonResult.put("status", bex.getCode().getCode());
				jsonResult.put("message", bex.getMessage());
			}else{
				jsonResult.put("status", StatusCode.ERROR.getCode());
				jsonResult.put("message", "服务器端异常");
			}
			
			//处理jsonp回调
			jsonpResult.append(jsonp).append("(").append(jsonResult.toJSONString()).append(")");
			
		}
		
		return jsonpResult.toString();
	}
	
}
