package pub.caterpillar.app.carpool.dao;

import java.util.List;
import org.springframework.stereotype.Component;
import pub.caterpillar.app.carpool.enumeration.SeatStatus;
import pub.caterpillar.app.carpool.po.SeatInfoPO;
import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.DeleteHql;
import pub.caterpillar.orm.hql.QueryHql;

/**
 * 车辆座位数据库操作<br/>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2018年5月9日 下午5:28:25
 */
@Component
public class SeatInfoDAO extends AbstractBaseDao<SeatInfoPO>{

	/**
	 * 删除一个车辆大于摸个序号的座位<br/>
	 * <p>调用场景：车辆编辑是将座位数调小</p>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2018年5月9日 下午5:17:42
	 * @param carId 车辆id
	 * @param serial 序号
	 */
	public void deletByCarAndGTSerial(long carId, int serial){
		DeleteHql hql = this.newDeleteHql();
		hql.andBy("car.id", Condition.eq, carId);
		hql.andBy("serialNum", Condition.gt, serial);
		this.deleteByHql(hql);
	}
	
	/**
	 * 根据车辆查询座位信息，并根据座位序号升序排列<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2018年5月9日 下午5:18:37
	 * @param carId 车辆id
	 * @return {@link pub.caterpillar.app.carpool.po.SeatInfoPO}
	 */
	public List<SeatInfoPO> queryByCarWithSerialASC(Long carId){
		QueryHql hql = this.newQueryHql();
		hql.andBy("car.id", Condition.eq, carId);
		hql.orderBy("serialNum", false);
		return this.findByHql(hql);
	}
	
	/**
	 * 根据司机电话号查询空闲座位，并按照座位序号升序排列<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2018年5月9日 下午5:20:56
	 * @param driverMobile 司机电话号
	 * @return {@link pub.caterpillar.app.carpool.po.SeatInfoPO}
	 */
	public List<SeatInfoPO> queryFreeByDriverWithSerailASC(String driverMobile){
		QueryHql hql = this.newQueryHql();
		hql.andBy("car.driver.mobile", Condition.eq, driverMobile);
		hql.andBy("status", Condition.eq, SeatStatus.FREE);
		hql.orderBy("serialNum", false);
		return this.findByHql(hql);
	}
	
	/**
	 * 根据车辆和订单查询座位信息并且过滤当前座位<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2018年5月9日 下午5:23:12
	 * @param carId 车辆id
	 * @param orderUuid 订单uuid
	 * @param self 当前座位id
	 * @return {@link pub.caterpillar.app.carpool.po.SeatInfoPO}
	 */
	public List<SeatInfoPO> queryByCarAndOrderNeSelf(Long carId, String orderUuid, Long self){
		QueryHql hql = this.newQueryHql();
		hql.andBy("car.id", Condition.eq, carId);
		hql.andBy("orderUuid", Condition.eq, orderUuid);
		hql.andBy("id", Condition.ne, self);
		return this.findByHql(hql);
	}
	
	public List<SeatInfoPO> queryByOrder(String orderUuid){
		QueryHql hql = this.newQueryHql();
		hql.andBy("orderUuid", Condition.eq, orderUuid);
		return this.findByHql(hql);
	}
	
	public List<SeatInfoPO> queryByDriver(String driverMobile){
		QueryHql hql = this.newQueryHql();
		hql.andBy("car.driver.mobile", Condition.eq, driverMobile);
		hql.orderBy("serialNum", false);
		return this.findByHql(hql);
	}
	
}
