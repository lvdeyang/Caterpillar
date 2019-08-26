package com.sumavision.tetris.system.role;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sumavision.tetris.system.role.exception.SystemRoleNotExistException;
import com.sumavision.tetris.user.UserDAO;
import com.sumavision.tetris.user.UserPO;
import com.sumavision.tetris.user.exception.UserNotExistException;

/**
 * 用户系统角色权限操作（主增删改）<br/>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年1月21日 下午2:32:04
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserSystemRolePermissionService {

	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private SystemRoleDAO systemRoleDao;
	
	@Autowired
	private UserSystemRolePermissionDAO userSystemRolePermissionDao;
	
	/**
	 * 用户绑定系统角色<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月21日 下午2:07:50
	 * @param Long userId 用户id
	 * @param Collection<Long> roleIds 系统角色id列表
	 * @return List<UserSystemRolePermissionVO> 新建的权限列表
	 */
	public List<UserSystemRolePermissionVO> bindSystemRole(Long userId, Collection<Long> roleIds) throws Exception{
		
		UserPO user = userDao.findOne(userId);
		if(user == null){
			throw new UserNotExistException(userId);
		}
		
		List<SystemRolePO> roles = systemRoleDao.findAll(roleIds);
		if(roles==null || roles.size()<=0) return null;
		
		List<UserSystemRolePermissionPO> existPermissions = userSystemRolePermissionDao.findByUserIdAndRoleIdIn(userId, roleIds);
		
		List<UserSystemRolePermissionPO> permissions = new ArrayList<UserSystemRolePermissionPO>();
		for(SystemRolePO role:roles){
			boolean finded = false;
			if(existPermissions!=null && existPermissions.size()>0){
				for(UserSystemRolePermissionPO existPermission:existPermissions){
					if(role.getId().equals(existPermission.getRoleId())){
						finded = true;
						break;
					}
				}
			}
			if(!finded){
				UserSystemRolePermissionPO permission = new UserSystemRolePermissionPO();
				permission.setRoleId(role.getId());
				permission.setUserId(user.getId());
				permission.setAutoGeneration(false);
				permission.setUpdateTime(new Date());
				permission.setRoleType(role.getType());
				permissions.add(permission);
			}
		}
		
		userSystemRolePermissionDao.save(permissions);
		
		List<UserSystemRolePermissionVO> view_permissions = new ArrayList<UserSystemRolePermissionVO>();
		for(UserSystemRolePermissionPO permission:permissions){
			for(SystemRolePO role:roles){
				if(permission.getRoleId().equals(role.getId())){
					view_permissions.add(new UserSystemRolePermissionVO().set(permission, role));
					break;
				}
			}
		}
		
		return view_permissions;
	}
	
	/**
	 * 系统角色授权用户<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月23日 下午8:06:45
	 * @param Long roleId 系统角色id
	 * @param Collectin<Long> userIds 用户id列表
	 * @return List<UserSystemRolePermissionVO> 添加的权限列表
	 */
	public List<UserSystemRolePermissionVO> bindUser(Long roleId, Collection<Long> userIds) throws Exception{
		
		SystemRolePO role = systemRoleDao.findOne(roleId);
		if(role == null){
			throw new SystemRoleNotExistException(roleId);
		}
		
		List<UserPO> users = userDao.findAll(userIds);
		if(users==null || users.size()<=0) return null;
		
		List<UserSystemRolePermissionPO> existPermissions = userSystemRolePermissionDao.findByRoleIdAndUserIdIn(roleId, userIds);
		
		List<UserSystemRolePermissionPO> permissions = new ArrayList<UserSystemRolePermissionPO>();
		for(UserPO user:users){
			boolean finded = false;
			if(existPermissions!=null && existPermissions.size()>0){
				for(UserSystemRolePermissionPO existPermission:existPermissions){
					if(user.getId().equals(existPermission.getUserId())){
						finded = true;
						break;
					}
				}
			}
			if(!finded){
				UserSystemRolePermissionPO permission = new UserSystemRolePermissionPO();
				permission.setRoleId(role.getId());
				permission.setUserId(user.getId());
				permission.setAutoGeneration(false);
				permission.setUpdateTime(new Date());
				permission.setRoleType(role.getType());
				permissions.add(permission);
			}
		}
		
		userSystemRolePermissionDao.save(permissions);
		
		List<UserSystemRolePermissionVO> view_permissions = new ArrayList<UserSystemRolePermissionVO>();
		for(UserSystemRolePermissionPO permission:permissions){
			for(UserPO user:users){
				if(permission.getUserId().equals(user.getId())){
					view_permissions.add(new UserSystemRolePermissionVO().set(permission, user));
					break;
				}
			}
		}
		
		return view_permissions;
		
	}
	
	/**
	 * 解绑用户系统角色权限<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月21日 下午5:29:54
	 * @param Long id 权限id
	 */
	public void unbind(Long id) throws Exception{
		UserSystemRolePermissionPO permission = userSystemRolePermissionDao.findOne(id);
		
		if(permission != null){
			userSystemRolePermissionDao.delete(permission);
		}
	}
	
}
