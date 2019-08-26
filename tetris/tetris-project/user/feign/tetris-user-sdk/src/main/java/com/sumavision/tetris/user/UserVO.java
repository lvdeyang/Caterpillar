package com.sumavision.tetris.user;

import java.util.List;

/**
 * 登录用户信息<br/>
 * <p>特殊注明：用uuid字段存储userId</p>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2018年11月19日 下午4:51:51
 */
public class UserVO{

	private Long id;
	
	private String uuid;
	
	private String updateTime;
	
	/** 用户名 */
	private String nickname;
	
	/** 头像 */
	private String icon;
	
	/** 用户状态 */
	private String status;
	
	/** 用户登录token */
	private String token;
	
	/** 手机号 */
	private String mobile;
	
	/** 邮箱 */
	private String mail;
	
	/** 是否是自动生成的 */
	private boolean autoGeneration;
	
	/** 用户分类 */
	private String classify;
	
	/** 发送给当前用户的信息数目 */
	private Integer numbersOfMessage;
	
	/** 用户隶属组织id */
	private String groupId;
	
	/** 用户隶属组织名称 */
	private String groupName;
	
	/** 用户隶属组织首页地址 */
	private String groupHomeLink;
	
	/** 皮肤链接 */
	private String themeUrl;
	
	/** logo */
	private String logo;
	
	/** logo样式 */
	private String logoStyle;
	
	/** logo缩写 */
	private String logoShortName;
	
	/** 平台全名 */
	private String platformFullName;
	
	/** 平台名称缩写 */
	private String platformShortName;
	
	/** 素材库文件夹id */
	private Long rootFolderId;
	
	/** 素材库文件夹名称 */
	private String rootFolderName;
	
	/** 业务角色id,以“,”分割 */
	private String businessRoles;
	
	/** 用户标签 */
	private List<String> tags;

	public Long getId() {
		return id;
	}

	public UserVO setId(Long id) {
		this.id = id;
		return this;
	}

	public String getUuid() {
		return uuid;
	}

	public UserVO setUuid(String uuid) {
		this.uuid = uuid;
		return this;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public UserVO setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public String getNickname() {
		return nickname;
	}

	public UserVO setNickname(String nickname) {
		this.nickname = nickname;
		return this;
	}

	public String getIcon() {
		return icon;
	}

	public UserVO setIcon(String icon) {
		this.icon = icon;
		return this;
	}

	public String getStatus() {
		return status;
	}

	public UserVO setStatus(String status) {
		this.status = status;
		return this;
	}
	
	public String getToken() {
		return token;
	}

	public UserVO setToken(String token) {
		this.token = token;
		return this;
	}

	public String getMobile() {
		return mobile;
	}

	public UserVO setMobile(String mobile) {
		this.mobile = mobile;
		return this;
	}

	public String getMail() {
		return mail;
	}

	public UserVO setMail(String mail) {
		this.mail = mail;
		return this;
	}

	public boolean isAutoGeneration() {
		return autoGeneration;
	}

	public UserVO setAutoGeneration(boolean autoGeneration) {
		this.autoGeneration = autoGeneration;
		return this;
	}

	public String getClassify() {
		return classify;
	}

	public UserVO setClassify(String classify) {
		this.classify = classify;
		return this;
	}

	public Integer getNumbersOfMessage() {
		return numbersOfMessage;
	}

	public UserVO setNumbersOfMessage(Integer numbersOfMessage) {
		this.numbersOfMessage = numbersOfMessage;
		return this;
	}

	public String getGroupId() {
		return groupId;
	}

	public UserVO setGroupId(String groupId) {
		this.groupId = groupId;
		return this;
	}

	public String getGroupName() {
		return groupName;
	}

	public UserVO setGroupName(String groupName) {
		this.groupName = groupName;
		return this;
	}

	public String getGroupHomeLink() {
		return groupHomeLink;
	}

	public UserVO setGroupHomeLink(String groupHomeLink) {
		this.groupHomeLink = groupHomeLink;
		return this;
	}

	public String getThemeUrl() {
		return themeUrl;
	}

	public UserVO setThemeUrl(String themeUrl) {
		this.themeUrl = themeUrl;
		return this;
	}

	public String getLogo() {
		return logo;
	}

	public UserVO setLogo(String logo) {
		this.logo = logo;
		return this;
	}

	public String getLogoStyle() {
		return logoStyle;
	}

	public UserVO setLogoStyle(String logoStyle) {
		this.logoStyle = logoStyle;
		return this;
	}

	public String getLogoShortName() {
		return logoShortName;
	}

	public UserVO setLogoShortName(String logoShortName) {
		this.logoShortName = logoShortName;
		return this;
	}

	public String getPlatformFullName() {
		return platformFullName;
	}

	public UserVO setPlatformFullName(String platformFullName) {
		this.platformFullName = platformFullName;
		return this;
	}

	public String getPlatformShortName() {
		return platformShortName;
	}

	public UserVO setPlatformShortName(String platformShortName) {
		this.platformShortName = platformShortName;
		return this;
	}

	public Long getRootFolderId() {
		return rootFolderId;
	}

	public UserVO setRootFolderId(Long rootFolderId) {
		this.rootFolderId = rootFolderId;
		return this;
	}

	public String getRootFolderName() {
		return rootFolderName;
	}

	public UserVO setRootFolderName(String rootFolderName) {
		this.rootFolderName = rootFolderName;
		return this;
	}

	public String getBusinessRoles() {
		return businessRoles;
	}

	public UserVO setBusinessRoles(String businessRoles) {
		this.businessRoles = businessRoles;
		return this;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	
}