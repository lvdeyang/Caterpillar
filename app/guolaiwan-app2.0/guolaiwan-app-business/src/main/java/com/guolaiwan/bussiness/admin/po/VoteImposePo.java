package com.guolaiwan.bussiness.admin.po;

import javax.persistence.Entity;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "t_sys_voteimposePo")
public class VoteImposePo extends AbstractBasePO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1495476799126985454L;
	
	private String UserId;// 用户id
	private String ProductId;// 对应的商品id
	private Integer poll;// 投票数
	private Integer buy;// 购买数
	private String orderId;//订单号


	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getUserId() {
		return UserId;
	}

	public void setUserId(String userId) {
		UserId = userId;
	}

	public String getProductId() {
		return ProductId;
	}

	public void setProductId(String productId) {
		ProductId = productId;
	}

	public Integer getPoll() {
		return poll;
	}

	public void setPoll(Integer poll) {
		this.poll = poll;
	}

	public Integer getBuy() {
		return buy;
	}

	public void setBuy(Integer buy) {
		this.buy = buy;
	}

	@Override
	public String toString() {
		return "VoteImposePo [UserId=" + UserId + ", ProductId=" + ProductId + ", poll=" + poll + ", buy=" + buy + "]";
	}

}
