package com.guolaiwan.bussiness.admin.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.dto.MerchantDTO;
import com.guolaiwan.bussiness.admin.dto.ProductDTO;
import com.guolaiwan.bussiness.admin.po.ActivityPO;
import com.guolaiwan.bussiness.admin.po.AdminPO;
import com.guolaiwan.bussiness.admin.po.ModularPO;

import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;
import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.QueryHql;


@Repository("com.guolaiwan.bussiness.admin.dao.ActivityDAO")
public class ActivityDAO extends AbstractBaseDao<ActivityPO> {

	//显示分公司 活动
	public List<ActivityPO> findByCom(long comId){
		QueryHql hql = this.newQueryHql();
		hql.andBy("comId", Condition.eq, comId);
		List<ActivityPO> activitys = findByHql(hql);
		return activitys;
	}

	//后台显示分公司 活动
	public List<ActivityPO> findByCom(long comId,int pageNum,int pageSize){
		QueryHql hql = this.newQueryHql();
		hql.andBy("comId", Condition.eq, comId);
		List<ActivityPO> activitys = findByHqlPage(hql, pageNum, pageSize);
		return activitys;
	}

	//后台显示分公司 活动 个数
	public int countByCom(long comId){
		CountHql cHql = this.newCountHql();
		cHql.andBy("comId", Condition.eq, comId);
		int  count = countByHql(cHql);
		return count;
	}

	/**
	 * 获取公司下的所有活动（App接口调用）
	 * @param comId 公司Id
	 * @return 活动s
	 */
	public List<ActivityPO> appFindBycomId(long comId){
		QueryHql hql = this.newQueryHql();
		hql.andBy("comId", Condition.eq, comId);
		List<ActivityPO> activitys = findByHql(hql);
		if(activitys==null || activitys.size()<=0) return null;
		return activitys;
	}


	/**
	 * 获取活动下的所有产品（App接口调用）
	 * @param comId 公司Id
	 * @return 活动s
	 */
	public List<ProductDTO> appFindProsByAct(long actId,int pageNum,int pageSize){
		StringBufferWrapper sqlWrapper = new StringBufferWrapper();
		sqlWrapper.append("select p.*,m.shopLongitude shopLongitude,m.shopLatitude shopLatitude from ");
		sqlWrapper.append("t_sys_modular_product p,t_sys_activity_relation a ,t_sys_merchant m ");
		sqlWrapper.append("where a.productId = p.id ");
		sqlWrapper.append("and m.id = p.productMerchantId ");
		sqlWrapper.append("and a.activityId= "+actId);
		System.out.println(sqlWrapper.toString());
		SQLQuery query = getCurrentSession().createSQLQuery(sqlWrapper.toString())
				.addScalar("id",StandardBasicTypes.LONG)
				.addScalar("updateTime",StandardBasicTypes.DATE)
				.addScalar("uuid",StandardBasicTypes.STRING)
				.addScalar("productBeginDate",StandardBasicTypes.DATE)
				.addScalar("productClassCode",StandardBasicTypes.STRING)
				.addScalar("productClassName",StandardBasicTypes.STRING)
				.addScalar("productCommissionCode",StandardBasicTypes.INTEGER)
				.addScalar("productCommissionPrice",StandardBasicTypes.LONG)
				.addScalar("productEctivedate",StandardBasicTypes.DATE)
				.addScalar("productEnddate",StandardBasicTypes.DATE)
				.addScalar("productIndexRecommend",StandardBasicTypes.INTEGER)
				.addScalar("productIntroduce",StandardBasicTypes.STRING)
				.addScalar("productIsShow",StandardBasicTypes.INTEGER)
				.addScalar("productLimitNum",StandardBasicTypes.LONG)
				.addScalar("productLimitType",StandardBasicTypes.INTEGER)
				.addScalar("productListRecommend",StandardBasicTypes.INTEGER)
				.addScalar("productMerchantName",StandardBasicTypes.STRING)
				.addScalar("productModularCode",StandardBasicTypes.STRING)
				.addScalar("productModularCodeName",StandardBasicTypes.STRING)
				.addScalar("productName",StandardBasicTypes.STRING)
				.addScalar("productOldPrice",StandardBasicTypes.LONG)
				.addScalar("productPrice",StandardBasicTypes.LONG)
				.addScalar("productSaleNum",StandardBasicTypes.LONG)
				.addScalar("productShowNum",StandardBasicTypes.LONG)
				.addScalar("productShowPic",StandardBasicTypes.STRING)
				.addScalar("shopLongitude",StandardBasicTypes.STRING)
				.addScalar("shopLatitude",StandardBasicTypes.STRING)
				.addScalar("productMorePic",StandardBasicTypes.STRING);

		query.setResultTransformer(Transformers.aliasToBean(ProductDTO.class));
		//分页
		int firstResult = (pageNum - 1) * pageSize;
		query.setFirstResult(firstResult);
		query.setMaxResults(pageSize);
		return query.list();
	}

	//首页活动的商品列表
	public List<ProductDTO> findProsByAct(long actId,int pageNum,int pageSize){
		StringBufferWrapper sqlWrapper = new StringBufferWrapper();
		sqlWrapper.append("select p.*,m.shopLongitude shopLongitude,m.shopLatitude shopLatitude from ");
		sqlWrapper.append("t_sys_modular_product p,t_sys_activity_relation a ,t_sys_merchant m ");
		sqlWrapper.append("where a.productId = p.id ");
		sqlWrapper.append("and m.id = p.productMerchantId ");
		sqlWrapper.append("and a.activityId= "+actId);
		System.out.println(sqlWrapper.toString());
		SQLQuery query = getCurrentSession().createSQLQuery(sqlWrapper.toString())
				.addScalar("id",StandardBasicTypes.LONG)
				.addScalar("updateTime",StandardBasicTypes.DATE)
				.addScalar("uuid",StandardBasicTypes.STRING)
				.addScalar("productBeginDate",StandardBasicTypes.DATE)
				.addScalar("productClassCode",StandardBasicTypes.STRING)
				.addScalar("productClassName",StandardBasicTypes.STRING)
				.addScalar("productCommissionCode",StandardBasicTypes.INTEGER)
				.addScalar("productCommissionPrice",StandardBasicTypes.LONG)
				.addScalar("productEctivedate",StandardBasicTypes.DATE)
				.addScalar("productEnddate",StandardBasicTypes.DATE)
				.addScalar("productIndexRecommend",StandardBasicTypes.INTEGER)
				.addScalar("productIntroduce",StandardBasicTypes.STRING)
				.addScalar("productIsShow",StandardBasicTypes.INTEGER)
				.addScalar("productLimitNum",StandardBasicTypes.LONG)
				.addScalar("productLimitType",StandardBasicTypes.INTEGER)
				.addScalar("productListRecommend",StandardBasicTypes.INTEGER)
				.addScalar("productMerchantName",StandardBasicTypes.STRING)
				.addScalar("productModularCode",StandardBasicTypes.STRING)
				.addScalar("productModularCodeName",StandardBasicTypes.STRING)
				.addScalar("productName",StandardBasicTypes.STRING)
				.addScalar("productOldPrice",StandardBasicTypes.LONG)
				.addScalar("productPrice",StandardBasicTypes.LONG)
				.addScalar("productSaleNum",StandardBasicTypes.LONG)
				.addScalar("productShowNum",StandardBasicTypes.LONG)
				.addScalar("productShowPic",StandardBasicTypes.STRING)
				.addScalar("shopLongitude",StandardBasicTypes.STRING)
				.addScalar("shopLatitude",StandardBasicTypes.STRING)
				.addScalar("productMorePic",StandardBasicTypes.STRING);

		query.setResultTransformer(Transformers.aliasToBean(ProductDTO.class));
		//分页
		int firstResult = (pageNum - 1) * pageSize;
		query.setFirstResult(firstResult);
		query.setMaxResults(pageSize);
		return query.list();
	}

	//活动页面的商品列表：（商品名称检索）
	public List<ProductDTO> findProsByActJS(Map<String, Object> map,int pageNum,int pageSize){
		StringBufferWrapper sqlWrapper = new StringBufferWrapper();
		sqlWrapper.append("select p.*,m.shopLongitude shopLongitude,m.shopLatitude shopLatitude from ");
		sqlWrapper.append("t_sys_modular_product p,t_sys_activity_relation a ,t_sys_merchant m ");
		sqlWrapper.append("where a.productId = p.id ");
		sqlWrapper.append("and m.id = p.productMerchantId ");
		for (Map.Entry<String,Object> entry : map.entrySet()) {
			if(entry.getKey().equals("productName")){
				sqlWrapper.append(" and p.productName like '%"+entry.getValue()+"%'");
			}else{
				sqlWrapper.append(" and a.activityId = "+entry.getValue());
			}

		}

		System.out.println(sqlWrapper.toString());
		SQLQuery query = getCurrentSession().createSQLQuery(sqlWrapper.toString())
				.addScalar("id",StandardBasicTypes.LONG)
				.addScalar("updateTime",StandardBasicTypes.DATE)
				.addScalar("uuid",StandardBasicTypes.STRING)
				.addScalar("productBeginDate",StandardBasicTypes.DATE)
				.addScalar("productClassCode",StandardBasicTypes.STRING)
				.addScalar("productClassName",StandardBasicTypes.STRING)
				.addScalar("productCommissionCode",StandardBasicTypes.INTEGER)
				.addScalar("productCommissionPrice",StandardBasicTypes.LONG)
				.addScalar("productEctivedate",StandardBasicTypes.DATE)
				.addScalar("productEnddate",StandardBasicTypes.DATE)
				.addScalar("productIndexRecommend",StandardBasicTypes.INTEGER)
				.addScalar("productIntroduce",StandardBasicTypes.STRING)
				.addScalar("productIsShow",StandardBasicTypes.INTEGER)
				.addScalar("productLimitNum",StandardBasicTypes.LONG)
				.addScalar("productLimitType",StandardBasicTypes.INTEGER)
				.addScalar("productListRecommend",StandardBasicTypes.INTEGER)
				.addScalar("productMerchantName",StandardBasicTypes.STRING)
				.addScalar("productModularCode",StandardBasicTypes.STRING)
				.addScalar("productModularCodeName",StandardBasicTypes.STRING)
				.addScalar("productName",StandardBasicTypes.STRING)
				.addScalar("productOldPrice",StandardBasicTypes.LONG)
				.addScalar("productPrice",StandardBasicTypes.LONG)
				.addScalar("productSaleNum",StandardBasicTypes.LONG)
				.addScalar("productShowNum",StandardBasicTypes.LONG)
				.addScalar("productShowPic",StandardBasicTypes.STRING)
				.addScalar("shopLongitude",StandardBasicTypes.STRING)
				.addScalar("shopLatitude",StandardBasicTypes.STRING)
				.addScalar("productMorePic",StandardBasicTypes.STRING);

		query.setResultTransformer(Transformers.aliasToBean(ProductDTO.class));
		//分页
		int firstResult = (pageNum - 1) * pageSize;
		query.setFirstResult(firstResult);
		query.setMaxResults(pageSize);
		return query.list();
	}

	//活动页面的商品列表：（商品名称检索）
	public int countProsByActJS(Map<String, Object> map){
		StringBufferWrapper sqlWrapper = new StringBufferWrapper();
		sqlWrapper.append("select count(*) acount from ");
		sqlWrapper.append("t_sys_modular_product p,t_sys_activity_relation a ,t_sys_merchant m ");
		sqlWrapper.append("where a.productId = p.id ");
		sqlWrapper.append(" and m.id = p.productMerchantId ");
		for (Map.Entry<String,Object> entry : map.entrySet()) {
			if(entry.getKey().equals("productName")){
				sqlWrapper.append(" and p.productName like '%"+entry.getValue()+"%'");
			}else{
				sqlWrapper.append(" and a.activityId = "+entry.getValue());
			}

		}

		System.out.println(sqlWrapper.toString());
		SQLQuery query = getCurrentSession().createSQLQuery(sqlWrapper.toString())
				.addScalar("acount",StandardBasicTypes.INTEGER);

		if(query.uniqueResult()==null) return 0;
		int acount = (int)query.uniqueResult();
		return acount;
	}
}