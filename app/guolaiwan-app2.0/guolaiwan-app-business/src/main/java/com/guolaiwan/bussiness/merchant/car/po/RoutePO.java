package com.guolaiwan.bussiness.merchant.car.po;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.guolaiwan.bussiness.admin.po.MerchantPO;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name="t_Sys_car_Route")
public class RoutePO extends AbstractBasePO{
	//名称
	private String name;
	//标识
	private String lab;
	//始发地
	private String origin;
	//终点
	private String end;
	//发车时间
	private String startTime;
	//价格
	private long price;


	//绑定商户
	private MerchantPO merchant;
	//
	private List<FlightPO> flights;
	//
	private List<SitePO> sites;

	@OneToMany(mappedBy="route", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false)
	public List<SitePO> getSites() {
		return sites;
	}

	public void setSites(List<SitePO> sites) {
		this.sites = sites;
	}

	@OneToMany(mappedBy="route", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false)
	public List<FlightPO> getFlights() {
		return flights;
	}

	public void setFlights(List<FlightPO> flights) {
		this.flights = flights;
	}

	@ManyToOne
	@JoinColumn(name = "merchant_id")
	public MerchantPO getMerchant() {
		return merchant;
	}

	public void setMerchant(MerchantPO merchant) {
		this.merchant = merchant;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getLab() {
		return lab;
	}

	public void setLab(String lab) {
		this.lab = lab;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

}
