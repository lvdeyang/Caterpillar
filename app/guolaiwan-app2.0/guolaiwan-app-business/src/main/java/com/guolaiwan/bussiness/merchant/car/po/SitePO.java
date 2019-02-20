package com.guolaiwan.bussiness.merchant.car.po;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.guolaiwan.bussiness.admin.po.MerchantPO;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name="t_Sys_car_Site")
public class SitePO extends AbstractBasePO{
	//路线名称
	private String name;
	//价格
	private Long price;
	//绑定商户
	private RoutePO route;

	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	
	@ManyToOne
	@JoinColumn(name = "route_id")
	public RoutePO getRoute() {
		return route;
	}

	public void setRoute(RoutePO route) {
		this.route = route;
	}

}
