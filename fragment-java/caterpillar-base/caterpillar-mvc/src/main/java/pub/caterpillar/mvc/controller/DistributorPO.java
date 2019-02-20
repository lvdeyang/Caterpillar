package pub.caterpillar.mvc.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * <p>Title: Distributor</p>
 * <p>Description: 经销商PO</p>
 * <p>Company: guolaiwan</p> 
 * @author Mr.Sun
 * @date 2017年11月27日 下午1:14:32
 */
public class DistributorPO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// 商户Id
	private long distributorId;
	
	// 商户名称
	private String distributorName;

	// 商户密码
	private String password;

	// 创建日期
	private Date createdDate;

	// 角色关联id
	private int roleId;

	// 城市代码
	private String cityCode;

	// 城市名
	private String cityName;

	public long getDistributorId() {
		return distributorId;
	}

	public void setDistributorId(long distributorId) {
		this.distributorId = distributorId;
	}

	public String getDistributorName() {
		return distributorName;
	}

	public void setDistributorName(String distributorName) {
		this.distributorName = distributorName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
}
