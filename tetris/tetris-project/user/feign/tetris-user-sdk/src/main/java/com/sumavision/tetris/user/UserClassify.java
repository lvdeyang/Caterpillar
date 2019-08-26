package com.sumavision.tetris.user;

import com.sumavision.tetris.orm.exception.ErrorTypeException;

/**
 * 用户分类<br/>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2018年11月20日 上午11:38:11
 */
public enum UserClassify {

	@Deprecated
	MAINTENANCE("运维", false),
	@Deprecated
	COMPANY_ADMIN("企业管理员", false),
	@Deprecated
	COMPANY_USER("企业用户----", false),
	
	TERMINAL("终端用户", false),
	INTERNAL("系统内置用户", false),
	COMPANY("企业用户", true),
	NORMAL("普通用户", true);
	
	private String name;
	
	private boolean show;
	
	private UserClassify(String name, boolean show){
		this.name = name;
		this.show = show;
	}
	
	public String getName(){
		return this.name;
	}
	
	public boolean isShow() {
		return this.show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}

	public static UserClassify fromName(String name) throws Exception{
		UserClassify[] values = UserClassify.values();
		for(UserClassify value:values){
			if(value.getName().equals(name)){
				return value;
			}
		}
		throw new ErrorTypeException("name", name);
	}
	
}

