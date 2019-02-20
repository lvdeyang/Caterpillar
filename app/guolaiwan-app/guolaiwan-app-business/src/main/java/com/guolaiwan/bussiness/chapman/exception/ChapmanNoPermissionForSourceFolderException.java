package com.guolaiwan.bussiness.chapman.exception;

import pub.caterpillar.commons.exception.BaseException;
import pub.caterpillar.commons.exception.code.enumeration.StatusCode;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;

public class ChapmanNoPermissionForSourceFolderException extends BaseException {

	private static final long serialVersionUID = 1L;

	public ChapmanNoPermissionForSourceFolderException(String chapmanname, String foldername){
		
		super(StatusCode.FORBIDDEN, new StringBufferWrapper().append("当前商户对该资源目录没有操作权限！商户：")
														     .append(chapmanname)
														     .append("，目录：")
														     .append(foldername)
														     .toString());
	}
	
}
