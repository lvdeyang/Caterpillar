package com.guolaiwan.bussiness.admin.exception;

import pub.caterpillar.commons.exception.BaseException;
import pub.caterpillar.commons.exception.code.enumeration.StatusCode;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;

public class MenuNotFoundException extends BaseException {

	private static final long serialVersionUID = 1L;

	public MenuNotFoundException(Long menuid){
		super(StatusCode.NOTFOUND, new StringBufferWrapper().append("未获取到当前菜单，菜单id：")
															.append(menuid)
															.append("！")
															.toString());
	}
	
}
