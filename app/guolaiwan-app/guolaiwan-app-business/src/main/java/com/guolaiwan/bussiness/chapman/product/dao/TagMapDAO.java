package com.guolaiwan.bussiness.chapman.product.dao;

import java.util.Collection;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.chapman.product.dto.TagMapDTO;
import com.guolaiwan.bussiness.chapman.product.po.TagMapPO;
import com.guolaiwan.bussiness.chapman.source.dto.SourceMapDTO;

import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;
import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;

@Repository
public class TagMapDAO extends AbstractBaseDao<TagMapPO> {

	//通过tagId获取标签
	public List<TagMapPO> gatTagMapsByTagId(Collection<Long> tagIds){
		if(tagIds==null || tagIds.size()<=0) return null;
		QueryHql hql = this.newQueryHql();
		hql.andBy("tag", Condition.in, tagIds);
		return this.findByHql(hql);
	}
	
	//获取商品标签
	public List<TagMapDTO> getTagByProduct(Long productId) {
		StringBufferWrapper sqlWrapper = new StringBufferWrapper().append("SELECT tm.id id, tm.uuid uuid, tm.updateTime updateTime, cl.name tagName ")
																  .append("FROM t_app_tag_map tm ")
																  .append("LEFT JOIN t_app_shelve_product sp ON tm.shelve_product_id=sp.id ")
																  .append("LEFT JOIN t_app_product p ON sp.product_id=p.id ")
																  .append("LEFT JOIN t_app_code_list cl ON tm.tag=cl.id ")
																  .append("WHERE p.id=")
																  .append(productId);
		SQLQuery query = getCurrentSession().createSQLQuery(sqlWrapper.toString())
											.addScalar("id", StandardBasicTypes.LONG)
											.addScalar("uuid")
											.addScalar("updateTime", StandardBasicTypes.DATE)
											.addScalar("tagName");
		
		query.setResultTransformer(Transformers.aliasToBean(SourceMapDTO.class));
		
		return query.list();
	}
	
}
