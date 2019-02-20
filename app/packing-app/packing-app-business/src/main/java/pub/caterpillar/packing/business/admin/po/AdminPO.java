package pub.caterpillar.packing.business.admin.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import pub.caterpillar.orm.po.AbstractBasePO;
import pub.caterpillar.packing.business.admin.enumeration.AdminRoleType;

@Entity
@Table(name = "t_app_admin")
public class AdminPO extends AbstractBasePO{

	private static final long serialVersionUID = 1L;

	//用户名
	private String username;
	
	//密码
	private String password;
	
	//权限
	private AdminRoleType roleType;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "role_type")
	public AdminRoleType getRoleType() {
		return roleType;
	}

	public void setRoleType(AdminRoleType roleType) {
		this.roleType = roleType;
	}
	
}
