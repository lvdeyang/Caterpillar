package pub.caterpillar.app.carpool.po;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import pub.caterpillar.app.carpool.po.metadata.Metadata;
import pub.caterpillar.orm.po.AbstractBasePO;

/**
 * 车队【|队长】信息<br/>
 * <p>
 *     1.车队名称
 *     2.队长登录用户名密码，以及队长电话号
 * </p>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2018年4月24日 下午6:35:20
 */
@Entity
@Table(name = Metadata.PREFIX + "FLEET")
public class FleetPO extends AbstractBasePO{

	private static final long serialVersionUID = 1L;

	/** 车队名称 */
	private String name;
	
	/** 队长登录用户名 */
	private String username;
	
	/** 队长登录密码 */
	private String password;
	
	/** 队长手机号 */
	private String mobile;

	/** 车队司机 */
	private Set<DriverPO> drivers;
	
	/** 车队路线 */
	private Set<RoutePO> routes;
	
	/** 车队名称 */
	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/** 队长登录用户名 */
	@Column(name = "USERNAME")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	/** 队长登录密码 */
	@Column(name = "PASSWORD")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/** 队长手机号 */
	@Column(name = "MOBILE")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/** 车队司机 */
	@OneToMany(mappedBy = "fleet", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false)
	public Set<DriverPO> getDrivers() {
		return drivers;
	}

	public void setDrivers(Set<DriverPO> drivers) {
		this.drivers = drivers;
	}

	/** 车队路线 */
	@OneToMany(mappedBy = "fleet", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false)
	public Set<RoutePO> getRoutes() {
		return routes;
	}

	public void setRoutes(Set<RoutePO> routes) {
		this.routes = routes;
	}
	
}
