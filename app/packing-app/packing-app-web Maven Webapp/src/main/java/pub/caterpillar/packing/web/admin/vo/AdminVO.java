package pub.caterpillar.packing.web.admin.vo;

import pub.caterpillar.mvc.converter.AbstractBaseVO;
import pub.caterpillar.packing.business.admin.po.AdminPO;

public class AdminVO extends AbstractBaseVO<AdminVO, AdminPO>{

	private String username;
	
	private String password;
	
	private String roleName;
	
	public String getUsername() {
		return username;
	}

	public AdminVO setUsername(String username) {
		this.username = username;
		return this;
	}
	
	public String getPassword() {
		return password;
	}

	public AdminVO setPassword(String password) {
		this.password = password;
		return this;
	}

	public String getRoleName() {
		return roleName;
	}

	public AdminVO setRoleName(String roleName) {
		this.roleName = roleName;
		return this;
	}

	@Override
	public AdminVO set(AdminPO entity) throws Exception {
		this.setId(entity.getId())
			.setUsername(entity.getUsername())
			.setPassword("********")
			.setRoleName(entity.getRoleType().getName());
		return this;
	}
	
}
