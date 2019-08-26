package com.sumavision.tetris.auth.login.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sumavision.tetris.commons.exception.BaseException;
import com.sumavision.tetris.commons.exception.code.StatusCode;
import com.sumavision.tetris.commons.util.date.DateUtil;
import com.sumavision.tetris.commons.util.wrapper.StringBufferWrapper;

public class UnknownAppIdException extends BaseException{

	private static final Logger LOG = LoggerFactory.getLogger(UnknownAppIdException.class);
	
	private static final long serialVersionUID = 1L;

	public UnknownAppIdException(String appId) {
		super(StatusCode.FORBIDDEN, "开发者不存在！");
		LOG.error(DateUtil.now());
		LOG.error(new StringBufferWrapper().append("开发者不存在！开发者id：")
										   .append(appId)
										   .toString());
	}

}
