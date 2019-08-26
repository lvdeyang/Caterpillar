package com.sumavision.tetris.user.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sumavision.tetris.commons.exception.BaseException;
import com.sumavision.tetris.commons.exception.code.StatusCode;
import com.sumavision.tetris.commons.util.date.DateUtil;
import com.sumavision.tetris.commons.util.wrapper.StringBufferWrapper;

public class UsernameAlreadyExistException extends BaseException{

	private static final Logger LOG = LoggerFactory.getLogger(UsernameAlreadyExistException.class);
	
	private static final long serialVersionUID = 1L;

	public UsernameAlreadyExistException(String username) {
		super(StatusCode.FORBIDDEN, "用户名已存在！");
		LOG.error(DateUtil.now());
		LOG.error(new StringBufferWrapper().append("用户名已经存在！")
										   .append(username)
										   .toString());
	}

}
