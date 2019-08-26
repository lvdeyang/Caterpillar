package com.sumavision.tetris.user.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sumavision.tetris.commons.exception.BaseException;
import com.sumavision.tetris.commons.exception.code.StatusCode;
import com.sumavision.tetris.commons.util.wrapper.StringBufferWrapper;

public class UserNotExistException extends BaseException{

	private static final Logger LOG = LoggerFactory.getLogger(UserNotExistException.class);
	
	private static final long serialVersionUID = 1L;

	public UserNotExistException(Long userId) {
		super(StatusCode.FORBIDDEN, "用户不存在！");
		LOG.error(new StringBufferWrapper().append("用户不存在！用户id：")
										   .append(userId)
										   .toString());
	}

}
