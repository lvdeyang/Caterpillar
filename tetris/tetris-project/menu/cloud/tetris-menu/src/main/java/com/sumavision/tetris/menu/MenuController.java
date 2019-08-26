package com.sumavision.tetris.menu;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sumavision.tetris.menu.MenuPO.MenuComparator;
import com.sumavision.tetris.mvc.ext.response.json.aop.annotation.JsonBody;
import com.sumavision.tetris.user.UserQuery;
import com.sumavision.tetris.user.UserVO;

/**
 * 菜单rest接口<br/>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年1月16日 上午10:46:14
 */
@Controller
@RequestMapping(value = "/menu")
public class MenuController {

	@Autowired
	private MenuQuery menuQuery;
	
	@Autowired
	private UserQuery userQuery;
	
	@Autowired
	private MenuDAO menuDao;
	
	@Autowired
	private MenuComparator menuComparator;
	
	@Autowired
	private MenuService menuService;
	
	/**
	 * 查询菜单树<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月16日 下午4:01:03
	 * @return List<MenuVO> 菜单树列表
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/list/tree")
	public Object listTree(HttpServletRequest request) throws Exception{
		
		UserVO user = userQuery.current();
		
		//TODO 判断访问权限
		
		List<MenuPO> totalMenus = menuDao.findAll();
		
		//重新排序
		Collections.sort(totalMenus, menuComparator);
		
		List<MenuVO> rootMenus = menuQuery.generateRootMenus(totalMenus);
		menuQuery.packMenuTree(rootMenus, totalMenus);
		
		return rootMenus;
	}
	
	/**
	 * 添加一个根菜单<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月16日 下午8:46:52
	 * @return MenuVO 添加的菜单
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping("/add/root")
	public Object addRoot(HttpServletRequest request) throws Exception{
		
		UserVO user = userQuery.current();
		
		//TODO 权限校验
		
		MenuVO menu = menuService.addRoot();
		
		return menu;
	}
	
	/**
	 * 添加一个子菜单<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月16日 下午8:47:34
	 * @param Long parentId 父菜单id
	 * @return MenuVO 添加的子菜单
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/add/sub")
	public Object addSub(
			Long parentId,
			HttpServletRequest request) throws Exception{
	
		UserVO user = userQuery.current();
		
		//TODO 权限校验
		
		MenuVO menu = menuService.addSub(parentId);
		
		return menu;
	}
	
	/**
	 * 删除菜单（带子菜单）<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月17日 上午9:18:21
	 * @param @PathVariable Long id 菜单id
	 * @return List<MenuVO> 删除的所有菜单
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/remove/{id}")
	public Object remove(
			@PathVariable Long id,
			HttpServletRequest request) throws Exception{
		
		UserVO user = userQuery.current();
		
		//TODO 权限校验
		
		List<MenuVO> menus = menuService.remove(id);
		
		return menus;
	}
	
	/**
	 * 修改一个菜单<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月17日 上午10:31:37
	 * @param @PathVariable Long id 菜单id
	 * @param String title 菜单标题
	 * @param String link 菜单链接
	 * @param String icon 菜单图标
	 * @param String style 菜单图标样式
	 * @param int serial 菜单显示顺序
	 * @return MenuVO 修改后的菜单
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/save/{id}")
	public Object save(
			@PathVariable Long id,
			String title,
            String link,
            String icon,
            String style,
            int serial,
			HttpServletRequest request) throws Exception{
		
		UserVO user = userQuery.current();
		
		//TODO 权限校验
		
		MenuVO menu = menuService.save(id, title, link, icon, style, serial);
		
		return menu;
	}
	
}
