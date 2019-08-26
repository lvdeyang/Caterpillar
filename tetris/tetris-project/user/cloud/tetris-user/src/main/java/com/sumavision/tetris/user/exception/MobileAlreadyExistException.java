package com.sumavision.tetris.user.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sumavision.tetris.commons.exception.BaseException;
import com.sumavision.tetris.commons.exception.code.StatusCode;
import com.sumavision.tetris.commons.util.date.DateUtil;
import com.sumavision.tetris.commons.util.wrapper.StringBufferWrapper;

public class MobileAlreadyExistException extends BaseException{

	private static final Logger LOG = LoggerFactory.getLogger(MobileAlreadyExistException.class);
	
	private static final long serialVersionUID = 1L;

	public MobileAlreadyExistException(String mobile) {
		super(StatusCode.FORBIDDEN, "手机号已存在！");
		LOG.error(DateUtil.now());
		LOG.error(new StringBufferWrapper().append("手机号已存在！")
										   .append(mobile)
										   .toString());
	}

}
