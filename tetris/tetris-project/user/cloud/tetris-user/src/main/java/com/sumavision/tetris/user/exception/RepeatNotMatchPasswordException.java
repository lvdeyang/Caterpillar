package com.sumavision.tetris.user.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sumavision.tetris.commons.exception.BaseException;
import com.sumavision.tetris.commons.exception.code.StatusCode;

public class RepeatNotMatchPasswordException extends BaseException{

	private static final Logger LOG = LoggerFactory.getLogger(RepeatNotMatchPasswordException.class);
	
	private static final long serialVersionUID = 1L;

	public RepeatNotMatchPasswordException() {
		super(StatusCode.FORBIDDEN, "两次输入的密码不同！");
		LOG.error("两次输入的密码不同！");
	}

}
