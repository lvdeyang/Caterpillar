package com.guolaiwan.bussiness.user.dao;

import java.util.Collection;
import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;
import com.guolaiwan.bussiness.chapman.enumeration.TargetType;
import com.guolaiwan.bussiness.chapman.product.enumeration.ProductDetailType;
import com.guolaiwan.bussiness.chapman.source.enumeration.SourceMapType;
import com.guolaiwan.bussiness.user.dto.BasketDTO;
import com.guolaiwan.bussiness.user.po.BasketPO;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;
import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;

@Repository
public class BasketDAO extends AbstractBaseDao<BasketPO> {
	
	//获取用户特定的购物车
	public List<BasketPO> getBasketByUser(Long userId, Collection<Long> basketIds){
		if(basketIds==null || basketIds.size()<=0) return null;
		QueryHql hql = this.newQueryHql();
		hql.andBy("user.id", Condition.eq, userId);
		hql.andBy("id", Condition.in, basketIds);
		return this.findByHql(hql);
	}
	
	//通过用户商品获取购物车
	public BasketPO getBasketByUserAndProduct(Long userId, Long productId){
		QueryHql hql = this.newQueryHql();
		hql.andBy("user.id", Condition.eq, userId);
		hql.andBy("product.id", Condition.eq, productId);
		List<BasketPO> baskets = this.findByHql(hql);
		if(baskets==null || baskets.size()<=0) return null;
		return baskets.get(0);
	}
	
	//获取用户购物车
	public List<BasketDTO> getBasketByUser(Long userId){
		
		StringBufferWrapper sqlWrapper = new StringBufferWrapper().append("select b.id, b.updateTime, b.num, ")
																  .append("p.introduction, pd.price, ")
																  .append("b.num*pd.price count, f.content image ")
																  .append("FROM t_app_basket b ")
																  .append("LEFT JOIN t_app_product p ON b.product_id=p.id ")
																  .append("LEFT JOIN t_app_product_detail pd ON pd.product_id=p.id ")
																  .append("LEFT JOIN t_app_source_map sm ON sm.target_id=pd.id ")
																  .append("LEFT JOIN t_app_source s ON sm.source_id=s.id ")
																  .append("LEFT JOIN t_app_file f ON s.file_id=f.id ")
																  .append("WHERE b.user_id=")
																  .append(userId)
																  .append(" ")
																  .append("AND pd.type='")
																  .append(ProductDetailType.PRODUCT.toString())
																  .append("' ")
																  .append("AND sm.target_type='")
																  .append(TargetType.PRODUCTDETAIL.toString())
																  .append("' ")
																  .append("AND sm.type='")
																  .append(SourceMapType.THUMBNAIL.toString())
																  .append("'");
		System.out.println(sqlWrapper.toString());
		SQLQuery query = getCurrentSession().createSQLQuery(sqlWrapper.toString())
											.addScalar("id", StandardBasicTypes.LONG)
											.addScalar("updateTime", StandardBasicTypes.DATE)
											.addScalar("num", StandardBasicTypes.INTEGER)
											.addScalar("introduction")
											.addScalar("price", StandardBasicTypes.LONG)
											.addScalar("count", StandardBasicTypes.LONG)
											.addScalar("image", StandardBasicTypes.BINARY);
		
		query.setResultTransformer(Transformers.aliasToBean(BasketDTO.class));
		
		return query.list();
	}
	
	
	
	
	
}
