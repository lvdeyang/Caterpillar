package com.guolaiwan.bussiness.admin.po;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.guolaiwan.bussiness.admin.enumeration.ActivityType;
import com.sun.jna.platform.win32.Netapi32Util.UserInfo;

import pub.caterpillar.orm.po.AbstractBasePO;

/**
 * 收藏
 * @author Administrator
 *
 */
@Entity
@Table(name = "t_sys_collection")
public class CollectionPO extends AbstractBasePO {

	private static final long serialVersionUID = 1L;
	
	//商品Id
	private long productId;
	
	//商户Id
	private long merchantId;
	
	private long activityproductId;
	public long getActivityproductId() {
		return activityproductId;
	}
	public void setActivityproductId(long activityproductId) {
		this.activityproductId = activityproductId;
	}
	public long getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(long merchantId) {
		this.merchantId = merchantId;
	}
	//用户
	private UserInfoPO user;
	
	
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	@ManyToOne
	@JoinColumn(name = "user_id")
	public UserInfoPO getUser() {
		return user;
	}
	public void setUser(UserInfoPO user) {
		this.user = user;
	}
	


}
