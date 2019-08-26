package com.sumavision.tetris.system.role;

import com.sumavision.tetris.orm.exception.ErrorTypeException;

/**
 * 角色类型<br/>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年8月2日 上午11:43:48
 */
public enum SystemRoleType {
	
	SYSTEM("系统角色"),
	BUSINESS("业务角色");
	
	private String name;
	
	private SystemRoleType(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public static SystemRoleType fromName(String name) throws Exception{
		SystemRoleType[] values = SystemRoleType.values();
		for(SystemRoleType value:values){
			if(value.getName().equals(name)){
				return value;
			}
		}
		throw new ErrorTypeException("name", name);
	}
	
}
