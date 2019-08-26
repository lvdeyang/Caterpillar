package com.sumavision.tetris.user.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sumavision.tetris.commons.exception.BaseException;
import com.sumavision.tetris.commons.exception.code.StatusCode;
import com.sumavision.tetris.commons.util.date.DateUtil;
import com.sumavision.tetris.commons.util.wrapper.StringBufferWrapper;

public class MailAlreadyExistException extends BaseException{

	private static final Logger LOG = LoggerFactory.getLogger(MailAlreadyExistException.class);
	
	private static final long serialVersionUID = 1L;

	public MailAlreadyExistException(String mail) {
		super(StatusCode.FORBIDDEN, "邮箱已存在！");
		LOG.error(DateUtil.now());
		LOG.error(new StringBufferWrapper().append("邮箱已存在！")
										   .append(mail)
										   .toString());
	}

}
