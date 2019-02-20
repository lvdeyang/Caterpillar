package pub.caterpillar.app.carpool.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Component;
import pub.caterpillar.app.carpool.dto.DriverDTO;
import pub.caterpillar.app.carpool.enumeration.DriverDirections;
import pub.caterpillar.app.carpool.enumeration.DriverStatus;
import pub.caterpillar.app.carpool.enumeration.SeatStatus;
import pub.caterpillar.app.carpool.po.DriverPO;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;
import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;


@Component
public class DriverDAO extends AbstractBaseDao<DriverPO>{

	public List<DriverDTO> queryAllDriverDetails(){
		StringBufferWrapper sqlWrapper = new StringBufferWrapper().append("SELECT d.id id, d.uuid uuid, d.updateTime updateTime, ")
																  .append("d.NAME name, d.USERNAME username, d.MOBILE mobile, ")
																  .append("f.id fleetId, f.NAME fleetName, c.id carId, c.TYPE carType, ")
																  .append("r.id routeId, departure.NAME routeDeparture, destination.NAME routeDestination, ")
																  .append("re.id revertId, reDeparture.NAME revertDeparture, reDestination.NAME revertDestination ")
																  .append("FROM carpool_driver d ")
																  .append("LEFT JOIN carpool_fleet f ON d.FLEET_ID=f.id ")
																  .append("LEFT JOIN carpool_car c ON d.id=c.DRIVER_ID ")
																  .append("LEFT JOIN carpool_route r ON r.id=d.ROUTE_ID ")
																  .append("LEFT JOIN carpool_site departure ON r.DEPARTURE_SITE_ID=departure.id ")
																  .append("LEFT JOIN carpool_site destination on r.DESTINATION_SITE_ID=destination.id ")
																  .append("LEFT JOIN carpool_route re ON re.id=d.REVERT_ID ")
																  .append("LEFT JOIN carpool_site reDeparture ON re.DEPARTURE_SITE_ID=reDeparture.id ")
																  .append("LEFT JOIN carpool_site reDestination on re.DESTINATION_SITE_ID=reDestination.id");
		SQLQuery query = getCurrentSession().createSQLQuery(sqlWrapper.toString())
											.addScalar("id", StandardBasicTypes.LONG)
											.addScalar("uuid")
											.addScalar("updateTime", StandardBasicTypes.DATE)
											.addScalar("name")
											.addScalar("username")
											.addScalar("mobile")
											.addScalar("fleetId", StandardBasicTypes.LONG)
											.addScalar("fleetName")
											.addScalar("carId", StandardBasicTypes.LONG)
											.addScalar("carType")
											.addScalar("routeId", StandardBasicTypes.LONG)
											.addScalar("routeDeparture")
											.addScalar("routeDestination")
											.addScalar("revertId", StandardBasicTypes.LONG)
											.addScalar("revertDeparture")
											.addScalar("revertDestination");
		query.setResultTransformer(Transformers.aliasToBean(DriverDTO.class));
		return query.list();
	}
	
	public DriverPO queryByMobile(String mobile){
		if(mobile == null) return null;
		QueryHql hql = this.newQueryHql();
		hql.andBy("mobile", Condition.eq, mobile);
		List<DriverPO> drivers = this.findByHql(hql);
		if(drivers==null || drivers.size()<=0) return null;
		return drivers.get(0);
	}
	
	public DriverPO queryByOpenId(String openId){
		if(openId == null) return null;
		QueryHql hql = this.newQueryHql();
		hql.andBy("openId", Condition.eq, openId);
		List<DriverPO> drivers = this.findByHql(hql);
		if(drivers==null || drivers.size()<=0) return null;
		return drivers.get(0);
	}
	
	public List<String> queryMobileByRouteAndStatusAndFreeSeat(long routeId){
		StringBufferWrapper sqlBuffer = new StringBufferWrapper().append("SELECT distinct driver.MOBILE FROM carpool_driver driver ")
																 .append("LEFT JOIN carpool_car car ON car.DRIVER_ID=driver.id ")
																 .append("LEFT JOIN carpool_seat_info seat ON car.id=seat.CAR_ID ")
																 .append("WHERE seat.STATUS='").append(SeatStatus.FREE.toString()).append("' ")
																 .append("AND (driver.ROUTE_ID=").append(routeId).append(" ")
																 .append("OR driver.REVERT_ID=").append(routeId).append(") ")
																 .append("AND driver.STATUS = '").append(DriverStatus.ON).append("'");
		List<Object> result = this.findBySql(sqlBuffer.toString());
		if(result==null || result.size()<=0) return null;
		List<String> mobiles = new ArrayList<String>();
		for(Object record:result){
			mobiles.add(record.toString());
		}
		return mobiles;
	}
	
	public String queryMobileByOrder(long orderId){
		StringBufferWrapper sqlBuffer = new StringBufferWrapper().append("SELECT DISTINCT driver.MOBILE FROM carpool_order o ")
																 .append("LEFT JOIN carpool_seat_info seat ON seat.ORDER_UUID=o.uuid ")
																 .append("LEFT JOIN carpool_car car ON seat.CAR_ID=car.id ")
																 .append("LEFT JOIN carpool_driver driver ON car.DRIVER_ID=driver.id ")
																 .append("WHERE o.id=").append(orderId);
		List<Object> result = this.findBySql(sqlBuffer.toString());
		if(result==null || result.size()<=0) return null;
		return result.get(0).toString();
	}
	
	public List<DriverDTO> queryByRouteAndStatusAndFreeSeatNum(long routeId, int minFreeSeatNum){
		StringBufferWrapper sqlBuffer = new StringBufferWrapper().append("SELECT ")
																 .append("driver_outer.id id, ")
																 .append("driver_outer.uuid uuid, ")
																 .append("driver_outer.name name, ")
																 .append("driver_outer.mobile, ")
																 .append("driver_outer.updateTime, ")
																 .append("MAX(seat_outer.TRAVEL_TIME) maxTravelTime, ")
																 .append("MIN(seat_outer.TRAVEL_TIME) minTravelTime ")
																 .append("FROM(")
																 .append("SELECT driver.id id, driver.uuid uuid, driver.NAME name, driver.MOBILE mobile, driver.updateTime updateTime ")
																 .append("FROM carpool_driver driver ")
																 .append("LEFT JOIN carpool_car car ")
																 .append("ON driver.id=car.DRIVER_ID ")
																 .append("LEFT JOIN carpool_seat_info seat ")
																 .append("ON car.id=seat.CAR_ID ")
																 .append("WHERE ((driver.ROUTE_ID=").append(routeId).append(" AND driver.DIRECTION='").append(DriverDirections.ROUTE).append("') OR (driver.REVERT_ID=").append(routeId).append(" AND driver.DIRECTION='").append(DriverDirections.REVERT.toString()).append("')) ")
																 .append("AND driver.status='").append(DriverStatus.ON.toString()).append("' ")
																 .append("AND seat.STATUS='").append(SeatStatus.FREE.toString()).append("' ")
																 .append("GROUP BY car.id ")
																 .append("HAVING COUNT(car.id)>=").append(minFreeSeatNum).append(" ")
																 .append(") driver_outer ")
																 .append("LEFT JOIN carpool_car car_outer ")
																 .append("ON driver_outer.id=car_outer.DRIVER_ID ")
																 .append("LEFT JOIN carpool_seat_info seat_outer ")
																 .append("ON car_outer.id=seat_outer.CAR_ID");
		SQLQuery query = getCurrentSession().createSQLQuery(sqlBuffer.toString())
											.addScalar("id", StandardBasicTypes.LONG)
											.addScalar("updateTime", StandardBasicTypes.DATE)
											.addScalar("name")
											.addScalar("mobile")
											.addScalar("maxTravelTime", StandardBasicTypes.DATE)
											.addScalar("minTravelTime", StandardBasicTypes.DATE);
		query.setResultTransformer(Transformers.aliasToBean(DriverDTO.class));
		return query.list();
	}
	
}
