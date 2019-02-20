package com.guolaiwan.app.web.chapman.statistics.order.vo;

import com.guolaiwan.bussiness.chapman.product.enumeration.ProductType;
import com.guolaiwan.bussiness.order.dto.OrderDetailStatisticsPieDTO;
import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class OrderDetailStatisticsPieVO extends AbstractBaseVO<OrderDetailStatisticsPieVO, OrderDetailStatisticsPieDTO> {

	//商品类型
	private String type;
	
	//总销售额
	private Long count;
	
	public String getType() {
		return type;
	}

	public OrderDetailStatisticsPieVO setType(String type) {
		this.type = type;
		return this;
	}

	public Long getCount() {
		return count;
	}

	public OrderDetailStatisticsPieVO setCount(Long count) {
		this.count = count;
		return this;
	}

	@Override
	public OrderDetailStatisticsPieVO set(OrderDetailStatisticsPieDTO entity) throws Exception {
		
		this.setType(entity.getType())
			.setCount(entity.getCount());
		
		return this;
	}

	

	
	
}
