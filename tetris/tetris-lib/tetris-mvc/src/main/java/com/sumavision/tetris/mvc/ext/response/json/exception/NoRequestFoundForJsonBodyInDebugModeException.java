package com.sumavision.tetris.mvc.ext.response.json.exception;

import com.sumavision.tetris.commons.exception.BaseException;
import com.sumavision.tetris.commons.exception.code.StatusCode;
import com.sumavision.tetris.commons.util.wrapper.StringBufferWrapper;

/**
 * 处理器有@JsonBody注解在debug模式下
 * 没有传入HttpServletRequest参数
 * lvdeyang 2018年01月10日
 */
public class NoRequestFoundForJsonBodyInDebugModeException extends BaseException{

	private static final long serialVersionUID = 1L;

	public NoRequestFoundForJsonBodyInDebugModeException(String handler) {
		super(StatusCode.ERROR, new StringBufferWrapper().append("当前处理器：")
														 .append(handler)
														 .append("没有找到HttpServletRequest参数，在debug模式中，这个参数是必要的！")
														 .toString());
	}
	
}
