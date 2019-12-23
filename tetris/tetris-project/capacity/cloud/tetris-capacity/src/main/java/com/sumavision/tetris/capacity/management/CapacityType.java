package com.sumavision.tetris.capacity.management;

import com.sumavision.tetris.orm.exception.ErrorTypeException;

/**
 * 能力类型<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年12月5日 下午2:11:11
 */
public enum CapacityType {

	ENCAPSULATE("封装"), //输出为准
	TRANSCODE("转码");
	
	private String name;

	public String getName() {
		return name;
	}
	
	private CapacityType(String name){
		this.name = name;
	}
	
	public static CapacityType fromName(String name) throws Exception{
		CapacityType[] values = CapacityType.values();
		for(CapacityType value: values){
			if(value.getName().equals(name)){
				return value;
			}
		}
		
		throw new ErrorTypeException("name", name);
	}
	
}
