package com.sumavision.tetris.organization;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import com.sumavision.tetris.orm.dao.BaseDAO;

@RepositoryDefinition(domainClass = OrganizationUserPermissionPO.class, idClass = Long.class)
public interface OrganizationUserPermissionDAO extends BaseDAO<OrganizationUserPermissionPO>{

	/**
	 * 根据用户获取用户组织机构映射关系<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2018年11月21日 下午3:34:09
	 * @param Collection<String> userIds 用户id列表
	 * @return List<OrganizationUserPermissionPO> 用户组织机构映射关系
	 */
	public List<OrganizationUserPermissionPO> findByUserIdIn(Collection<String> userIds);
	
	/**
	 * 根据部门id查询权限<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2018年12月7日 下午3:45:38
	 * @param Long organizationId 部门id
	 */
	public List<OrganizationUserPermissionPO> findByOrganizationId(Long organizationId);
	
	/**
	 * 分页查询部门下的用户<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月24日 下午6:23:51
	 * @param Long organizationId 部门id
	 * @param Pageable page 分页信息
	 * @return Page<OrganizationUserPermissionPO> 权限信息
	 */
	public Page<OrganizationUserPermissionPO> findByOrganizationId(Long organizationId, Pageable page);
	
	/**
	 * 统计部门下的用户数量<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月24日 下午6:21:58
	 * @param Long organizationId 部门id
	 * @return int 用户数量
	 */
	public int countByOrganizationId(Long organizationId);
	
	/**
	 * 根据部门id（批量）查询权限<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月24日 下午3:14:15
	 * @param Collection<Long> organizationIds 部门id列表
	 * @return List<OrganizationUserPermissionPO> 权限列表
	 */
	public List<OrganizationUserPermissionPO> findByOrganizationIdIn(Collection<Long> organizationIds);
	
	/**
	 * 获取已经绑定的用户id列表<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2018年12月7日 下午6:37:42
	 * @param String groupId 企业id
	 * @return List<String> 用户id列表
	 */
	@Query(value = "SELECT permission.user_id FROM tetris_organization_user_permission permission LEFT JOIN tetris_organization organization ON permission.organization_id=organization.id WHERE organization.group_id=?1", nativeQuery = true)
	public List<String> findPermissionedUser(String groupId);
	
	/**
	 * 根据用户和部门获取权限<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2018年12月8日 上午9:15:06
	 * @param Long organizationId 部门id
	 * @param String userId 用户id
	 * @return List<OrganizationUserPermissionPO> 权限数据
	 */
	public List<OrganizationUserPermissionPO> findByOrganizationIdAndUserId(Long organizationId, String userId);
	
}
