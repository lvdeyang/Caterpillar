package com.sumavision.tetris.organization;

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
 * 部门用户映射查询操作<br/>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年1月24日 下午6:25:56
 */
@Component
public class OrganizationUserPermissionQuery {

	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private OrganizationUserPermissionDAO organizationUserPermissionDao;
	
	/**
	 * 分页查询部门的用户映射<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月25日 上午9:12:48
	 * @param Long organizationId 部门id
	 * @param int currentPage 当前页码
	 * @param int pageSize 每页数据量
	 * @return int total 总数据量
	 * @return List<OrganizationUserPermissionVO> rows 用户列表
	 */
	public Map<String, Object> listByOrganizationId(Long organizationId, int currentPage, int pageSize) throws Exception{
		
		int total = organizationUserPermissionDao.countByOrganizationId(organizationId);
		
		List<OrganizationUserPermissionPO> permissions = findByOrganizationId(organizationId, currentPage, pageSize);
		List<OrganizationUserPermissionVO> view_permissions = new ArrayList<OrganizationUserPermissionVO>();
		
		if(permissions!=null && permissions.size()>0){
			Set<Long> userIds = new HashSet<Long>();
			for(OrganizationUserPermissionPO permission:permissions){
				userIds.add(Long.valueOf(permission.getUserId()));
			}
			List<UserPO> users = userDao.findAll(userIds);
			for(OrganizationUserPermissionPO permission:permissions){
				for(UserPO user:users){
					if(permission.getUserId().equals(user.getId().toString())){
						view_permissions.add(new OrganizationUserPermissionVO().set(permission, user));
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
	 * 分页查询部门下的用户列表<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月24日 下午6:28:39
	 * @param Long organizationId 部门id
	 * @param int currentPage 当前页码
	 * @param int pageSize 每页数据量
	 * @return List<OrganizationUserPermissionPO> 权限列表
	 */
	public List<OrganizationUserPermissionPO> findByOrganizationId(Long organizationId, int currentPage, int pageSize){
		Pageable page = new PageRequest(currentPage -1, pageSize);
		Page<OrganizationUserPermissionPO> permissions = organizationUserPermissionDao.findByOrganizationId(organizationId, page);
		return permissions.getContent();
	}
	
}
