package com.guolaiwan.app.web.user.exception;

import pub.caterpillar.commons.exception.BaseException;
import pub.caterpillar.commons.exception.code.enumeration.StatusCode;

public class VerifyCodeErrorException extends BaseException {

	private static final long serialVersionUID = 1L;

	public VerifyCodeErrorException(){
		super(StatusCode.FORBIDDEN, "验证码错误！");
	}

}
