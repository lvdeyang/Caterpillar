package pub.caterpillar.app.carpool.po;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import pub.caterpillar.app.carpool.enumeration.CarStatus;
import pub.caterpillar.app.carpool.po.metadata.Metadata;
import pub.caterpillar.orm.po.AbstractBasePO;

/**
 * 车辆信息<br/>
 * <p>车辆信息</p>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2018年4月24日 下午6:39:28
 */
@Entity
@Table(name = Metadata.PREFIX + "CAR")
public class CarPO extends AbstractBasePO{

	private static final long serialVersionUID = 1L;
	
	/** 车辆型号 */
	private String type;
	
	/** 车牌号 */
	private String plateNumber;
	
	/** 车辆座位数 */
	private int seats;
	
	/** 车辆状态 */
	private CarStatus status;
	
	/** 隶属司机 */
	private DriverPO driver;
	
	/** 座位信息 */
	private Set<SeatInfoPO> seatInfos;

	/** 车辆型号 */
	@Column(name = "TYPE")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/** 车牌号 */
	@Column(name = "PLATE_NUMBER")
	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	/** 车辆座位数 */
	@Column(name = "SEATS")
	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	/** 车辆状态 */
	@Column(name = "STATUS")
	@Enumerated(EnumType.STRING)
	public CarStatus getStatus() {
		return status;
	}

	public void setStatus(CarStatus status) {
		this.status = status;
	}

	/** 隶属司机 */
	@OneToOne
	@JoinColumn(name = "DRIVER_ID")
	public DriverPO getDriver() {
		return driver;
	}

	public void setDriver(DriverPO driver) {
		this.driver = driver;
	}

	/** 座位信息 */
	@OneToMany(mappedBy = "car", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	public Set<SeatInfoPO> getSeatInfos() {
		return seatInfos;
	}

	public void setSeatInfos(Set<SeatInfoPO> seatInfos) {
		this.seatInfos = seatInfos;
	}
	
}
