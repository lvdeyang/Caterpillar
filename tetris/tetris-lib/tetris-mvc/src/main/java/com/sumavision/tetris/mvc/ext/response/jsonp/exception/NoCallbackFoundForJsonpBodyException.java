package com.sumavision.tetris.mvc.ext.response.jsonp.exception;

import com.sumavision.tetris.commons.exception.BaseException;
import com.sumavision.tetris.commons.exception.code.StatusCode;
import com.sumavision.tetris.commons.util.wrapper.StringBufferWrapper;

/**
 * 处理器有@JsonpBody注解
 * 前端没有传jsonp回调
 * lvdeyang 2018年01月10日
 */
public class NoCallbackFoundForJsonpBodyException extends BaseException{

	private static final long serialVersionUID = 1L;

	public NoCallbackFoundForJsonpBodyException(String handler){
		super(StatusCode.ERROR, new StringBufferWrapper().append("当前处理器：")
														 .append(handler)
														 .append("没有获取到jsonp回调，请确认前端发送的是jsonp请求！")
														 .toString());
	}
	
}
