package com.guolaiwan.app.web.commons.tree.menu.handler.impl;

import java.util.List;
import com.guolaiwan.app.web.commons.tree.menu.handler.MenuQueryHandler;
import com.guolaiwan.bussiness.admin.po.MenuPO;

public class RootMenuQueryHandler extends MenuQueryHandler {

	@Override
	public List<MenuPO> getMenus() throws Exception {
		
		List<MenuPO> menus = conn_menu.getRootMenu();
		
		return menus;
	}

}
