package com.sumavision.tetris.business.role;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sumavision.tetris.business.role.event.BusinessRoleRemovedEvent;
import com.sumavision.tetris.commons.util.wrapper.ArrayListWrapper;
import com.sumavision.tetris.system.role.SystemRoleDAO;
import com.sumavision.tetris.system.role.SystemRolePO;
import com.sumavision.tetris.system.role.SystemRoleType;
import com.sumavision.tetris.system.role.SystemRoleVO;
import com.sumavision.tetris.system.role.UserSystemRolePermissionDAO;
import com.sumavision.tetris.system.role.UserSystemRolePermissionPO;
import com.sumavision.tetris.user.UserQuery;
import com.sumavision.tetris.user.UserVO;

@Service
@Transactional(rollbackFor = Exception.class)
public class BusinessRoleService {

	@Autowired
	private UserQuery userQuery;
	
	@Autowired
	private SystemRoleDAO systemRoleDao;
	
	@Autowired
	private UserSystemRolePermissionDAO userSystemRolePermissionDao;
	
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;
	
	/**
	 * 添加企业业务角色<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年8月2日 下午5:31:49
	 * @param Long companyId 公司id
	 * @param String name 角色名称
	 * @return SystemRoleVO 角色
	 */
	public SystemRoleVO add(Long companyId, String name, boolean autoGeneration) throws Exception{
		SystemRolePO entity = new SystemRolePO();
		entity.setAutoGeneration(autoGeneration);
		entity.setCompanyId(companyId);
		entity.setName(name);
		entity.setType(SystemRoleType.BUSINESS);
		entity.setUpdateTime(new Date());
		systemRoleDao.save(entity);
		return new SystemRoleVO().set(entity);
	}
	
	/**
	 * 添加企业业务角色<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年8月2日 下午5:31:49
	 * @param String name 角色名称
	 * @return SystemRoleVO 角色
	 */
	public SystemRoleVO add(String name) throws Exception{
		UserVO user = userQuery.current();
		return add(Long.valueOf(user.getGroupId()), name, false);
	}
	
	/**
	 * 修改企业业务角色<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年8月2日 下午5:31:49
	 * @param Long id 角色id
	 * @param String name 角色名称
	 * @return SystemRoleVO 角色
	 */
	public SystemRoleVO edit(Long id, String name) throws Exception{
		SystemRolePO role = systemRoleDao.findOne(id);
		if(role != null){
			role.setName(name);
			systemRoleDao.save(role);
			return new SystemRoleVO().set(role);
		}
		return null;
	}
	
	/**
	 * 删除业务角色<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年8月6日 上午10:16:55
	 * @param Long id 角色id
	 */
	public void remove(Long id) throws Exception{
		SystemRolePO role = systemRoleDao.findOne(id);
		if(role != null){
			systemRoleDao.delete(role);
			//发布业务角色删除事件
			BusinessRoleRemovedEvent event = new BusinessRoleRemovedEvent(applicationEventPublisher, role.getId(), role.getName(), role.getCompanyId().toString());
			applicationEventPublisher.publishEvent(event);
		}
		List<UserSystemRolePermissionPO> permissions = userSystemRolePermissionDao.findByRoleIdIn(new ArrayListWrapper<Long>().add(id).getList());
		if(permissions!=null && permissions.size()>0){
			userSystemRolePermissionDao.deleteInBatch(permissions);
		}
	}
	
}
