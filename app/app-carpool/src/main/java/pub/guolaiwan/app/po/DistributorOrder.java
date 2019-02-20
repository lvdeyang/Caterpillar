package pub.guolaiwan.app.po;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.alibaba.druid.support.logging.Log;

import pub.caterpillar.orm.po.AbstractBasePO;
import pub.guolaiwan.app.classify.DistributorType;

@Entity
@Table(name = "distributor_Order")
public class DistributorOrder extends AbstractBasePO {

	private long productId;
	private long distributorId;
	private long parentId;
	private DistributorType type;
	private int amount;
	private double price;
	private boolean online;
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
	public long getParentId() {
		return parentId;
	}
	public void setParentId(long parentId) {
		this.parentId = parentId;
	}
	@Enumerated(EnumType.STRING)
	public DistributorType getType() {
		return type;
	}
	public void setType(DistributorType type) {
		this.type = type;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public boolean isOnline() {
		return online;
	}
	public void setOnline(boolean online) {
		this.online = online;
	}
	
}
