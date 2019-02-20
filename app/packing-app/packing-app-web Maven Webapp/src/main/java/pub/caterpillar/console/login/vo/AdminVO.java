package pub.caterpillar.console.login.vo;

import java.util.Date;
import pub.caterpillar.mvc.converter.AbstractBaseVO;
import pub.caterpillar.packing.business.admin.po.AdminPO;

public class AdminVO extends AbstractBaseVO<AdminVO, AdminPO>{

	public static final String CATCHNAME = "ADMIN";
	
	public static final int DEFAULTTIMEOUT = 1800000;
	
	private String username;

	private String password;
	
	private String roleName;
	
	//默认30分钟不触发功能就会超时
	private Date timestamp;
	
	//登录为用户生成一个密钥
	private String key;
	
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

	public Date getTimestamp() {
		return timestamp;
	}

	public AdminVO setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
		return this;
	}

	public String getKey() {
		return key;
	}

	public AdminVO setKey(String key) {
		this.key = key;
		return this;
	}

	@Override
	public AdminVO set(AdminPO entity) throws Exception {
		this.setId(entity.getId())
			.setUsername(entity.getUsername())
			.setPassword("********")
			.setRoleName(entity.getRoleType()==null?null:entity.getRoleType().getName());
		return this;
	}
	
}
