package com.sumavision.tetris.capacity.management;

import org.springframework.data.repository.RepositoryDefinition;

import com.sumavision.tetris.orm.dao.BaseDAO;

@RepositoryDefinition(domainClass = CapacityManagementPO.class, idClass = Long.class)
public interface CapacityManagementDAO extends BaseDAO<CapacityManagementPO>{

	public CapacityManagementPO findByIp(String ip);
	
}
