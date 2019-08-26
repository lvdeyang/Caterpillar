package com.sumavision.tetris.organization;

import java.util.List;
import org.springframework.data.repository.RepositoryDefinition;
import com.sumavision.tetris.orm.dao.BaseDAO;

@RepositoryDefinition(domainClass = OrganizationPO.class, idClass = Long.class)
public interface OrganizationDAO extends BaseDAO<OrganizationPO>{

	/**
	 * 查询公司下的部门列表<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2018年11月21日 下午3:00:30
	 * @param String companyId 公司id
	 * @return List<OrganizationPO> 部门列表
	 */
	public List<OrganizationPO> findByCompanyIdOrderBySerialAsc(Long companyId);
	
}
