package com.guolaiwan.app.xinglongshan.utils;

/**
 * 业务异常对象。
 * @author liwei
 */
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 5151669876695986575L;

	public BusinessException() {
		super();
	}
	
	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessException(Throwable cause) {
		super(cause);
	}
}
