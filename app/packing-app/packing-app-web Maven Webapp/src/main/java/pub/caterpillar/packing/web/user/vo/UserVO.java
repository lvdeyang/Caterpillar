package pub.caterpillar.packing.web.user.vo;

import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;
import pub.caterpillar.mvc.converter.AbstractBaseVO;
import pub.caterpillar.packing.business.user.po.UserPO;

public class UserVO extends AbstractBaseVO<UserVO, UserPO>{

	//注册者姓名
	private String name;
	
	//手机号码
	private String mobile;
	
	//车牌号
	private String license;
	
	//车型
	private String type;
	
	//二维码
	private String qrCode;
	
	public String getName() {
		return name;
	}

	public UserVO setName(String name) {
		this.name = name;
		return this;
	}

	public String getMobile() {
		return mobile;
	}

	public UserVO setMobile(String mobile) {
		this.mobile = mobile;
		return this;
	}

	public String getLicense() {
		return license;
	}

	public UserVO setLicense(String license) {
		this.license = license;
		return this;
	}

	public String getType() {
		return type;
	}

	public UserVO setType(String type) {
		this.type = type;
		return this;
	}

	public String getQrCode() {
		return qrCode;
	}

	public UserVO setQrCode(String qrCode) {
		this.qrCode = qrCode;
		return this;
	}

	@Override
	public UserVO set(UserPO entity) throws Exception {
		this.setId(entity.getId())
			.setName(entity.getName())
			.setMobile(entity.getMobile())
			.setLicense(new StringBufferWrapper().append(entity.getRegion()==null?"":entity.getRegion()).append("·").append(entity.getLicense()).toString())
			.setType(entity.getType());
		return this;
	}

}
