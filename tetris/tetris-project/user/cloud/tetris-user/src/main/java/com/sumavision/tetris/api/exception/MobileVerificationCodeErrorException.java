package com.sumavision.tetris.api.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sumavision.tetris.commons.exception.BaseException;
import com.sumavision.tetris.commons.exception.code.StatusCode;
import com.sumavision.tetris.commons.util.date.DateUtil;
import com.sumavision.tetris.commons.util.wrapper.StringBufferWrapper;

public class MobileVerificationCodeErrorException extends BaseException{

	private static final Logger LOG = LoggerFactory.getLogger(MobileVerificationCodeErrorException.class);
	
	private static final long serialVersionUID = 1L;

	public MobileVerificationCodeErrorException(String username, String mobile) {
		super(StatusCode.CONFLICT, "手机验证码输入错误。");
		LOG.error(DateUtil.now());
		LOG.error(new StringBufferWrapper().append("手机验证码输入错误，用户：")
										   .append(username)
										   .append("，手机：")
										   .append(mobile)
										   .toString());
	}

}
