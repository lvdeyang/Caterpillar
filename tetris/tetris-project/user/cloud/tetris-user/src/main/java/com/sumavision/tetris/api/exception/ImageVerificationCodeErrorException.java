package com.sumavision.tetris.api.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sumavision.tetris.commons.exception.BaseException;
import com.sumavision.tetris.commons.exception.code.StatusCode;
import com.sumavision.tetris.commons.util.date.DateUtil;
import com.sumavision.tetris.commons.util.wrapper.StringBufferWrapper;

public class ImageVerificationCodeErrorException extends BaseException{

	private static final Logger LOG = LoggerFactory.getLogger(ImageVerificationCodeErrorException.class);
	
	private static final long serialVersionUID = 1L;

	public ImageVerificationCodeErrorException(String username) {
		super(StatusCode.CONFLICT, "验证码输入错误。");
		LOG.error(DateUtil.now());
		LOG.error(new StringBufferWrapper().append("验证码输入错误，用户：")
										   .append(username)
										   .toString());
	}

}
