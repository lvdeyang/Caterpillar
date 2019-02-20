package com.guolaiwan.app.web.chapman.source.tree.util;

import java.util.ArrayList;
import java.util.List;

import com.guolaiwan.app.web.chapman.source.tree.enumeration.NodeType;
import com.guolaiwan.app.web.chapman.source.tree.handler.SourceFolderQueryHandler;
import com.guolaiwan.app.web.commons.tree.vo.UINodeVO;
import com.guolaiwan.bussiness.chapman.source.enumeration.SourceType;
import com.guolaiwan.bussiness.chapman.source.po.SourceFolderPO;
import com.guolaiwan.bussiness.chapman.source.po.SourcePO;

import pub.caterpillar.commons.util.wrapper.StringBuilderWrapper;

/**
 * 树形结构生成工具 
 * lvdeyang 2017年4月26日
 */
public class SourceFolderTreeUtil {

	//获取树形节点的类型
	public static final String LEVELTYPE_SOURCE = "source";
	public static final String LEVELTYPE_INST = "inst";
	
	//从根节点获取树
	public static UINodeVO getTree(SourceFolderQueryHandler rootQuery, SourceFolderQueryHandler firstLevelQuery, boolean draggable, boolean checkable) throws Exception{
		//创建根节点
		UINodeVO rootNode = SourceFolderTreeUtil.getNodeList(rootQuery, true, draggable, checkable).get(0);
		
		rootNode.setChildrenList(SourceFolderTreeUtil.getNodeList(firstLevelQuery, false, draggable, checkable));
		
		return rootNode;
	}
	
	//获取树形结构
	public static List<UINodeVO> getNodeList(SourceFolderQueryHandler handler, boolean open, boolean draggable, boolean checkable) throws Exception{
		return generateNode(handler.getFolders(), handler.getSources(), open, draggable, checkable);
	}
	
	/***************************
	 * 		生成树节点（新树）
	 **************************/
	
	//生成一组node
	public static List<UINodeVO> generateNode(List<SourceFolderPO> folders, List<SourcePO> sources, boolean open, boolean draggable, boolean checkable) throws Exception{
		List<UINodeVO> nodeList = new ArrayList<UINodeVO>();
		
		if(folders!=null && folders.size()>0){
			for(SourceFolderPO folder:folders){
				nodeList.add(generateNode(folder, open, draggable, checkable));
			}
		}
		
		if(sources!=null && sources.size()>0){
			for(SourcePO source:sources){
				nodeList.add(generateNode(source, open, draggable, checkable));
			}
		}
		
		return nodeList;
	}
	
	//生成一个node
	public static UINodeVO generateNode(Object target, boolean open, boolean draggable, boolean checkable) throws Exception{
		
		UINodeVO node = new UINodeVO();
		
		if(target instanceof SourceFolderPO){
			SourceFolderPO folder = (SourceFolderPO) target;
			node.setNodeContent(folder.getFoldername())
			.setParam(new StringBuilderWrapper().append(NodeType.FOLDER.getName())
												.append(UINodeVO.SPLITER)
												.append(folder.getId())
												.append(UINodeVO.SPLITER)
												.append(folder.getFoldername())
												.toString())
			.setType(UINodeVO.TYPE_FOLDER)
			.setStatus(open?UINodeVO.STATUS_OPEN:UINodeVO.STATUS_CLOSE)
			.setDraggable(draggable)
			.setCheckable(checkable)
			.setChildrenList(new ArrayList<UINodeVO>());
		}else if(target instanceof SourcePO){
			SourcePO source = (SourcePO) target;
			
			String icon = null;
			if(SourceType.PICTURE.equals(source.getType())){
				icon = "fa fa-file-picture-o";
			}else if(SourceType.AUDIO.equals(source.getType())){
				icon = "";
			}else if(SourceType.VIDEO.equals(source.getType())){
				icon = "";
			}
			
			node.setNodeContent(source.getIntroduction())
			    .setParam(new StringBuilderWrapper().append(NodeType.fromString(source.getType().toString()).getName())
					   							    .append(UINodeVO.SPLITER)
					   							    .append(source.getId())
					   							    .append(UINodeVO.SPLITER)
					   							    .append(source.getIntroduction())
					   							    .append(UINodeVO.SPLITER)
					   							    .append(source.getSize())
					   							    .append(UINodeVO.SPLITER)
					   							    .append(source.getSourceFolder().getId())
					   							    .append(UINodeVO.SPLITER)
					   							    .append(source.getSourceFolder().getFoldername())
					   							    .toString())
				.setType(UINodeVO.TYPE_FILE)
				.setIcon(icon)
				.setDraggable(draggable)
				.setCheckable(checkable);
		}
		
		return node;
	}
}
