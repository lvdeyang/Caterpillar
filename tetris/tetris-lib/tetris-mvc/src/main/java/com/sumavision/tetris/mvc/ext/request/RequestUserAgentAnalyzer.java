package com.sumavision.tetris.mvc.ext.request;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class RequestUserAgentAnalyzer {

	/**
	 * 判断是否是移动终端请求<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年3月12日 上午10:06:07
	 * @param HttpServletRequest request 请求
	 * @return boolean 判断结果
	 */
	public boolean isMobileDevie(HttpServletRequest request){
		return userAgentContains(request, "android","mac os","windows phone");
	}
	
	/**
	 * 判断是否是安卓终端请求<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年3月12日 上午10:06:07
	 * @param HttpServletRequest request 请求
	 * @return boolean 判断结果
	 */
	public boolean isAndroidDevice(HttpServletRequest request){
		return userAgentContains(request, "android");
	}
	
	/**
	 * 判断是否是苹果终端请求<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年3月12日 上午10:06:07
	 * @param HttpServletRequest request 请求
	 * @return boolean 判断结果
	 */
	public boolean isMacosDevice(HttpServletRequest request){
		return userAgentContains(request, "mac os");
	}
	
	/**
	 * 判断是否是windowsphone终端请求<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年3月12日 上午10:06:07
	 * @param HttpServletRequest request 请求
	 * @return boolean 判断结果
	 */
	public boolean isWindowsPhone(HttpServletRequest request){
		return userAgentContains(request, "windows phone");
	}
	
	/**
	 * 判断请求来源<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年3月12日 上午10:07:35
	 * @param HttpServletRequest request 请求
	 * @param String... types 终端类型
	 * @return boolean 判断结果
	 */
	private boolean userAgentContains(HttpServletRequest request, String... types){
		String requestHeader = request.getHeader("user-agent");
		if(requestHeader == null){
			return false;
		}else{
			requestHeader = requestHeader.toLowerCase();
	        for(int i=0;i<types.length;i++){
	            if(requestHeader.indexOf(types[i])>0){
	                return true;
	            }
	        }
	        return false;
		}
	}
	
}
