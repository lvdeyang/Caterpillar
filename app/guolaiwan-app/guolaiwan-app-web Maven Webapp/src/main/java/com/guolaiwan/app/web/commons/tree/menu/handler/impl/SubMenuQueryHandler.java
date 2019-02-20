package com.guolaiwan.app.web.commons.tree.menu.handler.impl;

import java.util.List;
import com.alibaba.fastjson.JSONObject;
import com.guolaiwan.app.web.chapman.source.tree.handler.SourceFolderQueryHandler;
import com.guolaiwan.app.web.commons.tree.menu.handler.MenuQueryHandler;
import com.guolaiwan.bussiness.admin.po.MenuPO;
import com.guolaiwan.bussiness.chapman.po.ChapmanPO;
import com.guolaiwan.bussiness.chapman.source.po.SourceFolderPO;
import com.guolaiwan.bussiness.chapman.source.po.SourcePO;

public class SubMenuQueryHandler extends MenuQueryHandler<Long> {

	public SubMenuQueryHandler(){}
	
	public SubMenuQueryHandler(Long menuId){
		super(menuId);
	}
	
	@Override
	public List<MenuPO> getMenus() throws Exception {
		
		Long menuId = this.getParams();
		
		List<MenuPO> menus = conn_menu.getSubMenu(menuId);
		
		return menus;
	}

}
