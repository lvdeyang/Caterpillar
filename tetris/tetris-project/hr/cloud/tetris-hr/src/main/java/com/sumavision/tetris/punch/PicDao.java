package com.sumavision.tetris.punch;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;

import com.sumavision.tetris.orm.dao.BaseDAO;

@RepositoryDefinition(domainClass = PicPo.class, idClass = Long.class)
public interface PicDao extends BaseDAO<PicPo>{

}
