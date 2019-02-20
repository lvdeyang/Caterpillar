package pub.caterpillar.app.carpool.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import pub.caterpillar.app.carpool.po.metadata.Metadata;
import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = Metadata.PREFIX + "USER")
public class UserPO extends AbstractBasePO{

	private static final long serialVersionUID = 1L;

	private String openId;
	
	private String mobile;
	
	private String name;
	
	private String headImgUrl;

	@Column(name = "OPEN_ID")
	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	@Column(name = "MOBILE")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "HEAD_IMG_URL")
	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}
	
}
