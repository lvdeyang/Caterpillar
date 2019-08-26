package com.sumavision.tetris.menu;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.sumavision.tetris.config.feign.FeignConfiguration;
import com.sumavision.tetris.user.UserVO;

/**
 * 菜单工具类<br/>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2018年11月20日 下午1:47:17
 */
@FeignClient(name = "tetris-menu", configuration = FeignConfiguration.class)
public interface MenuFeign {

	/**
	 * 获取有权限的菜单<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月11日 下午3:44:52
	 * @param UserVO user 用户
	 * @return List<MenuVO> 菜单列表
	 */
	@RequestMapping(value = "/menu/feign/permission/menus")
	public JSONObject permissionMenus(UserVO user) throws Exception;
	
}