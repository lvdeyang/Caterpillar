package com.guolaiwan.bussiness.order.po;

import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.guolaiwan.bussiness.order.enumeration.OrderStatusType;
import com.guolaiwan.bussiness.user.po.UserPO;
import pub.caterpillar.orm.po.AbstractBasePO;

/**
 * 订单
 * lvdeyang 2017年6月22日
 */
@Entity
@Table(name = "t_app_order")
public class OrderPO extends AbstractBasePO {
	
	private static final long serialVersionUID = 1L;
	
	//下单时间
	private Date ordertime;
	
	//订单状态
	private OrderStatusType status;
	
	//订单总金额
	private double count;
	
	//订单加密码
	private String qcode;
	
	//隶属账户
	private UserPO user;
	
	//订单明细
	private Set<OrderDetailPO> orderDetails;

	@Temporal(TemporalType.TIMESTAMP)
	public Date getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}

	@Enumerated(EnumType.STRING)
	public OrderStatusType getStatus() {
		return status;
	}

	public void setStatus(OrderStatusType status) {
		this.status = status;
	}

	public double getCount() {
		return count;
	}

	public void setCount(double count) {
		this.count = count;
	}

	public String getQcode() {
		return qcode;
	}

	public void setQcode(String qcode) {
		this.qcode = qcode;
	}

	@ManyToOne
	@JoinColumn(name = "user_id")
	public UserPO getUser() {
		return user;
	}

	public void setUser(UserPO user) {
		this.user = user;
	}

	@OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<OrderDetailPO> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(Set<OrderDetailPO> orderDetails) {
		this.orderDetails = orderDetails;
	}

}
