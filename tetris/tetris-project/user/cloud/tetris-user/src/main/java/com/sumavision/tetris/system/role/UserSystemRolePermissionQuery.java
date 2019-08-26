package com.sumavision.tetris.system.role;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import com.sumavision.tetris.commons.util.wrapper.HashMapWrapper;
import com.sumavision.tetris.user.UserDAO;
import com.sumavision.tetris.user.UserPO;

/**
 * 用户绑定系统角色查询操作<br/>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年1月21日 下午1:13:26
 */
@Component
public class UserSystemRolePermissionQuery {

	@Autowired
	private SystemRoleDAO systemRoleDao;
	
	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private UserSystemRolePermissionDAO userSystemRolePermissionDao;
	
	/**
	 * 分页查询用户绑定的系统角色信息<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月21日 下午1:13:58
	 * @param Long userId 用户id
	 * @param int currentPage 当前页码
	 * @param int pageSize 每页数据量
	 * @return long total 用户绑定系统角色数量
	 * @return List<UserSystemRolePermissionVO> rows 系统角色权限列表
	 */
	public Map<String, Object> listByUserIdAndRoleType(Long userId, SystemRoleType type, int currentPage, int pageSize) throws Exception{
		
		long total = userSystemRolePermissionDao.countByUserIdAndRoleType(userId, type);
		
		List<UserSystemRolePermissionPO> permissions = findByUserIdAndRoleType(userId, type, currentPage, pageSize);
		List<UserSystemRolePermissionVO> rows = new ArrayList<UserSystemRolePermissionVO>();
		
		if(permissions!=null && permissions.size()>0){
			Set<Long> roleIds = new HashSet<Long>();
			for(UserSystemRolePermissionPO permission:permissions){
				roleIds.add(permission.getRoleId());
			}
			List<SystemRolePO> roles = systemRoleDao.findAll(roleIds);
			for(UserSystemRolePermissionPO permission:permissions){
				for(SystemRolePO role:roles){
					if(permission.getRoleId().equals(role.getId())){
						rows.add(new UserSystemRolePermissionVO().set(permission, role));
						break;
					}
				}
			}
		}
		
		return new HashMapWrapper<String, Object>().put("total", total)
												   .put("rows", rows)
												   .getMap();
	}
	
	/**
	 * 分页获取用户的系统角色权限绑定<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月19日 下午4:34:45
	 * @param Long userId 用户id
	 * @param SystemRoleType roleType 角色类型
	 * @param int currentPage 当前页码
	 * @param int pageSize 每页数据量
	 * @return List<UserSystemRolePermissionPO> 权限列表
	 */
	public List<UserSystemRolePermissionPO> findByUserIdAndRoleType(Long userId, SystemRoleType roleType, int currentPage, int pageSize) throws Exception{
		Pageable page = new PageRequest(currentPage-1, pageSize);
		Page<UserSystemRolePermissionPO> permissions = userSystemRolePermissionDao.findByUserIdAndRoleType(userId, roleType, page);
		return permissions.getContent();
	}
	
	/**
	 * 分页查询系统角色的权限绑定信息<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月23日 下午5:04:48
	 * @param Long roleId 系统角色id
	 * @param int currentPage 档期页码
	 * @param int pageSize 每页数据量
	 * @return int total 总数据量
	 * @return rows List<UserSystemRolePermissionVO> 权限列表
	 */
	public Map<String, Object> listByRoleId(Long roleId, int currentPage, int pageSize) throws Exception{
		
		int total = userSystemRolePermissionDao.countByRoleId(roleId);
		
		List<UserSystemRolePermissionPO> permissions = findByRoleId(roleId, currentPage, pageSize);
		List<UserSystemRolePermissionVO> rows = new ArrayList<UserSystemRolePermissionVO>();
		
		if(permissions!=null && permissions.size()>0){
			Set<Long> userIds = new HashSet<Long>();
			for(UserSystemRolePermissionPO permission:permissions){
				userIds.add(permission.getUserId());
			}
			
			List<UserPO> users = userDao.findAll(userIds);
			for(UserSystemRolePermissionPO permission:permissions){
				for(UserPO user:users){
					if(permission.getUserId().equals(user.getId())){
						rows.add(new UserSystemRolePermissionVO().set(permission, user));
						break;
					}
				}
			}
		}
		
		return new HashMapWrapper<String, Object>().put("total", total)
												   .put("rows", rows)
												   .getMap();
	}
	
	/**
	 * 分页查询系统角色的权限绑定信息<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月23日 下午5:02:34
	 * @param Long roleId 系统角色id
	 * @param int currentPage 当前页码
	 * @param int pageSize 每页数据量
	 * @return List<UserSystemRolePermissionPO> 权限列表
	 */
	public List<UserSystemRolePermissionPO> findByRoleId(Long roleId, int currentPage, int pageSize) throws Exception{
		Pageable page = new PageRequest(currentPage-1, pageSize);
		Page<UserSystemRolePermissionPO> roles = userSystemRolePermissionDao.findByRoleId(roleId, page);
		return roles.getContent();
	}
	
}
