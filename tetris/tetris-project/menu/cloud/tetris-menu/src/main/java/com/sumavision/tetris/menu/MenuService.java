package com.sumavision.tetris.menu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sumavision.tetris.commons.util.wrapper.StringBufferWrapper;
import com.sumavision.tetris.menu.exception.MenuNotExistException;

/**
 * 菜单相关操作（主增删改）<br/>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年1月16日 下午8:27:26
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MenuService {

	@Autowired
	private MenuDAO menuDao;
	
	@Autowired
	private MenuQuery menuQuery;
	
	@Autowired
	private SystemRoleMenuPermissionDAO systemRoleMenuPermissionDao;
	
	/**
	 * 创建临时根菜单<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月16日 下午8:27:02
	 * @return MenuVO 新建的根菜单
	 */
	public MenuVO addRoot() throws Exception{
		
		Integer currentSerial = menuDao.findMaxRootSerial();
		
		MenuPO entity = new MenuPO();
		entity.setTitle("新建菜单");
		entity.setSerial(currentSerial==null?1:currentSerial+1);
		entity.setIsGroup(false);
		entity.setAutoGeneration(false);
		menuDao.save(entity);
		
		return new MenuVO().set(entity);
	}
	
	/**
	 * 向某菜单下添加子菜单<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月16日 下午8:42:37
	 * @param Long parentId 父菜单id
	 * @return MenuVO 新建的子菜单
	 */
	public MenuVO addSub(Long parentId) throws Exception{
		
		MenuPO parent = menuDao.findOne(parentId);
		if(parent == null){
			throw new MenuNotExistException(parentId);
		}
		
		if(!parent.getIsGroup()){
			parent.setIsGroup(true);
			menuDao.save(parent);
		}
		
		Integer currentSerial = menuDao.findMaxSerialByParentId(parent.getId());
		
		MenuPO entity = new MenuPO();
		entity.setTitle("新建菜单");
		entity.setSerial(currentSerial==null?1:currentSerial+1);
		entity.setParentId(parent.getId());
		entity.setIsGroup(false);
		entity.setAutoGeneration(false);
		String menuIdPath = "";
		if(parent.getMenuIdPath()==null || "".equals(parent.getMenuIdPath())){
			menuIdPath = new StringBufferWrapper().append("/").append(parent.getId()).toString();
		}else{
			menuIdPath = new StringBufferWrapper().append(parent.getMenuIdPath()).append("/").append(parent.getId()).toString();
		}
		entity.setMenuIdPath(menuIdPath);
		menuDao.save(entity);
		
		return new MenuVO().set(entity);
	}
	
	/**
	 * 删除菜单（带子菜单）<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月17日 上午9:16:19
	 * @param Long id 菜单id
	 * @return List<MenuVO> 删除的所有菜单
	 */
	public List<MenuVO> remove(Long id) throws Exception{
		
		MenuPO menu = menuDao.findOne(id);
		if(menu == null){
			throw new MenuNotExistException(id);
		}
		
		//删除子菜单
		List<MenuPO> subMenus = menuQuery.findAllSubMenus(menu.getId());
		
		List<MenuPO> totalMenus = new ArrayList<MenuPO>();
		totalMenus.add(menu);
		if(subMenus!=null && subMenus.size()>0) totalMenus.addAll(subMenus);
		
		menuDao.deleteInBatch(totalMenus);
		
		Set<Long> menuIds = new HashSet<Long>();
		for(MenuPO scope:totalMenus){
			menuIds.add(scope.getId());
		}
		
		//删除所有菜单权限
		List<SystemRoleMenuPermissionPO> permissions = systemRoleMenuPermissionDao.findByMenuIdIn(menuIds);
		if(permissions!=null && permissions.size()>0) systemRoleMenuPermissionDao.deleteInBatch(permissions);
		
		return MenuVO.getConverter(MenuVO.class).convert(totalMenus, MenuVO.class);
	}
	
	/**
	 * 修改一个菜单<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月17日 上午10:28:39
	 * @param Long id 菜单id
	 * @param String title 菜单标题
	 * @param String link 菜单链接
	 * @param String icon 菜单图标
	 * @param String style 菜单图标样式
	 * @param String serial 菜单显示顺序
	 * @return MenuVO 修改后的菜单
	 */
	public MenuVO save(
			Long id,
			String title,
            String link,
            String icon,
            String style,
            int serial) throws Exception{
		
		MenuPO menu = menuDao.findOne(id);
		if(menu == null){
			throw new MenuNotExistException(id);
		}
		
		menu.setIcon(icon);
		menu.setStyle(style);
		menu.setSerial(serial);
		if(!menu.isAutoGeneration()){
			menu.setTitle(title);
		}
		if(!menu.getIsGroup()){
			menu.setLink(link);
		}
		menuDao.save(menu);
	
		return new MenuVO().set(menu);
	}
	
}
