package com.guolaiwan.bussiness.chapman.product.exception;

import com.guolaiwan.bussiness.chapman.product.enumeration.ProductType;
import pub.caterpillar.commons.exception.BaseException;
import pub.caterpillar.commons.exception.code.enumeration.StatusCode;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;

public class ProductNotFoundException extends BaseException {

	private static final long serialVersionUID = 1L;

	public ProductNotFoundException(Long productId, ProductType type){
		super(StatusCode.NOTFOUND, new StringBufferWrapper().append("当前产品不存在，产品id：")
															.append(productId)
															.append(type==null?"。":new StringBufferWrapper().append("，产品类型：")
																											 .append(type.getName())
																											 .append("。")
																											 .toString())
															.toString());
	}
}
