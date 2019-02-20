package com.guolaiwan.bussiness.order.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;

/**
 * 订单信息描述
 * lvdeyang 2017年6月22日
 */
@Entity
@Table(name = "t_app_order_info")
public class OrderInfoPO extends AbstractBasePO{

	private static final long serialVersionUID = 1L;
	
	//订单描述建
	private String infoKey;
	
	//订单描述值
	private String infoValue;
	
	//一级分类
	private String level_1;
	
	//二级分类
	private String level_2;
	
	//三级分类
	private String level_3;
	
	//描述对象
	private Long target_id;
	
	//描述对象类型
	private Long target_type;

	@Column(name = "info_key")
	public String getInfoKey() {
		return infoKey;
	}

	public void setInfoKey(String infoKey) {
		this.infoKey = infoKey;
	}

	@Column(name = "info_value")
	public String getInfoValue() {
		return infoValue;
	}

	public void setInfoValue(String infoValue) {
		this.infoValue = infoValue;
	}

	public String getLevel_1() {
		return level_1;
	}

	public void setLevel_1(String level_1) {
		this.level_1 = level_1;
	}

	public String getLevel_2() {
		return level_2;
	}

	public void setLevel_2(String level_2) {
		this.level_2 = level_2;
	}

	public String getLevel_3() {
		return level_3;
	}

	public void setLevel_3(String level_3) {
		this.level_3 = level_3;
	}

	public Long getTarget_id() {
		return target_id;
	}

	public void setTarget_id(Long target_id) {
		this.target_id = target_id;
	}

	public Long getTarget_type() {
		return target_type;
	}

	public void setTarget_type(Long target_type) {
		this.target_type = target_type;
	}
	
}
