package com.sumavision.tetris.commons.exception.code;

import com.sumavision.tetris.commons.exception.BaseException;
import com.sumavision.tetris.commons.util.wrapper.StringBufferWrapper;

/**
 * 业务状态码
 * lvdeyang 2017年6月23日
 */
public enum StatusCode {

	//成功
	SUCCESS(200),
	
	//服务器拒绝
	FORBIDDEN(403),
	
	//未找到资源
	NOTFOUND(404),
	
	//超时
	TIMEOUT(408),
	
	//冲突
	CONFLICT(409),
	
	//异常
	ERROR(500);
	
	private int code;
	
	private StatusCode(int code){
		this.code = code;
	}
	
	public int getCode(){
		return this.code;
	}
	
	@Override
	public String toString(){
		return String.valueOf(this.code);
	}
	
	public static StatusCode fromCode(int code) throws Exception{
		StatusCode[] values = StatusCode.values();
		for(StatusCode value:values){
			if(value.getCode() == code){
				return value;
			}
		}
		throw new BaseException(StatusCode.FORBIDDEN, new StringBufferWrapper().append("错误的枚举：code:")
																			   .append(code)
																			   .toString());
	}
	
}
