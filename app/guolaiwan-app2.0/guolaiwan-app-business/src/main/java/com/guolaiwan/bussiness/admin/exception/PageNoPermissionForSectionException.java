package com.guolaiwan.bussiness.admin.exception;

import pub.caterpillar.commons.exception.BaseException;
import pub.caterpillar.commons.exception.code.enumeration.StatusCode;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;

public class PageNoPermissionForSectionException extends BaseException {

	private static final long serialVersionUID = 1L;

	public PageNoPermissionForSectionException(String pageName, String sectionTitle){
		super(StatusCode.FORBIDDEN, new StringBufferWrapper().append("当前页面与版块不匹配！页面：")
														     .append(pageName)
														     .append("，版块：")
														     .append(sectionTitle)
														     .toString());
	}
	
}
