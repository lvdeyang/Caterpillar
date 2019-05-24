package com.chenxi.web.admin.vo;

import java.util.ArrayList;
import java.util.List;

public class TreeVo {

	private long id;
	private String name;
	private List<TreeVo> children=new ArrayList<TreeVo>();
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<TreeVo> getChildren() {
		return children;
	}
	public void setChildren(List<TreeVo> children) {
		this.children = children;
	}
	
	
}
