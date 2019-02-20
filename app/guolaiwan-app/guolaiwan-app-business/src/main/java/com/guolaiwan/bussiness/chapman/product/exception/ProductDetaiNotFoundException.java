package com.guolaiwan.bussiness.chapman.product.exception;

import pub.caterpillar.commons.exception.BaseException;
import pub.caterpillar.commons.exception.code.enumeration.StatusCode;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;

public class ProductDetaiNotFoundException extends BaseException {
	
	private static final long serialVersionUID = 1L;

	public ProductDetaiNotFoundException(Long productDetailId){
		super(StatusCode.NOTFOUND, new StringBufferWrapper().append("当前产品明细不存在，产品明细id：")
															.append(productDetailId)
															.append("。").toString());
	}
	
}
