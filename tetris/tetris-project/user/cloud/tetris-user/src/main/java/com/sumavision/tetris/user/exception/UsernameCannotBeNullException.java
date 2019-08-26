package com.sumavision.tetris.user.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sumavision.tetris.commons.exception.BaseException;
import com.sumavision.tetris.commons.exception.code.StatusCode;

public class UsernameCannotBeNullException extends BaseException{

	private static final Logger LOG = LoggerFactory.getLogger(UsernameCannotBeNullException.class);
	
	private static final long serialVersionUID = 1L;

	public UsernameCannotBeNullException() {
		super(StatusCode.FORBIDDEN, "用户名不能为空！");
		LOG.error("用户名为空！");
	}

}
