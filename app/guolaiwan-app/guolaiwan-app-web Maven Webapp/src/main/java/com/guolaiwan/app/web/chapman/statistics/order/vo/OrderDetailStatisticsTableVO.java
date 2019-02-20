package com.guolaiwan.app.web.chapman.statistics.order.vo;

import com.guolaiwan.bussiness.order.dto.OrderDetailStatisticsTableDTO;
import com.guolaiwan.bussiness.order.enumeration.OrderStatusType;
import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class OrderDetailStatisticsTableVO extends AbstractBaseVO<OrderDetailStatisticsTableVO, OrderDetailStatisticsTableDTO> {

	//订单编号
	private String orderId;
	
	//下单时间
	private String orderTime;
	
	//产品名称
	private String product;
	
	//产品类型
	private String productType;
	
	//订购数量
	private int num;
	
	//总价
	private int count;
	
	//订单状态
	private String status;
	
	//买家姓名
	private String buyer;
	
	public String getOrderId() {
		return orderId;
	}

	public OrderDetailStatisticsTableVO setOrderId(String orderId) {
		this.orderId = orderId;
		return this;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public OrderDetailStatisticsTableVO setOrderTime(String orderTime) {
		this.orderTime = orderTime;
		return this;
	}

	public String getProduct() {
		return product;
	}

	public OrderDetailStatisticsTableVO setProduct(String product) {
		this.product = product;
		return this;
	}

	public String getProductType() {
		return productType;
	}

	public OrderDetailStatisticsTableVO setProductType(String productType) {
		this.productType = productType;
		return this;
	}

	public int getNum() {
		return num;
	}

	public OrderDetailStatisticsTableVO setNum(int num) {
		this.num = num;
		return this;
	}

	public int getCount() {
		return count;
	}

	public OrderDetailStatisticsTableVO setCount(int count) {
		this.count = count;
		return this;
	}

	public String getStatus() {
		return status;
	}

	public OrderDetailStatisticsTableVO setStatus(String status) {
		this.status = status;
		return this;
	}

	public String getBuyer() {
		return buyer;
	}

	public OrderDetailStatisticsTableVO setBuyer(String buyer) {
		this.buyer = buyer;
		return this;
	}

	@Override
	public OrderDetailStatisticsTableVO set(OrderDetailStatisticsTableDTO entity) throws Exception {
		
		this.setOrderId(entity.getOrderId())
			.setOrderTime(DateUtil.format(entity.getOrderTime(), DateUtil.dateTimePattern))
			.setProduct(entity.getProduct())
			.setProductType(entity.getProductType())
			.setNum(entity.getNum())
			.setCount(entity.getCount())
			.setStatus(OrderStatusType.fromString(entity.getStatus()).getName())
			.setBuyer(entity.getBuyer());
		
		return this;
	}

	
	
}
