package com.sumavision.tetris.auth.filter.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sumavision.tetris.commons.exception.BaseException;
import com.sumavision.tetris.commons.exception.code.StatusCode;
import com.sumavision.tetris.commons.util.date.DateUtil;
import com.sumavision.tetris.commons.util.wrapper.StringBufferWrapper;

public class SessionIdMissingException extends BaseException{

	private static final Logger LOG = LoggerFactory.getLogger(SessionIdMissingException.class);
	
	private static final long serialVersionUID = 1L;

	public SessionIdMissingException(String uri) {
		super(StatusCode.FORBIDDEN, "session id丢失！");
		LOG.error(DateUtil.now());
		LOG.error(new StringBufferWrapper().append("session id丢失！uri：")
										   .append(uri)
										   .toString());
	}

}
