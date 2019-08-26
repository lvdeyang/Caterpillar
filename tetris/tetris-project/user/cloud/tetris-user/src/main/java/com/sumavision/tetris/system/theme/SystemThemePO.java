package com.sumavision.tetris.system.theme;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.sumavision.tetris.orm.po.AbstractBasePO;

/**
 * 系统皮肤<br/>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年7月4日 上午11:21:25
 */
@Entity
@Table(name = "TETRIS_USER_SYSTEM_THEME")
public class SystemThemePO extends AbstractBasePO{

	private static final long serialVersionUID = 1L;
	
	/** 默认主题名 */
	public static final String DEFAULT_NAME = "默认主题";
	
	/** 默认主题地址 */
	public static final String DEFAULT_URL = "";
	
	/** 皮肤名称 */
	private String name;
	
	/** 皮肤url */
	private String url;

	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "URL", unique = true)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
