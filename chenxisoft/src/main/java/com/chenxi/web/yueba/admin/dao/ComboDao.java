package com.chenxi.web.yueba.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chenxi.web.yueba.admin.po.ComboPo;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.Hql;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("CommentDao")
public class ComboDao extends AbstractBaseDao<ComboPo> {

	public ComboPo findByRegionAndDays(String region,int days){
		QueryHql hql=this.newQueryHql();
		hql.andBy("region",Condition.eq,region);
		hql.andBy("days",Condition.eq,days);
		List<ComboPo> comboPos=this.findByHql(hql);
		if(comboPos!=null&&!comboPos.isEmpty()){
			return comboPos.get(0);
		}else{
			return null;
		}
	}
}
