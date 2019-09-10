package com.guolaiwan.bussiness.questionnaire.dao;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.questionnaire.po.RedPacketRecordPO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;

@Repository("com.guolaiwan.bussiness.questionnaire.dao.RedPacketRecordDAO")
public class RedPacketRecordDAO extends AbstractBaseDao<RedPacketRecordPO> {
	
	public int countByUId(Long userId,Long questionnaireId) {
		CountHql cHql = this.newCountHql();
		cHql.andBy("questionnaireId", Condition.eq, questionnaireId);
		cHql.andBy("userId", Condition.eq, userId);
		int count = this.countByHql(cHql);
		return count;
	}
	
}
