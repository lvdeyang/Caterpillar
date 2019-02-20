package pub.guolaiwan.app.po;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "distribute_policy")
public class DistributePolicy extends AbstractBasePO {

	private int count;
	private double price;
	private DistributeProduct distributeProduct;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@ManyToOne
	@JoinColumn(name = "distributeProductId")
	public DistributeProduct getDistributeProduct() {
		return distributeProduct;
	}
	public void setDistributeProduct(DistributeProduct distributeProduct) {
		this.distributeProduct = distributeProduct;
	}
	
}
