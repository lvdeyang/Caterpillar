package com.sumavision.tetris.organization.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sumavision.tetris.commons.exception.BaseException;
import com.sumavision.tetris.commons.exception.code.StatusCode;
import com.sumavision.tetris.commons.util.date.DateUtil;
import com.sumavision.tetris.commons.util.wrapper.StringBufferWrapper;

public class OrganizationNotExistException extends BaseException{

	private static final Logger LOG = LoggerFactory.getLogger(OrganizationNotExistException.class);
	
	private static final long serialVersionUID = 1L;

	public OrganizationNotExistException(Long id) {
		super(StatusCode.FORBIDDEN, "部门不存在！");
		LOG.error(DateUtil.now());
		LOG.error(new StringBufferWrapper().append("部门不存在，id：").append(id).toString());
	}
	
}
