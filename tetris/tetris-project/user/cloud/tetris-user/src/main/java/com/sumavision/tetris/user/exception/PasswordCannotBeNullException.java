package com.sumavision.tetris.user.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sumavision.tetris.commons.exception.BaseException;
import com.sumavision.tetris.commons.exception.code.StatusCode;

public class PasswordCannotBeNullException extends BaseException{

	private static final Logger LOG = LoggerFactory.getLogger(PasswordCannotBeNullException.class);
	
	private static final long serialVersionUID = 1L;

	public PasswordCannotBeNullException() {
		super(StatusCode.FORBIDDEN, "密码不能为空！");
		LOG.error("密码不能为空！");
	}

}
