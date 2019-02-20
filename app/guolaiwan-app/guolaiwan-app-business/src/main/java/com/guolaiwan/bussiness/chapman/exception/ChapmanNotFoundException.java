package com.guolaiwan.bussiness.chapman.exception;

import pub.caterpillar.commons.exception.BaseException;
import pub.caterpillar.commons.exception.code.enumeration.StatusCode;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;

public class ChapmanNotFoundException extends BaseException {
	
	private static final long serialVersionUID = 1L;

	public ChapmanNotFoundException(Long chapmanId){
		this(chapmanId.toString());
	}
	
	public ChapmanNotFoundException(String chapmanId){
		super(StatusCode.NOTFOUND, new StringBufferWrapper().append("未获取到当前操作商户，商户id：")
														    .append(chapmanId)
														    .append("。")
														    .toString());
	}
	
}
