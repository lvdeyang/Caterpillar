package com.guolaiwan.bussiness.user.po;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.cache.annotation.EnableCaching;

import com.guolaiwan.bussiness.chapman.po.ChapmanPO;
import com.guolaiwan.bussiness.order.po.OrderPO;
import com.guolaiwan.bussiness.user.enumeration.UserSexType;

import pub.caterpillar.orm.po.AbstractBasePO;

/**
 * 用户表
 * lvdeyang 2017年6月21日
 */
@Entity
@Table(name = "t_app_user")
public class UserPO extends AbstractBasePO {
	
	private static final long serialVersionUID = 1L;

	//用户名
	private String username;
	
	//密码
	private String password;
	
	//手机号
	private String mobile;
	
	//邮箱
	private String email;
	
	//身份证号
	private String idCard;
	
	//真实姓名
	private String realName;
	
	//性别
	private UserSexType sex;
	
	//年龄
	private int age;
	
	//银行卡
	private Set<BankcardPO> bankcards;
	
	//折扣券余额
	private Set<DiscountBalancePO> discountBalances; 
	
	//货币余额
	private Set<CoinBalancePO> coinBalances;
	
	//关联商户
	private ChapmanPO chapman;
	
	//订单
	private Set<OrderPO> orders;
	
	//操作详情
	private Set<OperationDetailPO> operationDetails;
	
	//购物车
	private Set<BasketPO> baskets;
	
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	@Enumerated(EnumType.STRING)
	public UserSexType getSex() {
		return sex;
	}

	public void setSex(UserSexType sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<BankcardPO> getBankcards() {
		return bankcards;
	}

	public void setBankcards(Set<BankcardPO> bankcards) {
		this.bankcards = bankcards;
	}

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<DiscountBalancePO> getDiscountBalances() {
		return discountBalances;
	}

	public void setDiscountBalances(Set<DiscountBalancePO> discountBalances) {
		this.discountBalances = discountBalances;
	}
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<CoinBalancePO> getCoinBalances() {
		return coinBalances;
	}

	public void setCoinBalances(Set<CoinBalancePO> coinBalances) {
		this.coinBalances = coinBalances;
	}
	
	@OneToOne(mappedBy="user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false)
	public ChapmanPO getChapman() {
		return chapman;
	}

	public void setChapman(ChapmanPO chapman) {
		this.chapman = chapman;
	}

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<OrderPO> getOrders() {
		return orders;
	}

	public void setOrders(Set<OrderPO> orders) {
		this.orders = orders;
	}

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<OperationDetailPO> getOperationDetails() {
		return operationDetails;
	}

	public void setOperationDetails(Set<OperationDetailPO> operationDetails) {
		this.operationDetails = operationDetails;
	}

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<BasketPO> getBaskets() {
		return baskets;
	}

	public void setBaskets(Set<BasketPO> baskets) {
		this.baskets = baskets;
	}

}
