package com.sumavision.tetris.auth.login.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sumavision.tetris.commons.exception.BaseException;
import com.sumavision.tetris.commons.exception.code.StatusCode;
import com.sumavision.tetris.commons.util.date.DateUtil;

public class SignCannotBeNullException extends BaseException{

	public static final Logger LOG = LoggerFactory.getLogger(SignCannotBeNullException.class);
	
	private static final long serialVersionUID = 1L;

	public SignCannotBeNullException() {
		super(StatusCode.FORBIDDEN, "签名为空！");
		LOG.error(DateUtil.now());
		LOG.error("签名为空！");
	}

}
