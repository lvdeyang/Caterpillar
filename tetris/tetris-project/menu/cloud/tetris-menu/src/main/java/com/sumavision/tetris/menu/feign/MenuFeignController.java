package com.sumavision.tetris.menu.feign;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sumavision.tetris.menu.MenuQuery;
import com.sumavision.tetris.menu.MenuVO;
import com.sumavision.tetris.mvc.ext.response.json.aop.annotation.JsonBody;
import com.sumavision.tetris.user.UserQuery;
import com.sumavision.tetris.user.UserVO;

/**
 * 菜单feign接口<br/>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年1月16日 上午10:46:14
 */
@Controller
@RequestMapping(value = "/menu/feign")
public class MenuFeignController {

	@Autowired
	private MenuQuery menuQuery;
	
	@Autowired
	private UserQuery userQuery;
	
	/**
	 * 获取有权限的菜单（feign接口）<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月16日 上午10:43:43
	 * @return List<MenuVO> 菜单列表
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/permission/menus")
	public Object permissionMenus(HttpServletRequest request) throws Exception{
		
		UserVO user = userQuery.current();
		
		List<MenuVO> menus = menuQuery.permissionMenus(user);
		
		return menus;
	}
	
}
