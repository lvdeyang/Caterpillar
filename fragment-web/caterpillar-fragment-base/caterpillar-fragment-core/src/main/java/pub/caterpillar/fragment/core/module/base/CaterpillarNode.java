package pub.caterpillar.fragment.core.module.base;

import java.util.Set;

import com.alibaba.fastjson.JSON;

/**
 * 菜单节点<br/>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2018年4月18日 上午9:12:58
 */
public class CaterpillarNode {

	/** 菜单uuid */
	private String uuid;
	
	/** 菜单名称 */
	private String name;
	
	/** 菜单的访问权限 */
	private Set<String> roles;
	
	/** 菜单的url地址 */
	private String url;
	
	/** 父菜单uuid */
	private String parentUuid;

	/** 菜单uuid */
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/** 菜单名称 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/** 菜单的访问权限 */
	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	/** 菜单的url地址 */
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	/** 父菜单uuid */
	public String getParentUuid() {
		return parentUuid;
	}

	public void setParentUuid(String parentUuid) {
		this.parentUuid = parentUuid;
	}
	
}
