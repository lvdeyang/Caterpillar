package pub.guolaiwan.app.po;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import pub.caterpillar.app.carpool.enumeration.CarStatus;
import pub.caterpillar.app.carpool.po.DriverPO;
import pub.caterpillar.app.carpool.po.SeatInfoPO;
import pub.caterpillar.app.carpool.po.metadata.Metadata;
import pub.caterpillar.orm.po.AbstractBasePO;
import pub.guolaiwan.app.classify.DistributorType;

@Entity
@Table(name = "distributor")
public class DistributorPo extends AbstractBasePO{

	private String legalPerson;
	private String bankNo;
	private String address;
	private String phone;
	private String licenseUrl;
	private String contractUrl;
	private String contractVideo;
	private DistributorType type;
	public String getLegalPerson() {
		return legalPerson;
	}
	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}
	public String getBankNo() {
		return bankNo;
	}
	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getLicenseUrl() {
		return licenseUrl;
	}
	public void setLicenseUrl(String licenseUrl) {
		this.licenseUrl = licenseUrl;
	}
	public String getContractUrl() {
		return contractUrl;
	}
	public void setContractUrl(String contractUrl) {
		this.contractUrl = contractUrl;
	}
	public String getContractVideo() {
		return contractVideo;
	}
	public void setContractVideo(String contractVideo) {
		this.contractVideo = contractVideo;
	}
	@Enumerated(EnumType.STRING)
	public DistributorType getType() {
		return type;
	}
	public void setType(DistributorType type) {
		this.type = type;
	}
	
	
}

