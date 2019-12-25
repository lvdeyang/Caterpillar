package com.sumavision.tetris.capacity.enumeration;

import com.sumavision.tetris.orm.exception.ErrorTypeException;

/**
 * 任务返回状态码<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月20日 上午11:33:06
 */
public enum TaskResponseEnum {

	SUCCESS(0, "成功");
	
	private Integer code;
	
	private String message;
	
	public Integer getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	private TaskResponseEnum (Integer code, String message){
		this.code = code;
		this.message = message;
	}
	
	public static TaskResponseEnum fromCode(Integer code) throws Exception{
		TaskResponseEnum[] values = TaskResponseEnum.values();
		for(TaskResponseEnum value: values){
			if(value.getCode().equals(code)){
				return value;
			}
		}
		throw new ErrorTypeException("code", code);
	}
}
