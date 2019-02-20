package com.guolaiwan.app.web.commons.tree.vo;

import java.util.List;

/**
 * 树形结构节点
 * lvdeyang 2017年4月17日
 */
public class UINodeVO {

	//节点类型--叶子节点（不带有叶子节点）
	public static final String TYPE_FILE = "file";
	
	//节点类型--目录节点（带有子节点）
	public static final String TYPE_FOLDER = "folder";
	
	//父节点展开
	public static final String STATUS_OPEN = "open";
	
	//父节点关闭
	public static final String STATUS_CLOSE = "close";
	
	//参数分隔符
	public static final String SPLITER = "@@";
	
	//节点显示文字
	private String nodeContent;
	
	//节点的参数
	private String param;
	
	//节点类型
	private String type;
	
	//节点图标
	private String icon;
	
	//节点图标微调
	private int iconTop;
	
	//节点状态
	private String status;
	
	//节点是否支持拖拽
	private boolean draggable = false;
	
	//节点是否有复选框
	private boolean checkable = false;
	
	//子节点列表
	private List<UINodeVO> childrenList;

	public String getNodeContent() {
		return nodeContent;
	}

	public UINodeVO setNodeContent(String nodeContent) {
		this.nodeContent = nodeContent;
		return this;
	}

	public String getParam() {
		return param;
	}

	public UINodeVO setParam(String param) {
		this.param = param;
		return this;
	}

	public String getType() {
		return type;
	}

	public UINodeVO setType(String type) {
		this.type = type;
		return this;
	}

	public String getIcon() {
		return icon;
	}

	public UINodeVO setIcon(String icon) {
		this.icon = icon;
		return this;
	}

	public int getIconTop() {
		return iconTop;
	}

	public UINodeVO setIconTop(int iconTop) {
		this.iconTop = iconTop;
		return this;
	}

	public String getStatus() {
		return status;
	}

	public UINodeVO setStatus(String status) {
		this.status = status;
		return this;
	}

	public boolean isDraggable() {
		return draggable;
	}

	public UINodeVO setDraggable(boolean draggable) {
		this.draggable = draggable;
		return this;
	}

	public boolean isCheckable() {
		return checkable;
	}

	public UINodeVO setCheckable(boolean checkable) {
		this.checkable = checkable;
		return this;
	}

	public List<UINodeVO> getChildrenList() {
		return childrenList;
	}

	public UINodeVO setChildrenList(List<UINodeVO> childrenList) {
		this.childrenList = childrenList;
		return this;
	}
	
}
