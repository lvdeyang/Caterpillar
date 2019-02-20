package pub.caterpillar.fragment.core.security.po;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import pub.caterpillar.orm.po.AbstractBasePO;

/**
 * 权限表<br/>
 * <p>权限表</p>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2018年4月12日 下午3:49:25
 */
@Entity
@Table(name = AbstractBasePO.PREFIX + "SECURITY_ROLE")
public class RolePO extends AbstractBasePO{

	private static final long serialVersionUID = 1L;

	/** 权限名称 */
	private String name;
	
	/** 权限代码 */
	private String code;
	
	/** 权限用户 */
	private Set<UserPO> users;
	
	/** 权限用户组 */
	private Set<GroupPO> groups;

	/** 权限名称 */
	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/** 权限代码 */
	@Column(name = "CODE")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/** 权限用户 */
	@ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public Set<UserPO> getUsers() {
		return users;
	}

	public void setUsers(Set<UserPO> users) {
		this.users = users;
	}

	/** 权限用户组 */
	@ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public Set<GroupPO> getGroups() {
		return groups;
	}

	public void setGroups(Set<GroupPO> groups) {
		this.groups = groups;
	}
	
}
