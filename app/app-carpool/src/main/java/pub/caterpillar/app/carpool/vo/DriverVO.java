package pub.caterpillar.app.carpool.vo;

import com.sun.tools.doclets.internal.toolkit.taglets.ReturnTaglet;

import pub.caterpillar.app.carpool.dto.DriverDTO;
import pub.caterpillar.app.carpool.po.DriverPO;
import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;
import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class DriverVO extends AbstractBaseVO<DriverVO, DriverDTO>{

	private String name;
	
	private String username;
	
	private String password;
	
	private String mobile;
	
	private Long fleetId;
	
	/** 车队名称 */
	private String fleetName;
	
	private Long carId;
	
	/** 车辆类型 */
	private String carType;
	
	private Long routeId;
	
	/** 路线：始发站-终点站 */
	private String route;
	
	private Long revertId;
	
	/** 路线：始发站-终点站 */
	private String revert;
	
	/** 司机接单出行时间范围 */
	private String travelTimeRange;
	
	public String getName() {
		return name;
	}

	public DriverVO setName(String name) {
		this.name = name;
		return this;
	}

	public String getUsername() {
		return username;
	}

	public DriverVO setUsername(String username) {
		this.username = username;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public DriverVO setPassword(String password) {
		this.password = password;
		return this;
	}

	public String getMobile() {
		return mobile;
	}

	public DriverVO setMobile(String mobile) {
		this.mobile = mobile;
		return this;
	}

	public Long getFleetId() {
		return fleetId;
	}

	public DriverVO setFleetId(Long fleetId) {
		this.fleetId = fleetId;
		return this;
	}

	public String getFleetName() {
		return fleetName;
	}

	public DriverVO setFleetName(String fleetName) {
		this.fleetName = fleetName;
		return this;
	}

	public Long getCarId() {
		return carId;
	}

	public DriverVO setCarId(Long carId) {
		this.carId = carId;
		return this;
	}

	public String getCarType() {
		return carType;
	}

	public DriverVO setCarType(String carType) {
		this.carType = carType;
		return this;
	}

	public Long getRouteId() {
		return routeId;
	}

	public DriverVO setRouteId(Long routeId) {
		this.routeId = routeId;
		return this;
	}

	public String getRoute() {
		return route;
	}

	public DriverVO setRoute(String route) {
		this.route = route;
		return this;
	}

	public Long getRevertId() {
		return revertId;
	}

	public DriverVO setRevertId(Long revertId) {
		this.revertId = revertId;
		return this;
	}

	public String getRevert() {
		return revert;
	}

	public DriverVO setRevert(String revert) {
		this.revert = revert;
		return this;
	}
	
	public String getTravelTimeRange() {
		return travelTimeRange;
	}

	public DriverVO setTravelTimeRange(String travelTimeRange) {
		this.travelTimeRange = travelTimeRange;
		return this;
	}

	@Override
	public DriverVO set(DriverDTO entity) throws Exception {
		if(entity.getId() == null) return null;
		this.setId(entity.getId())
		    .setUuid(entity.getUuid())
		    .setUpdateTime(DateUtil.format(entity.getUpdateTime(), DateUtil.dateTimePattern))
		    .setName(entity.getName()==null?"":entity.getName())
		    .setUsername(entity.getUsername()==null?"":entity.getUsername())
		    .setMobile(entity.getMobile()==null?"":entity.getMobile())
		    .setFleetId(entity.getFleetId()==null?-1:entity.getFleetId())
		    .setFleetName(entity.getFleetName()==null?"":entity.getFleetName())
		    .setCarId(entity.getCarId()==null?-1:entity.getCarId())
		    .setCarType(entity.getCarType()==null?"":entity.getCarType())
		    .setRouteId(entity.getRouteId()==null?-1:entity.getRouteId())
		    .setRoute(new StringBufferWrapper().append(entity.getRouteDeparture()==null?"":entity.getRouteDeparture())
		    								   .append(" - ")
		    								   .append(entity.getRouteDestination()==null?"":entity.getRouteDestination())
		    								   .toString())
		    .setRevertId(entity.getRevertId()==null?-1:entity.getRevertId())
		    .setRevert(new StringBufferWrapper().append(entity.getRevertDeparture()==null?"":entity.getRevertDeparture())
											   .append(" - ")
											   .append(entity.getRevertDestination()==null?"":entity.getRevertDestination())
											   .toString())
		    .setTravelTimeRange(new StringBufferWrapper().append(entity.getMinTravelTime()==null?"":DateUtil.format(entity.getMinTravelTime(), DateUtil.shortDateTimePatten))
		    											 .append(" - ")
		    											 .append(entity.getMaxTravelTime()==null?"":DateUtil.format(entity.getMaxTravelTime(), DateUtil.shortDateTimePatten))
		    											 .toString());
		return this;
	}
	
}
