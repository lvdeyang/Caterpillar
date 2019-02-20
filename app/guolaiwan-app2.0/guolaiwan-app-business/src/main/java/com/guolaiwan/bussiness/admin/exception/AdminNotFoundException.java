package com.guolaiwan.bussiness.admin.exception;

import pub.caterpillar.commons.exception.BaseException;
import pub.caterpillar.commons.exception.code.enumeration.StatusCode;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;

//Mr.Sun
public class AdminNotFoundException extends BaseException {
	
	private static final long serialVersionUID = 1L;

	public AdminNotFoundException(Long adminId){
		this(adminId.toString());
	}
	
	public AdminNotFoundException(String adminId){
		super(StatusCode.NOTFOUND, new StringBufferWrapper().append("未获取到当前操作管理员，管理员id：")
														    .append(adminId)
														    .append("。")
														    .toString());
	}

}
