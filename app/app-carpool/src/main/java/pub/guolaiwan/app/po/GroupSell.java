package pub.guolaiwan.app.po;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "group_sell")
public class GroupSell extends AbstractBasePO {
	private int count;
	private double price;
	private RetailPolicy retailPolicy;
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
	@JoinColumn(name = "retailId")
	public RetailPolicy getRetailPolicy() {
		return retailPolicy;
	}
	public void setRetailPolicy(RetailPolicy retailPolicy) {
		this.retailPolicy = retailPolicy;
	}
	

}
