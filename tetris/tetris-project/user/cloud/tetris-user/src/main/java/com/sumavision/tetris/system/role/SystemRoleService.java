package com.sumavision.tetris.system.role;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 系统角色操作（主增删改）<br/>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年1月23日 下午2:18:15
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SystemRoleService {

	@Autowired
	private SystemRoleDAO systemRoleDao;
	
	@Autowired
	private UserSystemRolePermissionDAO userSystemRolePermissionDao;
	
	/**
	 * 批量删除系统角色<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月23日 下午2:17:25
	 * @param Collection<SystemRolePO> roles 系统角色列表
	 */
	public void delete(Collection<SystemRolePO> roles) throws Exception{
		
		if(roles==null || roles.size()<=0) return;
		
		Set<Long> roleIds = new HashSet<Long>();
		for(SystemRolePO role:roles){
			roleIds.add(role.getId());
		}
		
		List<UserSystemRolePermissionPO> permissions = userSystemRolePermissionDao.findByRoleIdIn(roleIds);
		if(permissions!=null && permissions.size()>0){
			userSystemRolePermissionDao.deleteInBatch(permissions);
		}
		
		systemRoleDao.deleteInBatch(roles);
	}
	
}
