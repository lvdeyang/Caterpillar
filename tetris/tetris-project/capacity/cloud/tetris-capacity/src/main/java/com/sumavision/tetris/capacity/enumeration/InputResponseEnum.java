package com.sumavision.tetris.capacity.enumeration;

import com.sumavision.tetris.orm.exception.ErrorTypeException;

/**
 * 输入返回响应码<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月20日 上午9:02:43
 */
public enum InputResponseEnum {

	SUCCESS(0, "成功"),
	ERROR(1, "参数错误"),
	EXIST(2, "输入已存在");
	
	private Integer code;
	
	private String message;
	
	public Integer getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	private InputResponseEnum(Integer code, String message){
		this.code = code;
		this.message = message;
	}
	
	public static InputResponseEnum fromCode(Integer code) throws Exception{
		InputResponseEnum[] values = InputResponseEnum.values();
		for(InputResponseEnum value: values){
			if(value.getCode().equals(code)){
				return value;
			}
		}
		
		throw new ErrorTypeException("code", code);
	}
	
}
