package com.sumavision.tetris.auth.login.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sumavision.tetris.commons.exception.BaseException;
import com.sumavision.tetris.commons.exception.code.StatusCode;
import com.sumavision.tetris.commons.util.date.DateUtil;

public class TimestampCannotBeNullException extends BaseException{

	public static final Logger LOG = LoggerFactory.getLogger(TimestampCannotBeNullException.class);
	
	private static final long serialVersionUID = 1L;
	
	public TimestampCannotBeNullException() {
		super(StatusCode.FORBIDDEN, "时间戳为空！");
		LOG.error(DateUtil.now());
		LOG.error("时间戳为空！");
	}

}
