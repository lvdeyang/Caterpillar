package com.guolaiwan.app.web.admin.vo;

import java.text.SimpleDateFormat;

import com.guolaiwan.bussiness.admin.po.AdminPO;

import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class AdminVO extends AbstractBaseVO<AdminVO, AdminPO> {

	// 管理员名称
	private String adminName;

	// 管理员密码
	private String password;

	// 创建日期   
	private String createdDate;

	// 角色关联id
	private int roleId;
	// 角色关联名称
	private String roleName;

	// 城市代码
	private String cityCode;

	// 城市名
	private String cityName;

	// 分公司Id
	private long comId ;

	// 分公司名称
	private String comName;

	public String getCityCode() {
		return cityCode;
	}

	public AdminVO setCityCode(String cityCode) {
		this.cityCode = cityCode;
		return this;
	}

	public String getCityName() {
		return cityName;
	}

	public AdminVO setCityName(String cityName) {
		this.cityName = cityName;
		return this;
	}

	public String getAdminName() {
		return adminName;
	}

	public AdminVO setAdminName(String adminName) {
		this.adminName = adminName;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public AdminVO setPassword(String password) {
		this.password = password;
		return this;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public AdminVO setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
		return this;
	}

	public int getRoleId() {
		return roleId;
	}

	public AdminVO setRoleId(int roleId) {
		this.roleId = roleId;
		return this;
	}





	public long getComId() {
		return comId;
	}

	public AdminVO setComId(long comId) {
		this.comId = comId;
		return this;
	}

	public String getComName() {
		return comName;
	}

	public AdminVO setComName(String comName) {
		this.comName = comName;
		return this;
	}
	
	public String getRoleName() {
		return roleName;
	}

	public AdminVO setRoleName(String roleName) {
		this.roleName = roleName;
		return this;
	}

	@Override
	public AdminVO set(AdminPO entity) throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.setId(entity.getId())
		.setUuid(entity.getUuid())
		.setUpdateTime(DateUtil.format(entity.getUpdateTime(), DateUtil.dateTimePattern))
		.setAdminName(entity.getAdminName())
		.setPassword(entity.getPassword())
		.setCreatedDate(formatter.format( entity.getCreatedDate()))
		.setRoleId(entity.getRoleId())
		.setCityCode(entity.getCityCode())
		.setCityName(entity.getCityName())
		.setComId(entity.getComId())
		.setComName(entity.getComName())
		.setRoleName(entity.getRoleName());
		return this;
	}

}
