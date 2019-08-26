package com.sumavision.tetris.mvc.ext.response.json.exception;

import com.sumavision.tetris.commons.exception.BaseException;
import com.sumavision.tetris.commons.exception.code.StatusCode;
import com.sumavision.tetris.commons.util.wrapper.StringBufferWrapper;

/**
 * 处理器有@JsonBody注解在debug模式下
 * 没有获取到jsonp回调
 * lvdeyang 2018年01月10日
 */
public class NoCallbackFoundForJsonBodyInDebugModeException extends BaseException{

	private static final long serialVersionUID = 1L;

	public NoCallbackFoundForJsonBodyInDebugModeException(String handler){
		super(StatusCode.ERROR, new StringBufferWrapper().append("当前处理器：")
														 .append(handler)
														 .append("没有获取到jsonp回调，请确认前端也开启debug模式！")
														 .toString());
	}
	
}
