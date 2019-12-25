package com.sumavision.tetris.business.common.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;

import com.sumavision.tetris.business.common.enumeration.BusinessType;
import com.sumavision.tetris.business.common.po.TaskInputPO;
import com.sumavision.tetris.orm.dao.BaseDAO;

@RepositoryDefinition(domainClass = TaskInputPO.class, idClass = Long.class)
public interface TaskInputDAO extends BaseDAO<TaskInputPO>{

	public TaskInputPO findByTaskUuidAndType(String taskUuid, BusinessType type);
	
	public TaskInputPO findByUniq(String uniq);
	
	//悲观
	@Query(value = "SELECT input.* FROM TETRIS_CAPACITY_TASK_INPUT input WHERE input.uniq = ?1 for update", nativeQuery = true)
	public TaskInputPO selectByUniq(String uniq);
	
	public List<TaskInputPO> findByCount(Integer count);
	
	//乐观
	@Modifying
	@Query("update TaskInputPO input set input.version = ?2 + 1, input.count = ?3 where input.taskUuid = ?1 and input.version = ?2")
	public int update(String taskUuid, Integer version, Integer count);
	
}
