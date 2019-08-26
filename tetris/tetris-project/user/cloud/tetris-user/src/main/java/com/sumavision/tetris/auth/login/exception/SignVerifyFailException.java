package com.sumavision.tetris.auth.login.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sumavision.tetris.commons.exception.BaseException;
import com.sumavision.tetris.commons.exception.code.StatusCode;
import com.sumavision.tetris.commons.util.date.DateUtil;
import com.sumavision.tetris.commons.util.wrapper.StringBufferWrapper;

public class SignVerifyFailException extends BaseException{

	public static final Logger LOG = LoggerFactory.getLogger(SignVerifyFailException.class);
	
	private static final long serialVersionUID = 1L;

	public SignVerifyFailException(String appId, String timestamp, String sign) {
		super(StatusCode.FORBIDDEN, "签名验证失败！");
		LOG.error(DateUtil.now());
		LOG.error(new StringBufferWrapper().append("签名验证失败！appId：")
										   .append(appId)
										   .append("，时间戳：")
										   .append(timestamp)
										   .append("，签名：")
										   .append(sign)
										   .toString());
	}

}
