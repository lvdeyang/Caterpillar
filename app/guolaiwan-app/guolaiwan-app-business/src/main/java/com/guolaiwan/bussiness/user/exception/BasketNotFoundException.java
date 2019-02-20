package com.guolaiwan.bussiness.user.exception;

import pub.caterpillar.commons.exception.BaseException;
import pub.caterpillar.commons.exception.code.enumeration.StatusCode;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;

public class BasketNotFoundException extends BaseException {

	private static final long serialVersionUID = 1L;

	public BasketNotFoundException(Long basketId){
		super(StatusCode.NOTFOUND, new StringBufferWrapper().append("当前购物车条目不存在，购物车id：")
															.append(basketId)
															.append("。")
															.toString());
	}
	
}
