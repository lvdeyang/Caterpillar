package com.guolaiwan.bussiness.chapman.product.dao;

import java.util.Collection;
import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;
import com.guolaiwan.bussiness.chapman.enumeration.TargetType;
import com.guolaiwan.bussiness.chapman.product.dto.ProductDTO;
import com.guolaiwan.bussiness.chapman.product.enumeration.ProductDetailType;
import com.guolaiwan.bussiness.chapman.product.enumeration.ProductType;
import com.guolaiwan.bussiness.chapman.product.po.ProductPO;
import com.guolaiwan.bussiness.chapman.source.enumeration.SourceMapType;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;
import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;

@Repository
public class ProductDAO extends AbstractBaseDao<ProductPO>{

	//获取一个商户下的所有景点
	public List<ProductDTO> getChapmanScenerys(Long chapmanId){
		StringBufferWrapper sqlWrapper = new StringBufferWrapper().append("SELECT p.id id, p.uuid uuid, p.updateTime updateTime, p.name, ")
																  .append("p.introduction introduction, p.position position, p.price price ")
																  .append("FROM t_app_product p ")
																  .append("LEFT JOIN t_app_shelve_product sp ON sp.product_id=p.id ")
																  .append("WHERE sp.id is null ")
																  .append("and p.type='")
																  .append(ProductType.SCENERY.toString())
																  .append("' ")
																  .append("and p.chapman_id=")
																  .append(chapmanId);
		
		SQLQuery query = getCurrentSession().createSQLQuery(sqlWrapper.toString())
											.addScalar("id", StandardBasicTypes.LONG)
											.addScalar("uuid")
											.addScalar("updateTime", StandardBasicTypes.TIMESTAMP)
											.addScalar("name")
											.addScalar("introduction")
											.addScalar("position")
											.addScalar("price", StandardBasicTypes.LONG);
		
		query.setResultTransformer(Transformers.aliasToBean(ProductDTO.class));

		return query.list();
	}
	
	//获取商户下特定的景点
	public List<ProductPO> getChapmanScenerys(Long chapmanId, Collection<Long> sceneryIds){
		if(sceneryIds==null || sceneryIds.size()<=0) return null;
		QueryHql hql = this.newQueryHql();
		hql.andBy("chapman.id", Condition.eq, chapmanId);
		hql.andBy("id", Condition.in, sceneryIds);
		return this.findByHql(hql);
	}
	
	//获取一个景点
	public ProductPO getScenery(Long sceneryId){
		QueryHql hql = this.newQueryHql();
		hql.andBy("id", Condition.eq, sceneryId);
		hql.andBy("type", Condition.eq, ProductType.SCENERY);
		List<ProductPO> scenerys = this.findByHql(hql);
		if(scenerys==null || scenerys.size()<=0) return null;
		return scenerys.get(0);
	}
	
	//前端--获取产品列表
	public List<ProductDTO> getProductHasDetails(Long menuId, int pageNum, int pageSize){
		
		StringBufferWrapper sqlWrapper = new StringBufferWrapper().append("SELECT P.ID id, P.UPDATETIME updateTime, ")
																  .append("P.UUID uuid, P.INTRODUCTION title, ")
																  .append("P.POSITION position, PD.TITLE subTitle, ")
																  .append("PD.INTRODUCTION introduction, PD.PRICE price, ")
																  .append("F.CONTENT image ")
																  .append("FROM T_APP_PRODUCT P ")
																  .append("LEFT JOIN T_APP_PRODUCT_DETAIL PD ON P.ID=PD.PRODUCT_ID ")
																  .append("LEFT JOIN T_APP_SOURCE_MAP SM ON PD.ID=SM.TARGET_ID ")
																  .append("LEFT JOIN T_APP_SOURCE S ON SM.SOURCE_ID=S.ID ")
																  .append("LEFT JOIN T_APP_FILE F ON S.FILE_ID=F.ID ")
																  .append("LEFT JOIN T_APP_SHELVE_PRODUCT SP ON SP.PRODUCT_ID=P.ID ")
																  .append("WHERE SP.MENU_ID=")
																  .append(menuId)
																  .append(" AND PD.TYPE='")
																  .append(ProductDetailType.PRODUCT.toString())
																  .append("' AND SM.TARGET_TYPE='")
																  .append(TargetType.PRODUCTDETAIL.toString())
																  .append("' AND SM.TYPE='")
																  .append(SourceMapType.HOMEPAGE.toString())
																  .append("'");
		SQLQuery query = getCurrentSession().createSQLQuery(sqlWrapper.toString())
											.addScalar("id", StandardBasicTypes.LONG)
											.addScalar("updateTime", StandardBasicTypes.TIMESTAMP)
											.addScalar("uuid")
											.addScalar("title")
											.addScalar("position")
											.addScalar("subTitle")
											.addScalar("introduction")
											.addScalar("price", StandardBasicTypes.LONG)
											.addScalar("image", StandardBasicTypes.BINARY);
		
		query.setResultTransformer(Transformers.aliasToBean(ProductDTO.class));

		//分页
		int firstResult = (pageNum - 1) * pageSize;
		query.setFirstResult(firstResult);
		query.setMaxResults(pageSize);
		
		return query.list();
	}
	
	//获取商户栏目下的货架商品
	public List<ProductDTO> getProductByMenu(Long chapmanId, Long menuId){
		
		StringBufferWrapper sqlWrapper = new StringBufferWrapper().append("SELECT p.id id, p.updateTime updateTime, p.uuid uuid, ")
																  .append("p.introduction introduction, p.position position ")
																  .append("FROM t_app_product p ")
																  .append("LEFT JOIN t_app_shelve_product sp ON sp.product_id=p.id ")
																  .append("WHERE sp.menu_id=")
																  .append(menuId)
																  .append(" AND p.chapman_id=")
																  .append(chapmanId);
		SQLQuery query = getCurrentSession().createSQLQuery(sqlWrapper.toString())
											.addScalar("id", StandardBasicTypes.LONG)
											.addScalar("updateTime", StandardBasicTypes.TIMESTAMP)
											.addScalar("uuid")
											.addScalar("introduction")
											.addScalar("position");
		
		query.setResultTransformer(Transformers.aliasToBean(ProductDTO.class));
		
		return query.list();
	}
	
	
	
}
