package com.sumavision.tetris.menu.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sumavision.tetris.commons.exception.BaseException;
import com.sumavision.tetris.commons.exception.code.StatusCode;
import com.sumavision.tetris.commons.util.wrapper.StringBufferWrapper;

public class MenuNotExistException extends BaseException{

	private static final Logger LOG = LoggerFactory.getLogger(MenuNotExistException.class);
	
	private static final long serialVersionUID = 1L;

	public MenuNotExistException(Long parentId) {
		super(StatusCode.FORBIDDEN, "菜单不存在");
		LOG.error(new StringBufferWrapper().append("菜单不存在，id:")
										   .append(parentId)
										   .toString());
	}

}
