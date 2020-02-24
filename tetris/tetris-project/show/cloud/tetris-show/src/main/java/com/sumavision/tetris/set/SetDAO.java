package com.sumavision.tetris.set;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import com.sumavision.tetris.orm.dao.BaseDAO;


@RepositoryDefinition(domainClass = SetPO.class, idClass = long.class)
public interface SetDAO extends BaseDAO<SetPO>{

	public SetPO findOneByUserIdAndCurDate(long userId,Date curDate);
	
}
