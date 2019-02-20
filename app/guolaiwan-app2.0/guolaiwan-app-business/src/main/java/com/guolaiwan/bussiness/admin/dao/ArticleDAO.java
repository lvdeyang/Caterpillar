package com.guolaiwan.bussiness.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.po.ArticlePO;
import com.guolaiwan.bussiness.admin.po.ClassPO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("com.guolaiwan.bussiness.admin.dao.ArticleDAO")
public class ArticleDAO extends AbstractBaseDao<ArticlePO>{
	public ArticlePO getArticleByName(String articleName){
	   	 QueryHql hql = this.newQueryHql();
	   	 hql.andBy("article", Condition.eq, articleName);
	   	 List<ArticlePO> articles = findByHql(hql);
	   	 if(articles==null || articles.size()<=0) return null;
	   	 return articles.get(0);
	    }
	public List<ArticlePO> GetListbyPage(int pageNum,int pageSize) {
		 QueryHql hql = this.newQueryHql();
		
		 hql.orderBy("id", true);
	   	 List<ArticlePO> articles =findByHqlPage(hql,pageNum,pageSize);
	   	 if(articles==null || articles.size()<=0) return null;
	   	 return articles;
	}
	//统计总数
	public int  GetCountByPage() {
		CountHql cHql=this.newCountHql();
		
		int allcount=this.countByHql(cHql);
		return allcount;
	}


}
