package com.sumavision.tetris.capacity.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class QueryUtil {

	/**
	 * 校验ip是否是组播ip<br/>
	 * <b>作者:</b>wjw<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年12月4日 上午9:49:38
	 * @param String ip 需校验的ip
	 * @return boolean 是/否
	 */
	public boolean isMulticast(String ip) throws Exception{
		
		//匹配组播--"224.0.0.0~239.255.255.255"
		String regex = "2((2[4-9])|(3\\d))((\\.(([01]\\d{2})|(2(([0-4]\\d)|(5[0-5]))))){3}|(\\.\\d{2}){3}|(\\.\\d){3})";
		
		return ip.matches(regex);
	}
	
}
