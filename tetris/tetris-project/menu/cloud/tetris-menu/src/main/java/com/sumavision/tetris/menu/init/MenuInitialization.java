package com.sumavision.tetris.menu.init;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sumavision.tetris.commons.context.AsynchronizedSystemInitialization;
import com.sumavision.tetris.commons.util.wrapper.StringBufferWrapper;
import com.sumavision.tetris.config.server.UserServerPropsQuery;
import com.sumavision.tetris.menu.MenuDAO;
import com.sumavision.tetris.menu.MenuPO;
import com.sumavision.tetris.menu.SystemRoleMenuPermissionDAO;
import com.sumavision.tetris.menu.SystemRoleMenuPermissionPO;
import com.sumavision.tetris.menu.config.server.MenuServerPropsQuery;
import com.sumavision.tetris.menu.config.server.ServerProps;
import com.sumavision.tetris.system.role.SystemRoleQuery;
import com.sumavision.tetris.system.role.SystemRoleVO;

@Service
@Transactional(rollbackFor = Exception.class)
public class MenuInitialization implements AsynchronizedSystemInitialization{

	private static final Logger LOG = LoggerFactory.getLogger(MenuInitialization.class);
	
	@Autowired
	private MenuDAO menuDao;
	
	@Autowired
	private SystemRoleMenuPermissionDAO systemRolePermissionDao;
	
	@Autowired
	private SystemRoleQuery systemRoleQuery;
	
	@Autowired
	private UserServerPropsQuery userServerPropsQuery;
	
	@Autowired
	private MenuServerPropsQuery menuServerPropsQuery;
	
	@Override
	public int index() {
		return 0;
	}

	@Override
	public void init() {
		
		List<MenuPO> internalMenus = menuDao.findByAutoGeneration(true);
		
		if(internalMenus==null || internalMenus.size()<=0){
			Date now = new Date();
			SystemRoleVO internalRole = null;
			try{
				internalRole = systemRoleQuery.queryInternalRole();
			}catch(Exception e){
				e.printStackTrace();
				LOG.error("依赖tetris-user模块，请先启动用户模块！");
				System.exit(0);
			}
			
			MenuPO internalMenu0 = addMenu("系统运维", null, "icon-cogs", "position:relative; top:1px;", true, null, null, 1, true, now);
			
			String parentPath = new StringBufferWrapper().append("/").append(internalMenu0.getId()).toString();
			
			MenuPO internalMenu1 = addMenu("菜单管理", packMenuUrl("/index/{context:user;prop:token}#/page-menu"), "icon-circle-blank", "position:relative; top:1px;", false, internalMenu0.getId(), parentPath, 1, true, now);
			addPermission(internalMenu1, internalRole, now);
			
			MenuPO internalMenu2 = addMenu("用户管理", packUserUrl("/index/{context:user;prop:token}#/page-user"), "icon-circle-blank", "position:relative; top:1px;", false, internalMenu0.getId(), parentPath, 2, true, now);
			addPermission(internalMenu2, internalRole, now);
			
			MenuPO internalMenu3 = addMenu("系统角色", packUserUrl("/index/{context:user;prop:token}#/page-system-role"), "icon-circle-blank", "position:relative; top:1px;", false, internalMenu0.getId(), parentPath, 3, true, now);
			addPermission(internalMenu3, internalRole, now);
		}
	}
	
	/**
	 * 获取菜单url<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年3月4日 下午3:36:57
	 * @param String suffix url后缀
	 * @return String url
	 */
	public String packMenuUrl(String suffix){
		ServerProps props = menuServerPropsQuery.queryProps();
		return new StringBufferWrapper().append("http://")
										.append(props.getIp())
										.append(":")
										.append(props.getPort())
										.append(suffix)
									    .toString();
	}
	
	/**
	 * 获取用户url<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年3月4日 下午3:39:03
	 * @param String suffix url后缀
	 * @return String url
	 */
	public String packUserUrl(String suffix) {
		try {
			com.sumavision.tetris.config.server.ServerProps props = userServerPropsQuery.queryProps();
			return new StringBufferWrapper().append("http://")
											.append(props.getIp())
											.append(":")
											.append(props.getPort())
											.append(suffix)
										    .toString();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		return null;
	}
	
	/**
	 * 为角色绑定菜单权限<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年3月4日 下午3:51:43
	 * @param MenuPO menu 菜单
	 * @param SystemRoleVO internalRole 系统内置角色
	 * @param Date updateTime 修改时间
	 * @return SystemRoleMenuPermissionPO 权限
	 */
	public SystemRoleMenuPermissionPO addPermission(MenuPO menu, SystemRoleVO internalRole, Date updateTime){
		SystemRoleMenuPermissionPO permission = systemRolePermissionDao.findByMenuIdAndRoleIdAndAutoGeneration(menu.getId(), internalRole.getId(), true);
		if(permission == null){
			permission = new SystemRoleMenuPermissionPO();
			permission.setMenuId(menu.getId());
			permission.setRoleId(internalRole.getId());
			permission.setAutoGeneration(true);
			permission.setUpdateTime(updateTime);
			systemRolePermissionDao.save(permission);
		}
		return permission;
	}
	
	/**
	 * 添加一个菜单<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年3月4日 下午2:51:04
	 * @param String title 菜单标题
	 * @param String link 菜单链接
	 * @param String icon 菜单图标
	 * @param String style 菜单图标样式
	 * @param boolean isGroup 是否是菜单组
	 * @param Long parentId 父菜单id
	 * @param String menuIdPath 父菜单id路径
	 * @param int serial 排列顺序
	 * @param boolean autoGeneration 是否自动生成
	 * @param Date updateTime 修改时间
	 * @return MenuPO 菜单数据
	 */
	public MenuPO addMenu(
			String title,
			String link,
			String icon,
			String style,
			boolean isGroup,
			Long parentId,
			String menuIdPath,
			int serial,
			boolean autoGeneration,
			Date updateTime){
		
		MenuPO internalMenu = new MenuPO();
		internalMenu.setTitle(title);
		internalMenu.setLink(link);
		internalMenu.setIcon(icon);
		internalMenu.setStyle(style);
		internalMenu.setIsGroup(isGroup);
		internalMenu.setParentId(parentId);
		internalMenu.setMenuIdPath(menuIdPath);
		internalMenu.setSerial(serial);
		internalMenu.setAutoGeneration(autoGeneration);
		internalMenu.setUpdateTime(updateTime);
		menuDao.save(internalMenu);
		
		return internalMenu;
	}

}
