package pub.caterpillar.app.carpool.dto;

import java.util.Date;

import pub.caterpillar.app.carpool.po.CarPO;
import pub.caterpillar.app.carpool.po.DriverPO;
import pub.caterpillar.app.carpool.po.FleetPO;
import pub.caterpillar.app.carpool.po.RoutePO;
import pub.caterpillar.app.carpool.po.SitePO;

public class DriverDTO {

	private Long id;
	
	private String uuid;
	
	private Date updateTime;
	
	private String name;
	
	private String username;
	
	private String mobile;
	
	private Long fleetId;
	
	private String fleetName;
	
	private Long carId;
	
	private String carType;
	
	private Long routeId;
	
	private String routeDeparture;
	
	private String routeDestination;
	
	private Long revertId;
	
	private String revertDeparture;
	
	private String revertDestination;
	
	private Date maxTravelTime;
	
	private Date minTravelTime;

	public Long getId() {
		return id;
	}

	public DriverDTO setId(Long id) {
		this.id = id;
		return this;
	}

	public String getUuid() {
		return uuid;
	}

	public DriverDTO setUuid(String uuid) {
		this.uuid = uuid;
		return this;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public DriverDTO setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public String getName() {
		return name;
	}

	public DriverDTO setName(String name) {
		this.name = name;
		return this;
	}

	public String getUsername() {
		return username;
	}

	public DriverDTO setUsername(String username) {
		this.username = username;
		return this;
	}

	public String getMobile() {
		return mobile;
	}

	public DriverDTO setMobile(String mobile) {
		this.mobile = mobile;
		return this;
	}

	public Long getFleetId() {
		return fleetId;
	}

	public DriverDTO setFleetId(Long fleetId) {
		this.fleetId = fleetId;
		return this;
	}

	public String getFleetName() {
		return fleetName;
	}

	public DriverDTO setFleetName(String fleetName) {
		this.fleetName = fleetName;
		return this;
	}

	public Long getCarId() {
		return carId;
	}

	public DriverDTO setCarId(Long carId) {
		this.carId = carId;
		return this;
	}

	public String getCarType() {
		return carType;
	}

	public DriverDTO setCarType(String carType) {
		this.carType = carType;
		return this;
	}

	public Long getRouteId() {
		return routeId;
	}

	public DriverDTO setRouteId(Long routeId) {
		this.routeId = routeId;
		return this;
	}

	public String getRouteDeparture() {
		return routeDeparture;
	}

	public DriverDTO setRouteDeparture(String routeDeparture) {
		this.routeDeparture = routeDeparture;
		return this;
	}

	public String getRouteDestination() {
		return routeDestination;
	}

	public DriverDTO setRouteDestination(String routeDestination) {
		this.routeDestination = routeDestination;
		return this;
	}
	
	public Long getRevertId() {
		return revertId;
	}

	public DriverDTO setRevertId(Long revertId) {
		this.revertId = revertId;
		return this;
	}

	public String getRevertDeparture() {
		return revertDeparture;
	}

	public DriverDTO setRevertDeparture(String revertDeparture) {
		this.revertDeparture = revertDeparture;
		return this;
	}

	public String getRevertDestination() {
		return revertDestination;
	}

	public DriverDTO setRevertDestination(String revertDestination) {
		this.revertDestination = revertDestination;
		return this;
	}

	public Date getMaxTravelTime() {
		return maxTravelTime;
	}

	public DriverDTO setMaxTravelTime(Date maxTravelTime) {
		this.maxTravelTime = maxTravelTime;
		return this;
	}

	public Date getMinTravelTime() {
		return minTravelTime;
	}

	public DriverDTO setMinTravelTime(Date minTravelTime) {
		this.minTravelTime = minTravelTime;
		return this;
	}

	public DriverDTO set(
			DriverPO driver, 
			FleetPO fleet, 
			CarPO car, 
			RoutePO route, 
			SitePO routeDeparture, 
			SitePO routeDestination,
			RoutePO revert,
			SitePO revertDeparture,
			SitePO revertDestination){
		
		this.setId(driver.getId())
			.setUuid(driver.getUuid())
			.setUpdateTime(driver.getUpdateTime())
			.setName(driver.getName())
			.setMobile(driver.getMobile());
		
		if(fleet != null){
			this.setFleetId(fleet.getId())
			    .setFleetName(fleet.getName());
		}
		
		if(car != null){
			this.setCarId(car.getId())
				.setCarType(car.getType());
		}
		
		if(route != null){
			this.setRouteId(route.getId())
			    .setRouteDeparture(routeDeparture==null?null:routeDeparture.getName())
			    .setRouteDestination(routeDestination==null?null:routeDestination.getName());
		}
		
		if(revert != null){
			this.setRevertId(revert.getId())
			    .setRevertDeparture(revertDeparture==null?null:revertDeparture.getName())
			    .setRevertDestination(revertDestination==null?null:revertDestination.getName());
		}
		
		return this;
	}
	
}
