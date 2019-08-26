package com.sumavision.tetris.menu;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.RepositoryDefinition;
import com.sumavision.tetris.orm.dao.BaseDAO;

@RepositoryDefinition(domainClass = SystemRoleMenuPermissionPO.class, idClass = Long.class)
public interface SystemRoleMenuPermissionDAO extends BaseDAO<SystemRoleMenuPermissionPO>{

	/**
	 * 查询菜单权限（批量）<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月17日 上午9:11:03
	 * @param Collection<Long> menuIds 菜单列表
	 * @return List<SystemRoleMenuPermissionPO> 权限列表
	 */
	public List<SystemRoleMenuPermissionPO> findByMenuIdIn(Collection<Long> menuIds);
	
	/**
	 * 分页查询菜单的授权信息<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月17日 下午1:48:09
	 * @param Long menuId 菜单id
	 * @param Pageable page 分页信息
	 * @return List<SystemRoleMenuPermissionPO> 权限列表
	 */
	public Page<SystemRoleMenuPermissionPO> findByMenuId(Long menuId, Pageable page);
	
	/**
	 * 统计菜单的授权数量<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月17日 下午1:53:29
	 * @param Long menuId 菜单id
	 * @return int 授权数量
	 */
	public int countByMenuId(Long menuId);
	
	/**
	 * 根据菜单id角色id以及类型获取权限<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年3月4日 下午3:45:44
	 * @param Long menuId 菜单id
	 * @param Long roleId 角色id
	 * @param boolean autoGeneration 是否自动生成
	 * @return SystemRoleMenuPermissionPO 权限
	 */
	public SystemRoleMenuPermissionPO findByMenuIdAndRoleIdAndAutoGeneration(Long menuId, String roleId, boolean autoGeneration);
	
}
