package com.guolaiwan.bussiness.admin.po;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.bytedeco.javacpp.RealSense.intrinsics;

import pub.caterpillar.orm.po.AbstractBasePO;
@Entity
@Table(name = "t_sys_room")
public class RoomPo extends AbstractBasePO {

	private String name;
	private long productId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}
	
}
