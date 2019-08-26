package com.sumavision.tetris.menu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.sumavision.tetris.commons.util.wrapper.HashMapWrapper;
import com.sumavision.tetris.menu.exception.MenuNotExistException;
import com.sumavision.tetris.mvc.ext.response.json.aop.annotation.JsonBody;
import com.sumavision.tetris.system.role.SystemRoleQuery;
import com.sumavision.tetris.system.role.SystemRoleVO;
import com.sumavision.tetris.user.UserQuery;
import com.sumavision.tetris.user.UserVO;

@Controller
@RequestMapping(value = "/system/role/menu/permission")
public class SystemRoleMenuPermissionController {

	@Autowired
	private UserQuery userQuery;
	
	@Autowired
	private MenuDAO menuDao;
	
	@Autowired
	private SystemRoleQuery systemRoleQuery;
	
	@Autowired
	private SystemRoleMenuPermissionDAO systemRoleMenuPermissionDao;
	
	@Autowired
	private SystemRoleMenuPermissionQuery systemRoleMenuPermissionQuery;
	
	@Autowired
	private SystemRoleMenuPermissionService systemRoleMenuPermissionService;
	
	/**
	 * 分页查询菜单授权情况<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月17日 下午1:43:35
	 * @param Long menuId 菜单id
	 * @return List<SystemRoleMenuPermissionPO> 权限列表
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/list")
	public Object list(
			Long menuId,
			int pageSize,
			int currentPage,
			HttpServletRequest request) throws Exception{
		
		UserVO user = userQuery.current();
		
		//TODO 权限校验
		
		MenuPO menu = menuDao.findOne(menuId);
		if(menu == null){
			throw new MenuNotExistException(menuId);
		}
		
		int total = systemRoleMenuPermissionDao.countByMenuId(menuId);
		
		List<SystemRoleMenuPermissionPO> permissions = systemRoleMenuPermissionQuery.findByMenuIdOrderByUpdateTimeDesc(menuId, currentPage, pageSize);
		
		List<SystemRoleVO> roles = null;
		
		List<SystemRoleMenuPermissionVO> view_permissions = new ArrayList<SystemRoleMenuPermissionVO>();
		
		if(permissions!=null && permissions.size()>0){
			
			Set<String> roleIds = new HashSet<String>();
			for(SystemRoleMenuPermissionPO permission:permissions){
				roleIds.add(permission.getRoleId());
			}
			
			roles = systemRoleQuery.listByIds(roleIds);
			
			for(int i=0; i<permissions.size(); i++){
				SystemRoleMenuPermissionPO permission = permissions.get(i);
				for(SystemRoleVO role:roles){
					if(role.getId().equals(permission.getRoleId())){
						view_permissions.add(new SystemRoleMenuPermissionVO().set(permission, role));
						break;
					}
				}
			}
			
		}
		
		return new HashMapWrapper<String, Object>().put("total", total)
											       .put("rows", view_permissions)
											       .getMap();
	}
	
	/**
	 * 菜单绑定系统角色权限<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月18日 下午3:06:30
	 * @param @PathVariable Long id 菜单id
	 * @param JSONString roles 系统角色列表
	 * @return List<SystemRoleMenuPermissionVO> 添加的权限
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/bind/roles/{id}")
	public Object bindRoles(
			@PathVariable Long id,
			String roles,
			HttpServletRequest request) throws Exception{
		
		UserVO user = userQuery.current();
		
		//TODO 权限校验
		
		if(roles==null || "".equals(roles)) return null;
		
		List<SystemRoleVO> systemRoles = JSON.parseArray(roles, SystemRoleVO.class);
		
		List<SystemRoleMenuPermissionVO> permissions = systemRoleMenuPermissionService.bind(id, systemRoles);
		
		return permissions;
	}
	
	/**
	 * 菜单解除绑定系统角色权限<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月18日 下午4:23:40
	 * @param @PathVariable Long id 权限id
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/unbind/{id}")
	public Object unbind(
			@PathVariable Long id, 
			HttpServletRequest request) throws Exception{
		
		UserVO user = userQuery.current();
		
		//TODO 权限校验
		
		SystemRoleMenuPermissionPO permission = systemRoleMenuPermissionDao.findOne(id);
		
		if(permission != null){
			systemRoleMenuPermissionDao.delete(permission);
		}
		
		return null;
	}
	
}
