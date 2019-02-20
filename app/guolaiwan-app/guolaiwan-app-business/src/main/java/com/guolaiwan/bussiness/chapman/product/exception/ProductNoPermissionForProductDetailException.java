package com.guolaiwan.bussiness.chapman.product.exception;

import pub.caterpillar.commons.exception.BaseException;
import pub.caterpillar.commons.exception.code.enumeration.StatusCode;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;

public class ProductNoPermissionForProductDetailException extends BaseException {

	private static final long serialVersionUID = 1L;

	public ProductNoPermissionForProductDetailException(Long productId, Long productDetailId){
		super(StatusCode.FORBIDDEN, new StringBufferWrapper().append("当前产品与产品明细不匹配，产品id：")
															 .append(productId)
															 .append("，产品明细id：")
															 .append(productDetailId)
															 .append("。").toString());
	}
	
}
