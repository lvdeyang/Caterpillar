package com.sumavision.tetris.user.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sumavision.tetris.commons.exception.BaseException;
import com.sumavision.tetris.commons.exception.code.StatusCode;
import com.sumavision.tetris.commons.util.date.DateUtil;
import com.sumavision.tetris.commons.util.wrapper.StringBufferWrapper;

public class PasswordErrorException extends BaseException{

	private static final Logger LOG = LoggerFactory.getLogger(PasswordErrorException.class);
	
	private static final long serialVersionUID = 1L;

	public PasswordErrorException() {
		super(StatusCode.FORBIDDEN, "密码错误！", "/web/app/login/login.html");
		LOG.error("密码错误！");
	}

	public PasswordErrorException(String username, String password) {
		super(StatusCode.FORBIDDEN, "密码错误！", "/web/app/login/login.html");
		LOG.error(DateUtil.now());
		LOG.error(new StringBufferWrapper().append("密码错误！用户名：")
										   .append(username)
										   .append("，密码：")
										   .append(password)
										   .toString());
	}
	
}
