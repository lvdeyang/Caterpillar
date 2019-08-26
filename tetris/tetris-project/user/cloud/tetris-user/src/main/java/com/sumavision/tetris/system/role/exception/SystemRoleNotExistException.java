package com.sumavision.tetris.system.role.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sumavision.tetris.commons.exception.BaseException;
import com.sumavision.tetris.commons.exception.code.StatusCode;
import com.sumavision.tetris.commons.util.wrapper.StringBufferWrapper;

public class SystemRoleNotExistException extends BaseException{

	private static Logger LOG = LoggerFactory.getLogger(SystemRoleNotExistException.class);
	
	private static final long serialVersionUID = 1L;

	public SystemRoleNotExistException(Long id) {
		super(StatusCode.FORBIDDEN, "系统角色不存在！");
		LOG.error(new StringBufferWrapper().append("系统角色不存在！id: ")
									       .append(id)
									       .toString());
	}

}
