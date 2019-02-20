package com.guolaiwan.bussiness.user.exception;

import pub.caterpillar.commons.exception.BaseException;
import pub.caterpillar.commons.exception.code.enumeration.StatusCode;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;

public class UserNotFoundException extends BaseException {

	private static final long serialVersionUID = 1L;

	public UserNotFoundException(Long userId){
		super(StatusCode.NOTFOUND, new StringBufferWrapper().append("未获取到当前用户，用户id：")
															.append(userId)
															.append("!")
															.toString());
	}
	
}
