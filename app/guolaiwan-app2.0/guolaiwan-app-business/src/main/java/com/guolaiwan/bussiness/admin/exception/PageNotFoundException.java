package com.guolaiwan.bussiness.admin.exception;

import pub.caterpillar.commons.exception.BaseException;
import pub.caterpillar.commons.exception.code.enumeration.StatusCode;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;

public class PageNotFoundException extends BaseException {

	private static final long serialVersionUID = 1L;

	public PageNotFoundException(Long pageId){
		super(StatusCode.NOTFOUND, new StringBufferWrapper().append("未获取到当前页面，页面id：")
															.append(pageId)
															.append("！")
															.toString());
	}
	
}
