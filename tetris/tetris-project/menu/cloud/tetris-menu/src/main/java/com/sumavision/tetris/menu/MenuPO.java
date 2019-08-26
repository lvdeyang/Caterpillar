package com.sumavision.tetris.menu;

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
@Table(name="TETRIS_MENU")
public class MenuPO extends AbstractBasePO{
	
	private static final long serialVersionUID = 1L;
	
	/** menuIdPath 分隔符 */
	public static String SEPARATOR = "/";

	/** 菜单名称 */
	private String title;
	
	/** 菜单链接 */
	private String link;
	
	/** 菜单图标 */
	private String icon;
	
	/** 菜单图标微调 */
	private String style;
	
	/** 是否是菜单组 */
	private Boolean isGroup;
	
	/** 父级菜单id */
	private Long parentId;
	
	/** 菜单路径 格式：/id/id/id */
	private String menuIdPath;
	
	/** 排序序号 */
	private Integer serial;
	
	/** 是否是自动生成的 */
	private boolean autoGeneration;

	@Column(name = "TITLE")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "LINK")
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Column(name = "ICON")
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Column(name = "STYLE")
	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	@Column(name = "IS_GROUP")
	public Boolean getIsGroup() {
		return isGroup;
	}

	public void setIsGroup(Boolean isGroup) {
		this.isGroup = isGroup;
	}

	@Column(name = "PARENT_ID")
	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	@Column(name = "MENU_ID_PATH")
	public String getMenuIdPath() {
		return menuIdPath;
	}

	public void setMenuIdPath(String menuIdPath) {
		this.menuIdPath = menuIdPath;
	}

	@Column(name = "SERIAL")
	public Integer getSerial() {
		return serial;
	}

	public void setSerial(Integer serial) {
		this.serial = serial;
	}
	
	@Column(name = "AUTO_GENERATION")
	public boolean isAutoGeneration() {
		return autoGeneration;
	}

	public void setAutoGeneration(boolean autoGeneration) {
		this.autoGeneration = autoGeneration;
	}

	/**
	 * 菜单排序器<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2018年11月21日 下午4:26:49
	 */
	@Component
	public static class MenuComparator implements Comparator<MenuPO>{

		@Override
		public int compare(MenuPO o1, MenuPO o2) {
			int serial1 = o1.getSerial();
			int serial2 = o2.getSerial();
			if(serial1 > serial2){
				return 1;
			}else if(serial1 < serial2){
				return -1;
			}else {
				return 0;
			}
		}
		
	}
	
}
