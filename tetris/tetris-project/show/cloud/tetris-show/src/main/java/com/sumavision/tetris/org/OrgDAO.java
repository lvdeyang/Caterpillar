package com.sumavision.tetris.org;

import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import com.sumavision.tetris.orm.dao.BaseDAO;


@RepositoryDefinition(domainClass = OrgPO.class, idClass = long.class)
public interface OrgDAO extends BaseDAO<OrgPO>{

	
	/**
	 * 父组织查询子组织<br/>
	 * <b>作者:</b>Mr<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年5月8日 下午3:59:34
	 * @param Long parentId 父id
	 */
	public List<OrgPO> findByParentId(Long parentId);
	

	public List<OrgPO> findAll();
	
	
	@Query(value = "SELECT * FROM TETRIS_ORG WHERE PARENT_PATH LIKE ?1 OR PARENT_PATH LIKE ?2", nativeQuery = true)
	public List<OrgPO> findAllSubColumns(String reg1, String reg2);
}
