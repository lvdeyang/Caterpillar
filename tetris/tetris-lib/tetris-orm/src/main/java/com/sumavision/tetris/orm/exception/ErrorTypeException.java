package com.sumavision.tetris.orm.exception;

import com.sumavision.tetris.commons.exception.BaseException;
import com.sumavision.tetris.commons.exception.code.StatusCode;
import com.sumavision.tetris.commons.util.wrapper.StringBufferWrapper;

public class ErrorTypeException extends BaseException{

	private static final long serialVersionUID = 1L;

	public ErrorTypeException(String column, Object value) {
		super(StatusCode.FORBIDDEN, new StringBufferWrapper().append("错误的枚举：")
															 .append(column)
															 .append(":")
															 .append(value)
															 .toString());
	}
	
}
