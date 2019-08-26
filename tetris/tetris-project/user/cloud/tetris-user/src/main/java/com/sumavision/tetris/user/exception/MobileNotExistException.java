package com.sumavision.tetris.user.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sumavision.tetris.commons.exception.BaseException;
import com.sumavision.tetris.commons.exception.code.StatusCode;
import com.sumavision.tetris.commons.util.date.DateUtil;
import com.sumavision.tetris.commons.util.wrapper.StringBufferWrapper;

public class MobileNotExistException extends BaseException{

	private static final Logger LOG = LoggerFactory.getLogger(MobileNotExistException.class);
	
	private static final long serialVersionUID = 1L;

	public MobileNotExistException(String mobile) {
		super(StatusCode.FORBIDDEN, "手机号不存在！");
		LOG.error(DateUtil.now());
		LOG.error(new StringBufferWrapper().append("手机号不存在！")
										   .append(mobile)
										   .toString());
	}
}
