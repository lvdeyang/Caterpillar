package pub.caterpillar.app.carpool.vo;

import com.alibaba.fastjson.JSON;

import pub.caterpillar.app.carpool.po.SeatInfoPO;
import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class SeatInfoVO extends AbstractBaseVO<SeatInfoVO, SeatInfoPO>{

	private int serialNum;
	
	private String status;
	
	private String priceType;
	
	private float price;
	
	private float addMoney;
	
	private String orderUuid;
	
	private String mobile;
	
	private String travelTime;
	
	private int passengerNum;
	
	private String remark;
	
	public int getSerialNum() {
		return serialNum;
	}

	public SeatInfoVO setSerialNum(int serialNum) {
		this.serialNum = serialNum;
		return this;
	}

	public String getStatus() {
		return status;
	}

	public SeatInfoVO setStatus(String status) {
		this.status = status;
		return this;
	}

	public String getPriceType() {
		return priceType;
	}

	public SeatInfoVO setPriceType(String priceType) {
		this.priceType = priceType;
		return this;
	}

	public float getPrice() {
		return price;
	}

	public SeatInfoVO setPrice(float price) {
		this.price = price;
		return this;
	}

	public float getAddMoney() {
		return addMoney;
	}

	public SeatInfoVO setAddMoney(float addMoney) {
		this.addMoney = addMoney;
		return this;
	}

	public String getOrderUuid() {
		return orderUuid;
	}

	public SeatInfoVO setOrderUuid(String orderUuid) {
		this.orderUuid = orderUuid;
		return this;
	}

	public String getMobile() {
		return mobile;
	}

	public SeatInfoVO setMobile(String mobile) {
		this.mobile = mobile;
		return this;
	}

	public String getTravelTime() {
		return travelTime;
	}

	public SeatInfoVO setTravelTime(String travelTime) {
		this.travelTime = travelTime;
		return this;
	}
	
	public int getPassengerNum() {
		return passengerNum;
	}

	public SeatInfoVO setPassengerNum(int passengerNum) {
		this.passengerNum = passengerNum;
		return this;
	}

	public String getRemark() {
		return remark;
	}

	public SeatInfoVO setRemark(String remark) {
		this.remark = remark;
		return this;
	}

	@Override
	public SeatInfoVO set(SeatInfoPO entity) throws Exception {
		this.setId(entity.getId())
			.setUuid(entity.getUuid())
			.setUpdateTime(entity.getUpdateTime()==null?"":DateUtil.format(entity.getUpdateTime(), DateUtil.shortDateTimePatten))
			.setSerialNum(entity.getSerialNum())
			.setStatus(entity.getStatus().toString())
			.setPriceType(entity.getPriceType())
			.setPrice(entity.getPrice())
			.setAddMoney(entity.getAddMoney())
			.setOrderUuid(entity.getOrderUuid()==null?"":entity.getOrderUuid())
			.setMobile(entity.getMobile()==null?"":entity.getMobile())
			.setTravelTime(entity.getTravelTime()==null?"":DateUtil.format(entity.getTravelTime(), DateUtil.shortDateTimePatten))
			.setPassengerNum(entity.getPassengerNum())
			.setRemark(entity.getRemark()==null?"":entity.getRemark());
		return this;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	
}
