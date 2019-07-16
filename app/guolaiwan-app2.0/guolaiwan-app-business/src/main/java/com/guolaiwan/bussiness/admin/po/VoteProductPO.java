package com.guolaiwan.bussiness.admin.po;

import javax.persistence.Entity;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;


@Entity 
@Table(name="t_sys_voteproduct")
public class VoteProductPO extends AbstractBasePO{

	private static final long serialVersionUID = -6535407568497631324L;
	//模块code
	private long modularcode;
	//商品Id
	private long productId;
	//商品名称
	private String productName;
	//群众投票数
	private long peoplevotenum;
	//评委投票数
	private long judgesvotenum;
	//订单数量
	private long ordernum;
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public long getModularcode() {
		return modularcode;
	}
	public void setModularcode(long modularcode) {
		this.modularcode = modularcode;
	}
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public long getPeoplevotenum() {
		return peoplevotenum;
	}
	public void setPeoplevotenum(long peoplevotenum) {
		this.peoplevotenum = peoplevotenum;
	}
	public long getJudgesvotenum() {
		return judgesvotenum;
	}
	public void setJudgesvotenum(long judgesvotenum) {
		this.judgesvotenum = judgesvotenum;
	}
	public long getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(long ordernum) {
		this.ordernum = ordernum;
	}
	
	
}
