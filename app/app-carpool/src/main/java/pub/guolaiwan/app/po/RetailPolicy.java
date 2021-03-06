package pub.guolaiwan.app.po;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;
import pub.guolaiwan.app.classify.DistributorType;

@Entity
@Table(name = "retail_policy")
public class RetailPolicy extends AbstractBasePO {

	private long productId;
	private long distributorId;
	private DistributorType distributorType;
	private double price;
	private long regionId;
	private Set<GroupSell> groupSells;
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public long getDistributorId() {
		return distributorId;
	}
	public void setDistributorId(long distributorId) {
		this.distributorId = distributorId;
	}
	@Enumerated(EnumType.STRING)
	public DistributorType getDistributorType() {
		return distributorType;
	}
	public void setDistributorType(DistributorType distributorType) {
		this.distributorType = distributorType;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public long getRegionId() {
		return regionId;
	}
	public void setRegionId(long regionId) {
		this.regionId = regionId;
	}
	
	
	@OneToMany(mappedBy = "retailPolicy", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false)
	public Set<GroupSell> getGroupSells() {
		return groupSells;
	}
	public void setGroupSells(Set<GroupSell> groupSells) {
		this.groupSells = groupSells;
	}

	
}
