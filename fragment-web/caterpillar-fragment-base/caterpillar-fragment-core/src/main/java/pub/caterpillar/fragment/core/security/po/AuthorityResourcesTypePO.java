package pub.caterpillar.fragment.core.security.po;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import pub.caterpillar.orm.po.AbstractBasePO;

/**
 * 资源权限注册类型<br/>
 * <p>资源权限注册类型</p>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2018年4月13日 下午5:27:23
 */
@Entity
@Table(name = AbstractBasePO.PREFIX + "SECURITY_AUTHORITY_RESOURCES_TYPE")
public class AuthorityResourcesTypePO extends AbstractBasePO{

	private static final long serialVersionUID = 1L;
	
	/** 资源权限注册类型名称 */
	private String name;
	
	/** 资源权限注册类型代码--这个资源用于映射编码时枚举，便于扩展 */
	private String code;
	
	/** 资源权限 */
	private AuthorityResourcesPO authorityResources;

	/** 资源权限注册类型名称 */
	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/** 资源权限注册类型代码--这个资源用于映射编码时枚举，便于扩展 */
	@Column(name = "CODE")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/** 资源权限 */
	@OneToOne(mappedBy = "type", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public AuthorityResourcesPO getAuthorityResources() {
		return authorityResources;
	}

	public void setAuthorityResources(AuthorityResourcesPO authorityResources) {
		this.authorityResources = authorityResources;
	}
	
}
