package com.sumavision.tetris.capacity.exception;

import com.sumavision.tetris.commons.exception.BaseException;
import com.sumavision.tetris.commons.exception.code.StatusCode;
import com.sumavision.tetris.commons.util.wrapper.StringBufferWrapper;

public class TaskResponseErrorException extends BaseException{
	
	private static final long serialVersionUID = 1L;

	public TaskResponseErrorException(String message) {
		super(StatusCode.ERROR, new StringBufferWrapper().append("任务异常：")
														     .append(message)
														     .toString());
	}

}
