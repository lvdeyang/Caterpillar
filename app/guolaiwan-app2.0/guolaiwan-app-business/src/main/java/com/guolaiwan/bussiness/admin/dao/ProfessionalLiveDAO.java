package com.guolaiwan.bussiness.admin.dao;



import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.enumeration.LiveStatusType;
import com.guolaiwan.bussiness.admin.po.ProfessionalLivePO;
import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;



@Repository("com.guolaiwan.bussiness.admin.dao.ProfessionalLiveDAO")
public class ProfessionalLiveDAO extends AbstractBaseDao<ProfessionalLivePO> {
	
	/**
	 * 获取专业直播列表
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public List<ProfessionalLivePO> getProfessionalLiveList(int pageNum,int pageSize) throws Exception {
		QueryHql hql = newQueryHql();
		hql.andBy("liveStatusType", Condition.eq, "LIVING");
		List<ProfessionalLivePO> professionalLives = findByHqlPage(hql, pageNum, pageSize);
		if(professionalLives == null|| professionalLives.size() == 0) return null;
		return professionalLives;
	}
	
	
	/**
	 * 通过liveId获取直播数据
	 * @param liveId
	 * @return
	 */
	public ProfessionalLivePO findProfessionalLivePOByLiveId(long liveId){
		QueryHql hql = newQueryHql();
		hql.andBy("liveId", Condition.eq, liveId);
		List<ProfessionalLivePO> professionalLives = findByHql(hql);
		if(professionalLives == null|| professionalLives.size() == 0) return null;
		return professionalLives.get(0);
	}
}
