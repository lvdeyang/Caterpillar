package com.guolaiwan.app.web.commons.tree.menu.util;

import java.util.ArrayList;
import java.util.List;
import com.guolaiwan.app.web.commons.tree.menu.enumeration.NodeType;
import com.guolaiwan.app.web.commons.tree.menu.handler.MenuQueryHandler;
import com.guolaiwan.app.web.commons.tree.menu.handler.impl.RootMenuQueryHandler;
import com.guolaiwan.app.web.commons.tree.menu.handler.impl.SubMenuQueryHandler;
import com.guolaiwan.app.web.commons.tree.vo.UINodeVO;
import com.guolaiwan.bussiness.admin.po.MenuPO;
import pub.caterpillar.commons.util.wrapper.StringBuilderWrapper;

/**
 * 树形结构生成工具 
 * lvdeyang 2017年4月26日
 */
public class MenuTreeUtil {

	//获取树形节点的类型
	public static final String LEVELTYPE_SOURCE = "source";
	public static final String LEVELTYPE_INST = "inst";
	
	//从根节点获取树
	public static UINodeVO getTree(RootMenuQueryHandler rootQuery, SubMenuQueryHandler firstLevelQuery, boolean draggable, boolean checkable) throws Exception{
		//创建根节点
		UINodeVO rootNode = MenuTreeUtil.getNodeList(rootQuery, true, draggable, checkable).get(0);
		
		rootNode.setChildrenList(MenuTreeUtil.getNodeList(firstLevelQuery, false, draggable, checkable));
		
		return rootNode;
	}
	
	//获取树形结构
	public static List<UINodeVO> getNodeList(MenuQueryHandler handler, boolean open, boolean draggable, boolean checkable) throws Exception{
		return generateNode(handler.getMenus(), open, draggable, checkable);
	}
	
	/***************************
	 * 		生成树节点（新树）
	 **************************/
	
	//生成一组node
	public static List<UINodeVO> generateNode(List<MenuPO> menus, boolean open, boolean draggable, boolean checkable) throws Exception{
		List<UINodeVO> nodeList = new ArrayList<UINodeVO>();
		
		if(menus!=null && menus.size()>0){
			for(MenuPO menu:menus){
				nodeList.add(generateNode(menu, open, draggable, checkable));
			}
		}
		
		return nodeList;
	}
	
	//生成一个node
	public static UINodeVO generateNode(Object target, boolean open, boolean draggable, boolean checkable) throws Exception{
		
		UINodeVO node = new UINodeVO();
		
		if(target instanceof MenuPO){
			MenuPO menu = (MenuPO) target;
			node.setNodeContent(menu.getName())
				.setParam(new StringBuilderWrapper().append(NodeType.MENU.getName())
													.append(UINodeVO.SPLITER)
													.append(menu.getId())
													.append(UINodeVO.SPLITER)
													.append(menu.getName())
													.toString())
				.setType(UINodeVO.TYPE_FOLDER)
				.setIcon("fa fa-file-text-o")
				.setStatus(open?UINodeVO.STATUS_OPEN:UINodeVO.STATUS_CLOSE)
				.setDraggable(draggable)
				.setCheckable(checkable)
				.setChildrenList(new ArrayList<UINodeVO>());
		}
		
		return node;
	}
}
