package com.guolaiwan.bussiness.admin.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.enumeration.VideoPicType;
import com.guolaiwan.bussiness.admin.po.VPCommentPO;
import com.guolaiwan.bussiness.admin.po.VideoPicPO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.QueryHql;


@Repository("com.guolaiwan.bussiness.admin.dao.VPCommentDAO")
public class VPCommentDAO extends AbstractBaseDao<VPCommentPO> {
	
	   //删除信息
		public int deleteByFile(Long id)
		{
			String str_sql="delete from VPCommentPO where videoPic_id="+id;
			
			Query query = getCurrentSession().createQuery(str_sql);
			//this.deleteByField(propertyName, value)
	        return  query.executeUpdate();
		}
		
		
		public List<VPCommentPO> findAllDesc(int pageNum,int pageSize,String sName){
			QueryHql hql = newQueryHql();
			
			if(sName!=null && sName.length()>0)
			{
				hql.andBy("commentText", Condition.lk,sName);
			}
			
			hql.orderBy("updateTime", true);
			List<VPCommentPO> VideoPics = findByHqlPage(hql, pageNum, pageSize);
			return VideoPics;
		}
		
		
	
		//统计总数
		public int  GetCountByPage() 
		{
			CountHql cHql=this.newCountHql();
			int allcount=this.countByHql(cHql);
			return allcount;
		}
		
}
