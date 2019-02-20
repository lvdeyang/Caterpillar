package pub.caterpillar.mvc.controller;

import java.io.Serializable;
import java.util.Date;

public class AdminPO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 管理员名称
	private String adminName;

	// 管理员密码
	private String password;

	// 创建日期
	private Date createdDate;

	// 角色关联id
	private int roleId;

	// 城市代码
	private String cityCode;

	// 城市名
	private String cityName;

	// 城市代码
	private long comId;

	// 城市名
	private String comName;

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

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
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

	public long getComId() {
		return comId;
	}

	public void setComId(long comId) {
		this.comId = comId;
	}

	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}
	
	

}
