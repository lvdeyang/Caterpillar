package pub.caterpillar.packing.business.qr.dto;

public class QrCodeDTO {

	private Long id;
	
	private String identification;
	
	private String registed;

	public Long getId() {
		return id;
	}

	public QrCodeDTO setId(Long id) {
		this.id = id;
		return this;
	}

	public String getIdentification() {
		return identification;
	}

	public QrCodeDTO setIdentification(String identification) {
		this.identification = identification;
		return this;
	}

	public String getRegisted() {
		return registed;
	}

	public QrCodeDTO setRegisted(String registed) {
		this.registed = registed;
		return this;
	}
	
}
