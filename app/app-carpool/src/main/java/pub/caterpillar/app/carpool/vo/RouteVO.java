package pub.caterpillar.app.carpool.vo;

import pub.caterpillar.app.carpool.dto.RouteDTO;
import pub.caterpillar.app.carpool.po.FleetPO;
import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class RouteVO extends AbstractBaseVO<RouteVO, RouteDTO>{

	private Long departureId;
	
	private String departureName;
	
	private Long destinationId;
	
	private String destinationName;
	
	private String carpoolPrice;
	
	private String charterPrice;
	
	private Long fleetId;
	
	private String fleetName;
	
	public Long getDepartureId() {
		return departureId;
	}

	public RouteVO setDepartureId(Long departureId) {
		this.departureId = departureId;
		return this;
	}

	public String getDepartureName() {
		return departureName;
	}

	public RouteVO setDepartureName(String departureName) {
		this.departureName = departureName;
		return this;
	}

	public Long getDestinationId() {
		return destinationId;
	}

	public RouteVO setDestinationId(Long destinationId) {
		this.destinationId = destinationId;
		return this;
	}

	public String getDestinationName() {
		return destinationName;
	}

	public RouteVO setDestinationName(String destinationName) {
		this.destinationName = destinationName;
		return this;
	}

	public String getCarpoolPrice() {
		return carpoolPrice;
	}

	public RouteVO setCarpoolPrice(String carpoolPrice) {
		this.carpoolPrice = carpoolPrice;
		return this;
	}

	public String getCharterPrice() {
		return charterPrice;
	}

	public RouteVO setCharterPrice(String charterPrice) {
		this.charterPrice = charterPrice;
		return this;
	}

	public Long getFleetId() {
		return fleetId;
	}

	public RouteVO setFleetId(Long fleetId) {
		this.fleetId = fleetId;
		return this;
	}

	public String getFleetName() {
		return fleetName;
	}

	public RouteVO setFleetName(String fleetName) {
		this.fleetName = fleetName;
		return this;
	}

	@Override
	public RouteVO set(RouteDTO entity) throws Exception {
		this.setId(entity.getId())
		    .setUuid(entity.getUuid())
		    .setUpdateTime(DateUtil.format(entity.getUpdateTime(), DateUtil.dateTimePattern))
		    .setDepartureId(entity.getDepartureId()==null?-1:entity.getDepartureId())
		    .setDepartureName(entity.getDepartureName()==null?"":entity.getDepartureName())
		    .setDestinationId(entity.getDestinationId()==null?-1:entity.getDestinationId())
		    .setDestinationName(entity.getDestinationName()==null?"":entity.getDestinationName())
		    .setCarpoolPrice(entity.getCarpoolPrice())
		    .setCharterPrice(entity.getCharterPrice())
		    .setFleetId(entity.getFleetId()==null?-1:entity.getFleetId())
		    .setFleetName(entity.getFleetName()==null?"":entity.getFleetName());
		return this;
	}
	
}
