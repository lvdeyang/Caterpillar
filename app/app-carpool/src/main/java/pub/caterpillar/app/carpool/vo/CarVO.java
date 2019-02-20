package pub.caterpillar.app.carpool.vo;

import pub.caterpillar.app.carpool.po.CarPO;
import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class CarVO extends AbstractBaseVO<CarVO, CarPO>{

	private String type;
	
	private String plateNumber;
	
	private int seats;
	
	public String getType() {
		return type;
	}

	public CarVO setType(String type) {
		this.type = type;
		return this;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public CarVO setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
		return this;
	}

	public int getSeats() {
		return seats;
	}

	public CarVO setSeats(int seats) {
		this.seats = seats;
		return this;
	}
	
	@Override
	public CarVO set(CarPO entity) throws Exception {
		this.setId(entity.getId())
		    .setUuid(entity.getUuid())
		    .setUpdateTime(DateUtil.format(entity.getUpdateTime(), DateUtil.dateTimePattern))
		    .setType(entity.getType()==null?"":entity.getType())
		    .setPlateNumber(entity.getPlateNumber()==null?"":entity.getPlateNumber())
		    .setSeats(entity.getSeats());
		return this;
	}
	
}
