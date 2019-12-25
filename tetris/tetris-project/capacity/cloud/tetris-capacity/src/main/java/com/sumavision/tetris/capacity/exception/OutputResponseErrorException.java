package com.sumavision.tetris.capacity.exception;

import com.sumavision.tetris.commons.exception.BaseException;
import com.sumavision.tetris.commons.exception.code.StatusCode;
import com.sumavision.tetris.commons.util.wrapper.StringBufferWrapper;

public class OutputResponseErrorException extends BaseException{
	
	private static final long serialVersionUID = 1L;

	public OutputResponseErrorException(String message) {
		super(StatusCode.ERROR, new StringBufferWrapper().append("输出异常：")
															 .append(message)
															 .toString());
	}

}
