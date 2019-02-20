package com.guolaiwan.app.web.chapman.statistics.order.vo;

import com.guolaiwan.bussiness.order.dto.OrderDetailStatisticsBarDTO;
import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class OrderDetailStatisticsBarVO extends AbstractBaseVO<OrderDetailStatisticsBarVO, OrderDetailStatisticsBarDTO> {

	//订单月份
	private String orderMonth;
	
	//商品类型
	private String productType;
	
	//销售额
	private Long count;

	public String getOrderMonth() {
		return orderMonth;
	}

	public OrderDetailStatisticsBarVO setOrderMonth(String orderMonth) {
		this.orderMonth = orderMonth;
		return this;
	}

	public String getProductType() {
		return productType;
	}

	public OrderDetailStatisticsBarVO setProductType(String productType) {
		this.productType = productType;
		return this;
	}

	public Long getCount() {
		return count;
	}

	public OrderDetailStatisticsBarVO setCount(Long count) {
		this.count = count;
		return this;
	}

	@Override
	public OrderDetailStatisticsBarVO set(OrderDetailStatisticsBarDTO entity) throws Exception {
		
		this.setOrderMonth(entity.getOrderMonth())
			.setProductType(entity.getProductType())
			.setCount(entity.getCount());
		
		return this;
	}

	

	
	
}
