package com.sumavision.tetris.user;

import com.sumavision.tetris.orm.exception.ErrorTypeException;

/**
 * 用户在线状态<br/>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2018年11月19日 下午5:07:01
 */
public enum UserStatus {

	ONLINE("在线"),
	OFFLINE("离线");
	
	private String name;
	
	private UserStatus(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public static UserStatus fromName(String name) throws Exception{
		UserStatus[] values = UserStatus.values();
		for(UserStatus value:values){
			if(value.getName().equals(name)){
				return value;
			}
		}
		throw new ErrorTypeException("name", name);
	}
	
}
