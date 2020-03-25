package com.sumavision.tetris.temp;

import org.springframework.data.repository.RepositoryDefinition;

import com.sumavision.tetris.orm.dao.BaseDAO;

@RepositoryDefinition(domainClass = GlsPo.class, idClass = long.class)
public interface GlsDao extends BaseDAO<GlsPo>{

}
