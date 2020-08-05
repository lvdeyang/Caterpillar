package com.guolaiwan.bussiness.admin.dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.dto.CountGroupDTO;
import com.guolaiwan.bussiness.admin.enumeration.OrderSource;
import com.guolaiwan.bussiness.admin.enumeration.OrderStateType;
import com.guolaiwan.bussiness.admin.po.OrderInfoPO;

import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;
import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("com.guolaiwan.bussiness.admin.dao.OrderInfoDAO")
public class OrderInfoDAO extends AbstractBaseDao<OrderInfoPO> {
	public OrderInfoPO getOrderByNo(String orderno) {
		QueryHql hql = this.newQueryHql();
		hql.andBy("orderNO", Condition.eq, orderno);
		List<OrderInfoPO> orders = findByHql(hql);
		if (orders == null || orders.size() <= 0)
			return null;
		return orders.get(0);
	}

	public OrderInfoPO getById(long id) {
		QueryHql hql = this.newQueryHql();
		hql.andBy("id", Condition.eq, id);
		List<OrderInfoPO> orders = findByHql(hql);
		if (orders == null || orders.size() <= 0)
			return null;
		return orders.get(0);
	}

	public List<OrderInfoPO> GetListbyPage(int pageNum, int pageSize) {
		QueryHql hql = this.newQueryHql();

		hql.orderBy("id", true);
		List<OrderInfoPO> orders = findByHqlPage(hql, pageNum, pageSize);
		if (orders == null || orders.size() <= 0)
			return null;
		return orders;
	}

	// 统计总数
	public int GetCountByPage() {
		CountHql cHql = this.newCountHql();

		int allcount = this.countByHql(cHql);
		return allcount;
	}

	public int GetCountByMerchant(long merchantId) {
		CountHql cHql = this.newCountHql();
		cHql.andBy("shopId", Condition.eq, merchantId);
		int allcount = this.countByHql(cHql);
		return allcount;
	}

	public int countByDate(String date) throws ParseException {

		CountHql hql = this.newCountHql();
		hql.andBy("updateTime", Condition.gt, DateUtil.parse(date + " 00:00:00"));
		hql.andBy("updateTime", Condition.lt, DateUtil.parse(date + " 23:59:59"));
		return countByHql(hql);

	}

	public int countTodayByActpro(long actproId) throws ParseException {
		Date date = new Date();
		CountHql hql = this.newCountHql();
		hql.andBy("updateTime", Condition.gt, DateUtil.parse(DateUtil.format(date, "yyyy-MM-dd") + " 00:00:00"));
		hql.andBy("updateTime", Condition.lt, DateUtil.parse(DateUtil.format(date, "yyyy-MM-dd") + " 23:59:59"));
		hql.andBy("activityId", Condition.eq, actproId);
		return countByHql(hql);

	}

	public int countDateByActpro(long actproId, Date bDate) throws ParseException {
		String sqlString = "SELECT SUM(productNum) FROM t_sys_orderinfo where activityId=" + actproId
				+ " And (orderBookDate between '" + DateUtil.format(bDate, "yyyy-MM-dd") + " 00:00:00'" + " and '"
				+ DateUtil.format(bDate, "yyyy-MM-dd") + " 23:59:59'" + ")";
		List<Object> oList = this.findBySql(sqlString);
		if (oList.get(0) == null)
			return 0;
		return Integer.parseInt(oList.get(0).toString());

	}

	/**
	 * app专用
	 * 
	 * @param userId
	 * @param state
	 * @return
	 */
	public List<OrderInfoPO> getOrdersByState(long userId, OrderStateType state) {

		QueryHql hql = this.newQueryHql();
		hql.andBy("userId", Condition.eq, userId);
		hql.andBy("orderState", Condition.eq, state);
		hql.orderBy("createDate", true);
		List<OrderInfoPO> orders = findByHql(hql);
		return orders;
	}
	
	
	public List<OrderInfoPO> getBasOrdersByState(long userId) {

		QueryHql hql = this.newQueryHql();
		hql.andBy("userId", Condition.eq, userId);
		hql.andBy("orderState", Condition.eq, OrderStateType.NOTPAY);
		hql.andBy("inbas",Condition.eq,1);
		hql.orderBy("createDate", true);
		List<OrderInfoPO> orders = findByHql(hql);
		return orders;
	}
	

	public List<OrderInfoPO> getOrdersByMerBalanced(long merchantId) {

		QueryHql hql = this.newQueryHql();
		hql.andBy("shopId", Condition.eq, merchantId);
		hql.andBy("balance", Condition.eq, 0);
		List<OrderStateType> types = new ArrayList<OrderStateType>();
		types.add(OrderStateType.TESTED);
		types.add(OrderStateType.RECEIPT);
		types.add(OrderStateType.COMMENTED);
		hql.andBy("orderState", Condition.in, types);
		List<OrderInfoPO> orders = findByHql(hql);
		return orders;
	}

	/**
	 * app专用
	 * 
	 * @param userId
	 * @param state
	 * @return
	 */
	public List<OrderInfoPO> getOrdersByMerState(long merchantId, OrderStateType state) {
		QueryHql hql = this.newQueryHql();
		hql.andBy("shopId", Condition.eq, merchantId);
		hql.andBy("orderState", Condition.eq, state);
		hql.orderBy("createDate", true);
		List<OrderInfoPO> orders = findByHql(hql);
		return orders;
	}

	public List<OrderInfoPO> searchOrder(long merchantId, OrderStateType state, long proId, String start, String end)
			throws ParseException {
		QueryHql hql = this.newQueryHql();
		hql.andBy("shopId", Condition.eq, merchantId);
		hql.andBy("orderState", Condition.eq, state);
		if (state == OrderStateType.TESTED) {
			if (start != null && !start.equals("")) {
				hql.andBy("ydDate", Condition.gt, DateUtil.parse(start + " 00:00:00"));
			}
			if (end != null && !end.equals("")) {
				hql.andBy("ydDate", Condition.lt, DateUtil.parse(end + " 23:59:59"));
			}
		} else {
			if (start != null && !start.equals("")) {
				if (state.equals(OrderStateType.NOTPAY)) {
					hql.andBy("createDate", Condition.gt, DateUtil.parse(start + " 00:00:00"));
				} else {
					hql.andBy("payDate", Condition.gt, DateUtil.parse(start + " 00:00:00"));
				}
			}
			if (end != null && !end.equals("")) {
				if (state.equals(OrderStateType.NOTPAY)) {
					hql.andBy("createDate", Condition.lt, DateUtil.parse(end + " 23:59:59"));
				} else {
					hql.andBy("payDate", Condition.lt, DateUtil.parse(end + " 23:59:59"));
				}
			}
		}
		if (proId != 0l && proId != -1) {
			hql.andBy("productId", Condition.eq, proId);
		} else if (proId == -1) {
			hql.andBy("productId", Condition.eq, 0L);
		}
		hql.orderBy("createDate", true);
		List<OrderInfoPO> orders = findByHql(hql);
		return orders;
	}

	public List<OrderInfoPO> getOrdersByState(long userId, OrderStateType state, int pageNum, int pageSize) {

		QueryHql hql = this.newQueryHql();
		hql.andBy("userId", Condition.eq, userId);
		hql.andBy("orderState", Condition.eq, state);
		List<OrderInfoPO> orders = findByHqlPage(hql, pageNum, pageSize);

		return orders;
	}

	public List<OrderInfoPO> findByFieldByPage(String field, Object value, int pageNum, int pageSize) {
		QueryHql hql = newQueryHql();
		hql.andBy(field, Condition.eq, value);
		List<OrderInfoPO> orders = findByHqlPage(hql, pageNum, pageSize);
		return orders;
	}

	// 后台验单
	public OrderInfoPO getByRoderNo(String orderNo) {
		QueryHql hql = newQueryHql();
		hql.andBy("orderNO", Condition.eq, orderNo);
		List<OrderInfoPO> orders = findByHql(hql);
		if (orders == null || orders.size() == 0)
			return null;
		return orders.get(0);
	}

	// 后台验单
	public OrderInfoPO getByRoderNo(Long orderNo) {
		QueryHql hql = newQueryHql();
		hql.andBy("id", Condition.eq, orderNo);
		List<OrderInfoPO> orders = findByHql(hql);
		if (orders == null || orders.size() == 0)
			return null;
		return orders.get(0);
	}

	/**
	 * app专用
	 * 
	 * @param orderNo
	 * @return
	 */
	public OrderInfoPO appgGetByRoderNo(String orderNo) {
		QueryHql hql = newQueryHql();
		hql.andBy("orderNO", Condition.eq, orderNo);
		List<OrderInfoPO> orders = findByHql(hql);
		if (orders == null || orders.size() == 0)
			return null;
		return orders.get(0);
	}

	// 后台验单列表
	public List<OrderInfoPO> findByYd(int pageNum, int pageSize) {
		QueryHql hql = newQueryHql();
		hql.andBy("orderState", Condition.eq, OrderStateType.TESTED);
		hql.orderBy("ydDate", true);
		List<OrderInfoPO> orders = findByHqlPage(hql, pageNum, pageSize);
		return orders;
	}

	public List<OrderInfoPO> findByDistributorAndSource(long distributorId, OrderSource source) {
		QueryHql hql = newQueryHql();
		hql.andBy("distributorId", Condition.eq, distributorId);
		hql.andBy("source", Condition.eq, source);

		return this.findByHql(hql);
	}

	// 后台验单列表
	public int countByYd() {
		CountHql cHql = newCountHql();
		cHql.andBy("orderState", Condition.eq, OrderStateType.TESTED);
		int count = countByHql(cHql);
		return count;
	}

	public int countBySell(long merchantId) {
		CountHql cHql = newCountHql();
		cHql.andBy("source", Condition.eq, OrderSource.UNLINE);
		cHql.andBy("shopId", Condition.eq, merchantId);
		int count = countByHql(cHql);
		return count;
	}

	public List<OrderInfoPO> findBySell(int pageNum, int pageSize, long merchantId) {
		QueryHql hql = newQueryHql();
		hql.andBy("source", Condition.eq, OrderSource.UNLINE);
		hql.andBy("shopId", Condition.eq, merchantId);
		List<OrderInfoPO> orders = findByHqlPage(hql, pageNum, pageSize);
		return orders;
	}

	// 商户后台验单
	public OrderInfoPO getByRoderNo(String orderNo, long MerId) {
		QueryHql hql = newQueryHql();
		hql.andBy("orderNO", Condition.eq, orderNo);
		hql.andBy("shopId", Condition.eq, MerId);
		List<OrderInfoPO> orders = findByHql(hql);
		if (orders == null || orders.size() == 0)
			return null;
		return orders.get(0);
	}

	// 商户后台验单列表
	public List<OrderInfoPO> findByYd(long MerId, int pageNum, int pageSize) {
		QueryHql hql = newQueryHql();
		hql.andBy("orderState", Condition.eq, OrderStateType.TESTED);
		hql.andBy("shopId", Condition.eq, MerId);
		hql.orderBy("ydDate", true);
		List<OrderInfoPO> orders = findByHqlPage(hql, pageNum, pageSize);
		return orders;
	}

	// 商户后台验单列表
	public int countByYd(long MerId) {
		CountHql cHql = newCountHql();
		cHql.andBy("orderState", Condition.eq, OrderStateType.TESTED);
		cHql.andBy("shopId", Condition.eq, MerId);
		int count = countByHql(cHql);
		return count;
	}

	// 获取今日验单数量
	public int countYdNow(long merId) {
		StringBufferWrapper sqlWrapper = new StringBufferWrapper().append("select count(1) nc from t_sys_orderinfo a")
				.append(" where to_days(yddate) = to_days(now())").append(" and shopId = ").append(merId);
		System.out.println(sqlWrapper.toString());
		SQLQuery query = getCurrentSession().createSQLQuery(sqlWrapper.toString()).addScalar("nc",
				StandardBasicTypes.INTEGER);
		if (query.uniqueResult() == null)
			return 0;
		int nc = (int) query.uniqueResult();
		return nc;
	}

	/**
	 * app专用
	 * 
	 * @param userId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List<OrderInfoPO> getBasket(long userId, int pageNum, int pageSize) {
		QueryHql hql = this.newQueryHql();
		hql.andBy("userId", Condition.eq, userId);
		hql.andBy("orderState", Condition.eq, OrderStateType.NOTPAY);
		hql.orderBy("id", true);
		List<OrderInfoPO> orders = findByHqlPage(hql, pageNum, pageSize);
		return orders;
	}

	/**
	 * app专用
	 * 
	 * @param userId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public int countBasket(long userId) {
		CountHql cHql = this.newCountHql();
		cHql.andBy("userId", Condition.eq, userId);
		cHql.andBy("orderState", Condition.eq, OrderStateType.NOTPAY);
		int count = countByHql(cHql);
		return count;
	}

	// 获取餐桌已确认订单
	public List<OrderInfoPO> findTableOrders(long tableStatusId, OrderStateType type) {
		QueryHql hql = newQueryHql();
		hql.andBy("tableStatusId", Condition.eq, tableStatusId);
		hql.andBy("orderState", Condition.eq, type);
		List<OrderInfoPO> orders = findByHql(hql);
		return orders;
	}

	/*
	 * // 订单状态 public List<OrderInfoPO> findOrdersByState(OrderStateType type,
	 * Map<String, Object> map, int pageNum, int pageSize) throws Exception {
	 * QueryHql hql = newQueryHql(); hql.andBy("orderState", Condition.eq,
	 * type); for (Map.Entry<String, Object> entry : map.entrySet()) { if
	 * (entry.getKey().equals("shopId")) { hql.andBy(entry.getKey(),
	 * Condition.eq, Long.parseLong(entry.getValue().toString())); } else if
	 * (entry.getKey().equals("source")) { hql.andBy(entry.getKey(),
	 * Condition.eq, OrderSource.fromName(entry.getValue().toString())); } else
	 * { hql.andBy(entry.getKey(), Condition.lk, entry.getValue()); } }
	 * hql.orderBy("createDate", true);
	 * 
	 * List<OrderInfoPO> orders = findByHqlPage(hql, pageNum, pageSize); return
	 * orders; }
	 */

	// 订单状态 4/26 添加comId条件
	public List<OrderInfoPO> findOrdersByState(OrderStateType type, Map<String, Object> map, int pageNum, int pageSize)
			throws Exception {
		QueryHql hql = newQueryHql();
		hql.andBy("orderState", Condition.eq, type);
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			if (entry.getKey().equals("shopId")) {
				hql.andBy(entry.getKey(), Condition.eq, Long.parseLong(entry.getValue().toString()));
			} else if (entry.getKey().equals("source")) {
				hql.andBy(entry.getKey(), Condition.eq, OrderSource.fromName(entry.getValue().toString()));
			} else if (entry.getKey().equals("comId")) {
				if(!entry.getValue().equals("1")){
					hql.andBy(entry.getKey(), Condition.eq, Long.parseLong(entry.getValue().toString()));
				}
				
			} else {
				hql.andBy(entry.getKey(), Condition.lk, entry.getValue());
			}
		}
		hql.orderBy("createDate", true);

		List<OrderInfoPO> orders = findByHqlPage(hql, pageNum, pageSize);
		return orders;
	}

	/*
	 * // 订单状态（数量） public int countOrdersByState(OrderStateType type,
	 * Map<String, Object> map) throws Exception { CountHql cHql =
	 * newCountHql(); cHql.andBy("orderState", Condition.eq, type); for
	 * (Map.Entry<String, Object> entry : map.entrySet()) { if
	 * (entry.getKey().equals("shopId")) { cHql.andBy(entry.getKey(),
	 * Condition.eq, Long.parseLong(entry.getValue().toString())); } else if
	 * (entry.getKey().equals("source")) { cHql.andBy(entry.getKey(),
	 * Condition.eq, OrderSource.fromName(entry.getValue().toString())); } else
	 * { cHql.andBy(entry.getKey(), Condition.lk, entry.getValue()); } } int
	 * count = countByHql(cHql); return count; }
	 */

	// 订单状态（数量）4/26 添加comId条件
	public int countOrdersByState(OrderStateType type, Map<String, Object> map) throws Exception {
		CountHql cHql = newCountHql();
		cHql.andBy("orderState", Condition.eq, type);
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			if (entry.getKey().equals("shopId")) {
				cHql.andBy(entry.getKey(), Condition.eq, Long.parseLong(entry.getValue().toString()));
			} else if (entry.getKey().equals("source")) {
				cHql.andBy(entry.getKey(), Condition.eq, OrderSource.fromName(entry.getValue().toString()));
			} else if (entry.getKey().equals("comId")) {
				cHql.andBy(entry.getKey(), Condition.eq, Long.parseLong(entry.getValue().toString()));
			} else {
				cHql.andBy(entry.getKey(), Condition.lk, entry.getValue());
			}
		}
		int count = countByHql(cHql);
		return count;
	}

	// 订单NO,Id
	public OrderInfoPO getByIdNO(Long orderId, String orderNO) {
		if (orderId == null && (orderNO == null || orderNO.length() == 0)) {
			return null;
		}
		QueryHql hql = newQueryHql();
		hql.andBy("id", Condition.eq, orderId);
		hql.andBy("orderNO", Condition.eq, orderNO);
		List<OrderInfoPO> orders = findByHql(hql);
		if (orders == null || orders.size() == 0)
			return null;
		return orders.get(0);
	}

	// 订单NO,Id
	public List<CountGroupDTO> countMP(String mName, String pName) {
		boolean m = mName == null || mName.length() == 0;
		boolean p = pName == null || pName.length() == 0;
		if (m && p) {
			return null;
		}
		StringBufferWrapper sqlWrapper = new StringBufferWrapper()
				.append("select orderstate name,count(*) count from t_sys_orderinfo where ");
		if ((!m) && (!p)) {
			sqlWrapper.append("shopName like '%" + mName + "%' ").append(" and ")
					.append("productName like '%" + pName + "%' ");
		} else {
			if (!m)
				sqlWrapper.append("shopName like '%" + mName + "%' ");
			if (!p)
				sqlWrapper.append("productName like '%" + pName + "%' ");
		}
		sqlWrapper.append("group by orderstate");
		SQLQuery query = getCurrentSession().createSQLQuery(sqlWrapper.toString())
				.addScalar("name", StandardBasicTypes.STRING).addScalar("count", StandardBasicTypes.INTEGER);
		query.setResultTransformer(Transformers.aliasToBean(CountGroupDTO.class));
		return query.list();

	}

	// 获取订单列表
	public List<CountGroupDTO> pCount(String pmenus) {
		StringBufferWrapper sqlWrapper = new StringBufferWrapper()
				.append("select orderstate name,count(*) count from t_sys_orderinfo where ");

		sqlWrapper.append("source like '%" + pmenus + "%' ");
		sqlWrapper.append("group by orderstate");
		SQLQuery query = getCurrentSession().createSQLQuery(sqlWrapper.toString())
				.addScalar("name", StandardBasicTypes.STRING).addScalar("count", StandardBasicTypes.INTEGER);
		query.setResultTransformer(Transformers.aliasToBean(CountGroupDTO.class));
		return query.list();
	}

	// 结算
	public List<CountGroupDTO> findSettle(int pageNum, int pageSize) {

		StringBufferWrapper sqlWrapper = new StringBufferWrapper().append(
				"select * from (select shopId as id,shopName as name,count(*) as count,SUM(payMoney) as sumMoney from t_sys_orderinfo group by shopId) a ");
		System.out.println(sqlWrapper.toString());
		SQLQuery query = getCurrentSession().createSQLQuery(sqlWrapper.toString())
				.addScalar("id", StandardBasicTypes.LONG).addScalar("name", StandardBasicTypes.STRING)
				.addScalar("count", StandardBasicTypes.INTEGER).addScalar("sumMoney", StandardBasicTypes.LONG);

		query.setResultTransformer(Transformers.aliasToBean(CountGroupDTO.class));

		// 分页
		int firstResult = (pageNum - 1) * pageSize;
		query.setFirstResult(firstResult);
		query.setMaxResults(pageSize);
		return query.list();

	}

	// 结算总数
	public int countSettle() {
		StringBufferWrapper sqlWrapper = new StringBufferWrapper()
				.append("select COUNT(DISTINCT shopId) as shopCount  from t_sys_orderinfo ;");
		System.out.println(sqlWrapper.toString());
		SQLQuery query = getCurrentSession().createSQLQuery(sqlWrapper.toString()).addScalar("shopCount",
				StandardBasicTypes.INTEGER);
		int count = (int) query.uniqueResult();
		return count;
	}

	// 获取订单Id
	public List<OrderInfoPO> getAllOrder(long id, int page, int size) {
		QueryHql hql = newQueryHql();
		hql.andBy("productId", Condition.eq, id);
		List<OrderInfoPO> list = findByHqlPage(hql, page, size);
		if (list == null || list.size() == 0)
			return null;
		return list;
	}

	// 获取订单Id
	public List<OrderInfoPO> newgetAllOrder(long id) {
		QueryHql hql = newQueryHql();
		hql.andBy("productId", Condition.eq, id);
		List<OrderInfoPO> list = findByHql(hql);
		if (list == null || list.size() == 0)
			return null;
		return list;
	}

	// 获取订单列表
	public List<OrderInfoPO> getOrderList(long id, int page, int limit) {
		QueryHql hql = this.newQueryHql();
		hql.andBy("shopId", Condition.eq, id);
		List<OrderInfoPO> orderInfoPOs = this.findByHqlPage(hql, page, limit);
		if (orderInfoPOs == null || orderInfoPOs.size() <= 0)
			return null;
		return orderInfoPOs;
	}

	// 订单数量
	public int GetCountbyPage(long id) {

		CountHql chql = this.newCountHql();
		chql.andBy("shopId", Condition.eq, id);

		int allcount = this.countByHql(chql);
		return allcount;
	}

	// 获取导出详情表的信息
	public List<OrderInfoPO> getAllDeriveDetails(long id) {
		QueryHql hql = this.newQueryHql();
		hql.andBy("shopId", Condition.eq, id);
		List<OrderInfoPO> orderInfoPOs = this.findByHql(hql);
		if (orderInfoPOs == null || orderInfoPOs.size() <= 0)
			return null;
		return orderInfoPOs;
	}

	public List<Object> getOrdersGroupByProduct() {
		StringBufferWrapper sqlBuffer = new StringBufferWrapper().append(
				"SELECT count(*) a,productName from t_sys_orderinfo GROUP BY productName ORDER BY a DESC LIMIT 5;");
		List<Object> result = this.findBySql(sqlBuffer.toString());
		if (result == null || result.size() <= 0)
			return null;
		return result;
	}

	public List<Object> getOrdersGroupBymerchant() {
		StringBufferWrapper sqlBuffer = new StringBufferWrapper()
				.append("SELECT count(*) a,shopName from t_sys_orderinfo GROUP BY shopName ORDER BY a DESC LIMIT 5;");
		List<Object> result = this.findBySql(sqlBuffer.toString());
		if (result == null || result.size() <= 0)
			return null;
		return result;
	}

	// 根据身份证获的地址Id列表中的UserId查询订单列表信息
	public List<OrderInfoPO> getOrdersByIds(List<Long> ids) {
		if (ids == null || ids.isEmpty()) {
			return null;
		}
		QueryHql hql = this.newQueryHql();
		hql.andIn("mailAddress", ids);
		List<OrderInfoPO> c = this.findByHql(hql);
		if (c == null || c.size() <= 0)
			return null;
		return c;
	}

	// 获取渠道商订单信息
	public List<Object> getOrderByOrderSource() {
		StringBufferWrapper sqlBuffer = new StringBufferWrapper()
				.append("SELECT count(*) a,source from t_sys_orderinfo GROUP BY source ORDER BY a DESC;");
		List<Object> result = this.findBySql(sqlBuffer.toString());
		if (result == null || result.size() <= 0) {
			return null;
		}
		return result;
	}

	// 获取客源地
	public List<Object> findOrdersByStateByOrigin(String orderStateType, Long id) {
		StringBufferWrapper sqlBuffer = new StringBufferWrapper()
				.append("SELECT count(*) a, addr.district FROM t_sys_orderinfo AS orderinfo LEFT JOIN t_website_address AS addr ON orderinfo.mailAddress = addr.id")
				.append(" WHERE orderinfo.orderState = '").append(orderStateType + "' ")
				.append("   and orderinfo.productId =  ").append(id).append(" GROUP BY addr.district ORDER BY a DESC;");
		List<Object> result = this.findBySql(sqlBuffer.toString());
		if (result == null || result.size() <= 0) {
			return null;
		}
		return result;
	}

	// 获取年龄4Echarts
	public List<Object> findOrdersByStateByAge(String filed, long id) {
		StringBufferWrapper sqlBuffer = new StringBufferWrapper().append("SELECT CASE ")
				.append(" WHEN TIMESTAMPDIFF( YEAR, STR_TO_DATE( substr( address.idNum, 7, 8 ), '%Y%m%d' ), sysdate( ) ) >= 0 ")
				.append(" AND TIMESTAMPDIFF( YEAR, STR_TO_DATE( substr( address.idNum, 7, 8 ), '%Y%m%d' ), sysdate( ) ) <= 10 ")
				.append(" THEN '10岁以下' ")
				.append(" WHEN TIMESTAMPDIFF( YEAR, STR_TO_DATE( substr( address.idNum, 7, 8 ), '%Y%m%d' ), sysdate( ) ) >= 11 ")
				.append(" AND TIMESTAMPDIFF( YEAR, STR_TO_DATE( substr( address.idNum, 7, 8 ), '%Y%m%d' ), sysdate( ) ) <= 18 ")
				.append(" THEN '11~17岁' ")
				.append(" WHEN TIMESTAMPDIFF( YEAR, STR_TO_DATE( substr( address.idNum, 7, 8 ), '%Y%m%d' ), sysdate( ) ) >= 19 ")
				.append(" AND TIMESTAMPDIFF( YEAR, STR_TO_DATE( substr( address.idNum, 7, 8 ), '%Y%m%d' ), sysdate( ) ) <= 29 ")
				.append(" THEN '18~30岁' ")
				.append(" WHEN TIMESTAMPDIFF( YEAR, STR_TO_DATE( substr( address.idNum, 7, 8 ), '%Y%m%d' ), sysdate( ) ) >= 30 ")
				.append(" AND TIMESTAMPDIFF( YEAR, STR_TO_DATE( substr( address.idNum, 7, 8 ), '%Y%m%d' ), sysdate( ) ) <= 59 ")
				.append(" THEN '31~60岁' ")
				.append(" WHEN TIMESTAMPDIFF( YEAR, STR_TO_DATE( substr( address.idNum, 7, 8 ), '%Y%m%d' ), sysdate( ) ) >= 60 ")
				.append(" THEN '60岁以上' ").append(" END age, count(1) count ")
				.append(" FROM t_sys_orderinfo AS orderinfo ").append(" LEFT JOIN t_website_address AS address ")
				.append(" ON orderinfo.mailAddress = address.id ").append(" WHERE orderinfo.orderState = '")
				.append(filed + "' ").append(" and orderinfo.productId =  ").append(id).append(" GROUP BY age");
		List<Object> result = this.findBySql(sqlBuffer.toString());
		if (result == null || result.size() <= 0) {
			return null;
		}
		return result;
	}

	// 获取性别4Echarts
	public List<Object> findOrdersByStateByGender(String filed, long id) {
		StringBufferWrapper sqlBuffer = new StringBufferWrapper().append("SELECT CASE ")
				.append(" WHEN  substr( address.idNum, -2, 1 )%2=1 ").append(" THEN '男' ")
				.append(" WHEN  substr( address.idNum, -2, 1 )%2=0 ").append(" THEN '女' ")
				.append(" END gender, count(1) count ").append(" FROM t_sys_orderinfo AS orderinfo ")
				.append(" LEFT JOIN t_website_address AS address ").append(" ON orderinfo.mailAddress = address.id ")
				.append(" WHERE orderinfo.orderState = '").append(filed + "' ").append(" and orderinfo.productId =  ")
				.append(id).append(" GROUP BY gender");
		List<Object> result = this.findBySql(sqlBuffer.toString());
		if (result == null || result.size() <= 0) {
			return null;
		}
		return result;
	}

	// 获取游客趋势4Echarts
	public List<Object> findOrdersByStateByMonthForPeople(String filed, long id) {
		StringBufferWrapper sqlBuffer = new StringBufferWrapper()
				.append(" SELECT b.year_data AS 年份, a.old_month AS 月份, IFNULL( b.count_month, 0 ) AS 数量   FROM ")
				.append(" (SELECT DATE_FORMAT( DATE_SUB( CURDATE( ), INTERVAL 5 MONTH ), '%m' ) AS old_month UNION ALL ")
				.append(" SELECT DATE_FORMAT( DATE_SUB( CURDATE( ), INTERVAL 4 MONTH ), '%m' ) AS old_month UNION ALL ")
				.append(" SELECT DATE_FORMAT( DATE_SUB( CURDATE( ), INTERVAL 3 MONTH ), '%m' ) AS old_month UNION ALL ")
				.append(" SELECT DATE_FORMAT( DATE_SUB( CURDATE( ), INTERVAL 2 MONTH ), '%m' ) AS old_month UNION ALL ")
				.append(" SELECT DATE_FORMAT( DATE_SUB( CURDATE( ), INTERVAL 1 MONTH ), '%m' ) AS old_month UNION ALL ")
				.append(" SELECT DATE_FORMAT( CURDATE( ), '%m' ) AS old_month ) a ")
				.append(" LEFT JOIN ( SELECT YEAR(orderinfo.ydDate) AS year_data, ")
				.append(" DATE_FORMAT( orderinfo.ydDate, '%m' ) AS data_month, sum(orderinfo.productNum) AS count_month ")
				.append(" FROM  t_sys_orderinfo AS orderinfo ")
				.append(" LEFT JOIN t_website_address AS address ON orderinfo.mailAddress = address.id ")
				.append(" WHERE orderinfo.orderState = '").append(filed + "' ").append(" AND orderinfo.productId = ")
				.append(id)
				.append(" GROUP BY  DATE_FORMAT( orderinfo.ydDate, '%Y-%m' ) ) b ON a.old_month = b.data_month; ");
		List<Object> result = this.findBySql(sqlBuffer.toString());
		if (result == null || result.size() <= 0) {
			return null;
		}
		return result;
	}

	// 获取交易量
	public List<Object> findDealsByState(String filed, long id) {
		StringBufferWrapper sqlBuffer = new StringBufferWrapper().append("SELECT c.comName, o.source, count(1) a ")
				.append(" FROM t_sys_orderinfo o ").append(" LEFT JOIN t_sys_merchant m ON o.shopId = m.id ")
				.append(" LEFT JOIN t_sys_company c ON m.comId = c.id ").append(" WHERE o.orderState = '")
				.append(filed + "' ").append(" AND o.productId = ").append(id).append(" GROUP BY m.comId, o.source ")
				.append(" ORDER BY a DESC;");
		List<Object> result = this.findBySql(sqlBuffer.toString());
		if (result == null || result.size() <= 0) {
			return null;
		}
		return result;
	}

	// 获取交易e
	public List<Object> findOrdersByState(String filed, long id) {
		StringBufferWrapper sqlBuffer = new StringBufferWrapper()
				.append(" SELECT old_month, IFNULL( companyName, ''), IFNULL( money, 0) FROM ( ")
				.append(" SELECT DATE_FORMAT( DATE_SUB( CURDATE( ), INTERVAL 11 MONTH ), '%m' ) AS old_month UNION ALL ")
				.append(" SELECT DATE_FORMAT( DATE_SUB( CURDATE( ), INTERVAL 10 MONTH ), '%m' ) AS old_month UNION ALL ")
				.append(" SELECT DATE_FORMAT( DATE_SUB( CURDATE( ), INTERVAL 9 MONTH ), '%m' ) AS old_month UNION ALL ")
				.append(" SELECT DATE_FORMAT( DATE_SUB( CURDATE( ), INTERVAL 8 MONTH ), '%m' ) AS old_month UNION ALL ")
				.append(" SELECT DATE_FORMAT( DATE_SUB( CURDATE( ), INTERVAL 7 MONTH ), '%m' ) AS old_month UNION ALL ")
				.append(" SELECT DATE_FORMAT( DATE_SUB( CURDATE( ), INTERVAL 6 MONTH ), '%m' ) AS old_month UNION ALL ")
				.append(" SELECT DATE_FORMAT( DATE_SUB( CURDATE( ), INTERVAL 5 MONTH ), '%m' ) AS old_month UNION ALL ")
				.append(" SELECT DATE_FORMAT( DATE_SUB( CURDATE( ), INTERVAL 4 MONTH ), '%m' ) AS old_month UNION ALL ")
				.append(" SELECT DATE_FORMAT( DATE_SUB( CURDATE( ), INTERVAL 3 MONTH ), '%m' ) AS old_month UNION ALL ")
				.append(" SELECT DATE_FORMAT( DATE_SUB( CURDATE( ), INTERVAL 2 MONTH ), '%m' ) AS old_month UNION ALL ")
				.append(" SELECT DATE_FORMAT( DATE_SUB( CURDATE( ), INTERVAL 1 MONTH ), '%m' ) AS old_month UNION ALL ")
				.append(" SELECT DATE_FORMAT( CURDATE( ), '%m' ) AS old_month) a ")
				.append(" LEFT JOIN ( SELECT DATE_FORMAT( orderinfo.ydDate, '%m' ) AS DATETIME, c.comName AS companyName, SUM( orderinfo.payMoney ) AS money ")
				.append(" FROM t_sys_orderinfo AS orderinfo ")
				.append(" LEFT JOIN t_sys_merchant m ON orderinfo.shopId = m.id ")
				.append(" LEFT JOIN t_sys_company c ON m.comId = c.id  ").append(" WHERE ")
				.append(" orderinfo.orderState = '").append(filed + "' ").append(" AND orderinfo.productId = ")
				.append(id).append(" GROUP BY m.comId, DATETIME ORDER BY orderinfo.ydDate DESC ")
				.append(" ) b ON a.old_month = b.DATETIME ");
		List<Object> result = this.findBySql(sqlBuffer.toString());
		if (result == null || result.size() <= 0) {
			return null;
		}
		return result;
	}

	public List<Object> findOrdersByStateComIdProductId(String filed, long productId, Long companyId) {
		StringBufferWrapper sqlBuffer = new StringBufferWrapper().append(" SELECT old_month, IFNULL( money, 0) FROM ( ")
				.append(" SELECT DATE_FORMAT( DATE_SUB( CURDATE( ), INTERVAL 11 MONTH ), '%Y-%m' ) AS old_month UNION ALL ")
				.append(" SELECT DATE_FORMAT( DATE_SUB( CURDATE( ), INTERVAL 10 MONTH ), '%Y-%m' ) AS old_month UNION ALL ")
				.append(" SELECT DATE_FORMAT( DATE_SUB( CURDATE( ), INTERVAL 9 MONTH ), '%Y-%m' ) AS old_month UNION ALL ")
				.append(" SELECT DATE_FORMAT( DATE_SUB( CURDATE( ), INTERVAL 8 MONTH ), '%Y-%m' ) AS old_month UNION ALL ")
				.append(" SELECT DATE_FORMAT( DATE_SUB( CURDATE( ), INTERVAL 7 MONTH ), '%Y-%m' ) AS old_month UNION ALL ")
				.append(" SELECT DATE_FORMAT( DATE_SUB( CURDATE( ), INTERVAL 6 MONTH ), '%Y-%m' ) AS old_month UNION ALL ")
				.append(" SELECT DATE_FORMAT( DATE_SUB( CURDATE( ), INTERVAL 5 MONTH ), '%Y-%m' ) AS old_month UNION ALL ")
				.append(" SELECT DATE_FORMAT( DATE_SUB( CURDATE( ), INTERVAL 4 MONTH ), '%Y-%m' ) AS old_month UNION ALL ")
				.append(" SELECT DATE_FORMAT( DATE_SUB( CURDATE( ), INTERVAL 3 MONTH ), '%Y-%m' ) AS old_month UNION ALL ")
				.append(" SELECT DATE_FORMAT( DATE_SUB( CURDATE( ), INTERVAL 2 MONTH ), '%Y-%m' ) AS old_month UNION ALL ")
				.append(" SELECT DATE_FORMAT( DATE_SUB( CURDATE( ), INTERVAL 1 MONTH ), '%Y-%m' ) AS old_month UNION ALL ")
				.append(" SELECT DATE_FORMAT( CURDATE( ), '%Y-%m' ) AS old_month) a ")
				.append(" LEFT JOIN ( SELECT DATE_FORMAT( orderinfo.ydDate, '%Y-%m' ) AS DATETIME, SUM( orderinfo.payMoney ) AS money ")
				.append(" FROM t_sys_orderinfo AS orderinfo ")
				.append(" LEFT JOIN t_sys_merchant m ON orderinfo.shopId = m.id ")
				.append(" WHERE orderinfo.orderState = '").append(filed + "' ").append(" AND orderinfo.productId = ")
				.append(productId).append(" AND m.comId = ").append(companyId)
				.append(" GROUP BY DATETIME ORDER BY orderinfo.ydDate DESC ")
				.append(" ) b ON a.old_month = b.DATETIME ");
		List<Object> result = this.findBySql(sqlBuffer.toString());
		if (result == null || result.size() <= 0) {
			return null;
		}
		return result;
	}

	// 查询多个商户的订单
	public List<OrderInfoPO> findOrdersByMerchantMessage(long userId, Collection<Long> ids, OrderStateType state) {
		QueryHql hql = this.newQueryHql();
		hql.andBy("userId", Condition.eq, userId);
		hql.andBy("orderState", Condition.eq, state);
		hql.andBy("shopId", Condition.in, ids);
		hql.orderBy("createDate", true);
		List<OrderInfoPO> orders = findByHql(hql);
		return orders;
	}

	/**
	 * 预约购票新增 根据订单实时查询剩余可预约票数
	 * 
	 */
	public int countProductNumByDate(long id, String date) throws ParseException {
		String sqlString = "SELECT sum(productNum) as productCount FROM t_sys_orderinfo WHERE productId= '"+ id
				+"' AND (orderState= 'PAYSUCCESS' OR orderState= 'TESTED') AND Date(orderBookDate) = '"+ date +"'";
		SQLQuery query = getCurrentSession().createSQLQuery(sqlString).addScalar("productCount", StandardBasicTypes.INTEGER);
		int count = 0;
		if(query.uniqueResult() != null) {
			count = (int) query.uniqueResult();
		}		 
		return count;
	}
}
