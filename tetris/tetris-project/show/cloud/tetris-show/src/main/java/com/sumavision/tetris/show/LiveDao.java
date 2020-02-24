package com.sumavision.tetris.show;

import com.sumavision.tetris.orm.dao.BaseDAO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;

@RepositoryDefinition(domainClass = LivePo.class, idClass = long.class)

public interface LiveDao extends BaseDAO<LivePo> {
    @Query(value = "select * from TETRIS_SHOW where type =?", nativeQuery = true)
    List<LivePo> getByType(Integer type);
}
