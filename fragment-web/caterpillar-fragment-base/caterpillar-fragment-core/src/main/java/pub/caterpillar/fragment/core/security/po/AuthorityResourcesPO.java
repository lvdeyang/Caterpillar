package pub.caterpillar.fragment.core.security.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import pub.caterpillar.orm.po.AbstractBasePO;

/**
 * 权限资源表<br/>
 * <p>权限资源表</p>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2018年4月12日 下午3:51:39
 */
@Entity
@Table(name = AbstractBasePO.PREFIX + "SECURITY_AUTHORITY_RESOURCES")
public class AuthorityResourcesPO extends AbstractBasePO{

	private static final long serialVersionUID = 1L;

	/** 权限映射表名 */
	private String authorityTableName;
	
	/** 权限映射表中权限字段名 */
	private String authorityColRoleName;
	
	/** 权限映射表中资源字段名 */
	private String authorityColResourceName;
	
	/** 资源表名 */
	private String resourceTableName;
	
	/** 资源注册类型 */
	private AuthorityResourcesTypePO type;

	/** 权限映射表名 */
	@Column(name = "AUTHORITY_TABLE_NAME")
	public String getAuthorityTableName() {
		return authorityTableName;
	}

	public void setAuthorityTableName(String authorityTableName) {
		this.authorityTableName = authorityTableName;
	}

	/** 权限映射表中权限字段名 */
	@Column(name = "AUTHORITY_COL_ROLE_NAME")
	public String getAuthorityColRoleName() {
		return authorityColRoleName;
	}

	public void setAuthorityColRoleName(String authorityColRoleName) {
		this.authorityColRoleName = authorityColRoleName;
	}

	/** 权限映射表中资源字段名 */
	@Column(name = "AUTHORITY_COL_RESOURCE_NAME")
	public String getAuthorityColResourceName() {
		return authorityColResourceName;
	}

	public void setAuthorityColResourceName(String authorityColResourceName) {
		this.authorityColResourceName = authorityColResourceName;
	}

	/** 资源表名 */
	@Column(name = "RESOURCE_TABLE_NAME")
	public String getResourceTableName() {
		return resourceTableName;
	}

	public void setResourceTableName(String resourceTableName) {
		this.resourceTableName = resourceTableName;
	}

	/** 资源注册类型 */
	@OneToOne
	@JoinColumn(name = "TYPE_ID")
	public AuthorityResourcesTypePO getType() {
		return type;
	}

	public void setType(AuthorityResourcesTypePO type) {
		this.type = type;
	}
	
}
