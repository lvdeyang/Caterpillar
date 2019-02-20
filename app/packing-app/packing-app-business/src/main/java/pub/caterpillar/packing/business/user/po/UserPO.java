package pub.caterpillar.packing.business.user.po;

import javax.persistence.Entity;
import javax.persistence.Table;
import pub.caterpillar.orm.po.AbstractBasePO;

/**
 * 用户表
 * lvdeyang 2017年12月1日
 */
@Entity
@Table(name = "t_app_user")
public class UserPO extends AbstractBasePO {

	private static final long serialVersionUID = 1L;

	//手机号码
	private String mobile;
	
	//车牌号
	private String license;
	
	//车牌地区
	private String region;
	
	//车型
	private String type;
	
	//姓名
	private String name;
	
	//绑定id
	private String identification;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}
	
}
