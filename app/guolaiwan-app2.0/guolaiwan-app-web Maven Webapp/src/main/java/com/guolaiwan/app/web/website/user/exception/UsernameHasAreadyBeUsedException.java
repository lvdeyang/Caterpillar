package com.guolaiwan.app.web.website.user.exception;

import pub.caterpillar.commons.exception.BaseException;
import pub.caterpillar.commons.exception.code.enumeration.StatusCode;

public class UsernameHasAreadyBeUsedException extends BaseException {

	private static final long serialVersionUID = 1L;

	public UsernameHasAreadyBeUsedException(){
		super(StatusCode.FORBIDDEN, "当前用户名已经注册，请更换！");
	}
}
