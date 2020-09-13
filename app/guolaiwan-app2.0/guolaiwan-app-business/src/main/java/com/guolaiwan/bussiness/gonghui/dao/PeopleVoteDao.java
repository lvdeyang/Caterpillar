package com.guolaiwan.bussiness.gonghui.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.gonghui.dto.PeopleVoteDto;
import com.guolaiwan.bussiness.gonghui.po.ArticlePo;
import com.guolaiwan.bussiness.gonghui.po.PeopleVotePo;
import com.guolaiwan.bussiness.merchant.dto.ReportDTO;

import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;
import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;
@Repository("PeopleVoteDao")
public class PeopleVoteDao extends AbstractBaseDao<PeopleVotePo> {

	public List<PeopleVoteDto> getPeoples() {
		StringBufferWrapper sqlWrapper = new StringBufferWrapper().append(
				"SELECT count,a.* from peoplevote a LEFT JOIN  (SELECT count(*) count, peoplevoteId from peoplevoteuser GROUP BY peoplevoteId) b on a.id=b.peoplevoteId  ORDER BY count desc");
		
		SQLQuery query = getCurrentSession().createSQLQuery(sqlWrapper.toString())
				.addScalar("count", StandardBasicTypes.INTEGER)
				.addScalar("article", StandardBasicTypes.STRING)
				.addScalar("org", StandardBasicTypes.STRING)
				.addScalar("articleFrom", StandardBasicTypes.STRING)
				.addScalar("voice", StandardBasicTypes.STRING)
				.addScalar("name", StandardBasicTypes.STRING)
				.addScalar("id",StandardBasicTypes.LONG)
				.addScalar("headerimg", StandardBasicTypes.STRING);
		     
		query.setResultTransformer(Transformers.aliasToBean(PeopleVoteDto.class));
		return query.list();
	}
	
	
	

}
