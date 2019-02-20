package com.guolaiwan.bussiness.admin.po;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "t_sys_logistics")
public class LogisticsPo extends AbstractBasePO {

	private String name;
	private long price;
    private Set<LogisticsPoint> logisticsPoints=new HashSet<LogisticsPoint>();
    
    
    
    public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	@OneToMany(mappedBy = "logisticsPo", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = false)
	public Set<LogisticsPoint> getLogisticsPoints() {
		return logisticsPoints;
	}

	public void setLogisticsPoints(Set<LogisticsPoint> logisticsPoints) {
		this.logisticsPoints = logisticsPoints;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
