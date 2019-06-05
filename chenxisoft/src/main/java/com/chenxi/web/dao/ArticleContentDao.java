package com.chenxi.web.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chenxi.web.po.ArticleContentPo;
import com.chenxi.web.po.ArticlePo;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("ArticleContentDao")
public class ArticleContentDao extends AbstractBaseDao<ArticleContentPo>{

	public List<ArticleContentPo> findByAticleId(long articleId){
		QueryHql hql=this.newQueryHql();
		hql.andBy("articleId",Condition.eq,articleId);
		hql.orderBy("myindex", false);
		return this.findByHql(hql);
	}
}
