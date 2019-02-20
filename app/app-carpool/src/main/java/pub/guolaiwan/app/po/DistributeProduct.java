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
@Table(name = "distribute_product")
public class DistributeProduct extends AbstractBasePO {

	private long productId;
	private long distributorId;
	private DistributorType distributorType;
	private int left;
	private int minAmount;
	private double price;//分
	private Set<DistributePolicy> distributePolicies;
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
	public int getMinAmount() {
		return minAmount;
	}
	public void setMinAmount(int minAmount) {
		this.minAmount = minAmount;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@OneToMany(mappedBy = "distributeProduct", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false)
	public Set<DistributePolicy> getDistributePolicies() {
		return distributePolicies;
	}
	public void setDistributePolicies(Set<DistributePolicy> distributePolicies) {
		this.distributePolicies = distributePolicies;
	}
	 
}
