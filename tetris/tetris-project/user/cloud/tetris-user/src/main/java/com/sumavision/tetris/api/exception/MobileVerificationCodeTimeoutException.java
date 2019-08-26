package com.sumavision.tetris.api.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sumavision.tetris.commons.exception.BaseException;
import com.sumavision.tetris.commons.exception.code.StatusCode;
import com.sumavision.tetris.commons.util.date.DateUtil;
import com.sumavision.tetris.commons.util.wrapper.StringBufferWrapper;

public class MobileVerificationCodeTimeoutException extends BaseException{

	private static final Logger LOG = LoggerFactory.getLogger(MobileVerificationCodeTimeoutException.class);
	
	private static final long serialVersionUID = 1L;

	public MobileVerificationCodeTimeoutException(String username, String mobile) {
		super(StatusCode.CONFLICT, "超时！请重新获取手机验证码。");
		LOG.error(DateUtil.now());
		LOG.error(new StringBufferWrapper().append("注册手机验证码超时，用户：")
										   .append(username)
										   .append("，手机：")
										   .append(mobile)
										   .toString());
	}

}
