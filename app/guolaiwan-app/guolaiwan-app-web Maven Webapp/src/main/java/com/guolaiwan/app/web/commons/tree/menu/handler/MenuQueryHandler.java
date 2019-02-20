package com.guolaiwan.app.web.commons.tree.menu.handler;

import java.util.List;
import pub.caterpillar.commons.context.SpringContext;
import pub.caterpillar.commons.handler.BaseHandler;

import com.guolaiwan.bussiness.admin.dao.MenuDAO;
import com.guolaiwan.bussiness.admin.po.MenuPO;
import com.guolaiwan.bussiness.chapman.source.dao.SourceDAO;
import com.guolaiwan.bussiness.chapman.source.dao.SourceFolderDAO;
import com.guolaiwan.bussiness.chapman.source.po.SourceFolderPO;
import com.guolaiwan.bussiness.chapman.source.po.SourcePO;

/**
 * 资源树形结构层级查询接口
 * lvdeyang  2017年4月26日
 */
public abstract class MenuQueryHandler<T> extends BaseHandler<T> {
	
	protected MenuDAO conn_menu = SpringContext.getBean(MenuDAO.class);
	
	//获取菜单列表
	public abstract List<MenuPO> getMenus() throws Exception;
	
	public MenuQueryHandler(){}
	
	public MenuQueryHandler(T params){
		super(params);
	}
	
}
