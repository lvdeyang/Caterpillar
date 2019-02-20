package com.guolaiwan.bussiness.admin.exception;

import pub.caterpillar.commons.exception.BaseException;
import pub.caterpillar.commons.exception.code.enumeration.StatusCode;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;

public class SectionNotFoundException extends BaseException {

	private static final long serialVersionUID = 1L;

	public SectionNotFoundException(Long sectionId){
		super(StatusCode.NOTFOUND, new StringBufferWrapper().append("未获取到当前版块，版块id：")
															.append(sectionId)
															.append("！")
															.toString());
	}
	
}
