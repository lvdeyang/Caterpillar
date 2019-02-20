package com.guolaiwan.app.web.website.user.exception;

import pub.caterpillar.commons.exception.BaseException;
import pub.caterpillar.commons.exception.code.enumeration.StatusCode;

public class UnkonwnUserException extends BaseException{

	private static final long serialVersionUID = 1L;

	public UnkonwnUserException(){
		super(StatusCode.FORBIDDEN, "当前用户不存在！");
	}
	
}
