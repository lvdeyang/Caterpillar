package com.sumavision.tetris.spring.zuul.config.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ZuulServerPropsQuery {

	@Autowired
	private ServerProps serverProps;
	
	/**
	 * 查询网关服务属性<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年3月4日 下午3:11:36
	 * @return ServerProps 服务属性
	 */
	public ServerProps queryProps(){
		return serverProps;
	}
	
}
