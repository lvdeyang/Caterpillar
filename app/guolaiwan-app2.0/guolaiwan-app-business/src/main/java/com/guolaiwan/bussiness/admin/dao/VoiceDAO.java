package com.guolaiwan.bussiness.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.po.VoicePO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.DeleteHql;
import pub.caterpillar.orm.hql.QueryHql;


@Repository("com.guolaiwan.bussiness.admin.dao.VoiceDAO")
public class VoiceDAO extends AbstractBaseDao<VoicePO> {
	public List<VoicePO> getVoiceByPage(int pageNum,int pageSize){
		QueryHql hql = this.newQueryHql();
		hql.orderBy("updateTime", true);
		List<VoicePO> Voices= findByHqlPage(hql,pageNum,pageSize);
		if(Voices==null || Voices.size()<=0) return null;
		return Voices;
	}

	//统计总数
	public int  getCountByPage() {
		CountHql cHql=this.newCountHql();
		int allcount=this.countByHql(cHql);
		return allcount;
	}

	//
	public void deleteByUuid(String uuid){
		DeleteHql hql = this.newDeleteHql();
		hql.andBy("uuid",Condition.eq, uuid);
		deleteByHql(hql);
	}

}
