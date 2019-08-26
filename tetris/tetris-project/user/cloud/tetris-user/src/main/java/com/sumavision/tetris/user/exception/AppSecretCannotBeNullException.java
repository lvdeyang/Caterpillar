package com.sumavision.tetris.user.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sumavision.tetris.commons.exception.BaseException;
import com.sumavision.tetris.commons.exception.code.StatusCode;
import com.sumavision.tetris.commons.util.date.DateUtil;

public class AppSecretCannotBeNullException extends BaseException{

	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = LoggerFactory.getLogger(AppSecretCannotBeNullException.class);

	public AppSecretCannotBeNullException() {
		super(StatusCode.FORBIDDEN, "开发者密码（AppSecret）不能为空！");
		LOG.error(DateUtil.now());
		LOG.error("开发者密码（AppSecret）不能为空！");
	}

}
