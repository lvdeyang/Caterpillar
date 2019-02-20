package com.guolaiwan.bussiness.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.enumeration.VideoPicType;
import com.guolaiwan.bussiness.admin.po.ArticlePO;
import com.guolaiwan.bussiness.admin.po.VPCommentPO;
import com.guolaiwan.bussiness.admin.po.VideoPicPO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.QueryHql;


@Repository("com.guolaiwan.bussiness.admin.dao.VideoPicDAO")
public class VideoPicDAO extends AbstractBaseDao<VideoPicPO> {
	
	
	public List<VideoPicPO> findAllDesc(int pageNum,int pageSize,VideoPicType sType,String sName,Long userId){
		QueryHql hql = newQueryHql();
		if(sType!=null)
		{
		
		hql.andBy("type", Condition.eq,sType);
		}
		if(sName!=null && sName.length()>0)
		{
			hql.andBy("name", Condition.lk,sName);
		}
		if(userId!=null && userId>0)
		{
			hql.andBy("userId", Condition.eq,userId);
		}
		hql.orderBy("updateTime", true);
		List<VideoPicPO> VideoPics = findByHqlPage(hql, pageNum, pageSize);
		return VideoPics;
	}
	
	//对list分页
	public List<VPCommentPO> findCommentsPage(VideoPicPO videoPic,int pageNum,int pageSize){
		int firstResult = (pageNum - 1) * pageSize;
		@SuppressWarnings("unchecked")
		List<VPCommentPO> vpComments = getCurrentSession().createFilter(videoPic.getVPComments(),"order by updateTime desc")
		.setFirstResult(firstResult).setMaxResults(pageSize).list();
		return vpComments;
	}
	
	//统计总数
	public int  GetCountByPage() 
	{
		CountHql cHql=this.newCountHql();
		int allcount=this.countByHql(cHql);
		return allcount;
	}
	
}
