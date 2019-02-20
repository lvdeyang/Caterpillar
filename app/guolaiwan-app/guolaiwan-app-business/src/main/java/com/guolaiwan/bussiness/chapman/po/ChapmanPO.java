package com.guolaiwan.bussiness.chapman.po;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.guolaiwan.bussiness.chapman.product.po.ProductPO;
import com.guolaiwan.bussiness.chapman.source.po.SourceFolderPO;
import com.guolaiwan.bussiness.order.po.OrderDetailPO;
import com.guolaiwan.bussiness.order.po.OrderPO;
import com.guolaiwan.bussiness.user.po.UserPO;
import pub.caterpillar.orm.po.AbstractBasePO;

/**
 * 商户
 * lvdeyang 2017年6月21日
 */
@Entity
@Table(name = "t_app_chapman")
public class ChapmanPO extends AbstractBasePO{

	private static final long serialVersionUID = 1L;

	//商户名称
	private String chapmanname;
	
	//商户用户名
	private String username;
	
	//商户密码
	private String password;
	
	//关联账户
	private UserPO user;
	
	//资源文件夹
	private Set<SourceFolderPO> sourceFolders;
	
	//产品列表
	private Set<ProductPO> products;
	
	//订单明细
	private Set<OrderDetailPO> orderDetails;
	
	public String getChapmanname() {
		return chapmanname;
	}

	public void setChapmanname(String chapmanname) {
		this.chapmanname = chapmanname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@OneToOne
	@JoinColumn(name = "user_id")
	public UserPO getUser() {
		return user;
	}

	public void setUser(UserPO user) {
		this.user = user;
	}

	@OneToMany(mappedBy = "chapman", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<SourceFolderPO> getSourceFolders() {
		return sourceFolders;
	}

	public void setSourceFolders(Set<SourceFolderPO> sourceFolders) {
		this.sourceFolders = sourceFolders;
	}

	@OneToMany(mappedBy = "chapman", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<ProductPO> getProducts() {
		return products;
	}

	public void setProducts(Set<ProductPO> products) {
		this.products = products;
	}

	@OneToMany(mappedBy = "chapman", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<OrderDetailPO> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(Set<OrderDetailPO> orderDetails) {
		this.orderDetails = orderDetails;
	}
	
}
