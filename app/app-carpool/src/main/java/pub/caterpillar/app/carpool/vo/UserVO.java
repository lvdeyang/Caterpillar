package pub.caterpillar.app.carpool.vo;



import pub.caterpillar.app.carpool.po.UserPO;
import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class UserVO extends AbstractBaseVO<UserVO, UserPO>{

	private String openId;
	
	private String mobile;
	
	private String name;
	
	private String headImgUrl;
	
	public String getOpenId() {
		return openId;
	}

	public UserVO setOpenId(String openId) {
		this.openId = openId;
		return this;
	}

	public String getMobile() {
		return mobile;
	}

	public UserVO setMobile(String mobile) {
		this.mobile = mobile;
		return this;
	}

	public String getName() {
		return name;
	}

	public UserVO setName(String name) {
		this.name = name;
		return this;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public UserVO setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
		return this;
	}

	@Override
	public UserVO set(UserPO entity) throws Exception {
		this.setId(entity.getId())
			.setUuid(entity.getUuid())
			.setUpdateTime(entity.getUpdateTime()==null?"":DateUtil.format(entity.getUpdateTime(), DateUtil.dateTimePattern))
			.setOpenId(entity.getOpenId())
			.setHeadImgUrl(entity.getHeadImgUrl())
			.setName(entity.getName())
			.setMobile(entity.getMobile());
		return this;
	}
	
}
