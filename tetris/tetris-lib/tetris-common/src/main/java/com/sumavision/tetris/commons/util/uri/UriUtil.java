package com.sumavision.tetris.commons.util.uri;

import java.util.Arrays;
import java.util.Collection;

public class UriUtil {

	/**
	 * 对比uri<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年4月3日 上午10:20:04
	 * @param String test 对比值
	 * @param Collection<String> pattens 对比范围
	 * @return boolean 对比结果
	 */
	public static boolean match(String test, Collection<String> pattens){
		for(String patten:pattens){
			if(patten.equals(test)){
				return true;
			}else{
				if(patten.endsWith("/*")){
					patten = patten.replace("/*", "");
					if(test.startsWith(patten)){
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * 对比uri<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年4月3日 上午10:20:04
	 * @param String test 对比值
	 * @param String[] pattens 对比范围
	 * @return boolean 对比结果
	 */
	public static boolean match(String test, String[] pattens){
		return match(test, Arrays.asList(pattens));
	}
	
}
