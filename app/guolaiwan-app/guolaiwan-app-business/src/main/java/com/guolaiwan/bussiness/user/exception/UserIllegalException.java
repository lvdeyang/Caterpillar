package com.guolaiwan.bussiness.user.exception;

import pub.caterpillar.commons.exception.BaseException;
import pub.caterpillar.commons.exception.code.enumeration.StatusCode;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;

public class UserIllegalException extends BaseException {

	private static final long serialVersionUID = 1L;

	public UserIllegalException(Long userId){
		super(StatusCode.FORBIDDEN, new StringBufferWrapper().append("您不能操作该用户，用户id：")
															 .append(userId)
															 .append("!")
															 .toString());
	}
	
}
