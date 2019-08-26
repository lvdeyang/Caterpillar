package com.sumavision.tetris.menu;

import java.util.List;
import com.sumavision.tetris.commons.util.date.DateUtil;
import com.sumavision.tetris.mvc.converter.AbstractBaseVO;

/**
 * 菜单项<br/>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2018年11月19日 下午4:59:19
 */
public class MenuVO extends AbstractBaseVO<MenuVO, MenuPO>{

	/** 菜单名称 */
	private String title;
	
	/** 菜单链接 */
	private String link;
	
	/** 菜单图标 */
	private String icon;
	
	/** 菜单图标微调 */
	private String style;
	
	/** 是否是当前菜单 */
	private boolean active;
	
	/** 是否是菜单组 */
	private boolean isGroup;
	
	/** 是否是叶子节点 */
	private boolean isLeaf;
	
	/** 父菜单id */
	private Long parentId;
	
	/** 显示顺序 */
	private int serial;
	
	/** 是否是自动生成的 */
	private boolean autoGeneration;
	
	/** 子菜单 */
	private List<MenuVO> sub;
	
	public String getTitle() {
		return title;
	}

	public MenuVO setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getLink() {
		return link;
	}

	public MenuVO setLink(String link) {
		this.link = link;
		return this;
	}

	public String getIcon() {
		return icon;
	}

	public MenuVO setIcon(String icon) {
		this.icon = icon;
		return this;
	}

	public String getStyle() {
		return style;
	}

	public MenuVO setStyle(String style) {
		this.style = style;
		return this;
	}
	
	public boolean isActive() {
		return active;
	}

	public MenuVO setActive(boolean active) {
		this.active = active;
		return this;
	}
	
	public boolean getIsGroup() {
		return isGroup;
	}

	public MenuVO setIsGroup(boolean isGroup) {
		this.isGroup = isGroup;
		return this;
	}

	public boolean getIsLeaf() {
		return isLeaf;
	}

	public MenuVO setIsLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
		return this;
	}
	
	public Long getParentId() {
		return parentId;
	}

	public MenuVO setParentId(Long parentId) {
		this.parentId = parentId;
		return this;
	}

	public int getSerial() {
		return serial;
	}

	public MenuVO setSerial(int serial) {
		this.serial = serial;
		return this;
	}

	public boolean isAutoGeneration() {
		return autoGeneration;
	}

	public MenuVO setAutoGeneration(boolean autoGeneration) {
		this.autoGeneration = autoGeneration;
		return this;
	}

	public List<MenuVO> getSub() {
		return sub;
	}

	public MenuVO setSub(List<MenuVO> sub) {
		this.sub = sub;
		return this;
	}

	@Override
	public MenuVO set(MenuPO entity) throws Exception {
		this.setId(entity.getId())
			.setUuid(entity.getUuid())
			.setUpdateTime(entity.getUpdateTime()==null?"":DateUtil.format(entity.getUpdateTime(), DateUtil.dateTimePattern))
			.setTitle(entity.getTitle())
			.setLink(entity.getLink())
			.setIcon(entity.getIcon())
			.setStyle(entity.getStyle())
			.setIsGroup(entity.getIsGroup())
			.setIsLeaf(!entity.getIsGroup())
			.setSerial(entity.getSerial())
			.setParentId(entity.getParentId())
			.setAutoGeneration(entity.isAutoGeneration());
		return this;
	}
	
}
