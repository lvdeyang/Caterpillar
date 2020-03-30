package com.sumavision.tetris.temp;

import org.springframework.data.repository.RepositoryDefinition;

import com.sumavision.tetris.orm.dao.BaseDAO;


@RepositoryDefinition(domainClass = TempPo.class, idClass = long.class)

public interface TempDao extends BaseDAO<TempPo>{

}