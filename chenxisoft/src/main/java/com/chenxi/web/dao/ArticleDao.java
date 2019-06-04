package com.chenxi.web.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chenxi.web.po.ArticlePo;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;
@Repository("ArticleDao")
public class ArticleDao extends AbstractBaseDao<ArticlePo> {

	
	public boolean ifhasByTitle(String title){
		QueryHql hql=this.newQueryHql();
		hql.andBy("title",Condition.eq,title);
		List<ArticlePo> articlePos=this.findByHql(hql);
		return (!articlePos.isEmpty()&&articlePos.size()!=0);
	}

}
