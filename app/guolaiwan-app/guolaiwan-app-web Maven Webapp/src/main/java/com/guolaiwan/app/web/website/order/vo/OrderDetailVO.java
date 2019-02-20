package com.guolaiwan.app.web.website.order.vo;

import com.guolaiwan.bussiness.order.dto.OrderDetailDTO;
import com.guolaiwan.bussiness.order.enumeration.OrderStatusType;

import pub.caterpillar.commons.img.ImageUtil;
import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class OrderDetailVO extends AbstractBaseVO<OrderDetailVO, OrderDetailDTO> {

	//商品描述
	private String introduction;
	
	//商品订购数量
	private int num;
	
	//缩略图
	private String image;
	
	//订单明细总价
	private Long count;
	
	//商品隶属商户
	private String chapman;

	//订单状态
	private String status;
	
	public String getIntroduction() {
		return introduction;
	}

	public OrderDetailVO setIntroduction(String introduction) {
		this.introduction = introduction;
		return this;
	}

	public int getNum() {
		return num;
	}

	public OrderDetailVO setNum(int num) {
		this.num = num;
		return this;
	}

	public String getImage() {
		return image;
	}

	public OrderDetailVO setImage(String image) {
		this.image = image;
		return this;
	}

	public Long getCount() {
		return count;
	}

	public OrderDetailVO setCount(Long count) {
		this.count = count;
		return this;
	}

	public String getChapman() {
		return chapman;
	}

	public OrderDetailVO setChapman(String chapman) {
		this.chapman = chapman;
		return this;
	}

	public String getStatus() {
		return status;
	}

	public OrderDetailVO setStatus(String status) {
		this.status = status;
		return this;
	}

	@Override
	public OrderDetailVO set(OrderDetailDTO entity) throws Exception {
		this.setId(entity.getId())
			.setUuid(entity.getUuid())
			.setUpdateTime(DateUtil.format(entity.getOrderTime(), DateUtil.dateTimePattern))
			.setIntroduction(entity.getIntroduction())
			.setNum(entity.getNum())
			.setImage(ImageUtil.getUrlSchema(entity.getImage()))
			.setCount(entity.getCount())
			.setChapman(entity.getChapman())
			.setStatus(OrderStatusType.fromString(entity.getStatus()).getName());
		return this;
	}

	
	
}
