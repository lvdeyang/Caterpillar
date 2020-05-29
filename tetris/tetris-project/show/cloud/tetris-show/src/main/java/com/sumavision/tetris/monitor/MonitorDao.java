package com.sumavision.tetris.monitor;

import com.sumavision.tetris.orm.dao.BaseDAO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;

@RepositoryDefinition(domainClass = MonitorPo.class, idClass = long.class)

public interface MonitorDao extends BaseDAO<MonitorPo> {
  
}
