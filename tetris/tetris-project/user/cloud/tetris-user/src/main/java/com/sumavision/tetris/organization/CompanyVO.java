package com.sumavision.tetris.organization;

import com.sumavision.tetris.commons.util.date.DateUtil;
import com.sumavision.tetris.mvc.converter.AbstractBaseVO;

public class CompanyVO extends AbstractBaseVO<CompanyVO, CompanyPO>{

	/** 公司名称 */
	private String name;
	
	/** 公司首页地址 */
	private String homeLink;
	
	/** 皮肤id */
	private Long themeId;
	
	/** 皮肤名称 */
	private String themeName;
	
	/** 皮肤url */
	private String themeUrl;
	
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
	
	public String getName() {
		return name;
	}

	public CompanyVO setName(String name) {
		this.name = name;
		return this;
	}
	
	public String getHomeLink() {
		return homeLink;
	}

	public CompanyVO setHomeLink(String homeLink) {
		this.homeLink = homeLink;
		return this;
	}

	public Long getThemeId() {
		return themeId;
	}

	public CompanyVO setThemeId(Long themeId) {
		this.themeId = themeId;
		return this;
	}

	public String getThemeName() {
		return themeName;
	}

	public CompanyVO setThemeName(String themeName) {
		this.themeName = themeName;
		return this;
	}

	public String getThemeUrl() {
		return themeUrl;
	}

	public CompanyVO setThemeUrl(String themeUrl) {
		this.themeUrl = themeUrl;
		return this;
	}

	public String getLogo() {
		return logo;
	}

	public CompanyVO setLogo(String logo) {
		this.logo = logo;
		return this;
	}

	public String getLogoStyle() {
		return logoStyle;
	}

	public CompanyVO setLogoStyle(String logoStyle) {
		this.logoStyle = logoStyle;
		return this;
	}

	public String getLogoShortName() {
		return logoShortName;
	}

	public CompanyVO setLogoShortName(String logoShortName) {
		this.logoShortName = logoShortName;
		return this;
	}

	public String getPlatformFullName() {
		return platformFullName;
	}

	public CompanyVO setPlatformFullName(String platformFullName) {
		this.platformFullName = platformFullName;
		return this;
	}

	public String getPlatformShortName() {
		return platformShortName;
	}

	public CompanyVO setPlatformShortName(String platformShortName) {
		this.platformShortName = platformShortName;
		return this;
	}

	@Override
	public CompanyVO set(CompanyPO entity) throws Exception {
		this.setId(entity.getId())
			.setUuid(entity.getUuid())
			.setUpdateTime(entity.getUpdateTime()==null?"":DateUtil.format(entity.getUpdateTime(), DateUtil.dateTimePattern))
			.setName(entity.getName())
			.setHomeLink(entity.getHomeLink()==null?"":entity.getHomeLink())
			.setThemeId(entity.getThemeId())
			.setLogo(entity.getLogo())
			.setLogoStyle(entity.getLogoStyle())
			.setLogoShortName(entity.getLogoShortName())
			.setPlatformFullName(entity.getPlatformFullName())
			.setPlatformShortName(entity.getPlatformShortName());
		
		if(this.getLogo() == null) this.setLogo(CompanyPO.DEFAULT_LOGO);
		if(this.getLogoStyle() == null) this.setLogoStyle(CompanyPO.DEFAULT_LOGOSTYLE);
		if(this.getLogoShortName() == null) this.setLogoShortName(CompanyPO.DEFAULT_LOGOSHORTNAME);
		if(this.getPlatformFullName() == null) this.setPlatformFullName(CompanyPO.DEFAULT_PLATFORMFULLNAME);
		if(this.getPlatformShortName() == null) this.setPlatformShortName(CompanyPO.DEFAULT_PLATFORMSHORTNAME);
		
		return this;
	}

}
