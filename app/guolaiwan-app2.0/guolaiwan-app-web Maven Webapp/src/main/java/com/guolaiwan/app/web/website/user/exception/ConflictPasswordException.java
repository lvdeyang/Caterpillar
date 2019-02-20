package com.guolaiwan.app.web.website.user.exception;

import pub.caterpillar.commons.exception.BaseException;
import pub.caterpillar.commons.exception.code.enumeration.StatusCode;

public class ConflictPasswordException extends BaseException {

	private static final long serialVersionUID = 1L;

	public ConflictPasswordException(){
		super(StatusCode.FORBIDDEN, "两次密码输入不一致！");
	}
	
}
