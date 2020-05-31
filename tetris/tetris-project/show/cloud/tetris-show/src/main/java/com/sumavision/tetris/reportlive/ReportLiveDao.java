package com.sumavision.tetris.reportlive;

import com.sumavision.tetris.orm.dao.BaseDAO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;

@RepositoryDefinition(domainClass = ReportLivePo.class, idClass = long.class)

public interface ReportLiveDao extends BaseDAO<ReportLivePo> {
  
}
