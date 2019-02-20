package com.guolaiwan.bussiness.chapman.source.exception;

import pub.caterpillar.commons.exception.BaseException;
import pub.caterpillar.commons.exception.code.enumeration.StatusCode;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;

public class SourceFolderNotFoundException extends BaseException {

	private static final long serialVersionUID = 1L;

	public SourceFolderNotFoundException(Long folderId){
		this(folderId.toString());
	}
	
	public SourceFolderNotFoundException(String folderId){
		
		super(StatusCode.NOTFOUND, new StringBufferWrapper().append("未获取到当前资源目录，目录id：")
														    .append(folderId)
														    .append("。")
														    .toString());
	}
	
}
