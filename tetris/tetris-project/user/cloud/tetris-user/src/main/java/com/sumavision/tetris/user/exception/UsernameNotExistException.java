package com.sumavision.tetris.user.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sumavision.tetris.commons.exception.BaseException;
import com.sumavision.tetris.commons.exception.code.StatusCode;
import com.sumavision.tetris.commons.util.date.DateUtil;
import com.sumavision.tetris.commons.util.wrapper.StringBufferWrapper;

public class UsernameNotExistException extends BaseException{

	private static final Logger LOG = LoggerFactory.getLogger(UsernameNotExistException.class);
	
	private static final long serialVersionUID = 1L;

	public UsernameNotExistException(String username) {
		super(StatusCode.FORBIDDEN, "用户不存在！", "/web/app/login/login.html");
		LOG.error(DateUtil.now());
		LOG.error(new StringBufferWrapper().append("用户不存在！用户名：")
										   .append(username)
										   .toString());
	}

}
