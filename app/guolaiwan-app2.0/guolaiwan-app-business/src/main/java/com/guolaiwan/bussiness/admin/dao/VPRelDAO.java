package com.guolaiwan.bussiness.admin.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.po.VideoPicPO;
import com.guolaiwan.bussiness.admin.po.VPRelPO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.DeleteHql;
import pub.caterpillar.orm.hql.QueryHql;


@Repository("com.guolaiwan.bussiness.admin.dao.VPRelDAO")
public class VPRelDAO extends AbstractBaseDao<VPRelPO> {
	//获取点赞数
	public int countPraise(VideoPicPO videoPic){
		CountHql cHql = newCountHql();
		cHql.andBy("videoPic", Condition.eq, videoPic);
		cHql.andBy("praise", Condition.eq, 1);
		int count = countByHql(cHql);
		return count;
	}
	
	//获取登录用户的点赞的记录
	public VPRelPO getPraiseByVU(VideoPicPO videoPic,Long UserId){
		QueryHql hql = newQueryHql();
		hql.andBy("videoPic", Condition.eq, videoPic);
		hql.andBy("userId", Condition.eq, UserId);
		hql.andBy("praise", Condition.eq, 1);
		List<VPRelPO> vPRels = findByHql(hql);
		if(vPRels==null||vPRels.size()==0) return null;
		return vPRels.get(0);
	}
	
	//判断登录用户的点赞的记录
	public VPRelPO getPraiseByVpU(VideoPicPO videoPic,Long UserId){
		QueryHql hql = newQueryHql();
		hql.andBy("videoPic", Condition.eq, videoPic);
		hql.andBy("userId", Condition.eq, UserId);
		List<VPRelPO> vPRels = findByHql(hql);
		if(vPRels==null||vPRels.size()==0) return null;
		return vPRels.get(0);
	}
    //删除信息
	public int deleteByFile(Long id)
	{
		String str_sql="delete from VPRelPO where videoPic_id="+id;
		
		Query query = getCurrentSession().createQuery(str_sql);
		//this.deleteByField(propertyName, value)
        return  query.executeUpdate();
	}
	
}
