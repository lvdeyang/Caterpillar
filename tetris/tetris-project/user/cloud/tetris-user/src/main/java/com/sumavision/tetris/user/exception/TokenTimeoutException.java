package com.sumavision.tetris.user.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sumavision.tetris.commons.exception.BaseException;
import com.sumavision.tetris.commons.exception.code.StatusCode;
import com.sumavision.tetris.commons.util.wrapper.StringBufferWrapper;

public class TokenTimeoutException extends BaseException{
	
	private static final Logger LOG = LoggerFactory.getLogger(TokenTimeoutException.class);

	private static final long serialVersionUID = 1L;

	public TokenTimeoutException() {
		super(StatusCode.TIMEOUT, "超时，请重新登录！");
		LOG.error(new StringBufferWrapper().append("超时，请重新登录！")
										   .toString());
	}

}
