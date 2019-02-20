package com.guolaiwan.app.web.user.exception;

import pub.caterpillar.commons.exception.BaseException;
import pub.caterpillar.commons.exception.code.enumeration.StatusCode;

public class PasswordErrorException extends BaseException {
	
	private static final long serialVersionUID = 1L;

	public PasswordErrorException(){
		super(StatusCode.FORBIDDEN, "密码错误！");
	}
	
}
