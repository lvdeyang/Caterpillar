package com.guolaiwan.app.zhaji;

import org.springframework.stereotype.Service;

public class ZhajiService {
	
	public static String checkIn(String uniqueCode, String version){
		/**
		 * 处理传来数据
		 */
		String response=ThirdHttp.getInstance().checkIn(uniqueCode, version);
		/**
		 * 处理返回的数据
		 */
		return response;
	}
	
	public static String checkHeart(String uniqueCode) {
		/**
		 * 处理传来数据
		 */
		String response=ThirdHttp.getInstance().checkHeart(uniqueCode);
		/**
		 * 处理返回的数据
		 */
		return response;
	}

	public static String checkAvailable(AvailablePo po) {
		/**
		 * 处理传来数据
		 */
		String response=ThirdHttp.getInstance().checkAvailable(po);
		/**
		 * 处理返回的数据
		 */
		return response;
	}

}
