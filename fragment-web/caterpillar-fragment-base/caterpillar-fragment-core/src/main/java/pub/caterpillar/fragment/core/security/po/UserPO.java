package pub.caterpillar.fragment.core.security.po;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import pub.caterpillar.orm.po.AbstractBasePO;

/**
 * 用户表<br/>
 * <p>用户表</p>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2018年4月12日 下午3:49:07
 */
@Entity
@Table(name = AbstractBasePO.PREFIX + "SECURITY_USER")
public class UserPO extends AbstractBasePO{

	private static final long serialVersionUID = 1L;

	/** 用户名 */
	private String username;
	
	/** 密码 */
	private String password;
	
	/** 用户权限 */
	private Set<RolePO> roles;
	
	/** 用户组 */
	private Set<GroupPO> groups;

	/** 用户名 */
	@Column(name = "USERNAME")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	/** 密码 */
	@Column(name = "PASSWORD")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/** 用户权限 */
	@ManyToMany
	@JoinTable(name = AbstractBasePO.PREFIX + "SECURITY_USER_ROLE", 
		       joinColumns = {@JoinColumn(name = "USER_ID")}, 
		       inverseJoinColumns = {@JoinColumn(name = "ROLE_ID")})
	public Set<RolePO> getRoles() {
		return roles;
	}

	public void setRoles(Set<RolePO> roles) {
		this.roles = roles;
	}

	/** 用户组 */
	@ManyToMany
	@JoinTable(name = AbstractBasePO.PREFIX + "SECURITY_USER_GROUP",
			   joinColumns = {@JoinColumn(name = "USER_ID")},
			   inverseJoinColumns = {@JoinColumn(name = "GROUP_ID")})
	public Set<GroupPO> getGroups() {
		return groups;
	}

	public void setGroups(Set<GroupPO> groups) {
		this.groups = groups;
	}
	
}
