package com.sumavision.tetris.system.role;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import com.sumavision.tetris.orm.dao.BaseDAO;

@RepositoryDefinition(domainClass = SystemRoleGroupPO.class, idClass = Long.class)
public interface SystemRoleGroupDAO extends BaseDAO<SystemRoleGroupPO>{

	/**
	 * 查询所有的系统角色组<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月23日 上午11:05:48
	 * @return List<SystemRoleGroupPO> 角色组列表
	 */
	@Query(value = "from com.sumavision.tetris.system.role.SystemRoleGroupPO group order by group.updateTime desc")
	public List<SystemRoleGroupPO> findAllOrderByUpdateTimeDesc();
	
	/**
	 * 根据类型查询系统权限组<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年3月4日 下午1:16:13
	 * @param boolean autoGeneration 是否自动生成
	 * @return SystemRoleGroupPO 系统角色组
	 */
	public SystemRoleGroupPO findByAutoGeneration(boolean autoGeneration);
	
}
