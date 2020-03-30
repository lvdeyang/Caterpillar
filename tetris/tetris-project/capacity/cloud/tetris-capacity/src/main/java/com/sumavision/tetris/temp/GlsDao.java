package com.sumavision.tetris.temp;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.RepositoryDefinition;

import com.sumavision.tetris.orm.dao.BaseDAO;

@RepositoryDefinition(domainClass = GlsPo.class, idClass = long.class)
public interface GlsDao extends BaseDAO<GlsPo>{
	public Page<GlsPo> findAllByTempId(Long tempId,Pageable page);
}
