package pub.caterpillar.app.carpool.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;

import pub.caterpillar.app.carpool.enumeration.OrderBalanceStatus;
import pub.caterpillar.app.carpool.enumeration.OrderPayStatus;
import pub.caterpillar.app.carpool.enumeration.OrderPriceType;
import pub.caterpillar.app.carpool.enumeration.OrderTakingStatus;
import pub.caterpillar.app.carpool.po.metadata.Metadata;
import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = Metadata.PREFIX + "ORDER")
public class OrderPO extends AbstractBasePO{

	private static final long serialVersionUID = 1L;

	/** 始发站id */
	private Long departureId;
	
	/** 始发站 */
	private String departureName;
	
	/** 终点站id */
	private Long destinationId;
	
	/** 终点站 */
	private String destinationName;
	
	/** 车队id */
	private Long fleetId;
	
	/** 车队名称 */
	private String fleetName;
	
	/** 路线id */
	private Long routeId;
	
	/** 路线uuid */
	private String routeUuid;
	
	/** 出行时间 */
	private Date travelTime;
	
	/** 出行人数 */
	private int passengerNum;
	
	/** 拼车|包车*/
	private OrderPriceType priceType;
	
	/** 商品总价*/
	private float price;
	
	/** 应付总价 */
	private float payPrice;
	
	/** 加钱 */
	private float addMoney;
	
	/** 备注 */
	private String remark;
	
	/** 订单创建时间 */
	private Date createTime;
	
	/** 乘客手机号 */
	private String mobile;
	
	private String driverMobile;
	
	/** 订单接单状态 */
	private OrderTakingStatus takingStatus;
	
	/** 订单支付状态 */
	private OrderPayStatus payStatus;
	
	/*订单结算状态*/
	private OrderBalanceStatus balanceStatus;
	
	/** 绑定用户 */
	private Long userId;
	
	/** 微信单号 */
	private String prepayId;

	@Column(name = "DEPARTURE_ID")
	public Long getDepartureId() {
		return departureId;
	}

	public void setDepartureId(Long departureId) {
		this.departureId = departureId;
	}

	@Column(name = "DEPARTURE_NAME")
	public String getDepartureName() {
		return departureName;
	}

	public void setDepartureName(String departureName) {
		this.departureName = departureName;
	}

	@Column(name = "DESTINATION_ID")
	public Long getDestinationId() {
		return destinationId;
	}

	public void setDestinationId(Long destinationId) {
		this.destinationId = destinationId;
	}

	@Column(name = "DESTINATION_NAME")
	public String getDestinationName() {
		return destinationName;
	}

	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}

	@Column(name = "FLEET_ID")
	public Long getFleetId() {
		return fleetId;
	}

	public void setFleetId(Long fleetId) {
		this.fleetId = fleetId;
	}

	@Column(name = "FLEET_NAME")
	public String getFleetName() {
		return fleetName;
	}

	public void setFleetName(String fleetName) {
		this.fleetName = fleetName;
	}

	@Column(name = "ROUTE_ID")
	public Long getRouteId() {
		return routeId;
	}

	public void setRouteId(Long routeId) {
		this.routeId = routeId;
	}

	@Column(name = "ROUTE_UUID")
	public String getRouteUuid() {
		return routeUuid;
	}

	public void setRouteUuid(String routeUuid) {
		this.routeUuid = routeUuid;
	}

	@Column(name = "TRAVEL_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getTravelTime() {
		return travelTime;
	}

	public void setTravelTime(Date travelTime) {
		this.travelTime = travelTime;
	}

	@Column(name = "PASSENGER_NUM")
	public int getPassengerNum() {
		return passengerNum;
	}

	public void setPassengerNum(int passengerNum) {
		this.passengerNum = passengerNum;
	}

	@Column(name = "PRICE_TYPE")
	@Enumerated(EnumType.STRING)
	public OrderPriceType getPriceType() {
		return priceType;
	}

	public void setPriceType(OrderPriceType priceType) {
		this.priceType = priceType;
	}

	@Column(name = "PRICE")
	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
	@Column(name = "PAY_PRICE")
	public float getPayPrice() {
		return payPrice;
	}

	public void setPayPrice(float payPrice) {
		this.payPrice = payPrice;
	}

	@Column(name = "ADD_MONEY")
	public float getAddMoney() {
		return addMoney;
	}

	public void setAddMoney(float addMoney) {
		this.addMoney = addMoney;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "CREATE_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Column(name = "MOBILE")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "TAKING_STATUS")
	@Enumerated(EnumType.STRING)
	public OrderTakingStatus getTakingStatus() {
		return takingStatus;
	}

	public void setTakingStatus(OrderTakingStatus takingStatus) {
		this.takingStatus = takingStatus;
	}

	@Column(name = "PAY_STATUS")
	@Enumerated(EnumType.STRING)
	public OrderPayStatus getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(OrderPayStatus payStatus) {
		this.payStatus = payStatus;
	}

	@Column(name = "USER_ID")
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "PREPAY_ID")
	public String getPrepayId() {
		return prepayId;
	}

	public void setPrepayId(String prepayId) {
		this.prepayId = prepayId;
	}
	@Column(name = "BALANCE_STATUS")
	@Enumerated(EnumType.STRING)
	public OrderBalanceStatus getBalanceStatus() {
		return balanceStatus;
	}

	public void setBalanceStatus(OrderBalanceStatus balanceStatus) {
		this.balanceStatus = balanceStatus;
	}
	@Column(name = "DRIVER_MOBILE")
	public String getDriverMobile() {
		return driverMobile;
	}

	public void setDriverMobile(String driverMobile) {
		this.driverMobile = driverMobile;
	}
	
}
