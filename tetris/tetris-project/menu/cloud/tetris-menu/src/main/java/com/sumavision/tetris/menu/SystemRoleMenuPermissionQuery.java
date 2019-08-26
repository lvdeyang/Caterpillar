package com.sumavision.tetris.menu;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

/**
 * 菜单系统角色授权查询<br/>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年1月17日 下午1:59:47
 */
@Component
public class SystemRoleMenuPermissionQuery {

	@Autowired
	private SystemRoleMenuPermissionDAO systemRoleMenuPermissionDao;
	
	/**
	 * 分页查询菜单的授权信息<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月17日 下午1:58:41
	 * @param Long menuId 菜单id
	 * @param int currentPage 当前页码
	 * @param int pageSize 每页数据量
	 * @return List<SystemRoleMenuPermissionPO> 授权列表
	 */
	public List<SystemRoleMenuPermissionPO> findByMenuIdOrderByUpdateTimeDesc(Long menuId, int currentPage, int pageSize){
		Pageable page = new PageRequest(currentPage - 1, pageSize);
		Page<SystemRoleMenuPermissionPO> permissions = systemRoleMenuPermissionDao.findByMenuId(menuId, page);
		return permissions.getContent();
	}
	
}
