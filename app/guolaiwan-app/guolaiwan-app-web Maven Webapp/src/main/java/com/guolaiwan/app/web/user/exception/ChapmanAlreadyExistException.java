package com.guolaiwan.app.web.user.exception;

import pub.caterpillar.commons.exception.BaseException;
import pub.caterpillar.commons.exception.code.enumeration.StatusCode;

public class ChapmanAlreadyExistException extends BaseException {

	private static final long serialVersionUID = 1L;

	public ChapmanAlreadyExistException(){
		super(StatusCode.FORBIDDEN, "当前账户已经申请过商户！");
	}
	
}
