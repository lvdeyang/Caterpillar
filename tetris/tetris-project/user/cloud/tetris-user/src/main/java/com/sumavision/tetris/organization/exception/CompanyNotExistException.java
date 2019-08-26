package com.sumavision.tetris.organization.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sumavision.tetris.commons.exception.BaseException;
import com.sumavision.tetris.commons.exception.code.StatusCode;
import com.sumavision.tetris.commons.util.wrapper.StringBufferWrapper;

public class CompanyNotExistException extends BaseException{

	private static final Logger LOG = LoggerFactory.getLogger(CompanyNotExistException.class);
	
	private static final long serialVersionUID = 1L;

	public CompanyNotExistException(Long id) {
		super(StatusCode.FORBIDDEN, "公司不存在！");
		LOG.error(new StringBufferWrapper().append("公司不存在！id:")
										   .append(id)
										   .toString());
	}

}
