package com.sumavision.tetris.system.role;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 系统角色组操作（主增删改）<br/>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年1月23日 下午1:47:26
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SystemRoleGroupService {

	@Autowired
	private SystemRoleGroupDAO systemRoleGroupDao;
	
	@Autowired
	private SystemRoleDAO systemRoleDao;
	
	@Autowired
	private SystemRoleService systemRoleService;
	
	/**
	 * 删除系统角色组<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月23日 下午2:20:12
	 * @param Long id 角色组id
	 */
	public void delete(Long id) throws Exception{
		
		SystemRoleGroupPO group = systemRoleGroupDao.findOne(id);
		if(group != null){
			systemRoleGroupDao.delete(group);
		}

		List<SystemRolePO> roles = systemRoleDao.findBySystemRoleGroupId(group.getId());
		if(roles!=null && roles.size()>0){
			systemRoleService.delete(roles);
		}
		
	}
	
}
