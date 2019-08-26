package com.sumavision.tetris.organization;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.sumavision.tetris.orm.po.AbstractBasePO;

/**
 * 公司信息<br/>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年1月24日 上午8:59:06
 */
@Entity
@Table(name = "TETRIS_COMPANY")
public class CompanyPO extends AbstractBasePO{

	private static final long serialVersionUID = 1L;

	/** 默认logo */
	public static final String DEFAULT_LOGO = "/logos/__default__/suma.png";
	
	/** 默认logo样式 */
	public static final String DEFAULT_LOGOSTYLE = "width:48%; top:4px;";
	
	/** 默认logo缩写 */
	public static final String DEFAULT_LOGOSHORTNAME = "SUMA";
	
	/** 默认系统全名 */
	public static final String DEFAULT_PLATFORMFULLNAME = "融媒体平台";
	
	/** 默认系统名称缩写 */
	public static final String DEFAULT_PLATFORMSHORTNAME = "MIMS";
	
	/** 公司名称 */
	private String name;
	
	/** 公司首页地址 */
	private String homeLink;
	
	/** 申请人 */
	private String userId;
	
	/** 皮肤id */
	private Long themeId;
	
	/** logo url */
	private String logo;
	
	/** logo样式 */
	private String logoStyle;
	
	/** logo缩略名称 */
	private String logoShortName;
	
	/** 平台全称 */
	private String platformFullName;
	
	/** 平台缩略名称 */
	private String platformShortName;

	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "HOME_LINK")
	public String getHomeLink() {
		return homeLink;
	}

	public void setHomeLink(String homeLink) {
		this.homeLink = homeLink;
	}

	@Column(name = "USER_ID")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "THEME_ID")
	public Long getThemeId() {
		return themeId;
	}

	public void setThemeId(Long themeId) {
		this.themeId = themeId;
	}

	@Column(name = "LOGO")
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	@Column(name = "LOGO_STYLE")
	public String getLogoStyle() {
		return logoStyle;
	}

	public void setLogoStyle(String logoStyle) {
		this.logoStyle = logoStyle;
	}

	@Column(name = "LOGO_SHORT_NAME")
	public String getLogoShortName() {
		return logoShortName;
	}

	public void setLogoShortName(String logoShortName) {
		this.logoShortName = logoShortName;
	}

	@Column(name = "PLATFORM_FULL_NAME")
	public String getPlatformFullName() {
		return platformFullName;
	}

	public void setPlatformFullName(String platformFullName) {
		this.platformFullName = platformFullName;
	}

	@Column(name = "PLATFORM_SHORT_NAME")
	public String getPlatformShortName() {
		return platformShortName;
	}

	public void setPlatformShortName(String platformShortName) {
		this.platformShortName = platformShortName;
	}
	
}
