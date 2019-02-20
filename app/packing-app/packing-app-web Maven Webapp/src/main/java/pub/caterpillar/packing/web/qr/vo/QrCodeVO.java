package pub.caterpillar.packing.web.qr.vo;

import pub.caterpillar.mvc.converter.AbstractBaseVO;
import pub.caterpillar.packing.business.qr.dto.QrCodeDTO;
import pub.caterpillar.packing.business.qr.po.QrCodePO;

public class QrCodeVO extends AbstractBaseVO<QrCodeVO, QrCodeDTO>{

	private Long id;
	
	private String identification;
	
	private String registed;
	
	private String path;
	
	private String content;
	
	public Long getId() {
		return id;
	}

	public QrCodeVO setId(Long id) {
		this.id = id;
		return this;
	}

	public String getIdentification() {
		return identification;
	}

	public QrCodeVO setIdentification(String identification) {
		this.identification = identification;
		return this;
	}

	public String getRegisted() {
		return registed;
	}

	public QrCodeVO setRegisted(String registed) {
		this.registed = registed;
		return this;
	}

	public String getPath() {
		return path;
	}

	public QrCodeVO setPath(String path) {
		this.path = path;
		return this;
	}

	public String getContent() {
		return content;
	}

	public QrCodeVO setContent(String content) {
		this.content = content;
		return this;
	}

	@Override
	public QrCodeVO set(QrCodeDTO entity) throws Exception {
		this.setId(entity.getId())
			.setIdentification(entity.getIdentification())
			.setRegisted(entity.getRegisted());
		return this;
	}
	
	public QrCodeVO set(QrCodePO entity) {
		this.setId(entity.getId())
			.setIdentification(entity.getIdentification())
			.setPath(entity.getPath())
			.setContent(entity.getContent());
		return this;
	}
	
}
