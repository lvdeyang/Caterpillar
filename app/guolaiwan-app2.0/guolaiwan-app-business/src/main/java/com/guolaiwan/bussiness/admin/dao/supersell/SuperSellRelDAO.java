package com.guolaiwan.bussiness.admin.dao.supersell;

import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.dto.MerchantDTO;
import com.guolaiwan.bussiness.admin.dto.ProductDTO;
import com.guolaiwan.bussiness.admin.po.ActivityPO;
import com.guolaiwan.bussiness.admin.po.ActivityRelPO;
import com.guolaiwan.bussiness.admin.po.AdminPO;
import com.guolaiwan.bussiness.admin.po.ColumnPO;
import com.guolaiwan.bussiness.admin.po.ModularPO;
import com.guolaiwan.bussiness.admin.po.supersell.SuperSellPO;
import com.guolaiwan.bussiness.admin.po.supersell.SuperSellRelPO;

import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;
import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.QueryHql;


@Repository("com.guolaiwan.bussiness.admin.dao.SuperSellRelDAO")
public class SuperSellRelDAO extends AbstractBaseDao<SuperSellRelPO> {

	public int countByProAndSellId(long proId,long sellId){
		CountHql hql=this.newCountHql();
		hql.andBy("supersellId",Condition.eq,sellId);
		hql.andBy("productId",Condition.eq,proId);
		return countByHql(hql);
	}
	public int countBysuperIdAndProduct(long supersellId,String pName,Long pId){
		CountHql hql=this.newCountHql();
		hql.andBy("supersellId", Condition.eq, supersellId);
		hql.andBy("productId", Condition.eq, pId);
		hql.andBy("productName", Condition.lk, pName);
		return countByHql(hql);
	}
	
	
	public List<SuperSellRelPO> findBysuperIdAndProduct(long supersellId,String pName,Long pId, int pageNum,int pageSize){
		QueryHql hql = this.newQueryHql(); 
		hql.andBy("supersellId", Condition.eq, supersellId);
		hql.andBy("productId", Condition.eq, pId);
		hql.andBy("productName", Condition.lk, pName);
		hql.orderBy("updateTime", true);
		List<SuperSellRelPO> superSellRelPOs = this.findByHqlPage(hql, pageNum, pageSize);
		return superSellRelPOs;
	}
	
	public List<SuperSellRelPO> findBysuperIdRandom(long supersellId,int pageSize){
		StringBufferWrapper sqlWrapper = new StringBufferWrapper().append(
				"select s.* from supersell_relation s where s.supersellId="+supersellId);
		sqlWrapper.append(" order by rand()");
		System.out.println(sqlWrapper.toString());
		SQLQuery query = getCurrentSession().createSQLQuery(sqlWrapper.toString())
				.addScalar("supersellId", StandardBasicTypes.LONG)
				.addScalar("productId", StandardBasicTypes.LONG)
				.addScalar("productName", StandardBasicTypes.STRING)
				.addScalar("productPic", StandardBasicTypes.STRING)
				.addScalar("merchantName", StandardBasicTypes.STRING)
				.addScalar("oldPrice", StandardBasicTypes.LONG)
				.addScalar("price", StandardBasicTypes.LONG);
		query.setResultTransformer(Transformers.aliasToBean(SuperSellRelPO.class));
		query.setMaxResults(pageSize);
		return query.list();
	}
	
}