package com.sumavision.tetris.system.theme.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sumavision.tetris.commons.exception.BaseException;
import com.sumavision.tetris.commons.exception.code.StatusCode;
import com.sumavision.tetris.commons.util.date.DateUtil;
import com.sumavision.tetris.commons.util.wrapper.StringBufferWrapper;

public class SystemThemeNotFoundException extends BaseException{

	private static final Logger LOG = LoggerFactory.getLogger(SystemThemeNotFoundException.class);
	
	private static final long serialVersionUID = 1L;

	public SystemThemeNotFoundException(Long id) {
		super(StatusCode.FORBIDDEN, "系统主题不存在！");
		LOG.error(DateUtil.now());
		LOG.error(new StringBufferWrapper().append("系统主题不存在！主题id：")
										   .append(id)
										   .toString());
	}

}
