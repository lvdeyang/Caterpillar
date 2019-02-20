package com.guolaiwan.app.web.user.exception;

import pub.caterpillar.commons.exception.BaseException;
import pub.caterpillar.commons.exception.code.enumeration.StatusCode;

public class LoginTimeoutException extends BaseException {

	private static final long serialVersionUID = 1L;

	public LoginTimeoutException(){
		super(StatusCode.FORBIDDEN, "超时，请重新登录！");
	}
	
}
