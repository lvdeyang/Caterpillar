package com.sumavision.tetris.mvc.constant;

/**
 * http相关常量<br/>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年3月7日 上午9:19:26
 */
public interface HttpConstant {

	/** header token */
	public static final String HEADER_AUTH_TOKEN = "tetris-001";
	
	/** header session id */
	public static final String HEADER_SESSION_ID = "tetris-002";
	
	/** header feign client id */
	public static final String HEADER_FEIGN_CLIENT = "tetris-005";
	
	/** feign 调用密钥 */
	public static final String HEADER_FEIGN_CLIENT_KEY = "4b159799e7ce411386e4cdfc3c8f2c39";
	
	/** header internal process client id */
	public static final String HEADER_PROCESS_CLIENT = "tetris-006";
	
	/** process 调用密钥 */
	public static final String HEADER_PROCESS_CLIENT_KEY = "6acee36b1d0346709ba3701b5d913601";
	
	/** process调用，做用户id登录 */
	public static final String HEADER_PROCESS_DO_USER_ID_LOGIN = "tetris-007";
	
	/** session attribute token */
	public static final String ATTRIBUTE_AUTH_TOKEN = "tetris-003";
	
	/** session attribute user */
	public static final String ATTRIBUTE_USER = "tetris-004";
	
	/** model token */
	public static final String MODEL_TOKEN = "token";
	
	/** model session id  */
	public static final String MODEL_SESSION_ID = "sessionId";
	
	/** session超时时间：30分钟 */
	public static final int SESSION_TIMEOUT = 30*60;
	
	/** 临时session超时时间：5秒 */
	public static final int TEMPORARY_SESSION_TIMEOUT = 5;
	
	/** 验证码有效期：3分钟 */
	public static final int VERIFICATION_CODE_TIMEOUT = 3*60;
	
}
