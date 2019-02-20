package pub.caterpillar.app.carpool.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import pub.caterpillar.app.carpool.enumeration.SeatStatus;
import pub.caterpillar.app.carpool.po.metadata.Metadata;
import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = Metadata.PREFIX + "SEAT_INFO")
public class SeatInfoPO extends AbstractBasePO{

	private static final long serialVersionUID = 1L;

	/** 座位序号 */
	private int serialNum;
	
	/** 座位状态 */
	private SeatStatus status;
	
	/** 拼车|包车*/
	private String priceType;
	
	/** 总价 */
	private float price;
	
	/** 加钱 */
	private float addMoney;
	
	/** 系统订单uuid */
	private String orderUuid;
	
	/** 订单联系人电话 */
	private String mobile;
	
	/** 出行时间 */
	private Date travelTime;
	
	/** 人数 */
	private int passengerNum;
	
	/** 备注 */
	private String remark;
	
	/** 隶属车辆 */
	private CarPO car;

	/** 座位序号 */
	@Column(name = "SERIALNUM")
	public int getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(int serialNum) {
		this.serialNum = serialNum;
	}

	/** 座位状态 */
	@Column(name = "STATUS")
	@Enumerated(EnumType.STRING)
	public SeatStatus getStatus() {
		return status;
	}

	public void setStatus(SeatStatus status) {
		this.status = status;
	}
	
	/** 拼车|包车*/
	@Column(name = "PRICE_TYPE")
	public String getPriceType() {
		return priceType;
	}

	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}
	
	/** 总价 */
	@Column(name = "PRICE")
	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	/** 加钱 */
	@Column(name = "ADD_MONEY")
	public float getAddMoney() {
		return addMoney;
	}

	public void setAddMoney(float addMoney) {
		this.addMoney = addMoney;
	}

	/** 系统订单uuid */
	@Column(name = "ORDER_UUID")
	public String getOrderUuid() {
		return orderUuid;
	}

	public void setOrderUuid(String orderUuid) {
		this.orderUuid = orderUuid;
	}

	/** 订单联系人电话 */
	@Column(name = "MOBILE")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/** 出行时间 */
	@Column(name = "TRAVEL_TIME")
	public Date getTravelTime() {
		return travelTime;
	}

	public void setTravelTime(Date travelTime) {
		this.travelTime = travelTime;
	}
	
	/** 人数 */
	@Column(name = "PASSENGER_NUM")
	public int getPassengerNum() {
		return passengerNum;
	}

	public void setPassengerNum(int passengerNum) {
		this.passengerNum = passengerNum;
	}

	/** 备注 */
	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/** 隶属车辆 */
	@ManyToOne
	@JoinColumn(name = "CAR_ID")
	public CarPO getCar() {
		return car;
	}

	public void setCar(CarPO car) {
		this.car = car;
	}
	
}
