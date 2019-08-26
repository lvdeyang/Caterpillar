package com.sumavision.tetris.menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sumavision.tetris.commons.util.wrapper.StringBufferWrapper;
import com.sumavision.tetris.menu.MenuPO.MenuComparator;
import com.sumavision.tetris.system.role.SystemRoleQuery;
import com.sumavision.tetris.system.role.SystemRoleVO;
import com.sumavision.tetris.user.UserVO;

/**
 * 菜单工具类<br/>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2018年11月20日 下午1:47:17
 */
@Component
public class MenuQuery {

	@Autowired
	private SystemRoleQuery systemRoleQuery;
	
	@Autowired
	private MenuDAO menuDao;
	
	@Autowired
	private MenuComparator menuComparator;
	
	/**
	 * 获取有权限的菜单<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月11日 下午3:44:52
	 * @param UserVO user 用户
	 * @return List<MenuVO> 菜单列表
	 */
	public List<MenuVO> permissionMenus(UserVO user) throws Exception{
		
		List<SystemRoleVO> roles = systemRoleQuery.queryUserRoles(user.getUuid());
		if(roles!=null && roles.size()>0){
			Set<String> roleIds = new HashSet<String>();
			for(SystemRoleVO role:roles){
				roleIds.add(role.getId());
			}
			
			//根据权限获取菜单
			List<MenuPO> menus = menuDao.findByRoleIdIn(roleIds);
			
			List<MenuPO> parentMenus = null;
			if(menus!=null && menus.size()>0) parentMenus = queryParentMenus(menus);
			
			List<MenuPO> totalMenus = new ArrayList<MenuPO>();
			if(menus!=null && menus.size()>0) totalMenus.addAll(menus);
			if(parentMenus!=null && parentMenus.size()>0) totalMenus.addAll(parentMenus);
			
			//重新排序
			Collections.sort(totalMenus, menuComparator);
			
			List<MenuVO> rootMenus = generateRootMenus(totalMenus);
			packMenuTree(rootMenus, totalMenus);
			
			return rootMenus;
		}
		
		return null;
	}
	
	/**
	 * 获取给定菜单列表的所有父级菜单<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2018年11月20日 下午1:52:07
	 * @param List<MenuPO> menus 菜单列表
	 * @return List<MenuPO> 父级菜单
	 */
	public List<MenuPO> queryParentMenus(List<MenuPO> menus) throws Exception{
		Set<Long> groupMenuIds = new HashSet<Long>();
		for(int i=0; i<menus.size(); i++){
			MenuPO menuPO = menus.get(i);
			String menuIdPath = menuPO.getMenuIdPath();
			if(menuIdPath != null){
				String[] ids = menuIdPath.split(MenuPO.SEPARATOR);
				for(int j=1; j<ids.length; j++){
					groupMenuIds.add(Long.valueOf(ids[j]));
				}
			}
		}
		List<MenuPO> groupMenus = menuDao.findByIdInOrderBySerialAsc(groupMenuIds);
		return groupMenus;
	}
	
	/**
	 * 根据给定的菜单列表获取根菜单，并包装成VO<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2018年11月20日 下午1:54:31
	 * @param List<MenuPO> menus 菜单列表
	 * @return List<MenuVO> 根菜单
	 */
	public List<MenuVO> generateRootMenus(List<MenuPO> menus) throws Exception{
		List<MenuVO> viewMenus = new ArrayList<MenuVO>();
		for(int i=0; i<menus.size(); i++){
			MenuPO menu = menus.get(i);
			if(menu.getParentId() == null){
				viewMenus.add(new MenuVO().set(menu));
			}
		}
		return viewMenus;
	}
	
	/**
	 * 根据给定的根菜单组装菜单树，并且包装成VO<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2018年11月20日 下午2:02:38
	 * @param List<MenuVO> rootMenus 根菜单
	 * @param List<MenuPO> totalMenus 全部菜单
	 */
	public void packMenuTree(List<MenuVO> rootMenus, List<MenuPO> totalMenus) throws Exception{
		for(int i=0; i<rootMenus.size(); i++){
			MenuVO rootMenu = rootMenus.get(i);
			for(int j=0; j<totalMenus.size(); j++){
				MenuPO menu = totalMenus.get(j);
				if(menu.getParentId()!=null && menu.getParentId().equals(rootMenu.getId())){
					if(rootMenu.getSub() == null) rootMenu.setSub(new ArrayList<MenuVO>());
					rootMenu.getSub().add(new MenuVO().set(menu));
				}
			}
			if(rootMenu.getSub()!=null && rootMenu.getSub().size()>0){
				packMenuTree(rootMenu.getSub(), totalMenus);
			}
		}
	}
	
	/**
	 * 查询一个菜单下的所有子菜单<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月17日 上午9:34:32
	 * @param Long id 给定菜单id
	 * @return List<MenuPO> 所有的子菜单
	 */
	public List<MenuPO> findAllSubMenus(Long id){
		return menuDao.findAllSubMenus(new StringBufferWrapper().append("%/")
														        .append(id)
														        .toString(), 
									   new StringBufferWrapper().append("%/")
															    .append(id)
															    .append("/%")
															    .toString());
	}
	
}
