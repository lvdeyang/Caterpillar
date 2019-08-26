package com.sumavision.tetris.mvc.controller;

import java.net.URLEncoder;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.sumavision.tetris.commons.exception.BaseException;
import com.sumavision.tetris.commons.exception.code.StatusCode;
import com.sumavision.tetris.commons.util.wrapper.StringBufferWrapper;

/**
 * 统一异常处理，注意：有@JsonBody的controller异常由@JsonBody处理<br/>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年7月10日 下午2:58:43
 */
@ControllerAdvice
public class ErrorController {

	@SuppressWarnings("deprecation")
	@ExceptionHandler({Exception.class})
	public ModelAndView loginError(Exception e){
		ModelAndView mv = null;
		String redirectUrl = "/web/app/error/request-fail.html";
		String hash = null;
		if(e instanceof BaseException){
			BaseException baseException = (BaseException)e;
			hash = URLEncoder.encode(new StringBufferWrapper().append(baseException.getCode().getCode()).append("&&").append(baseException.getMessage()).toString());
			if(baseException.getForwardPath() != null){
				redirectUrl = baseException.getForwardPath();
			}
		}else{
			hash = URLEncoder.encode(new StringBufferWrapper().append(StatusCode.ERROR.getCode()).append("&&").append(e.getMessage()).toString());
		}
		hash = URLEncoder.encode(hash);
		mv = new ModelAndView(new StringBufferWrapper().append("redirect:")
													   .append(redirectUrl)
													   .append("#")
													   .append(hash)
													   .toString());
		return mv;
	}
	
}
