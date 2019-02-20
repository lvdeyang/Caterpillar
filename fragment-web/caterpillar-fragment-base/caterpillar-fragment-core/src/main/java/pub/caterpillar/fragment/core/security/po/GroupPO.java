package pub.caterpillar.fragment.core.security.po;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import pub.caterpillar.orm.po.AbstractBasePO;

/**
 * 用户组表<br/>
 * <p>用户组表</p>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2018年4月12日 下午3:49:53
 */
@Entity
@Table(name = AbstractBasePO.PREFIX + "SECURITY_GROUP")
public class GroupPO extends AbstractBasePO{

	private static final long serialVersionUID = 1L;
	
	/** 组名称 */
	private String name;
	
	/** 组内用户 */
	private Set<UserPO> users;
	
	/** 用户组权限 */
	private Set<RolePO> roles;

	/** 组名称 */
	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/** 组内用户 */
	@ManyToMany(mappedBy = "groups", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public Set<UserPO> getUsers() {
		return users;
	}

	public void setUsers(Set<UserPO> users) {
		this.users = users;
	}

	/** 用户组权限 */
	@ManyToMany
	@JoinTable(name = AbstractBasePO.PREFIX + "SECURITY_GROUP_ROLE",
			   joinColumns = {@JoinColumn(name = "GROUP_ID")},
			   inverseJoinColumns = {@JoinColumn(name = "ROLE_ID")})
	public Set<RolePO> getRoles() {
		return roles;
	}

	public void setRoles(Set<RolePO> roles) {
		this.roles = roles;
	}

}
