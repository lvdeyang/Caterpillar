package com.sumavision.tetris.commons.constant;

import java.util.Collection;

import com.alibaba.fastjson.JSONArray;
import com.sumavision.tetris.commons.util.wrapper.StringBufferWrapper;

/**
 * 数据类型<br/>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年3月21日 下午5:52:29
 */
public enum DataType {

	STRING("字符串"),
	FLOAT("浮点型"),
	DOUBLE("双精度浮点型"),
	INTEGER("整型"),
	LONG("长整型"),
	SHORT("短整型"),
	BOOLEAN("布尔型"),
	CHARACTER("字符型"),
	BYTE("字节型"),
	ARRAY("数组"),
	OBJECT("复杂对象");
	
	private String name;
	
	private DataType(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public static DataType fromName(String name) throws Exception{
		DataType[] values = DataType.values();
		for(DataType value:values){
			if(value.getName().equals(name)){
				return value;
			}
		}
		throw new Exception(new StringBufferWrapper().append("错误的枚举类型，name:").append(name).toString());
	}
	
	/**
	 * 判断是否是基本数据类型<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年3月26日 下午1:30:25
	 * @param Object target 数据
	 * @return boolean 判断结果
	 */
	public static boolean isBasicType(Object target) throws Exception{
		if(target instanceof String) return true;
		if(target instanceof Integer) return true;
		if(target instanceof Float) return true;
		if(target instanceof Double) return true;
		if(target instanceof Long) return true;
		if(target instanceof Boolean) return true;
		if(target instanceof Character) return true;
		if(target instanceof Short) return true;
		if(target instanceof Byte) return true;
		return false;
	}
	
	/**
	 * 判断是否为数组<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年3月26日 下午1:33:25
	 * @param Object target 数据
	 * @return boolean 判断结果
	 */
	public static boolean isArray(Object target) throws Exception{
		if(target instanceof JSONArray) return true;
		if(target instanceof Collection) return true;
		if(target instanceof Object[]) return true;
		if(target instanceof int[]) return true;
		if(target instanceof float[]) return true;
		if(target instanceof double[]) return true;
		if(target instanceof long[]) return true;
		if(target instanceof boolean[]) return true;
		if(target instanceof char[]) return true;
		if(target instanceof short[]) return true;
		if(target instanceof byte[]) return true;
		return false;
	}
	
	/**
	 * 判断是否为对象<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年3月26日 下午1:34:09
	 * @param Object target 数据
	 * @return boolean 判断结果
	 */
	public static boolean isObject(Object target) throws Exception{
		return !(isBasicType(target) || isArray(target));
	}
	
}
