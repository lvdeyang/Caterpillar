package com.guolaiwan.bussiness.admin.exception;

import pub.caterpillar.commons.exception.BaseException;
import pub.caterpillar.commons.exception.code.enumeration.StatusCode;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;

public class PageLayoutNotFoundException extends BaseException {
	
	private static final long serialVersionUID = 1L;

	public PageLayoutNotFoundException(Long layoutId){
		super(StatusCode.NOTFOUND, new StringBufferWrapper().append("未获取到当前页面布局，布局id：")
															.append(layoutId)
															.append("!")
															.toString());
	}
	
}
