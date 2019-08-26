package com.sumavision.tetris.auth.login.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sumavision.tetris.commons.exception.BaseException;
import com.sumavision.tetris.commons.exception.code.StatusCode;
import com.sumavision.tetris.commons.util.date.DateUtil;

public class AppIdCannotBeNullException extends BaseException{

	public static final Logger LOG = LoggerFactory.getLogger(AppIdCannotBeNullException.class);
	
	private static final long serialVersionUID = 1L;

	public AppIdCannotBeNullException() {
		super(StatusCode.FORBIDDEN, "开发者id为空！");
		LOG.error(DateUtil.now());
		LOG.error("开发者id为空！");
	}

}
