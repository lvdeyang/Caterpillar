package com.sumavision.tetris.system.role;

import java.util.ArrayList;
import java.util.Collection;
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

/**
 * 用户系统角色查询<br/>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年1月11日 下午3:21:45
 */
@Component
public class SystemRoleQuery {

	@Autowired
	private SystemRoleDAO systemRoleDao;
	
	@Autowired
	private SystemRoleGroupDAO systemRoleGroupDao;
	
	/**
	 * 查询系统内置角色<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年3月4日 下午1:44:52
	 * @return SystemRoleVO 系统内置角色
	 */
	public SystemRoleVO queryInternalRole(){
		SystemRolePO internalRole =  systemRoleDao.findByAutoGeneration(true);
		return new SystemRoleVO().set(internalRole);
	}
	
	/**
	 * 分页查询系统角色<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月23日 上午10:25:27
	 * @param Long groupId 系统角色组id
	 * @param int currentPage 当前页码
	 * @param int pageSize 每页数据量
	 * @return long total 系统角色数据总量 
	 * @return List<SystemRoleVO> rows 系统角色列表
	 */
	public Map<String, Object> list(Long groupId, int currentPage, int pageSize) throws Exception{
		long total = systemRoleDao.count();
		List<SystemRolePO> roles = findBySystemRoleGroupIdAndTypeOrderByUpdateTimeDesc(groupId, SystemRoleType.SYSTEM, currentPage, pageSize);
		List<SystemRoleVO> view_roles = new ArrayList<SystemRoleVO>();
		if(roles!=null && roles.size()>0){
			for(int i=0; i<roles.size(); i++){
				view_roles.add(new SystemRoleVO().set(roles.get(i)));
			}
		}
		return new HashMapWrapper<String, Object>().put("total", total)
												   .put("rows", view_roles)
												   .getMap();
	}
	
	/**
	 * 根据分组和类型查询系统角色<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年8月2日 下午2:06:38
	 * @param Long systemRoleGroupId 角色组id
	 * @param SystemRoleType type 角色类型
	 * @param int currentPage 当前页码
	 * @param int pageSize 每页数据量
	 * @return List<SystemRolePO> 系统角色列表
	 */
	public List<SystemRolePO> findBySystemRoleGroupIdAndTypeOrderByUpdateTimeDesc(Long systemRoleGroupId, SystemRoleType type, int currentPage, int pageSize){
		Pageable page = new PageRequest(currentPage-1, pageSize);
		Page<SystemRolePO> roles = systemRoleDao.findBySystemRoleGroupIdAndTypeOrderByUpdateTimeDesc(systemRoleGroupId, type, page);
		return roles.getContent();
	}
	
	/**
	 * 分组查询角色（带例外）<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月17日 上午11:53:30
	 * @param Collection<String> roleIds 例外角色id列表
	 * @return List<SystemRoleGroupVO> 分组后的角色列表
	 */
	public List<SystemRoleGroupVO> listWithGroupByExceptIds(Collection<String> roleIds) throws Exception{
		List<SystemRolePO> roles = null;
		if(roleIds==null || roleIds.size()<=0){
			roles = systemRoleDao.findByType(SystemRoleType.SYSTEM);
		}else{
			Set<Long> transRoleIds = new HashSet<Long>();
			for(String roleId:roleIds){
				transRoleIds.add(Long.valueOf(roleId));
			}
			roles = systemRoleDao.findByTypeAndIdNotIn(SystemRoleType.SYSTEM, transRoleIds);
		}
		return packageSystemRolesWithGroup(roles);
	}
	
	/**
	 * 根据给定的系统角色id分组查询系统橘色<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月17日 下午1:16:22
	 * @param Collection<String> roleIds 系统角色id列表
	 * @return List<SystemRoleGroupVO> 分组后的系统角色列表
	 */
	public List<SystemRoleGroupVO> listWithGroupByIds(Collection<String> roleIds) throws Exception{
		List<SystemRolePO> roles = null;
		if(roleIds==null || roleIds.size()<=0){
			return null;
		}else{
			Set<Long> transRoleIds = new HashSet<Long>();
			for(String roleId:roleIds){
				transRoleIds.add(Long.valueOf(roleId));
			}
			roles = systemRoleDao.findAll(transRoleIds);
		}
		return packageSystemRolesWithGroup(roles);
	}
	
	/**
	 * 分组打包系统橘色<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月17日 下午1:14:58
	 * @param List<SystemRolePO> roles 系统角色列表
	 * @return List<SystemRoleGroupVO 分组后的角色列表
	 */
	private List<SystemRoleGroupVO> packageSystemRolesWithGroup(List<SystemRolePO> roles) throws Exception{
		if(roles!=null && roles.size()>0){
			Set<Long> groupIds = new HashSet<Long>();
			for(SystemRolePO role:roles){
				groupIds.add(role.getSystemRoleGroupId());
			}
			List<SystemRoleGroupPO> groups = systemRoleGroupDao.findAll(groupIds);
			List<SystemRoleGroupVO> view_groups = new ArrayList<SystemRoleGroupVO>();
			for(SystemRoleGroupPO group:groups){
				SystemRoleGroupVO view_group = new SystemRoleGroupVO().set(group).setRoles(new ArrayList<SystemRoleVO>());
				view_groups.add(view_group);
				for(SystemRolePO role:roles){
					if(role.getSystemRoleGroupId().equals(group.getId())){
						view_group.getRoles().add(new SystemRoleVO().set(role));
					}
				}
			}
			return view_groups;
		}
		return null;
	}
	
	/**
	 * 根据id（批量）查询系统角色列表<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月17日 下午1:31:31
	 * @param Collection<String> roleIds 系统角色id列表
	 * @return List<SystemRoleVO> 系统角色列表
	 */
	public List<SystemRoleVO> listByIds(Collection<String> roleIds) throws Exception{
		List<SystemRolePO> roles = null;
		if(roleIds==null || roleIds.size()<=0){
			return null;
		}else{
			Set<Long> transRoleIds = new HashSet<Long>();
			for(String roleId:roleIds){
				transRoleIds.add(Long.valueOf(roleId));
			}
			roles = systemRoleDao.findAll(transRoleIds);
			
			if(roles!=null && roles.size()>0){
				List<SystemRoleVO> view_roles = new ArrayList<SystemRoleVO>();
				for(SystemRolePO role:roles){
					view_roles.add(new SystemRoleVO().set(role));
				}
				return view_roles;
			}else{
				return null;
			}
		}
	}
	
	/**
	 * 查询用户的所有系统角色，包含组织机构关联角色，取并集<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月11日 下午3:21:59
	 * @param String userId 用户id
	 * @return List<SystemRoleVO> 角色列表
	 */
	public List<SystemRoleVO> queryUserRoles(String userId) throws Exception{
		List<SystemRolePO> roles = systemRoleDao.findByUserId(Long.valueOf(userId));
		List<SystemRoleVO> view_roles = new ArrayList<SystemRoleVO>();
		if(roles!=null && roles.size()>0){
			for(SystemRolePO role:roles){
				view_roles.add(new SystemRoleVO().set(role));
			}
		}
		return view_roles;
	}
	
}
