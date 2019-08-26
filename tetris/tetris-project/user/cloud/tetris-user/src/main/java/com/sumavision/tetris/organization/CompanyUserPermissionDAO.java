package com.sumavision.tetris.organization;

import java.util.List;

import org.springframework.data.repository.RepositoryDefinition;
import com.sumavision.tetris.orm.dao.BaseDAO;

@RepositoryDefinition(domainClass = CompanyUserPermissionPO.class, idClass = Long.class)
public interface CompanyUserPermissionDAO extends BaseDAO<CompanyUserPermissionPO>{

	/**
	 * 根据公司获取用户映射<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月24日 下午3:20:55
	 * @param Long companyId 公司id
	 * @return List<CompanyUserPermissionPO> 权限列表
	 */
	public List<CompanyUserPermissionPO> findByCompanyId(Long companyId);
	
	/**
	 * 根据用户id获取权限<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月24日 下午3:27:14
	 * @param String userId 用户id
	 * @return List<CompanyUserPermissionPO> 权限列表
	 */
	public List<CompanyUserPermissionPO> findByUserId(String userId);
	
}
