package com.sumavision.tetris.organization;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 组织架构操作（主 增、删、改）<br/>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2018年12月7日 下午3:37:30
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OrganizationService {

	@Autowired
	private OrganizationDAO organizationDao;
	
	@Autowired
	private OrganizationUserPermissionDAO organizationUserPermissionDao;
	
	/**
	 * 删除部门<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2018年12月7日 下午3:48:40
	 * @param OrganizationPO organization 部门数据
	 * @return OrganizationPO 被删除的数据
	 */
	public OrganizationPO delete(OrganizationPO organization) throws Exception{
		
		List<OrganizationUserPermissionPO> permissions = organizationUserPermissionDao.findByOrganizationId(organization.getId());
		
		if(permissions!=null && permissions.size()>0){
			organizationUserPermissionDao.deleteInBatch(permissions);
		}
		
		organizationDao.delete(organization);
		
		return organization;
	}
	
}
