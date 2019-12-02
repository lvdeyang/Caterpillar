package com.sumavision.tetris.org;

import java.util.Comparator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.sumavision.tetris.orm.po.AbstractBasePO;

/**
 * 菜单数据，包含父菜单和子菜单<br/>
 * <p>详细描述</p>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2018年11月20日 上午10:41:49
 */
@Entity
@Table(name="TETRIS_ORG")
public class OrgPO extends AbstractBasePO{
	
	private static final long serialVersionUID = 1L;
	
	/** menuIdPath 分隔符 */
	public static String SEPARATOR = "/";

	/** 名称 */
	private String name;
	
	/** 父id */
	private Long parentId;
	
	/** 上级id路径：/id/id/id */
	private String parentPath;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getParentPath() {
		return parentPath;
	}

	public void setParentPath(String parentPath) {
		this.parentPath = parentPath;
	}

	
	
}
