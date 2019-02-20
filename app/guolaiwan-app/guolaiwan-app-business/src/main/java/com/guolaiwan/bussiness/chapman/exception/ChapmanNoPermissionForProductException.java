package com.guolaiwan.bussiness.chapman.exception;

import pub.caterpillar.commons.exception.BaseException;
import pub.caterpillar.commons.exception.code.enumeration.StatusCode;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;

public class ChapmanNoPermissionForProductException extends BaseException {
	
	private static final long serialVersionUID = 1L;

	public ChapmanNoPermissionForProductException(String chapmanname, String productname){
		super(StatusCode.FORBIDDEN, new StringBufferWrapper().append("当前商户对该产品没有权限，商户：")
															 .append(chapmanname)
															 .append("，产品：")
															 .append(productname)
															 .append("。")
															 .toString());
	}
	
}
