package com.guolaiwan.bussiness.admin.dao;



import java.util.List;

import org.springframework.stereotype.Repository;
import com.guolaiwan.bussiness.admin.po.ProfessionalLiveMatPlayVedioPO;
import com.guolaiwan.bussiness.admin.po.ProfessionalLivePO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;




@Repository("com.guolaiwan.bussiness.admin.dao.ProfessionalLiveMatPlayVedioDAO")
public class ProfessionalLiveMatPlayVedioDAO extends AbstractBaseDao<ProfessionalLiveMatPlayVedioPO> {
	
	/**
	 * 通过liveId获取对应的垫播视频数据
	 * @param liveId
	 * @return
	 */
	public ProfessionalLiveMatPlayVedioPO findByLiveId(long liveId){
		QueryHql hql = newQueryHql();
		hql.andBy("liveId", Condition.eq, liveId);
		List<ProfessionalLiveMatPlayVedioPO> resultList = findByHql(hql);
		if(resultList == null|| resultList.size() == 0) return null;
		return resultList.get(0);
	}
}
