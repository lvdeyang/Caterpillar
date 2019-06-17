package com.guolaiwan.bussiness.admin.po;


import javax.persistence.Entity;
import javax.persistence.Table;
import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "t_sys_groupbuy")
public class GroupBuyPO extends AbstractBasePO {

	private static final long serialVersionUID = -181793527608765854L;
	
	private long productid;
	
	private int groupnum;
	
	private long groupprice;

	public long getProductid() {
		return productid;
	}

	public void setProductid(long productid) {
		this.productid = productid;
	}

	public int getGroupnum() {
		return groupnum;
	}

	public void setGroupnum(int groupnum) {
		this.groupnum = groupnum;
	}

	public long getGroupprice() {
		return groupprice;
	}

	public void setGroupprice(long groupprice) {
		this.groupprice = groupprice;
	}
	
	
}
