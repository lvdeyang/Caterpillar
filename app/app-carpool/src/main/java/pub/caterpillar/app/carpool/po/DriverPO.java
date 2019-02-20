package pub.caterpillar.app.carpool.po;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import pub.caterpillar.app.carpool.enumeration.DriverDirections;
import pub.caterpillar.app.carpool.enumeration.DriverStatus;
import pub.caterpillar.app.carpool.po.metadata.Metadata;
import pub.caterpillar.orm.po.AbstractBasePO;

/**
 * 司机数据<br/>
 * <p>
 *     1.司机姓名以及手机号
 *     2.司机登录的用户名密码
 * </p>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2018年4月24日 下午6:36:40
 */
@Entity
@Table(name = Metadata.PREFIX + "DRIVER")
public class DriverPO extends AbstractBasePO{

	private static final long serialVersionUID = 1L;

	/** 关联微信openId */
	private String openId;
	
	/** 司机姓名 */
	private String name;
	
	/** 司机登录用户名 */
	private String username;
	
	/** 司机登录密码 */
	private String password;
	
	/** 司机手机号码 */
	private String mobile;
	
	/** 记录当前司机是出发还是返回 */
	private DriverDirections direction;
	
	/** 系统控制司机上岗或下岗 */
	private DriverStatus status;
	
	/** 隶属车队 */
	private FleetPO fleet;
	
	/** 司机的车 */
	private CarPO car;
	
	/** 司机出发路线 */
	private RoutePO route;
	
	/** 司机返回路线 */
	private RoutePO revert;

	/** 关联微信openId */
	@Column(name = "OPEN_ID")
	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	/** 司机姓名 */
	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/** 司机登录用户名 */
	@Column(name = "USERNAME")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	/** 司机登录密码 */
	@Column(name = "PASSWORD")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/** 司机手机号码 */
	@Column(name = "MOBILE")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	/** 记录当前司机是出发还是返回 */
	@Column(name = "DIRECTION")
	@Enumerated(EnumType.STRING)
	public DriverDirections getDirection() {
		return direction;
	}

	public void setDirection(DriverDirections direction) {
		this.direction = direction;
	}

	/** 系统控制司机上岗或下岗 */
	@Column(name = "STATUS")
	@Enumerated(EnumType.STRING)
	public DriverStatus getStatus() {
		return status;
	}

	public void setStatus(DriverStatus status) {
		this.status = status;
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

	/** 司机的车 */
	@OneToOne(mappedBy = "driver", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false)
	public CarPO getCar() {
		return car;
	}

	public void setCar(CarPO car) {
		this.car = car;
	}

	/** 司机出发路线 */
	@ManyToOne
	@JoinColumn(name = "ROUTE_ID")
	public RoutePO getRoute() {
		return route;
	}

	public void setRoute(RoutePO route) {
		this.route = route;
	}
	
	/** 司机返回路线 */
	@ManyToOne
	@JoinColumn(name = "REVERT_ID")
	public RoutePO getRevert() {
		return revert;
	}

	public void setRevert(RoutePO revert) {
		this.revert = revert;
	}
	
}
