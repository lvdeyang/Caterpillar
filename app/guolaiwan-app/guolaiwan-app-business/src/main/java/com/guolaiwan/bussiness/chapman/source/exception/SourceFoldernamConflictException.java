package com.guolaiwan.bussiness.chapman.source.exception;

import pub.caterpillar.commons.exception.BaseException;
import pub.caterpillar.commons.exception.code.enumeration.StatusCode;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;

public class SourceFoldernamConflictException extends BaseException {

	private static final long serialVersionUID = 1L;

	public SourceFoldernamConflictException(String parentFoldername, String foldername){
		super(StatusCode.CONFLICT, new StringBufferWrapper().append("文件夹“")
															.append(parentFoldername)
															.append("”下已经存在名称为“")
															.append(foldername)
															.append("”的文件夹，请重新命名。")
															.toString());
	}
	
}
