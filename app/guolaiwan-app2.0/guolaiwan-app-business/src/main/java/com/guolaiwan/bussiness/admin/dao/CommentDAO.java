package com.guolaiwan.bussiness.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.po.CommentPO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.QueryHql;


@Repository("com.guolaiwan.bussiness.admin.dao.CommentDAO")
public class CommentDAO extends AbstractBaseDao<CommentPO> {
	
	/**
	 * 商品下的评论
	 * @param proId
	 * @return comments
	 */
	public List<CommentPO> findByPro(long proId,int pageNum,int pageSize){
		QueryHql hql = this.newQueryHql();
		hql.andBy("proId", Condition.eq, proId);
		hql.orderBy("userDate", true);   //用户评论的时间排序
		List<CommentPO> comments = this.findByHqlPage(hql, pageNum, pageSize);
		return comments;
		
	}
	/**
	 * 商品下的评论个数
	 * @param proId
	 * @return comments
	 */
	public int countByPro(long proId){
		CountHql cHql = this.newCountHql();
		cHql.andBy("proId", Condition.eq, proId);
		int count  = this.countByHql(cHql);
		return count;
		
	}
	
	public int countByuserId(long userId){
		CountHql cHql = this.newCountHql();
		cHql.andBy("user_id", Condition.eq, userId);
		int count  = this.countByHql(cHql);
		return count;
		
	}
	
	public List<CommentPO> findByProId(long proId,int pageNum,int pageSize){
		QueryHql hql = this.newQueryHql();
		hql.andBy("proId", Condition.eq, proId);
		hql.orderBy("userDate", true);   //用户评论的时间排序
		List<CommentPO> comments = this.findByHqlPage(hql, pageNum, pageSize);
		return comments;
		
	}
	
	public CommentPO getComById(long id){
		QueryHql hql = this.newQueryHql();
		hql.andBy("id", Condition.eq, id);
		List<CommentPO> list = findByHql(hql);
		return list.get(0);
	}
	
	public List<CommentPO> getChildPL(long proId){
		QueryHql hql = this.newQueryHql();
		hql.andBy("proId", Condition.eq, proId);
		List<CommentPO> list = findByHql(hql);
		return list;
	}
	
}
