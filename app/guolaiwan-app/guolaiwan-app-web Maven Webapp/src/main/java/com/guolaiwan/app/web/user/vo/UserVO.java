package com.guolaiwan.app.web.user.vo;

import com.guolaiwan.bussiness.user.po.UserPO;
import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class UserVO extends AbstractBaseVO<UserVO, UserPO> {

	//用户名
	private String username;
	
	//手机号
	private String mobile;
	
	//邮箱
	private String email;
	
	//身份证号
	private String idCard;
	
	//真实姓名
	private String realName;
	
	//性别
	private String sex;
	
	//年龄
	private int age;
	
	public String getUsername() {
		return username;
	}

	public UserVO setUsername(String username) {
		this.username = username;
		return this;
	}

	public String getMobile() {
		return mobile;
	}

	public UserVO setMobile(String mobile) {
		this.mobile = mobile;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public UserVO setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getIdCard() {
		return idCard;
	}

	public UserVO setIdCard(String idCard) {
		this.idCard = idCard;
		return this;
	}

	public String getRealName() {
		return realName;
	}

	public UserVO setRealName(String realName) {
		this.realName = realName;
		return this;
	}

	public String getSex() {
		return sex;
	}

	public UserVO setSex(String sex) {
		this.sex = sex;
		return this;
	}

	public int getAge() {
		return age;
	}

	public UserVO setAge(int age) {
		this.age = age;
		return this;
	}

	@Override
	public UserVO set(UserPO entity) throws Exception {
		this.setId(entity.getId())
			.setUuid(entity.getUuid())
			.setUsername(entity.getUsername())
			.setMobile(entity.getMobile())
			.setEmail(entity.getEmail())
			.setIdCard(entity.getIdCard())
			.setRealName(entity.getRealName())
			//.setSex(entity.getSex().getName())
			.setAge(entity.getAge());
		return this;
	}

}
