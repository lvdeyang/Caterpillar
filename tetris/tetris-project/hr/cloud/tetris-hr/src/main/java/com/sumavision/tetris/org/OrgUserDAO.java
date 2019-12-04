package com.sumavision.tetris.org;

import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import com.sumavision.tetris.orm.dao.BaseDAO;


@RepositoryDefinition(domainClass = OrgUserPO.class, idClass = long.class)
public interface OrgUserDAO extends BaseDAO<OrgUserPO>{

	public List<OrgUserPO> findByOrgId(long orgId);
	
	public List<OrgUserPO> findByUserId(long userId);
	
}
