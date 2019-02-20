package pub.caterpillar.app.carpool.vo;

import pub.caterpillar.app.carpool.po.OrderPO;
import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class OrderVO extends AbstractBaseVO<OrderVO, OrderPO>{

	private Long departureId;
	
	private String departureName;
	
	private Long destinationId;
	
	private String destinationName;
	
	private Long fleetId;
	
	private String fleetName;
	
	private Long routeId;
	
	private String routeUuid;
	
	private String travelTime;
	
	private int passengerNum;
	
	private String priceType;
	
	private float price;
	
	private float addMoney;
	
	private String remark;
	
	private String createTime;
	
	private String mobile;
	
	private String takingStatus;
	
	private String payStatus;
	
	public Long getDepartureId() {
		return departureId;
	}

	public OrderVO setDepartureId(Long departureId) {
		this.departureId = departureId;
		return this;
	}

	public String getDepartureName() {
		return departureName;
	}

	public OrderVO setDepartureName(String departureName) {
		this.departureName = departureName;
		return this;
	}

	public Long getDestinationId() {
		return destinationId;
	}

	public OrderVO setDestinationId(Long destinationId) {
		this.destinationId = destinationId;
		return this;
	}

	public String getDestinationName() {
		return destinationName;
	}

	public OrderVO setDestinationName(String destinationName) {
		this.destinationName = destinationName;
		return this;
	}

	public Long getFleetId() {
		return fleetId;
	}

	public OrderVO setFleetId(Long fleetId) {
		this.fleetId = fleetId;
		return this;
	}

	public String getFleetName() {
		return fleetName;
	}

	public OrderVO setFleetName(String fleetName) {
		this.fleetName = fleetName;
		return this;
	}

	public Long getRouteId() {
		return routeId;
	}

	public OrderVO setRouteId(Long routeId) {
		this.routeId = routeId;
		return this;
	}

	public String getRouteUuid() {
		return routeUuid;
	}

	public OrderVO setRouteUuid(String routeUuid) {
		this.routeUuid = routeUuid;
		return this;
	}

	public String getTravelTime() {
		return travelTime;
	}

	public OrderVO setTravelTime(String travelTime) {
		this.travelTime = travelTime;
		return this;
	}

	public int getPassengerNum() {
		return passengerNum;
	}

	public OrderVO setPassengerNum(int passengerNum) {
		this.passengerNum = passengerNum;
		return this;
	}

	public String getPriceType() {
		return priceType;
	}

	public OrderVO setPriceType(String priceType) {
		this.priceType = priceType;
		return this;
	}

	public float getPrice() {
		return price;
	}

	public OrderVO setPrice(float price) {
		this.price = price;
		return this;
	}

	public float getAddMoney() {
		return addMoney;
	}

	public OrderVO setAddMoney(float addMoney) {
		this.addMoney = addMoney;
		return this;
	}

	public String getRemark() {
		return remark;
	}

	public OrderVO setRemark(String remark) {
		this.remark = remark;
		return this;
	}

	public String getCreateTime() {
		return createTime;
	}

	public OrderVO setCreateTime(String createTime) {
		this.createTime = createTime;
		return this;
	}
	
	public String getMobile() {
		return mobile;
	}

	public OrderVO setMobile(String mobile) {
		this.mobile = mobile;
		return this;
	}

	public String getTakingStatus() {
		return takingStatus;
	}

	public OrderVO setTakingStatus(String takingStatus) {
		this.takingStatus = takingStatus;
		return this;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public OrderVO setPayStatus(String payStatus) {
		this.payStatus = payStatus;
		return this;
	}

	@Override
	public OrderVO set(OrderPO entity) throws Exception {
		this.setId(entity.getId())
			.setUuid(entity.getUuid())
			.setUpdateTime(entity.getUpdateTime()==null?"":DateUtil.format(entity.getUpdateTime(), DateUtil.shortDateTimePatten))
			.setDepartureId(entity.getDepartureId())
			.setDepartureName(entity.getDepartureName())
			.setDestinationId(entity.getDestinationId())
			.setDestinationName(entity.getDestinationName())
			.setFleetId(entity.getFleetId())
			.setFleetName(entity.getFleetName())
			.setRouteId(entity.getRouteId())
			.setRouteUuid(entity.getRouteUuid())
			.setTravelTime(entity.getTravelTime()==null?"":DateUtil.format(entity.getTravelTime(), DateUtil.shortDateTimePatten))
			.setPassengerNum(entity.getPassengerNum())
			.setPriceType(entity.getPriceType()==null?"":entity.getPriceType().getName())
			.setPrice(entity.getPrice())
			.setAddMoney(entity.getAddMoney())
			.setRemark(entity.getRemark())
			.setCreateTime(entity.getCreateTime()==null?"":DateUtil.format(entity.getCreateTime(), DateUtil.shortDateTimePatten))
			.setMobile(entity.getMobile()==null?"":entity.getMobile())
			.setTakingStatus(entity.getTakingStatus().getName())
			.setPayStatus(entity.getPayStatus().getName());
		return this;
	}
	
}
