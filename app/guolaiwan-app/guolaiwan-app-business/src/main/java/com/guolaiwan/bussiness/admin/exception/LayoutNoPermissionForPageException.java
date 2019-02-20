package com.guolaiwan.bussiness.admin.exception;

import pub.caterpillar.commons.exception.BaseException;
import pub.caterpillar.commons.exception.code.enumeration.StatusCode;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;

public class LayoutNoPermissionForPageException extends BaseException {

	private static final long serialVersionUID = 1L;

	public LayoutNoPermissionForPageException(String layoutName, String pageName){
		super(StatusCode.FORBIDDEN, new StringBufferWrapper().append("当前布局与页面不匹配！布局：")
														     .append(layoutName)
														     .append("，页面：")
														     .append(pageName)
														     .toString());
	}
	
}
