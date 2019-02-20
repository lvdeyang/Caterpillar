package pub.caterpillar.app.carpool.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import pub.caterpillar.app.carpool.enumeration.OrderBalanceStatus;
import pub.caterpillar.app.carpool.enumeration.OrderPayStatus;
import pub.caterpillar.app.carpool.enumeration.OrderTakingStatus;
import pub.caterpillar.app.carpool.po.OrderPO;
import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.commons.util.wrapper.ArrayListWrapper;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;
import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;

@Component
public class OrderDAO extends AbstractBaseDao<OrderPO>{

	public List<OrderPO> queryNewOrderByRoute(Long routeId){
		QueryHql hql = this.newQueryHql();
		hql.andBy("routeId", Condition.eq, routeId);
		hql.andBy("takingStatus", Condition.eq, OrderTakingStatus.NEW);
		hql.orderBy("addMoney", true);
		hql.orderBy("travelTime", true);
		return this.findByHql(hql);
	}
	
	
	public List<OrderPO> queryBalanceOrderByDriver(String mobile){
		QueryHql hql = this.newQueryHql();
		hql.andBy("payStatus", Condition.eq, OrderPayStatus.PAYED);
		hql.andBy("balanceStatus", Condition.eq, OrderBalanceStatus.NO);
		hql.andBy("driverMobile", Condition.eq, mobile);
		return this.findByHql(hql);
	}
	
	
	public String queryOrderStatus(long id){
		StringBufferWrapper sqlBuffer = new StringBufferWrapper().append("SELECT TAKING_STATUS FROM carpool_order WHERE id=").append(id);
		List<Object> result = this.findBySql(sqlBuffer.toString());
		if(result==null || result.size()<=0) return null;
		return result.get(0).toString();
	}
	public String queryOrderPayStatus(long id){
		StringBufferWrapper sqlBuffer = new StringBufferWrapper().append("SELECT PAY_STATUS FROM carpool_order WHERE id=").append(id);
		List<Object> result = this.findBySql(sqlBuffer.toString());
		if(result==null || result.size()<=0) return null;
		return result.get(0).toString();
	}
	
	public OrderPO queryNewOrderByUser(long userId){
		QueryHql hql = this.newQueryHql();
		hql.andBy("userId", Condition.eq, userId);
		hql.andBy("takingStatus", Condition.eq, OrderTakingStatus.NEW);
		List<OrderPO> orders = this.findByHql(hql);
		if(orders==null || orders.size()<=0) return null;
		return orders.get(0);
	}
	
	public OrderPO queryTakedOrderByUser(long userId){
		QueryHql hql = this.newQueryHql();
		hql.andBy("userId", Condition.eq, userId);
	    hql.andBy("payStatus",Condition.eq,OrderPayStatus.UNPAY);
		hql.andBy("takingStatus", Condition.in,new ArrayListWrapper<OrderTakingStatus>().add(OrderTakingStatus.TAKED).add(OrderTakingStatus.SUCCESS).getList());
		List<OrderPO> orders = this.findByHql(hql);
		if(orders==null || orders.size()<=0) return null;
		return orders.get(0);
	}
	
	public List<OrderPO> queryNewOrderAfter30Minutes(){
		Date now = new Date();
		Date _30MinuteAgo = DateUtil.addMinute(now, -30);
		QueryHql hql = this.newQueryHql();
		hql.andBy("takingStatus", Condition.eq, OrderTakingStatus.NEW);
		hql.andBy("createTime", Condition.le, _30MinuteAgo);
		return this.findByHql(hql);
	}
	
	public List<OrderPO> queryUnpayOrder(){
		QueryHql hql = this.newQueryHql();
		hql.andBy("payStatus", Condition.eq, OrderPayStatus.UNPAY);
		return this.findByHql(hql);
	}
	
}
