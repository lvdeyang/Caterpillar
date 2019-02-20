package pub.caterpillar.app.carpool.po;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import pub.caterpillar.app.carpool.po.metadata.Metadata;
import pub.caterpillar.orm.po.AbstractBasePO;

/**
 * 行车路线<br/>
 * <p>行车路线</p>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2018年4月24日 下午7:03:06
 */
@Entity
@Table(name = Metadata.PREFIX + "ROUTE")
public class RoutePO extends AbstractBasePO{

	private static final long serialVersionUID = 1L;

	/** 始发站，站点id */
	private Long departure;
	
	/** 终点站，站点id */
	private Long destination;
	
	/** 拼车价格 */
	private String carpoolPrice;
	
	/** 包车价格 */
	private String charterPrice;
	
	/** 隶属车队 */
	private FleetPO fleet;
	
	/** 出发路线的司机 */
	private Set<DriverPO> routeDrivers;
	
	/** 返回路线的司机 */
	private Set<DriverPO> revertDrivers;
	
	/** 始发站 */
	@Column(name = "DEPARTURE_SITE_ID")
	public Long getDeparture() {
		return departure;
	}

	public void setDeparture(Long departure) {
		this.departure = departure;
	}

	/** 终点站 */
	@Column(name = "DESTINATION_SITE_ID")
	public Long getDestination() {
		return destination;
	}

	public void setDestination(Long destination) {
		this.destination = destination;
	}

	/** 拼车价格 */
	@Column(name = "CARPOOL_PRICE")
	public String getCarpoolPrice() {
		return carpoolPrice;
	}

	public void setCarpoolPrice(String carpoolPrice) {
		this.carpoolPrice = carpoolPrice;
	}

	/** 包车价格 */
	@Column(name = "CHARTER_PRICE")
	public String getCharterPrice() {
		return charterPrice;
	}

	public void setCharterPrice(String charterPrice) {
		this.charterPrice = charterPrice;
	}

	/** 隶属车队 */
	@ManyToOne
	@JoinColumn(name = "FLEET_ID")
	public FleetPO getFleet() {
		return fleet;
	}

	public void setFleet(FleetPO fleet) {
		this.fleet = fleet;
	}

	/** 出发路线的司机 */
	@OneToMany(mappedBy = "route", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false)
	public Set<DriverPO> getRouteDrivers() {
		return routeDrivers;
	}

	public void setRouteDrivers(Set<DriverPO> routeDrivers) {
		this.routeDrivers = routeDrivers;
	}

	/** 返回路线的司机 */
	@OneToMany(mappedBy = "revert", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false)
	public Set<DriverPO> getRevertDrivers() {
		return revertDrivers;
	}

	public void setRevertDrivers(Set<DriverPO> revertDrivers) {
		this.revertDrivers = revertDrivers;
	}
	
}
