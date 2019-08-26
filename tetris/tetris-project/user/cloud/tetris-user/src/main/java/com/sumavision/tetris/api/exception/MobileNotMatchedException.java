package com.sumavision.tetris.api.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sumavision.tetris.commons.exception.BaseException;
import com.sumavision.tetris.commons.exception.code.StatusCode;
import com.sumavision.tetris.commons.util.date.DateUtil;
import com.sumavision.tetris.commons.util.wrapper.StringBufferWrapper;

public class MobileNotMatchedException extends BaseException{
	
	private static final Logger LOG = LoggerFactory.getLogger(MobileNotMatchedException.class);
	
	private static final long serialVersionUID = 1L;

	public MobileNotMatchedException(String mobile, String phone) {
		super(StatusCode.CONFLICT, "该手机号与接收验证码手机号不匹配！");
		LOG.error(DateUtil.now());
		LOG.error(new StringBufferWrapper().append("该手机号：")
										   .append(mobile)
										   .append("与接收验证码手机号：")
										   .append(phone)
										   .append("不匹配！")
										   .toString());
	}

}
