package com.guolaiwan.bussiness.chapman.source.exception;

import pub.caterpillar.commons.exception.BaseException;
import pub.caterpillar.commons.exception.code.enumeration.StatusCode;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;

public class RootFolderNotFondException extends BaseException {

	private static final long serialVersionUID = 1L;

	public RootFolderNotFondException(String chapmanname){
		
		super(StatusCode.NOTFOUND, new StringBufferWrapper().append("未获取到商户：")
															.append(chapmanname)
															.append(" 的根目录，请联系管理员")
															.toString());
	}
	
}
