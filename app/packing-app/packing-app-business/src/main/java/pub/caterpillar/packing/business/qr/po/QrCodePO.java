package pub.caterpillar.packing.business.qr.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;
/**
 * 绑定id与二维码信息
 * @author 吕德阳
 */
@Entity
@Table(name = "t_app_qr_code")
public class QrCodePO extends AbstractBasePO{
	
	private static final long serialVersionUID = 1L;

	//绑定id
	private String identification;
	
	//二维码路径
	private String path;
	
	//二维码内容
	private String content;
	
	//二维码管理员
	private Long adminId;

	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "admin_id")
	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}
	
}
