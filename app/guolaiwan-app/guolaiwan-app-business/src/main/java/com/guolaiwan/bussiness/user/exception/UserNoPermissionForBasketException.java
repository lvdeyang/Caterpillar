package com.guolaiwan.bussiness.user.exception;

import pub.caterpillar.commons.exception.BaseException;
import pub.caterpillar.commons.exception.code.enumeration.StatusCode;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;

public class UserNoPermissionForBasketException extends BaseException {

	private static final long serialVersionUID = 1L;

	public UserNoPermissionForBasketException(String username, Long basketId){
		super(StatusCode.FORBIDDEN, new StringBufferWrapper().append("当前用户对购物车没有操作权限！用户：")
														     .append(username)
														     .append("，购物车id：")
														     .append(basketId)
														     .toString());
	}
	
}
