package com.guolaiwan.bussiness.questionnaire.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.questionnaire.po.QuestionBankPO;
import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.DeleteHql;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("com.guolaiwan.bussiness.questionnaire.dao.QuestionBankDAO")
public class QuestionBankDAO extends AbstractBaseDao<QuestionBankPO> {
	
	public int countByQId(Long questionnaireId) {
		CountHql cHql = this.newCountHql();
		cHql.andBy("questionnaireId", Condition.eq, questionnaireId);
		int count = this.countByHql(cHql);
		return count;
	}
	
	
	public List<QuestionBankPO> findByQId(Long questionnaireId, int pageNum, int pageSize) {
		QueryHql hql = this.newQueryHql();
		hql.andBy("questionnaireId", Condition.eq, questionnaireId);
		List<QuestionBankPO> VoteProducts = this.findByHqlPage(hql, pageNum, pageSize);
		return VoteProducts;
	}
	
	public List<QuestionBankPO> findByQId(Long questionnaireId) {
		QueryHql hql = this.newQueryHql();
		hql.andBy("questionnaireId", Condition.eq, questionnaireId);
		List<QuestionBankPO> VoteProducts = this.findByHql(hql);
		return VoteProducts;
	}
	
	public void deleteByQuestionnaireId(Long questionnaireId){
		DeleteHql hql=this.newDeleteHql();
		hql.andBy("questionnaireId", Condition.eq, questionnaireId);
		this.deleteByHql(hql);
	}
	
}
