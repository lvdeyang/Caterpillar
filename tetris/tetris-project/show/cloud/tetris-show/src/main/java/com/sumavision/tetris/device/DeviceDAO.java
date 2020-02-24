package com.sumavision.tetris.device;

import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import com.sumavision.tetris.orm.dao.BaseDAO;


@RepositoryDefinition(domainClass = DevicePO.class, idClass = long.class)
public interface DeviceDAO extends BaseDAO<DevicePO>{

	
	
}
