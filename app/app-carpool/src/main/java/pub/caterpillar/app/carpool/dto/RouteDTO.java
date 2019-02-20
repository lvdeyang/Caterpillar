package pub.caterpillar.app.carpool.dto;

import java.util.Date;
import pub.caterpillar.app.carpool.po.FleetPO;
import pub.caterpillar.app.carpool.po.RoutePO;
import pub.caterpillar.app.carpool.po.SitePO;

public class RouteDTO {

	private Long id;
	
	private String uuid;
	
	private Date updateTime;
	
	private Long departureId;
	
	private String departureName;
	
	private Long destinationId;
	
	private String destinationName;
	
	private String carpoolPrice;
	
	private String charterPrice;
	
	private Long fleetId;
	
	private String fleetName;

	public Long getId() {
		return id;
	}

	public RouteDTO setId(Long id) {
		this.id = id;
		return this;
	}

	public String getUuid() {
		return uuid;
	}

	public RouteDTO setUuid(String uuid) {
		this.uuid = uuid;
		return this;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public RouteDTO setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public Long getDepartureId() {
		return departureId;
	}

	public RouteDTO setDepartureId(Long departureId) {
		this.departureId = departureId;
		return this;
	}

	public String getDepartureName() {
		return departureName;
	}

	public RouteDTO setDepartureName(String departureName) {
		this.departureName = departureName;
		return this;
	}

	public Long getDestinationId() {
		return destinationId;
	}

	public RouteDTO setDestinationId(Long destinationId) {
		this.destinationId = destinationId;
		return this;
	}

	public String getDestinationName() {
		return destinationName;
	}

	public RouteDTO setDestinationName(String destinationName) {
		this.destinationName = destinationName;
		return this;
	}

	public String getCarpoolPrice() {
		return carpoolPrice;
	}

	public RouteDTO setCarpoolPrice(String carpoolPrice) {
		this.carpoolPrice = carpoolPrice;
		return this;
	}

	public String getCharterPrice() {
		return charterPrice;
	}

	public RouteDTO setCharterPrice(String charterPrice) {
		this.charterPrice = charterPrice;
		return this;
	}

	public Long getFleetId() {
		return fleetId;
	}

	public RouteDTO setFleetId(Long fleetId) {
		this.fleetId = fleetId;
		return this;
	}

	public String getFleetName() {
		return fleetName;
	}

	public RouteDTO setFleetName(String fleetName) {
		this.fleetName = fleetName;
		return this;
	}
	
	public RouteDTO set(RoutePO route, SitePO departure, SitePO destination, FleetPO fleet){
		this.setId(route.getId())
			.setUuid(route.getUuid())
			.setUpdateTime(route.getUpdateTime())
			.setCarpoolPrice(route.getCarpoolPrice())
			.setCharterPrice(route.getCharterPrice());
		
		if(departure != null){
			this.setDepartureId(departure.getId())
				.setDepartureName(departure.getName());
		}
		
		if(destination != null){
			this.setDestinationId(destination.getId())
				.setDestinationName(destination.getName());
		}
		
		if(fleet != null){
			this.setFleetId(fleet.getId())
				.setFleetName(fleet.getName());
		}
		
		return this;
	}
	
}
