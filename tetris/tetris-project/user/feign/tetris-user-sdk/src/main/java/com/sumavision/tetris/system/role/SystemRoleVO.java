package com.sumavision.tetris.system.role;

/**
 * 系统角色<br/>
 * <b>作者:</b>lvdeynag<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年1月11日 下午2:43:34
 */
public class SystemRoleVO {

	/** 角色id */
	private String id;
	
	/** 角色名称 */
	private String name;
	
	/** 是否是自动生成 */
	private boolean autoGeneration;
	
	/** ui树形结构数据 */
	private boolean isGroup = false;
	
	/** 系统角色一级分类 */
	private SystemRoleLevel level_1;
	
	/** 系统角色二级分类 */
	private SystemRoleLevel level_2;

	public String getId() {
		return id;
	}

	public SystemRoleVO setId(String id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public SystemRoleVO setName(String name) {
		this.name = name;
		return this;
	}

	public boolean isAutoGeneration() {
		return autoGeneration;
	}

	public SystemRoleVO setAutoGeneration(boolean autoGeneration) {
		this.autoGeneration = autoGeneration;
		return this;
	}

	public boolean getIsGroup() {
		return isGroup;
	}

	public SystemRoleLevel getLevel_1() {
		return level_1;
	}

	public SystemRoleVO setLevel_1(SystemRoleLevel level_1) {
		this.level_1 = level_1;
		return this;
	}

	public SystemRoleLevel getLevel_2() {
		return level_2;
	}

	public SystemRoleVO setLevel_2(SystemRoleLevel level_2) {
		this.level_2 = level_2;
		return this;
	}
	
}
