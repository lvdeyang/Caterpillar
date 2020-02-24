package com.sumavision.tetris.sche;

import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import com.sumavision.tetris.orm.dao.BaseDAO;


@RepositoryDefinition(domainClass = SchePO.class, idClass = long.class)
public interface ScheDAO extends BaseDAO<SchePO>{

	
	
}
