package com.sumavision.tetris.system.role;

import com.sumavision.tetris.orm.exception.ErrorTypeException;

/**
 * 系统角色内置分级分类<br/>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年1月11日 下午2:56:28
 */
public enum SystemRoleLevel {

	/**********
	 * 一级分类
	 **********/
	
	SYSTEM_ADMIN("系统运维"),
	BUSINESS("业务人员"),
	
	
	/**********
	 * 二级分类
	 **********/
	
	MENU("菜单运维", SYSTEM_ADMIN),
	PROCESS("流程运维", SYSTEM_ADMIN),
	MIMS("媒资运维", SYSTEM_ADMIN),
	
	COMPANY_ADMIN("企业管理员", BUSINESS),
	COMPANY_USER("企业用户", BUSINESS),
	NORMAL("普通用户", BUSINESS);
	
	private String name;
	
	private SystemRoleLevel parent;
	
	private SystemRoleLevel(String name){
		this.name = name;
	}
	
	private SystemRoleLevel(String name, SystemRoleLevel parent){
		this.name = name;
		this.parent = parent;
	}
	
	public String getName(){
		return this.name;
	}
	
	public SystemRoleLevel getParent(){
		return this.parent;
	}
	
	public static SystemRoleLevel fromName(String name) throws Exception{
		SystemRoleLevel[] values = SystemRoleLevel.values();
		for(SystemRoleLevel value:values){
			if(value.getName().equals(name)){
				return value;
			}
		}
		throw new ErrorTypeException("name", name);
	}
	
}
