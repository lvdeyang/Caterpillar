package com.sumavision.tetris.organization;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sumavision.tetris.organization.exception.OrganizationNotExistException;
import com.sumavision.tetris.user.UserDAO;
import com.sumavision.tetris.user.UserPO;

/**
 * 部门用户映射操作（主增删改）<br/>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年1月25日 上午9:21:52
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OrganizationUserPermissionService {

	@Autowired
	private OrganizationUserPermissionDAO organizationUserPermissionDao;
	
	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private OrganizationDAO organizationDao;
	
	/**
	 * 添加用户到部门<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2018年12月7日 下午6:01:44
	 * @param Long organizationId 部门id
	 * @param List<String> userIds 用户id列表
	 * @return List<OrganizationUserPermissionVO> 添加的映射
	 */
	public List<OrganizationUserPermissionVO> bind(Long organizationId, List<String> userIds) throws Exception{
		
		OrganizationPO organization = organizationDao.findOne(organizationId);
		if(organization == null){
			throw new OrganizationNotExistException(organizationId);
		}
		
		if(userIds!=null && userIds.size()>0){
			Set<Long> transUserIds = new HashSet<Long>();
			for(String userId:userIds){
				transUserIds.add(Long.valueOf(userId));
			}
			List<UserPO> users = userDao.findAll(transUserIds);
			if(users!=null && users.size()>0){
				List<OrganizationUserPermissionPO> permissions = new ArrayList<OrganizationUserPermissionPO>();
				for(UserPO user:users){
					OrganizationUserPermissionPO permission = new OrganizationUserPermissionPO();
					permission.setOrganizationId(organization.getId());
					permission.setUserId(user.getId().toString());
					permission.setUpdateTime(new Date());
					permissions.add(permission);
				}
				organizationUserPermissionDao.save(permissions);
				
				List<OrganizationUserPermissionVO> view_permissions = new ArrayList<OrganizationUserPermissionVO>();
				for(OrganizationUserPermissionPO permission:permissions){
					for(UserPO user:users){
						if(permission.getUserId().equals(user.getId().toString())){
							view_permissions.add(new OrganizationUserPermissionVO().set(permission, user));
							break;
						}
					}
				}
				
				return view_permissions;
			}
		}
		return null;
	}
	
}
