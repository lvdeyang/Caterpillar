package com.guolaiwan.bussiness.chapman.source.exception;

import com.guolaiwan.bussiness.chapman.source.enumeration.SourceType;

import pub.caterpillar.commons.exception.BaseException;
import pub.caterpillar.commons.exception.code.enumeration.StatusCode;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;

public class SourceNotFoundException extends BaseException{

	private static final long serialVersionUID = 1L;

	public SourceNotFoundException(Long sourceId, SourceType type){
		super(StatusCode.NOTFOUND, new StringBufferWrapper().append("未获取到当前素材，素材id：")
														    .append(sourceId)
														    .append("，素材类型：")
														    .append(type.getName())
														    .append("。")
														    .toString());
	}
	
}
