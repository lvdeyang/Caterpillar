package com.sumavision.tetris.menu;

import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import com.sumavision.tetris.orm.dao.BaseDAO;


@RepositoryDefinition(domainClass = MenuPO.class, idClass = long.class)
public interface MenuDAO extends BaseDAO<MenuPO>{

	/**
	 * 获取用户有权限的菜单，并根据显示序号排序<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2018年11月20日 下午1:37:13
	 * @param Collection<String> roleIds 用户角色列表
	 * @return List<MenuPO> 菜单
	 */
	@Query(value = "select m.id, m.uuid, m.update_time, m.title, m.link, m.icon, m.style, m.is_group, m.parent_id, m.menu_id_path, m.serial, m.auto_generation from TETRIS_MENU m left join TETRIS_SYSTEM_ROLE_MENU_PERMISSION mp on m.id=mp.menu_id where m.is_group=0 and mp.role_id in ?1 order by m.serial asc", nativeQuery = true)
	public List<MenuPO> findByRoleIdIn(Collection<String> roleIds);
	
	/**
	 * id查询,并且根据显示序号排序（一般用于遍历父菜单id后查询父菜单）<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2018年11月20日 下午1:37:49
	 * @param Collection<Long> ids 菜单id
	 * @return List<MenuPO> 菜单
	 */
	public List<MenuPO> findByIdInOrderBySerialAsc(Collection<Long> ids);
	
	/**
	 * 查询根菜单最大序号<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月16日 下午8:37:42
	 * @return Integer 菜单序号
	 */
	@Query(value = "SELECT MAX(serial) FROM tetris_menu WHERE parent_id IS NULL", nativeQuery = true)
	public Integer findMaxRootSerial();
	
	/**
	 * 查询某菜单下最大菜单序号<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月16日 下午8:39:28
	 * @param Long parentId 父菜单id
	 * @return Integer 菜单序号
	 */
	@Query(value = "SELECT MAX(serial) FROM tetris_menu WHERE parent_id=?1", nativeQuery = true)
	public Integer findMaxSerialByParentId(Long parentId);
	
	/**
	 * 查询一个菜单下的所有子菜单<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月17日 上午8:57:46
	 * @param String reg0 '%/id'
	 * @param String reg1 '%/id/%'
	 * @return List<MenuPO> 所有的子菜单
	 */
	@Query(value = "SELECT id, update_time, uuid, icon, is_group, link, menu_id_path, parent_id, serial, style, title, auto_generation FROM tetris_menu WHERE menu_id_path LIKE ?1 OR menu_id_path LIKE ?2", nativeQuery = true)
	public List<MenuPO> findAllSubMenus(String reg0, String reg1);
	
	/**
	 * 根据类型查询菜单<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年3月4日 下午2:33:45
	 * @param boolean autoGeneration 是否自动生成
	 * @return List<MenuPO> 菜单列表
	 */
	public List<MenuPO> findByAutoGeneration(boolean autoGeneration);
	
}
