package com.sumavision.tetris.capacity.exception;

import com.sumavision.tetris.commons.exception.BaseException;
import com.sumavision.tetris.commons.exception.code.StatusCode;
import com.sumavision.tetris.commons.util.wrapper.StringBufferWrapper;

/**
 * 输入异常<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月20日 上午9:17:32
 */
public class InputResponseErrorException extends BaseException{
	
	private static final long serialVersionUID = 1L;

	public InputResponseErrorException(String message) {
		super(StatusCode.FORBIDDEN, new StringBufferWrapper().append("输入异常：")
															 .append(message)
															 .toString());
	}

}
