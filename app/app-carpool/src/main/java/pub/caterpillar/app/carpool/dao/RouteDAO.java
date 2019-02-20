package pub.caterpillar.app.carpool.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Component;
import pub.caterpillar.app.carpool.dto.RouteDTO;
import pub.caterpillar.app.carpool.po.RoutePO;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;
import pub.caterpillar.orm.dao.AbstractBaseDao;

@Component
public class RouteDAO extends AbstractBaseDao<RoutePO>{

	public List<RouteDTO> queryAllRouteDetails(){
		
		StringBufferWrapper sqlWrapper = new StringBufferWrapper().append("SELECT r.id id, r.uuid uuid, r.updateTime updateTime, ")
																  .append("departure.id departureId, departure.name departureName, ")
																  .append("destination.id destinationId, destination.name destinationName, ")
																  .append("r.CARPOOL_PRICE carpoolPrice, r.CHARTER_PRICE charterPrice, ")
																  .append("f.id fleetId, f.name fleetName ")
																  .append("FROM carpool_route r ")
																  .append("LEFT JOIN carpool_site departure ON r.DEPARTURE_SITE_ID=departure.id ")
																  .append("LEFT JOIN carpool_site destination ON r.DESTINATION_SITE_ID=destination.id ")
																  .append("LEFT JOIN carpool_fleet f ON r.FLEET_ID=f.id");
		SQLQuery query = getCurrentSession().createSQLQuery(sqlWrapper.toString())
											.addScalar("id", StandardBasicTypes.LONG)
											.addScalar("uuid")
											.addScalar("updateTime", StandardBasicTypes.DATE)
											.addScalar("departureId", StandardBasicTypes.LONG)
											.addScalar("departureName")
											.addScalar("destinationId", StandardBasicTypes.LONG)
											.addScalar("destinationName")
											.addScalar("carpoolPrice")
											.addScalar("charterPrice")
											.addScalar("fleetId", StandardBasicTypes.LONG)
											.addScalar("fleetName");
		query.setResultTransformer(Transformers.aliasToBean(RouteDTO.class));
		return query.list();
	}
	
	public List<RouteDTO> queryRouteDetailsByFleet(Long fleetId){
		StringBufferWrapper sqlWrapper = new StringBufferWrapper().append("SELECT r.id id, r.uuid uuid, r.updateTime updateTime, ")
																  .append("departure.id departureId, departure.name departureName, ")
																  .append("destination.id destinationId, destination.name destinationName, ")
																  .append("r.CARPOOL_PRICE carpoolPrice, r.CHARTER_PRICE charterPrice, ")
																  .append("f.id fleetId, f.name fleetName ")
																  .append("FROM carpool_route r ")
																  .append("LEFT JOIN carpool_site departure ON r.DEPARTURE_SITE_ID=departure.id ")
																  .append("LEFT JOIN carpool_site destination ON r.DESTINATION_SITE_ID=destination.id ")
																  .append("LEFT JOIN carpool_fleet f ON r.FLEET_ID=f.id ")
																  .append("WHERE f.id='").append(fleetId).append("'");
		SQLQuery query = getCurrentSession().createSQLQuery(sqlWrapper.toString())
											.addScalar("id", StandardBasicTypes.LONG)
											.addScalar("uuid")
											.addScalar("updateTime", StandardBasicTypes.DATE)
											.addScalar("departureId", StandardBasicTypes.LONG)
											.addScalar("departureName")
											.addScalar("destinationId", StandardBasicTypes.LONG)
											.addScalar("destinationName")
											.addScalar("carpoolPrice")
											.addScalar("charterPrice")
											.addScalar("fleetId", StandardBasicTypes.LONG)
											.addScalar("fleetName");
		query.setResultTransformer(Transformers.aliasToBean(RouteDTO.class));
		return query.list();
	}
	
	public List<RouteDTO> queryRouteDetailsByDepartureAndDestination(Long departureId, Long destinationId){
		StringBufferWrapper sqlWrapper = new StringBufferWrapper().append("SELECT r.id id, r.uuid uuid, r.updateTime updateTime, ")
																  .append("departure.id departureId, departure.name departureName, ")
																  .append("destination.id destinationId, destination.name destinationName, ")
																  .append("r.CARPOOL_PRICE carpoolPrice, r.CHARTER_PRICE charterPrice, ")
																  .append("f.id fleetId, f.name fleetName ")
																  .append("FROM carpool_route r ")
																  .append("LEFT JOIN carpool_site departure ON r.DEPARTURE_SITE_ID=departure.id ")
																  .append("LEFT JOIN carpool_site destination ON r.DESTINATION_SITE_ID=destination.id ")
																  .append("LEFT JOIN carpool_fleet f ON r.FLEET_ID=f.id ")
																  .append("WHERE departure.id=").append(departureId).append(" ")
																  .append("AND destination.id=").append(destinationId).append(" ")
																  .append("ORDER BY r.CARPOOL_PRICE ASC, r.CHARTER_PRICE ASC");
		SQLQuery query = getCurrentSession().createSQLQuery(sqlWrapper.toString())
		.addScalar("id", StandardBasicTypes.LONG)
		.addScalar("uuid")
		.addScalar("updateTime", StandardBasicTypes.DATE)
		.addScalar("departureId", StandardBasicTypes.LONG)
		.addScalar("departureName")
		.addScalar("destinationId", StandardBasicTypes.LONG)
		.addScalar("destinationName")
		.addScalar("carpoolPrice")
		.addScalar("charterPrice")
		.addScalar("fleetId", StandardBasicTypes.LONG)
		.addScalar("fleetName");
		query.setResultTransformer(Transformers.aliasToBean(RouteDTO.class));
		return query.list();
	}
	
	public List<Long> queryDepatureIds(){
		String sql = "SELECT DISTINCT DEPARTURE_SITE_ID FROM carpool_route";
		List<Object> result = this.findBySql(sql);
		if(result==null || result.size()<=0) return null;
		List<Long> depatureIds = new ArrayList<Long>();
		for(Object obj:result){
			depatureIds.add(Long.valueOf(obj.toString()));
		}
		return depatureIds;
	}
	
	public List<Long> queryDestinationIds(Long depatureId){
		StringBufferWrapper sqlBuffer = new StringBufferWrapper().append("SELECT DISTINCT DESTINATION_SITE_ID FROM carpool_route WHERE DEPARTURE_SITE_ID=").append(depatureId);
		List<Object> result = this.findBySql(sqlBuffer.toString());
		if(result==null || result.size()<=0) return null;
		List<Long> destinationIds = new ArrayList<Long>();
		for(Object obj:result){
			destinationIds.add(Long.valueOf(obj.toString()));
		}
		return destinationIds;
	}
	
}
