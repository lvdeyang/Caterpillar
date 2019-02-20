package com.guolaiwan.bussiness.order.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.chapman.enumeration.TargetType;
import com.guolaiwan.bussiness.chapman.product.enumeration.ProductDetailType;
import com.guolaiwan.bussiness.chapman.source.enumeration.SourceMapType;
import com.guolaiwan.bussiness.order.dto.OrderDetailDTO;
import com.guolaiwan.bussiness.order.dto.OrderDetailStatisticsBarDTO;
import com.guolaiwan.bussiness.order.dto.OrderDetailStatisticsPieDTO;
import com.guolaiwan.bussiness.order.dto.OrderDetailStatisticsTableDTO;
import com.guolaiwan.bussiness.order.enumeration.OrderStatusType;
import com.guolaiwan.bussiness.order.po.OrderDetailPO;
import com.sun.tools.doclets.formats.html.resources.standard;

import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;
import pub.caterpillar.orm.dao.AbstractBaseDao;

@Repository
public class OrderDetailDAO extends AbstractBaseDao<OrderDetailPO>{

	//获取用户订单
	public List<OrderDetailDTO> getOrderDetailByUser(Long userId){
		
		StringBufferWrapper sqlWrapper = new StringBufferWrapper().append("SELECT od.id id, od.uuid uuid, od.num num, ")
																  .append("o.ordertime orderTime, o.status status, ")
																  .append("p.introduction introduction, pd.price*od.num count, ")
																  .append("c.chapmanname chapman, ")
																  .append("f.content image ")
																  .append("FROM t_app_order_detail od ")
																  .append("LEFT JOIN t_app_order o ON od.order_id=o.id ")
																  .append("LEFT JOIN t_app_product p ON od.product_id=p.id ")
																  .append("LEFT JOIN t_app_chapman c ON od.chapman_id=c.id ")
																  .append("LEFT JOIN t_app_product_detail pd ON pd.product_id=p.id ")
																  .append("LEFT JOIN t_app_source_map sm ON sm.target_id=pd.id ")
																  .append("LEFT JOIN t_app_source s ON sm.source_id=s.id ")
																  .append("LEFT JOIN t_app_file f ON s.file_id=f.id ")
																  .append("WHERE pd.type='")
																  .append(ProductDetailType.PRODUCT.toString())
																  .append("' ")
																  .append("AND sm.target_type='")
																  .append(TargetType.PRODUCTDETAIL.toString())
																  .append("' ")
																  .append("AND sm.type='")
																  .append(SourceMapType.THUMBNAIL.toString())
																  .append("' ")
																  .append("AND o.user_id=")
																  .append(userId);
		SQLQuery query = getCurrentSession().createSQLQuery(sqlWrapper.toString())
											.addScalar("id", StandardBasicTypes.LONG)
											.addScalar("uuid")
											.addScalar("num", StandardBasicTypes.INTEGER)
											.addScalar("orderTime", StandardBasicTypes.DATE)
											.addScalar("status")
											.addScalar("introduction")
											.addScalar("count", StandardBasicTypes.LONG)
											.addScalar("chapman")
											.addScalar("image", StandardBasicTypes.BINARY);
		query.setResultTransformer(Transformers.aliasToBean(OrderDetailDTO.class));
		return query.list();
	}
	
	//根据订单id获取订单明细
	public List<OrderDetailDTO> getOrderDetailByOrderId(Long orderId){
		
		StringBufferWrapper sqlWrapper = new StringBufferWrapper().append("SELECT od.id id, od.uuid uuid, od.num num, ")
																  .append("o.ordertime orderTime, o.status status, ")
																  .append("p.introduction introduction, pd.price*od.num count, ")
																  .append("c.chapmanname chapman, ")
																  .append("f.content image ")
																  .append("FROM t_app_order_detail od ")
																  .append("LEFT JOIN t_app_order o ON od.order_id=o.id ")
																  .append("LEFT JOIN t_app_product p ON od.product_id=p.id ")
																  .append("LEFT JOIN t_app_chapman c ON od.chapman_id=c.id ")
																  .append("LEFT JOIN t_app_product_detail pd ON pd.product_id=p.id ")
																  .append("LEFT JOIN t_app_source_map sm ON sm.target_id=pd.id ")
																  .append("LEFT JOIN t_app_source s ON sm.source_id=s.id ")
																  .append("LEFT JOIN t_app_file f ON s.file_id=f.id ")
																  .append("WHERE pd.type='")
																  .append(ProductDetailType.PRODUCT.toString())
																  .append("' ")
																  .append("AND sm.target_type='")
																  .append(TargetType.PRODUCTDETAIL.toString())
																  .append("' ")
																  .append("AND sm.type='")
																  .append(SourceMapType.THUMBNAIL.toString())
																  .append("' ")
																  .append("AND o.id=")
																  .append(orderId);
		SQLQuery query = getCurrentSession().createSQLQuery(sqlWrapper.toString())
											.addScalar("id", StandardBasicTypes.LONG)
											.addScalar("uuid")
											.addScalar("num", StandardBasicTypes.INTEGER)
											.addScalar("orderTime", StandardBasicTypes.DATE)
											.addScalar("status")
											.addScalar("introduction")
											.addScalar("count", StandardBasicTypes.LONG)
											.addScalar("chapman")
											.addScalar("image", StandardBasicTypes.BINARY);
		query.setResultTransformer(Transformers.aliasToBean(OrderDetailDTO.class));
		return query.list();
	}
	
	//商户订单统计列表
	public List<OrderDetailStatisticsTableDTO> orderDetailStatisticsTable(Long chapmanId, int pageNum, int pageSize) {
		
		StringBufferWrapper sqlWrapper = new StringBufferWrapper().append("select o.uuid orderId, o.ordertime orderTime, ")
																  .append("p.introduction product, p.type productType, ")
																  .append("od.num num, od.num*pd.price count, ")
																  .append("o.status status, u.realName buyer ")
																  .append("from t_app_order_detail od ")
																  .append("LEFT JOIN t_app_order o on od.order_id=o.id ")
																  .append("LEFT JOIN t_app_user u on o.user_id=u.id ")
																  .append("LEFT JOIN t_app_product p on od.product_id=p.id ")
																  .append("LEFT JOIN t_app_product_detail pd on pd.product_id=p.id ")
																  .append("where od.chapman_id=")
																  .append(chapmanId)
																  .append(" ")
																  .append("ORDER BY p.type asc, o.ordertime desc");
		SQLQuery query = getCurrentSession().createSQLQuery(sqlWrapper.toString())
											.addScalar("orderId")
											.addScalar("orderTime", StandardBasicTypes.TIMESTAMP)
											.addScalar("product")
											.addScalar("productType")
											.addScalar("num")
											.addScalar("count", StandardBasicTypes.INTEGER)
											.addScalar("status")
											.addScalar("buyer");

		query.setResultTransformer(Transformers.aliasToBean(OrderDetailStatisticsTableDTO.class));
		
		//分页
		int firstResult = (pageNum - 1) * pageSize;
		query.setFirstResult(firstResult);
		query.setMaxResults(pageSize);
		
		return query.list();
	}
	
	//销售总额饼图
	public List<OrderDetailStatisticsPieDTO> orderDetailStatisticsPie(Long chapmanId){
		
		StringBufferWrapper sqlWrapper = new StringBufferWrapper().append("select p.type type, sum(od.num*pd.price) count from t_app_order_detail od ")
																  .append("LEFT JOIN t_app_order o on od.order_id=o.id ")
																  .append("LEFT JOIN t_app_product p on od.product_id=p.id ")
																  .append("LEFT JOIN t_app_product_detail pd on pd.product_id=p.id ")
																  .append("where od.chapman_id=")
																  .append(chapmanId)
																  .append(" ")
																  .append("AND o.status='")
																  .append(OrderStatusType.PAID.toString())
																  .append("' ")
																  .append("GROUP BY p.type");
		
		SQLQuery query = getCurrentSession().createSQLQuery(sqlWrapper.toString())
											.addScalar("type")
											.addScalar("count", StandardBasicTypes.LONG);
		
		query.setResultTransformer(Transformers.aliasToBean(OrderDetailStatisticsPieDTO.class));
		
		return query.list();
	}
	
	//月销售额柱状图
	public List<OrderDetailStatisticsBarDTO> orderDetailStatisticsBar(Long chapmanId){
		
		StringBufferWrapper sqlWrapper = new StringBufferWrapper().append("select tmp.timeaxis orderMonth, tmp.product productType, IFNULL(sr.count,0) count from ")
																  .append("(")
																  .append("SELECT ta.code timeaxis, pt.name product FROM ")
																  .append("(select code from t_app_code_list WHERE level_1='TIMEAXIS' and code BETWEEN '2017/01' AND '2017/07') ta,")
																  .append("(select NAME from t_app_code_list where level_1='PRODUCTTYPE') pt ")
																  .append(") tmp ")
																  .append("LEFT JOIN ")
																  .append("(")
																  .append("select DATE_FORMAT(o.ordertime, '%Y/%m') orderMonth, p.type productType, sum(od.num*pd.price) count ")
																  .append("from t_app_order_detail od ")
																  .append("LEFT JOIN t_app_order o on od.order_id=o.id ")
																  .append("LEFT JOIN t_app_product p on od.product_id=p.id ")
																  .append("LEFT JOIN t_app_product_detail pd on pd.product_id=p.id ")
																  .append("where o.ordertime BETWEEN '2017/01/01' AND '2017/07/30' and od.chapman_id=")
																  .append(chapmanId)
																  .append(" ")
																  .append("and o.status='PAID' ")
																  .append("GROUP BY orderMonth, p.type ")
																  .append(") sr ")
																  .append("ON tmp.timeaxis=sr.orderMonth and tmp.product=sr.productType ")
																  .append("ORDER BY orderMonth");
		SQLQuery query = getCurrentSession().createSQLQuery(sqlWrapper.toString())
											.addScalar("orderMonth")
											.addScalar("productType")
											.addScalar("count", StandardBasicTypes.LONG);
		
		query.setResultTransformer(Transformers.aliasToBean(OrderDetailStatisticsBarDTO.class));
		
		return query.list();
	}
	
}
