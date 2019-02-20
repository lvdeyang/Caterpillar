package pub.caterpillar.app.carpool.dao;

import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Component;

import pub.caterpillar.app.carpool.enumeration.CarStatus;
import pub.caterpillar.app.carpool.po.CarPO;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;
import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;

@Component
public class CarDAO extends AbstractBaseDao<CarPO>{

	//获取没有配置司机的车辆
	public List<CarPO> queryFreeCars(){
		String sql = "SELECT id, uuid, updateTime, TYPE type, SEATS seats, PLATE_NUMBER plateNumber FROM carpool_car WHERE DRIVER_ID IS NULL";
		
		SQLQuery query = getCurrentSession().createSQLQuery(sql)
											.addScalar("id", StandardBasicTypes.LONG)
											.addScalar("uuid")
											.addScalar("updateTime", StandardBasicTypes.DATE)
											.addScalar("type")
											.addScalar("seats")
											.addScalar("plateNumber");
		query.setResultTransformer(Transformers.aliasToBean(CarPO.class));
		return query.list();
	}
	
	//查询订单是否被接以及接单的车辆状态
	public CarStatus queryStatusByOrder(long orderId){
		StringBufferWrapper sql = new StringBufferWrapper().append("SELECT c.STATUS status FROM carpool_seat_info si ")
														   .append("LEFT JOIN carpool_order o ON si.ORDER_UUID=o.UUID ")
														   .append("LEFT JOIN carpool_car c ON si.CAR_ID=c.ID ")
														   .append("WHERE o.id=").append(orderId);
		List<Object> result = this.findBySql(sql.toString());
		if(result==null || result.size()<=0){
			return null;
		}else{
			return CarStatus.valueOf(result.get(0).toString());
		}
	}
	
	//根据司机手机号查询车辆信息
	public CarPO queryByDriverMobile(String mobile){
		QueryHql hql = this.newQueryHql();
		hql.andBy("driver.mobile", Condition.eq, mobile);
		List<CarPO> cars = this.findByHql(hql);
		if(cars==null || cars.size()<=0) return null;
		return cars.get(0);
	}
	
}
