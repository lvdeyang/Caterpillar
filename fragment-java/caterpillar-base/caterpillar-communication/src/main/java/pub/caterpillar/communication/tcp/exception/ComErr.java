/**
 * Copyright (C) 2014 Sumavision
 * @author:zhuzheng
 * @date:2014骞�鏈�鏃�涓婂崍11:26:00
 */
package pub.caterpillar.communication.tcp.exception;



public class ComErr {
	public static ComErr TRY_LOCK_ERR = new ComErr("com_001", "");
	public static ComErr CONNECT_HAS_SHUTDOWN_ERR = new ComErr("com_002", "");
	public static ComErr CLIENT_IS_NOT_EXIST = new ComErr("com_003","");
	/**
	 * @param errCode
	 */
	public ComErr(String errCode) {
		
	}
	
	public ComErr(String errCode, String errMsg)
	{
		
	}
}
