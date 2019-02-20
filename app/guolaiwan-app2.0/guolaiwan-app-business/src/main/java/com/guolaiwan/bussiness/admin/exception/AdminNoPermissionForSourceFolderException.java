package com.guolaiwan.bussiness.admin.exception;

import pub.caterpillar.commons.exception.BaseException;
import pub.caterpillar.commons.exception.code.enumeration.StatusCode;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;

public class AdminNoPermissionForSourceFolderException extends BaseException {

	private static final long serialVersionUID = 1L;

	public AdminNoPermissionForSourceFolderException(String adminname, String foldername){
			
			super(StatusCode.FORBIDDEN, new StringBufferWrapper().append("当前管理员对该资源目录没有操作权限！管理员：")
														     .append(adminname)
														     .append("，目录：")
														     .append(foldername)
														     .toString());
	} 
}
