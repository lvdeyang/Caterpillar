package com.sumavision.tetris.api.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sumavision.tetris.commons.exception.BaseException;
import com.sumavision.tetris.commons.exception.code.StatusCode;
import com.sumavision.tetris.commons.util.date.DateUtil;
import com.sumavision.tetris.commons.util.wrapper.StringBufferWrapper;

public class ImageVerificationCodeTimeoutException extends BaseException{

	private static final Logger LOG = LoggerFactory.getLogger(ImageVerificationCodeTimeoutException.class);
	
	private static final long serialVersionUID = 1L;

	public ImageVerificationCodeTimeoutException(String username) {
		super(StatusCode.CONFLICT, "超时！请重新获取验证码。");
		LOG.error(DateUtil.now());
		LOG.error(new StringBufferWrapper().append("验证码超时，用户：")
										   .append(username)
										   .toString());
	}

}
