package com.sumavision.tetris.capacity.enumeration;

import com.sumavision.tetris.orm.exception.ErrorTypeException;

/**
 * 输出返回状态码<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月20日 下午1:45:19
 */
public enum OutputResponseEnum {

	SUCCESS(0, "成功"),
	ERROR(1, "参数错误"),
	EXSIT(2, "输出已存在");
	
	private Integer code;
	
	private String message;

	public Integer getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
	
	private OutputResponseEnum(Integer code, String message){
		this.code = code;
		this.message = message;
	}
	
	public static OutputResponseEnum fromCode(Integer code) throws Exception{
		OutputResponseEnum[] values = OutputResponseEnum.values();
		for(OutputResponseEnum value: values){
			if(value.getCode().equals(code)){
				return value;
			}
		}
		
		throw new ErrorTypeException("code", code);
	}
	
}
