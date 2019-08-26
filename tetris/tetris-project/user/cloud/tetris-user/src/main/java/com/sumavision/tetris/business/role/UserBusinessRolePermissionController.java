package com.sumavision.tetris.business.role;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.sumavision.tetris.mvc.ext.response.json.aop.annotation.JsonBody;
import com.sumavision.tetris.system.role.SystemRoleType;
import com.sumavision.tetris.system.role.UserSystemRolePermissionQuery;
import com.sumavision.tetris.system.role.UserSystemRolePermissionService;
import com.sumavision.tetris.system.role.UserSystemRolePermissionVO;
import com.sumavision.tetris.user.UserVO;

@Controller
@RequestMapping(value = "/user/business/role/permission")
public class UserBusinessRolePermissionController {

	@Autowired
	private UserSystemRolePermissionQuery userSystemRolePermissionQuery;
	
	@Autowired
	private UserSystemRolePermissionService userSystemRolePermissionService;
	
	/**
	 * 分页查询用户绑定的业务角色<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月21日 下午12:28:39
	 * @param Long userId 用户id
	 * @param int currentPage 当前页码
	 * @param int pageSize 每页数据量
	 * @return int total 用户绑定的角色数量
	 * @return List<UserSystemRolePermissionVO> rows 系统角色权限列表
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/list/by/userId")
	public Object listByUserId(
			Long userId,
			int currentPage,
			int pageSize,
			HttpServletRequest request) throws Exception{
		
		return userSystemRolePermissionQuery.listByUserIdAndRoleType(userId, SystemRoleType.BUSINESS, currentPage, pageSize);
	}
	
	/**
	 * 分页查询业务角色的权限绑定信息<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月23日 下午5:10:27
	 * @param Long roleId 系统角色id
	 * @param int currentPage 当前页码
	 * @param int pageSize 每页数据量
	 * @return int total 总数据量
	 * @return rows List<UserSystemRolePermissionVO> 权限列表
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/list/by/roleId")
	public Object listByRoleId(
			Long roleId,
			int currentPage,
			int pageSize,
			HttpServletRequest request) throws Exception{
		return userSystemRolePermissionQuery.listByRoleId(roleId, currentPage, pageSize);
	}
	
	/**
	 * 用户绑定业务角色<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月21日 下午2:35:47
	 * @param Long userId 用户id
	 * @param JSONString roleIds 系统角色id列表
	 * @return List<UserSystemRolePermissionVO> 绑定的权限列表
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/bind/system/role")
	public Object bindSystemRole(
			Long userId,
			String roleIds,
			HttpServletRequest request) throws Exception{
		
		if(roleIds==null || "".equals(roleIds)) return null;
		
		List<UserSystemRolePermissionVO> permissions = userSystemRolePermissionService.bindSystemRole(userId, JSON.parseArray(roleIds, Long.class));
		
		return permissions;
	}
	
	/**
	 * 解绑用户系统角色权限<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月21日 下午5:30:41
	 * @param @PathVariable Long id 权限id
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/unbind/{id}")
	public Object unbind(
			@PathVariable Long id,
			HttpServletRequest request) throws Exception{
		userSystemRolePermissionService.unbind(id);
		return null;
	}
	
	/**
	 * 系统角色授权用户<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月23日 下午8:09:36
	 * @param Long roleId 系统角色id
	 * @param JSONString userIds 用户id列表
	 * @return List<UserSystemRolePermissionVO> 权限列表
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/bind/user")
	public Object bindUser(
			Long roleId,
			String userIds,
			HttpServletRequest request) throws Exception{
		
		if(userIds == null) return null;
		
		List<UserSystemRolePermissionVO> permissions = userSystemRolePermissionService.bindUser(roleId, JSON.parseArray(userIds, Long.class));
		
		return permissions;
	}
	
}
